import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {

    private static int Count = 0;
    private static Task[] List = new Task[10]; //creating list of Task for inputs
    private static Boolean isBye = false;


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        String basePath=System.getProperty("user.dir");
        String path=basePath.concat("\\tasks.txt");

        try {
            printFileContents(path);
        } catch (FileNotFoundException e) {
            System.out.println("tasks.txt file not found");
        }

        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);

        while (!isBye) {
            String line = in.nextLine();

            if (line.equals("bye")) {
                isBye = true;
                break;
            }//end programme

            //print out list
            else if (line.equals("list")){
                callList();
            }

            //find task
            else if (line.contains("find")){
                findTask(line);
            }

            // mark description in list as done
            else if (line.contains("done")){
                callDone(line);
            }
            // delete task
            else if (line.contains("delete")){
                List = deleteTask(List,line);
            }
            //todo_
            else if (line.contains("todo")){
                callToDo(line);
            }
            //deadline action /by datetime
            else if (line.contains("deadline")){
                callDeadline(line);
             }
            //event action /at datetime
            else if (line.contains("event")){
                callEvent(line);
            }
            //error
            else {
                System.out.println("OOPS!! I'm sorry, but I don't know what that means :-(");
                //continue;
            }
        }



        try {
            deleteFileContent(path);
        }catch(IOException e){
        }

        for (int i=0;i<Count;i++) {
            try {
                writeToFile(path, List[i].statusString());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    //add task object into list method
    public static void addList(Task s) {
        List[Count] = s;
    }

    // print out list method
    public static void callList() {
        for (int i = 0; i < Count; i++) {
            System.out.print((i + 1) + ".");
            List[i].printStatus();
        }
    }

    // find task in list
    public static void findTask(String line){
        if (line.trim().equals("find")) {
            System.out.println("OOPS!! The description of a find cannot be empty.");
            //continue;
        }else{
            int divider = line.indexOf(" ");
            String key = line.substring(divider + 1);
            System.out.println("Here are the matching tasks in your list:");

            int point = 1;

            for (int i = 0; i < Count ; i++){
                String status = List[i].statusString();
                if (status.contains(key)){
                    System.out.println(point + "." + List[i].statusString());
                    point++;
                }
                //continue;
            }
            System.out.println("End of search");
        }
    }

    //set task done method
    public static void callDone(String line) {
        int divider = line.indexOf(" ");
        String index = line.substring(divider + 1);
        int x = Integer.parseInt(index);

        List[x - 1].setDone();
    }

    //todo method
    public static void callToDo(String line) {
        if (line.trim().equals("todo")) {
            System.out.println("OOPS!! The description of a todo cannot be empty.");
            //continue;
        } else {
            int divider = line.indexOf(" ");
            String index = line.substring(divider + 1);

            addList(new Todo(index));
            List[Count].printAction();
            Count++;
            //continue;
        }
    }

    //deadline method -- deadline action /by datetime
    public static void callDeadline(String line) {
        if (line.trim().equals("deadline")) {
            System.out.println("OOPS!! The description of a deadline cannot be empty.");
            //continue;
        } else {
            int divider = line.indexOf(" ");
            String action = line.substring(divider + 1);

            int divider2 = action.indexOf("by");
            String description = action.substring(0, divider2-1);

            String deadline = action.substring(divider2);
            int divider3 = deadline.indexOf(" ");
            String datetime = deadline.substring(divider3 + 1);

            addList(new deadline(description, datetime));
            List[Count].printAction();
            Count++;
            //continue;
        }
    }

    //event method -- event action /at datetime
    public static void callEvent(String line) {
        if (line.trim().equals("event")) {
            System.out.println("OOPS!! The description of a event cannot be empty.");
            //continue;
        } else {
            int divider = line.indexOf(" ");
            String action = line.substring(divider + 1);

            int divider2 = action.indexOf("at");
            String description = action.substring(0, divider2-1);

            String deadline = action.substring(divider2);
            int divider3 = deadline.indexOf(" ");
            String datetime = deadline.substring(divider3 + 1);

            addList(new event(description, datetime));
            List[Count].printAction();
            Count++;
            //continue;
        }
    }

    //delete task method
    public static Task[] deleteTask(Task[] List, String line) {
        int divider = line.indexOf(" ");
        String s = line.substring(divider + 1);
        int index = Integer.parseInt(s) - 1;

        System.out.println("Noted. I've removed this task: ");
        List[index].printStatus();
        List[index].reduceTaskCount();

        // If the array is empty or the index is not in array range, return the original array
        if (List == null || index < 0 || index >= List.length) {
            return List;
        }

        // Create another array of size one less
        Task[] anotherList = new Task[List.length - 1];

        // Copy the elements except the index from original array to the other array
        for (int i = 0, j = 0; i < List.length; i++) {
            // if the index is found, remove element index
            if (i == index) {
                continue;
            }
            // if the index is not found
            anotherList[j++] = List[i];
        }
        Count--; //reduce main array list count by 1

        System.out.println("Now you have " + Count + " tasks in the list");
        // return the resultant array
        return anotherList;
    }

    //print file contents
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String content = s.nextLine();
            if (content.contains("[T]")) {
                callToDo(content);
            } else if (content.contains("[D]")) {
                callDeadline(content);
            } else if (content.contains("[E]")) {
                callEvent(content);
            }
        }
    }


    //write file method
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    //delete content in file
    private static void deleteFileContent(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }


}