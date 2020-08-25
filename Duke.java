import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static class Task{
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        }

        public String markAsDone(){
            this.isDone = true;
            int divider = description.indexOf("]");
            this.description = description.substring(divider+2);

            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("\t [\u2713]" + description);
            String Icon = "[" + getStatusIcon() + "] " + description;
            return Icon;

        }
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
        Boolean Bye = false;
        int Count = 0;
        String[] List = new String[10];

        while (!Bye){
            String line = in.nextLine();
            if (line.equals("bye")){
                Bye = true;
                break;
            }

            if (line.equals("list")){
                for (int i = 0; i<Count; i++){
                    System.out.println((i+1) + "." + List[i]);
                }
                continue;
            }

            if (line.contains("done")){
                int divider = line.indexOf(" ");
                String index = line.substring(divider+1);
                int x = Integer.parseInt(index);

                Task t = new Task(List[x-1]);
                String Icon = t.markAsDone();
                List[x-1] = Icon;
                continue;
            }

            List[Count] = "[\u2718] "+ line;
            Count += 1;


            System.out.println("added: " +line);

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
//test3