import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree <K, V> implements IBinarySearchTree<K, V> {
    private int count;
	private treeNode<K, V> root;
	private Comparator<K> keyComparator;
    
    public BinarySearchTree(Comparator<K> _keyComparator) {
		this.keyComparator = _keyComparator;
		root = null;
		count = 0;
	}

    @Override
	public void insert(K id, V value) {
		if (isEmpty()) {
			root = new treeNode<K, V>(id, value);
			count++;
		} else {
			internalInsert(root, id, value);
		}
	}

    @Override
	public V delete(K id) {
		if (!isEmpty()) {
			int result = keyComparator.compare(root.getId(), id);
			if (result > 0) {
				return internalDelete(root.getLeft(), id, true);
			} else if (result < 0) {
				return internalDelete(root.getRight(), id, false);
			} else { //Root is the deleted node
				if (count() == 1) {
					V temp = root.getValue();
					root = null;
					count--;
					return temp;
				} else {
					
					V tempValue = root.getValue();

					if (root.getRight() != null) { // buscar hijo derecho mas izquierdo
							
						treeNode<K, V> leftOfTheRights = root.getRight();
						
						while(leftOfTheRights.getLeft() != null) {
							leftOfTheRights = leftOfTheRights.getLeft(); 
						}
						//Assigning the left side
						leftOfTheRights.setLeft(root.getLeft());
						if (leftOfTheRights.getLeft() != null) {
							leftOfTheRights.getLeft().setParent(leftOfTheRights);
						}
						//Assiginig the right side
						if (keyComparator.compare(root.getRight().getId(), leftOfTheRights.getId()) != 0) { //Only if the leftOfTheRights is different than root.right
							leftOfTheRights.getParent().setLeft(null);
							
							treeNode<K, V> newRootRight = leftOfTheRights;
							
							while (newRootRight.getRight() != null) {
								newRootRight = newRootRight.getRight();
							}
							newRootRight.setRight(root.getRight());
							if (newRootRight.getRight() != null) {
								newRootRight.getRight().setParent(newRootRight);;
							}
						}
						//Assginig the new parents
						if (leftOfTheRights.getRight() != null) {
							leftOfTheRights.getRight().setParent(leftOfTheRights);
						}
							
						
						leftOfTheRights.setParent(null);
						root = leftOfTheRights;
						count--;
						return tempValue;
					} else { //Buscar hijo izquierdo mas derecho
						
						treeNode<K, V> rightOfTheLefts = root.getLeft();
						
						while(rightOfTheLefts.getRight() != null) {
							rightOfTheLefts = rightOfTheLefts.getRight(); 
						}
						
						//Assigning the right side
						rightOfTheLefts.setRight(root.getRight());
						if (rightOfTheLefts.getRight() != null) {
							rightOfTheLefts.getRight().setParent(rightOfTheLefts);
						}
						//Assiginig the left side
						if (keyComparator.compare(root.getLeft().getId(), rightOfTheLefts.getId()) != 0) { //Only if the rightOfTheLefts is different than root.left
							rightOfTheLefts.getParent().setRight(null);
							
							treeNode<K, V> newRootLeft = rightOfTheLefts;
							
							while (newRootLeft.getLeft() != null) {
								newRootLeft = newRootLeft.getLeft();
							}
							newRootLeft.setLeft(root.getLeft());
							if (newRootLeft.getLeft() != null) {
								newRootLeft.getLeft().setParent(newRootLeft);;
							}
						}
						//Assginig the new parentes
						if (rightOfTheLefts.getLeft() != null) {
							rightOfTheLefts.getLeft().setParent(rightOfTheLefts);
						}
						rightOfTheLefts.setParent(null);
						root = rightOfTheLefts;
						
						count--;
						return tempValue;	
					}	
				}
			}
		}		
		return null;
	}

    @Override
	public V find(K id) {
		return internalFind(root, id);
	}

	@Override
	public int count() {
		return count;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public ArrayList<V> getElements() {
		ArrayList<V> list = new ArrayList<V>();
		
		internalGetElements(list, root);
		
		return list;
	}

	@Override
	public void inOrder(ITreeTraversal<V> traversal) {
		internalInOrder(root, traversal);
	}

	@Override
	public void preOrder(ITreeTraversal<V> traversal) {
		internalPreOrder(root, traversal);
		
	}

	@Override
	public void postOrder(ITreeTraversal<V> traversal) {
		internalPostOrder(root, traversal);
	}

    private void internalInsert(treeNode<K, V> actual, K id, V value) {
		
		int result = keyComparator.compare(actual.getId(), id);
		
		if (result > 0) { 
			if (actual.getLeft() == null) { // no tiene hijos izquierdos
				treeNode<K, V> newNode = new treeNode<K, V>(id, value);
				actual.setLeft(newNode);
				newNode.setParent(actual);
				count++;
			} else {
				internalInsert(actual.getLeft(), id, value);
			}	
		} else if (result < 0) {
			if (actual.getRight() == null) { // no tiene hijos izquierdos
				treeNode<K, V> newNode = new treeNode<K, V>(id, value);
				actual.setRight(newNode);
				newNode.setParent(actual);
				count++;
			} else {
				internalInsert(actual.getRight(), id, value);
			}
		}	
	}

    private void internalInOrder(treeNode<K, V> actual, ITreeTraversal<V> traversal) {
		if (actual != null) {
			internalInOrder(actual.getLeft(), traversal);
			
			traversal.Walk(actual.getValue());
			
			internalInOrder(actual.getRight(), traversal);
		}
	}

    private void internalPreOrder(treeNode<K, V> actual, ITreeTraversal<V> traversal) {
		if (actual != null) {
			traversal.Walk(actual.getValue());
			
			internalPreOrder(actual.getLeft(), traversal);
			
			internalPreOrder(actual.getRight(), traversal);
		}
	}

    private void internalPostOrder(treeNode<K, V> actual, ITreeTraversal<V> traversal) {
		if (actual != null) {
		
			internalPostOrder(actual.getLeft(), traversal);
			
			internalPostOrder(actual.getRight(), traversal);
			
			traversal.Walk(actual.getValue());
		}
	}

    private V internalFind(treeNode<K, V> actual, K id) {
		if (actual != null) {
			int result = keyComparator.compare(actual.getId(), id);
			
			if (result > 0) {
				return internalFind(actual.getLeft(), id);
			} else if (result < 0) {
				return internalFind(actual.getRight(), id);
			} else {
				return actual.getValue();
			}		
		} else {
			return null;
		}
	}

    
}
