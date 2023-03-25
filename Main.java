import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static BinarySearchTree<String, String> englishSpanishDictionary = new BinarySearchTree<String,String>(new compareStrings()<String>());
    private static BinarySearchTree<String, String> frenchSpanishDictionary = new BinarySearchTree<String,String>(new compareStrings()<String>());

    public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Bienvenido al diccionario traductor :) ");
		start();
    }
}