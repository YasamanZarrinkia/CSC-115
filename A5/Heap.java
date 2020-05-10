
import java.util.NoSuchElementException;
import java.util.Vector;

/*
This class is to make a heap for patients
*/

public class Heap<E extends Comparable<E>> {

	private Vector<E> heapArray;
	public int count;

	public Heap() {
		heapArray=new Vector<E> ();
		heapArray.add(0,null);
		count=0;
	}
	//to see if there is any patient in the heap or not
	public boolean isEmpty(){
		if(count==0){
			return true;
		}else{
			return false;
		}
	}
	//number of patiens
	public int size(){
		return count;
	}
	//inserting new patient
	public void insert(E item){
		E temp;
		count++;
		heapArray.add(item);
		int index = count;
		while(index > 1){
			if(heapArray.get(index).compareTo(heapArray.get(index/2))>0){
				temp = heapArray.get(index/2);
				heapArray.set(index/2, heapArray.get(index));
				heapArray.set(index,temp);
				index = index/2;
			}
			else{
				break;
			}
		}
	}
	//removing root
	public E removeRootItem() throws NoSuchElementException{	
		if(isEmpty()){
			throw new NoSuchElementException("The heap is empty");
		}
	    E temp = heapArray.get(1);
		int parent = 1;
	    heapArray.set(1,heapArray.get(count));
		count--;
		int right;
		int big;
		int left = 2;
		while(parent * 2 <= count){
			big = parent*2;
			if(parent*2+1 <= count && heapArray.get(parent*2+1).compareTo(heapArray.get(parent*2))>0){
				big = parent*2+1;
			}
			if(heapArray.get(parent).compareTo(heapArray.get(big))>0){
				break;
			}
			else{
				E swap = heapArray.get(parent);
				heapArray.set(parent,heapArray.get(big));
				heapArray.set(big, swap);
			}
			parent = big;
		}
		return temp;
	}
	//getting root value
	public E getRootItem() throws NoSuchElementException{
		if(isEmpty()){
			throw new NoSuchElementException("The heap is empty") ;
		}
		return heapArray.get(1);
	}
	
	
	private int indexOf(E p){
		for (int i = 1; i < heapArray.capacity(); i++) {
			if (heapArray.elementAt(i).equals(p))   {
				return i;
			}
		}
		return -1;
	}
	
	private int getParenetindex(int child){
		return child/2;
	}

	//debuging tool
	public void print_vector() {
		System.out.println(" *************** Array is ***************");
		for (int i = 1; i < heapArray.size(); i++){
			System.out.println(heapArray.elementAt(i));
		}
	}

	public static void main(String args []){
		
		Heap <Integer> hp = new Heap <Integer>();
		hp.insert(2);		
		hp.insert(6);
		hp.insert(4);
		hp.insert(9);
		hp.insert(3);
		hp.insert(6);

		hp.print_vector();
		hp.getRootItem();
		hp.removeRootItem();
		hp.print_vector();


		
	}
}
