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
	private  TreeNode<E> returnVal ;
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
		throw new UnsupportedOperationException();
	}

	public void insert(E item) {
		root=insert(root,item);
	}

	private TreeNode<E> insert(TreeNode<E> node,E item){
		if(node==null)
			return new TreeNode<E>(item);
		if(item.compareTo(node.item)<0)
			node.left=insert(node.left,item);
		else
			node.right=insert(node.right,item);
		return node;

	}

	
	public E retrieve(E item) {
		return retrive(root,item);
	}

	private E retrive(TreeNode<E> node,E item){
		if(node==null)
			return null;
		if(item.compareTo(node.item)<0)
			return retrive(node.left,item);
		else if(item.compareTo(node.item)>0)
			return retrive(node.right,item);
		return node.item;
	}

	public E delete(E item)  {

		root=delete(root,item);
		return item;
	}

	private TreeNode<E> delete(TreeNode<E> root,E item) {
		TreeNode<E> cur=root;
		if(root==null){
			//System.out.println("reached");
			return cur;
		}
	    if(item.compareTo(cur.item)<0)
	    	cur.left=delete(cur.left,item);
	    else if(item.compareTo(cur.item)>0)
	    	cur.right=delete(cur.right,item);
	    else{
	    	if(cur.right==null && cur.left==null)
	    		return null;
	    	else if(cur.right==null)
	    		return root.left;
	    	else if(cur.left==null)
	    		return cur.right;
	    	else{
	    		E leftMostValue=findLeftMost(cur.right);
	    		cur.item=leftMostValue;
	    		cur.right=delete(root.right,leftMostValue);
	        }
	    }
	    return root;
	}

	public E findLeftMost(TreeNode<E>  node){
		if(node.left==null)
			return node.item;
		else
			return findLeftMost(node.left);
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
		String s5 = new String("codes");
		String s6 = new String("csc");
		String s7 = new String("yasamanzarinkia");
		String s8 = new String("baba");
		String s9 = new String("problems");
		tree.insert(s1);
		tree.insert(s2);
		tree.insert(s3);
		tree.insert(s4);
		tree.insert(s5);
		tree.insert(s6);
		tree.insert(s7);
		tree.insert(s8);
		tree.insert(s9);
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
		String test2=tree.delete("needs");
		Iterator<String> it = tree.inorderIterator();
		System.out.println("printing out the contents of the tree in sorted order:");
		while (it.hasNext()) {
			System.out.print(it.next()+" ");
		} 
		
		System.out.println();
		DrawableBTree<String> dbt = new DrawableBTree<String>(tree);
		dbt.showFrame();
		tree.delete(s1);
		tree.delete(s6);
		tree.delete(s3);
		System.out.println();
		DrawableBTree<String> bbt = new DrawableBTree<String>(tree);
		bbt.showFrame();

		/*try{
		System.out.println("1. Testing the constructor.");
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		}catch (Exception e){
			System.out.println("Constructor could not be created.");
		}
			
		try{
		BinarySearchTree<String> tree1 = new BinarySearchTree<String>();
		BinarySearchTree<String> tree2 = new BinarySearchTree<String>();
		tree1.insert(new String("b"));
		tree2.insert(new String("a"));
		System.out.println("\n2. Testing attachRightSubtree().");
		tree1.attachRightSubtree(tree2);
		}catch (Exception e){
			System.out.println("Throws invalidOperation exception. Test passes.");
		}
		
		///creating a tree
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		
		String s1 = new String("k");
		String s2 = new String("g");
		String s3 = new String("q");
		String s4 = new String("c");
		String s5 = new String("i");
		String s6 = new String("h");
		String s7 = new String("m");
		String s8 = new String("y");
		String s9 = new String("p");
		String s10 = new String("b");
		String s11 = new String("d");
		String s12 = new String("j");
		
		try{		
		tree.insert(s1);
		tree.insert(s2);
		tree.insert(s3);
		tree.insert(s4);
		tree.insert(s5);
		tree.insert(s6);
		tree.insert(s7);
		tree.insert(s8);
		tree.insert(s9);
		tree.insert(s10);
		tree.insert(s11);
		tree.insert(s12);
		System.out.println("\n3. Testing insert(). Test Passes.");
		}catch (Exception e){
			System.out.println("insert() method throws excpetion. Test fails.");
		}
		
		System.out.println("\n4. Testing height().");
		if(tree.height() == 4){
			System.out.println("The height is "+ tree.height()+". Test Passes.");
		}
		else{
			System.out.println("Wrong height determined. Test fails.");
		}
		
		
		///////////////BinaryTreeIteratorClass///////////
		Iterator<String> it = tree.inorderIterator();
		System.out.println("Printing out the contents of the tree in sorted order:");
		while (it.hasNext()) {
			System.out.print(it.next()+" ");
		} 
		///
		
		/////////////Binary Search Tree/////////////////
		
		String test = tree.retrieve("k");
		if (test != null && !test.equals("")) {
			System.out.println("\n6. Testing retrieve: retrieving the node that contains "+s1);
			if (test.equals(s1)) {
				System.out.println("Retrieved the correct node. Test passes.");
			} else {
				System.out.println("Retrieve returns the wrong item. Test fails.");
			}
		} else {
			System.out.println("Retrieve returns nothing when it should not. Test fails.");
		}
		
		String test2 = tree.delete("y");
		System.out.println(test2);
		if (test2 != null && !test2.equals("")) {
			System.out.println("\n7. Testing delete: deleting the node that contains "+s8);
			if (test2.equals(s8)) {
				System.out.println("Deleted the correct node. Test passes.");
			} else {
				System.out.println("Delete returns the wrong item. Test fails.");
			}
		} else {
			System.out.println("Delete returns nothing when it should not. Test fails.");
		}
		
		
		try{
		DrawableBTree<String> dbt = new DrawableBTree<String>(tree);
		dbt.showFrame();
		}catch(Exception E){
			System.out.println("Cannot print an empty tree");
		}
	}*/
	}
}

	

	
