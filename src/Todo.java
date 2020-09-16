import java.awt.*;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public void printStatus(){
        System.out.print("[T]");
        super.printStatus();
        System.out.println();
    }

    public void printAction(){
        super.printAction();
        printStatus();
        System.out.println("Now you have "+ TaskCount + " tasks in the list.");
    }

    public String statusString(){
        return "[T]" + isDone + " " + description;
    }

}
