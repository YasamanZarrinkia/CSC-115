public class TaskListLL implements TaskList {
    public TaskListLL() {

    }


    public int getLength() {
        System.out.println("ECHO getLength()");
        return -1;
    }


    public boolean isEmpty() {
        System.out.println("ECHO isEmpty()");
        return false;
    }


    public Task removeHead() {
        Task result;

        System.out.println("ECHO removeHead()");
        return null;
    }

    
    public Task remove(int number) {
        System.out.println("ECHO remove(task with number " + number
            + ")" );
        return null;
    }


    public boolean insert(int priority, int number) {
        System.out.println("ECHO insert(Task(" + priority + " " +
            number + "))");
        return false;
    }


    public Task retrieve(int pos) {
        System.out.println("ECHO retrieve(" + pos + ")");
        return null;
    }
}
