import java.util.Iterator;

/*
 * NOTE TO STUDENT:
 * Comment and implement all incomplete methods.
 * Any methods that have comments are already complete and
 * you must not change them.
 * You may add methods that you find helpful, remembering
 * that no public method allows acces to a TreeNode directly.
 * Remove this comment an provide your own header.
 */

/**
 * BinarySearchTree is an ordered binary tree, where the element in each node
 * comes after all the elements in the left subtree rooted at that node
 * and before all the elements in the right subtree rooted at that node.
 * For this assignment, we can assume that there are no elements that are
 * identical in this tree. 
 * A small modification will allow duplicates.
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

	// the root is inherited from BinaryTree.

	/**
	 * Create an empty BinarySearchTree.
	 */
	public BinarySearchTree() {
		super();
	}

	/**
	 * Creates a BinarySearchTree with a single item.
	 * @param item The single item in the tree.
	 */
	public BinarySearchTree(E item) {
		super(item);
	}

	/**
 	 * <b>This method is not allowed in a BinarySearchTree.</b>
	 * It's description from the subclass:<br>
	 * <br>
	 * {@inheritDoc}
	 * @throws UnsupportedOperationException if this method is invoked.
	 */
	public void attachLeftSubtree(BinaryTree<E> left) {
		throw new UnsupportedOperationException();
	}

	public void attachRightSubtree(BinaryTree<E> right) {
	}

	public void insert(E item) {
	}
	
	public E retrieve(E item) {
		return null;
	}

	public E delete(E item) {
		return null;
	}

	/**
	 * Internal test harness.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		// NOTE TO STUDENT: something to get you started.
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		String s1 = new String("optimal");
		String s2 = new String("needs");
		String s3 = new String("programmers");
		String s4 = new String("CSC115");
		tree.insert(s1);
		tree.insert(s2);
		tree.insert(s3);
		tree.insert(s4);
		String test = tree.retrieve("needs");
		if (test != null && !test.equals("")) {
			System.out.println("retrieving the node that contains "+s2);
			if (test.equals(s2)) {
				System.out.println("Confirmed");
			} else {
				System.out.println("retrieve returns the wrong item");
			}
		} else {
			System.out.println("retrieve returns nothing when it should not");
		}
		Iterator<String> it = tree.inorderIterator();
		System.out.println("printing out the contents of the tree in sorted order:");
		while (it.hasNext()) {
			System.out.print(it.next()+" ");
		} 
		System.out.println();
		DrawableBTree<String> dbt = new DrawableBTree<String>(tree);
		dbt.showFrame();
	}
}

	

	
