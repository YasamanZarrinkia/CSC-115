import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * The shell of the class, to be completed as part
 * of CSC115 Assignment 5: Emergency Room.
 */


/**
 * Complete this class as per the Heap.html specification document.
 * Fill in any of the parts that have the comment:
 * ********  COMPLETE ******
 * Do not change method headers or code that has been supplied.
 * Delete all messages to you, including this one, before submitting.
 */

public class Heap<E extends Comparable<E>> {

	private Vector<E> heapArray;;

	public Heap() {
		/********  COMPLETE *******/
	}

	public boolean isEmpty(){
		/********  COMPLETE *******/
	}

	public int size(){
		/********  COMPLETE *******/
	}
	
	public void insert(E item){
		/********  COMPLETE *******/
	}
	
	public E removeRootItem(){	
		/********  COMPLETE *******/
	}
	
	public E getRootItem(){
		/********  COMPLETE *******/
	}
	
	/******** Tool methods ********/
	private int indexOf(E p){
		for (int i = 1; i < heapArray.capacity(); i++) {
			if (heapArray.elementAt(i).equals(p))   {
				return i;
			}
		}
		return -1;
	}
	/******** Tool methods ********/
	private int getParenetindex(int child){
		return child/2;
	}

	/********  DEBUG USE methods ********/
	public void print_vector() {
		System.out.println(" *************** Array is ***************");
		for (int i = 0; i < heapArray.size(); i++){
			System.out.println(heapArray.elementAt(i));
		}
	}

	public static void main(String args []){
		/*
		Heap <ER_Patient> hp = new Heap <ER_Patient>();
		hp.insert(new ER_Patient ("Chronic"));		
		*/
		/* you can add more tests */
	}
}
