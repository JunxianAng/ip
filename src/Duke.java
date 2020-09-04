import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    private static int Count = 0;
    private static Task[] List = new Task[10]; //creating list of Task for inputs

    //add task object into list
    public static void addList(Task s){
        List[Count] = s;
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        Boolean isBye = false;



        while (!isBye){
            String line = in.nextLine();

            if (line.equals("bye")){
                isBye = true;
                break;
            }//end programme

            //print out list
            if (line.equals("list")){
                for (int i = 0; i< Count; i++){
                    System.out.print((i+1) + ".");
                    List[i].printStatus();
                }
                continue;
            }

            // mark description in list as done
            if (line.contains("done")){
                int divider = line.indexOf(" ");
                String index = line.substring(divider+1);
                int x = Integer.parseInt(index);

                List[x-1].setDone();

            }

            if (line.contains("todo")){
                int divider = line.indexOf(" ");
                String index = line.substring(divider+1);

                addList(new Todo(index));
                List[Count].printAction();
                Count++;
                continue;
            }

            if (line.contains("deadline")){
                int divider = line.indexOf(" ");
                String index = line.substring(divider + 1);

                int divider2 = index.indexOf("/");
                String index2 = index.substring(0,divider2 - 1);
                String deadline =  index.substring(divider2 + 4);

                addList(new deadline(index2,deadline));
                List[Count].printAction();
                Count++;
                continue;
             }

            if (line.contains("event")){
                int divider = line.indexOf(" ");
                String index = line.substring(divider + 1);

                int divider2 = index.indexOf("/");
                String index2 = index.substring(0,divider2 - 1);
                String deadline =  index.substring(divider2 + 4);

                addList(new event(index2,deadline));
                List[Count].printAction();
                Count++;
                continue;
            }

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}