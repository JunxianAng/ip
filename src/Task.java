public class Task {

    protected String description;
    public static int TaskCount = 0 ;
    protected String isDone;


    public Task(String description) {
        this.description = description;
        isDone = "[\u2718]";
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

    //set task to done
    public void setDone(){
        this.isDone = "[\u2713]";
    }

    //print status of task
    public void printStatus(){
            String status =isDone + " " + description;
            System.out.print(status);
    }

    //delete task count by 1
    public void reduceTaskCount(){
        TaskCount-=1;
    }

    public String statusString(){
        return "successful";
    }

}
