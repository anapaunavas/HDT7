// Ana Paula Hong 22731

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Tests {
    
    @Test
	void InsertOneElementTest() {
        BinarySearchTree<String, String> myBST = new BinarySearchTree<String, String>(new compareStrings<String>());
        myBST.insert("ice", "hielo");
        assertEquals(1, myBST.count());
        assertEquals("hilo", myBST.getElements().get(0));
	}
	
	@Test
	void InsertMultipleElementsTest() {
        BinarySearchTree<String, String> myBST = new BinarySearchTree<String, String>(new compareStrings<String>());
        myBST.insert("yes", "si");
        myBST.insert("strawberry", "fresa");
        myBST.insert("honey", "miel");
        myBST.insert("ice cream", "helado");
        myBST.insert("beach", "playa");
        
        assertEquals(5, myBST.count());
	}
	
	@Test
	void InOrderWalkTest() {
        BinarySearchTree<String, String> myBST = new BinarySearchTree<String, String>(new compareStrings<String>());
        myBST.insert("yes", "si");
        myBST.insert("strawberry", "fresa");
        myBST.insert("honey", "miel");
        myBST.insert("ice cream", "helado");
        myBST.insert("beach", "playa");
        
        assertEquals(5, myBST.count());
        
	}
	

	@Test
	void findTest() {
		BinarySearchTree<String, String> myBST = new BinarySearchTree<String, String>(new compareStrings<String>());
        
        assertEquals(true, myBST.isEmpty());
        
        myBST.insert("yes", "si");
        myBST.insert("strawberry", "fresa");
        myBST.insert("honey", "miel");
        myBST.insert("ice cream", "helado");
        myBST.insert("beach", "playa");
        
        assertEquals(5, myBST.count());
        
        assertEquals(false, myBST.isEmpty());
        
        assertEquals("si", myBST.find("yes"));
	}
	
	@Test
	void DeleteRootOnlyOneElementTest() {
		BinarySearchTree<String, String> myBST = new BinarySearchTree<String, String>(new compareStrings<String>());
        
        assertEquals(true, myBST.isEmpty());
        
        myBST.insert("cat", "gato");
        
        assertEquals(1, myBST.count());
        
        assertEquals("gato", myBST.delete("cat"));
        
        assertEquals(0, myBST.count());
        
        assertEquals(true, myBST.isEmpty());
        
	}

	
	@Test
	void DeleteLeaftTest() {
		BinarySearchTree<String, String> myBST = new BinarySearchTree<String, String>(new compareStrings<String>());
        
        assertEquals(true, myBST.isEmpty());
        
        myBST.insert("yes", "si");
        myBST.insert("strawberry", "fresa");
        myBST.insert("honey", "miel");
        myBST.insert("ice cream", "helado");
        myBST.insert("beach", "playa");
        
        assertEquals(5, myBST.count());
        
        assertEquals("dinero", myBST.delete("money"));

	}
	
	@Test
	void DeleteRootMoreThanOneElementTest() {
		BinarySearchTree<String, String> myBST = new BinarySearchTree<String, String>(new compareStrings<String>());
        
        assertEquals(true, myBST.isEmpty());
        
        myBST.insert("yes", "si");
        myBST.insert("strawberry", "fresa");
        myBST.insert("honey", "miel");
        myBST.insert("ice cream", "helado");
        myBST.insert("beach", "playa");
        
        assertEquals(5, myBST.count());
        
        assertEquals("miel", myBST.delete("honey"));

	}
	
	
	@Test
	void DeleteIntermediateNodeMoreThanOneElementTest() {
		BinarySearchTree<String, String> myBST = new BinarySearchTree<String, String>(new compareStrings<String>());
        
        assertEquals(true, myBST.isEmpty());
        
        myBST.insert("yes", "si");
        myBST.insert("strawberry", "fresa");
        myBST.insert("honey", "miel");
        myBST.insert("ice cream", "helado");
        myBST.insert("beach", "playa");
        
        assertEquals(5, myBST.count());
                
        assertEquals("strawberry", myBST.delete("fresa"));

	}


}
