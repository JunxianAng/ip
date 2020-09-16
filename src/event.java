public class event extends Task{

    protected String byDate;

    public event(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    public void printStatus(){
        System.out.print("[E]");
        super.printStatus();
        System.out.println(" -at: " + byDate);
    }

    public void printAction(){
        super.printAction();
        printStatus();
        System.out.println("Now you have "+ TaskCount + " tasks in the list.");
    }

    public String statusString(){
        return "[E]" + isDone + " " + description + " -at: " + byDate;
    }

}
