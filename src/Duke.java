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

            //end programme
            //list is only saved to txt file if user inputs bye
            if (line.equals("bye")) {
                isBye = true;
                break;
            }

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
                try{
                    callDone(line);
                }catch(NumberFormatException e){
                    System.out.println("Please input in proper format!");
                }
            }

            // delete task
            else if (line.contains("delete")){
                try{
                    List = deleteTask(List,line);
                }catch(NumberFormatException e){
                    System.out.println("Please input in proper format!");
                }
            }

            //todo_
            else if (line.contains("todo")){
                callToDo(line);
            }

            //deadline action /by datetime
            else if (line.contains("deadline")){
                try{
                    callDeadline(line);
                }catch(StringIndexOutOfBoundsException e){
                    System.out.println("Please input in deadline task in this format! e.g deadline action by date");
                }
             }

            //event action /at datetime
            else if (line.contains("event")){
                try{
                    callEvent(line);
                }catch(StringIndexOutOfBoundsException e){
                    System.out.println("Please input in event task in this format! e.g event action at date");
                }
            }

            //error
            else {
                System.out.println("OOPS!! I'm sorry, but I don't know what that means :-(");
                //continue;
            }
        }

        //delete existing file
        try {
            deleteFileContent(path);
        }catch(IOException e){
        }

        //Rewrite to new file based on console list
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

    //print out list of tasks method
    public static void callList() {
        if (Count < 1){
            System.out.println("Oops List is empty!");
        }else {
            for (int i = 0; i < Count; i++) {
                System.out.print((i + 1) + ".");
                List[i].printStatus();
            }
        }
    }

    //find task in list method
    public static void findTask(String line){
        if (line.trim().equals("find")) {
            System.out.println("OOPS!! The description of a find cannot be empty.");
            //continue;
        }else if (Count < 1){
            System.out.println("Oops List is empty!");
        } else{
            int divider = line.indexOf("find");
            String key = line.substring(divider + 4);
            key = key.trim();
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
        if (line.trim().equals("done")) {
            System.out.println("OOPS!! The description of done cannot be empty.");
            //continue;
        }else if (Count < 1){
            System.out.println("Oops List is empty!");
        }else {
            int divider = line.indexOf(" ");
            String index = line.substring(divider + 1);
            int x = Integer.parseInt(index);

            if (x < 1 || x > Count) {
                System.out.println("Oops! unable to find task "+ x +" to delete!");
                return;
            }

            List[x - 1].setDone();
            System.out.println("Nice! I've marked this task as done:");
            List[x-1].printStatus();
        }
    }

    //todo method
    public static void callToDo(String line) {
        if (line.trim().equals("todo")) {
            System.out.println("OOPS!! The description of a todo cannot be empty.");
            //continue;
        } else {
            int divider = line.indexOf("todo");
            String index = line.substring(divider + 4);
            index = index.trim();

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
            int divider = line.indexOf("deadline");
            String action = line.substring(divider + 8);
            action = action.trim();

            int divider2 = action.indexOf("by");
            String description = action.substring(0, divider2);
            description = description.trim();

            String deadline = action.substring(divider2);
            int divider3 = deadline.indexOf("by");
            String datetime = deadline.substring(divider3 + 2);
            datetime = datetime.trim();

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
            int divider = line.indexOf("event");
            String action = line.substring(divider + 5);
            action = action.trim();

            int divider2 = action.indexOf("at");
            String description = action.substring(0, divider2);
            description = description.trim();

            String deadline = action.substring(divider2);
            int divider3 = deadline.indexOf("at");
            String datetime = deadline.substring(divider3 + 2);
            datetime = datetime.trim();

            addList(new event(description, datetime));
            List[Count].printAction();
            Count++;
            //continue;
        }
    }

    //delete task method
    public static Task[] deleteTask(Task[] List, String line) {
        if (line.trim().equals("delete")) {
            System.out.println("OOPS!! The description of a delete cannot be empty.");
            return List;
            //continue;
        }else if(Count < 1){ // If the array is empty
            System.out.println("Oops List is empty!");
            return List;
        }else {
            int divider = line.indexOf(" ");
            String s = line.substring(divider + 1);
            int index = Integer.parseInt(s) - 1;

            // If index is not in array range, return the original array
            if (index < 0 || index > Count - 1) {
                System.out.println("Oops! unable to find task "+ (index + 1) +" to delete!");
                return List;
            }

            System.out.println("Noted. I've removed this task: ");
            List[index].printStatus();
            List[index].reduceTaskCount();

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