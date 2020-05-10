/*
 * The shell of the class, to be completed as part of the CSC115 Assignment 3 : Calculator.
 */

/*
 * NOTE TO STUDENT:
 * Fill in any of the parts that have the comment:
	*******COMPLETE*******
 * Do not change method headers or code that has been supplied.
 * All methods must be properly commented.
 * Please delete all messages to you, including this one, before submitting.
 */
public class StringStack {

	public Node head;
	//constructor
	public StringStack(){
		head=null;
	}
	//checking if stack is empty
	public boolean isEmpty() {
		if(head==null)
			return true;
		else
			return false;
	}
	//removing stack head
	//throws empty exception
	public String pop() throws StackEmptyException{
		System.out.println("Popping a number");
		//System.out.println("Head.next: "+ head.next.data);
		if(head==null)
			throw new StackEmptyException("Stack is empty");
		Node temp=head;
		head=head.next;
		return temp.data;
	}
	//returning the value of stack's head
	public String peek() throws StackEmptyException{
		if (head==null)
			throw new StackEmptyException("Stack is empty");
		return head.data;
	
	}
	//adding value to stack
	public void push(String item) {
	head=new Node(item,head);
	}
	//deleting all the stack
	public void popAll() {
		head=null;
	}
}
