import java.util.Scanner;
import java.util.Arrays;

public class Duke {
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
                    System.out.println((i+1) + "." + " " + List[i]);
                }
                continue;
            }

            List[Count] = line;
            Count += 1;


            System.out.println("added: " +line);

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
//test3