public class deadline extends Task{

    protected String byDate;

    public deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    //print status of deadline task
    public void printStatus(){
        System.out.print("[D]");
        super.printStatus();
        System.out.println(" -by: " + byDate);
    }

    public void printAction(){
        super.printAction();
        printStatus();
        System.out.println("Now you have "+ TaskCount + " tasks in the list.");
    }

    //return status of deadline task as string
    public String statusString(){
        return "[D]" + isDone + " " + description + " -by: " + byDate;
    }
}
