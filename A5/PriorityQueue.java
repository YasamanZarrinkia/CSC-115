import java.util.NoSuchElementException;

//This class is for making priority for each category
public class PriorityQueue<E extends Comparable<E>> {
	
	private Heap<E> heap;

	public PriorityQueue() {
		heap=new Heap<E>();
	}
	//removing root
    public E dequeue() {
		return heap.removeRootItem();
    }
	//insering patient
 	public void enqueue(E item) {
        heap.insert(item);
    }

    public boolean isEmpty() {
		return heap.isEmpty();	
    }
    //returng root value
    public E peek() {
		return heap.getRootItem();
    }

    public static void main(String args []) {
		
		PriorityQueue <ER_Patient> p1 = new PriorityQueue <ER_Patient> ();

		//System.out.println(p1.isEmpty());
		p1.enqueue(new ER_Patient("Walk-in"));
		p1.enqueue(new ER_Patient("10:00:00",30,"Chronic"));
		p1.enqueue(new ER_Patient("Major fracture"));
		p1.enqueue(new ER_Patient("Life-threatening"));
		//p1.enqueue(new ER_Patient("Life-threatening"));
		p1.enqueue(new ER_Patient("11:00:00",40,"Chronic"));
		//System.out.println(p1.peek());
		//p1.dequeue();
		//System.out.println(p1.peek());
		//System.out.println(p1.isEmpty());
		for(int i=0;i<5;i++){
			System.out.println(p1.dequeue());
		}
		
    }
}
	
