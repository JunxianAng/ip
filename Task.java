public class Task {

    protected String description;
    protected static int TaskCount = 0 ;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
        TaskCount+=1;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void printAction(){
        System.out.println("Got it. I've added this task: ");
    }

    public void setDone(){
        this.isDone = true;
    }

    public void printStatus(){
        if (isDone){
            String status ="[\u2713] " + description;
            System.out.print(status);
        }
        else{
            String status = "[\u2718] " + description;
            System.out.print(status);
        }
    }

}
