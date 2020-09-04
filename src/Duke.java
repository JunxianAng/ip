import java.util.Scanner;

public class Duke {

    private static int Count = 0;
    private static Task[] List = new Task[10]; //creating list of Task for inputs
    private static Boolean isBye = false;

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


        while (!isBye){
            String line = in.nextLine();

            if (line.equals("bye")){
                isBye = true;
                break;
            }//end programme

            //print out list
            else if (line.equals("list")){
                for (int i = 0; i< Count; i++){
                    System.out.print((i+1) + ".");
                    List[i].printStatus();
                }
                //continue;
            }

            // mark description in list as done
            else if (line.contains("done")){
                int divider = line.indexOf(" ");
                String index = line.substring(divider+1);
                int x = Integer.parseInt(index);

                List[x-1].setDone();

            }

            else if (line.contains("todo")){
                if (line.trim().equals("todo")){
                    System.out.println("OOPS!! The description of a todo cannot be empty.");
                    //continue;
                }
                else {
                    int divider = line.indexOf(" ");
                    String index = line.substring(divider + 1);

                    addList(new Todo(index));
                    List[Count].printAction();
                    Count++;
                    //continue;
                }
            }

            else if (line.contains("deadline")){
                int divider = line.indexOf(" ");
                String action = line.substring(divider + 1);

                int divider2 = action.indexOf("/");
                String action2 = action.substring(0,divider2 - 1);
                String deadline =  action.substring(divider2 + 4);

                addList(new deadline(action2,deadline));
                List[Count].printAction();
                Count++;
                //continue;
             }

            else if (line.contains("event")){
                int divider = line.indexOf(" ");
                String action = line.substring(divider + 1);

                int divider2 = action.indexOf("/");
                String action2 = action.substring(0,divider2 - 1);
                String deadline =  action.substring(divider2 + 4);

                addList(new event(action2,deadline));
                List[Count].printAction();
                Count++;
                //continue;
            }

            else {
                System.out.println("OOPS!! I'm sorry, but I don't know what that means :-(");
                //continue;
            }

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}