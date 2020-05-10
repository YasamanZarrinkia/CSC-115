public class TaskListLL implements TaskList {

    private TaskListNode head;
    private int count;
 

    public TaskListLL() {

    head=null;
    count=0;

    }


    public int getLength() {
        return count;
    }


    public boolean isEmpty() {
        
        if(count==0)
            return true;
        else
            return false;
    }


    public Task removeHead() {
        TaskListNode temp=head;
        if (count==1){
            head=null;
             count--;
            return temp.task;   
        }
        else if (count > 1){
            head=head.next;
            count--;
            return temp.task;
        }


        return null;
    }

    
    public Task remove(int number) {
        
        if(isEmpty())
            return null;

        TaskListNode temp=head.next;
        TaskListNode prev=head;
        if(count==1){
           if( head.task.getNumber()==number ){
               head=null;
               count--;
               return prev.task;
            }
            return null;
        }

       if (head.task.getNumber()==number){
           head=temp;
           count--;
           return head.task;
        }

        while (temp != null){
            if(temp.task.getNumber()==number){
                prev.next=temp.next;
                count--;
                return temp.task;
                } 
            prev = temp;
            temp = temp.next;

        }

       return null;
   }
            
           
  

    public boolean insert(int priority, int number) {
        Task newTask=new Task(priority,number);
        TaskListNode newNode=new TaskListNode(newTask);
 
        if(isEmpty()){
            head = newNode;
            count++;
            return true;
        }

        if(count==1){
            if(head.task.getNumber()!=number){
                if(head.task.compareTo(newNode.task)==-1){
                     newNode.next = head;
                     head=newNode;
                     count++;
                 }
                 else if(head.task.compareTo(newNode.task)== 1 || head.task.compareTo(newNode.task) == 0){
                     head.next=newNode;
                     count++;
                }
                return true;              
            }
                return false;
        }

        TaskListNode temp=head.next;
        TaskListNode prev=head;

        if (count > 1){

        if (prev.task.getNumber()==number )
            return false;
        else if (prev.task.compareTo(newNode.task) == 1 && temp.task.compareTo(newNode.task) == -1){
            newNode.next=temp;
            head.next=newNode;
            count++;
            return true;
            }
        else if (prev.task.compareTo(newNode.task) == -1 ){
            newNode.next = prev;
            head = newNode;
            count++;
            return true;
            }
        else if (prev.task.compareTo(newNode.task) == 0 && temp.task.compareTo(newNode.task) != 0){
            newNode.next=prev.next;
                        prev.next=newNode;
                        count++;
                        return true;
        }

        while (temp.next != null){
            if (temp.task.getNumber()==number )
                return false;
           if (temp.task.compareTo(newNode.task) == 1 && temp.next.task.compareTo(newNode.task) == -1){
                        newNode.next = temp.next;
                        temp.next = newNode;
                        count++;
                        return true;
            }
           else if (temp.task.compareTo(newNode.task) == 0){
                    if(temp.task.compareTo(temp.next.task) != 0){
                        newNode.next=temp.next;
                        temp.next=newNode;
                        count++;
                        return true;
              }
           }

            temp = temp.next;
        }

        temp.next = newNode;
        count++;
        return true;

   }
   return false;
   }


    


    public Task retrieve(int pos) {
  
   
        if(pos >= 0 || pos < count){
            TaskListNode temp=head;
            for(int i=0;i<pos;i++){
               temp=temp.next; 
            }
      
            return temp.task; 
        }

        return null;  
    }


}



