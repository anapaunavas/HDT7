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
}
