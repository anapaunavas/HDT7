import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static BinarySearchTree<String, String> englishSpanishDictionary = new BinarySearchTree<String,String>(new compareStrings()<String>());
    private static BinarySearchTree<String, String> frenchSpanishDictionary = new BinarySearchTree<String,String>(new compareStrings()<String>());

    public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Bienvenidx al diccionario traductor :) ");
		start();
    }

    private static void start()throws IOException, InterruptedException {
		int selection = 0;
		
		ArrayList<ArrayList<String>> wordsDictionary = extracting_data();
		
		for(int i = 0; i < wordsDictionary.size(); i++) {
			englishSpanishDictionary.insert(wordsDictionary.get(i).get(0), wordsDictionary.get(i).get(1));
			frenchSpanishDictionary.insert(wordsDictionary.get(i).get(2), wordsDictionary.get(i).get(1));
		}
		
		while(selection != 6) {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			selection = OptionsMainMenu();
			switch(selection) {
				case 1:
					// agregar una nueva palabra al diccionario.
					typeDictionary(selection, englishSpanishDictionary, frenchSpanishDictionary);
					break;
				case 2:
					// eliminar una palabra del diccionario
					typeDictionary(selection, englishSpanishDictionary, frenchSpanishDictionary);
					break;
				case 3:
					// traduccion
					translateOptions(englishSpanishDictionary, frenchSpanishDictionary);
					break;
				case 4:
					// listado de palabras en ingles
					ITreeTraversal<String> englishValue = new ITreeTraversal<String>(){
						@Override	
						public void Walk(String value) {
							System.out.println(value);
						}
					};
					System.out.println("listado de palabras en ingles");
					englishSpanishDictionary.inOrder(englishValue);
					wait(3000);
					break;
				case 5:
					// listado de palabras en frances
					ITreeTraversal<String> franceValue = new ITreeTraversal<String>(){
						@Override	
						public void Walk(String value) {
							System.out.println(value);
						}
					};
					System.out.println("listado de palabras en frances");
					frenchSpanishDictionary.inOrder(franceValue);
					wait(3000);
					break;
				default:
					//End system.
					System.out.println("gracias por utilizar el diccionario traductor :D ");
					break;
			}
		}
	}
}