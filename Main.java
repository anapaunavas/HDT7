import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {
    private static BinarySearchTree<String, String> englishSpanishDictionary = new BinarySearchTree<String,String>(new compareStrings<String>());
	private static BinarySearchTree<String, String> frenchSpanishDictionary = new BinarySearchTree<String,String>(new compareStrings<String>());

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

    private static void translateOptions(BinarySearchTree<String, String> englishSpanishDictionary, BinarySearchTree<String, String> frenchSpanishDictionary) throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		int selection = translationOptions();
		switch(selection) {
			case 1:
				// traducir oracion en ingles
				translating(selection, englishSpanishDictionary);
				break;
			case 2:
				// traducir oracion en frances
				translating(selection, frenchSpanishDictionary);
				break;
			default:
				// end system
				start();
				break;
		}
	}

    private static void translating(int selection, BinarySearchTree<String, String> Dictionary) {
		String word = " ";
		ArrayList<String> translated = new ArrayList<String>();
		switch(selection) {
			case 1:
				word = JOptionPane.showInputDialog("Ingrese una oracion en ingles para traducirla: ");
				break;
			case 2:
				word = JOptionPane.showInputDialog("Ingrese una oracion en frances para traducirla: ");
				break;
		}
		//word = word.replaceAll(",", " ");
		//word = word.replaceAll(".", " ");

        String[] words = word.split(" ");
		for(int i = 0; i < words.length; i++) {
			if(Dictionary.find(words[i]) == null) {
				translated.add("*" + words[i]+"*");
			}else {
				translated.add(Dictionary.find(words[i]));
			}
		}
		
		for(int i = 0; i < translated.size(); i++) {
			System.out.print(translated.get(i) + " ");
		}
		wait(3000);
		
	}

    private static void DictionaryModifications(int selection, int userSelection, BinarySearchTree<String, String> englishSpanishDictionary, BinarySearchTree<String, String> frenchSpanishDictionary)throws IOException, InterruptedException {
		
		String keyWord = ""; // ingles o frances
		String valueWord = ""; // solo ingles
		// agregar una palabra
		if(userSelection == 1) {
			if(selection == 1) {
				keyWord = JOptionPane.showInputDialog("Ingrese la palabra en ingles: ");
				valueWord = JOptionPane.showInputDialog("Ingrese la traduccion de la palabra anterior: ");
				englishSpanishDictionary.insert(keyWord, valueWord);
			}else {
				keyWord = JOptionPane.showInputDialog("Ingrese la palabra en ingles: ");
				valueWord = JOptionPane.showInputDialog("Ingrese la traduccion de la palabra anterior: ");
				frenchSpanishDictionary.insert(keyWord, valueWord);
			}	
		}else {
			// eliminar una palabra
			if(selection == 1) {
				keyWord = JOptionPane.showInputDialog("Ingrese la palabra en ingles: ");
				englishSpanishDictionary.delete(keyWord);	
			}else {
				keyWord = JOptionPane.showInputDialog("Ingrese la palabra en ingles: ");
				frenchSpanishDictionary.delete(keyWord);
			}
		}		
	}

    private static void typeDictionary(int userSelection, BinarySearchTree<String, String> englishSpanishDictionary, BinarySearchTree<String, String> frenchSpanishDictionary)throws IOException, InterruptedException {
		int selection  = 0;
		while(selection != 3) {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			selection = selectionDictionary();
			switch(selection) {
				case 1:
					// English Dictionary.
					DictionaryModifications(selection, userSelection, englishSpanishDictionary, frenchSpanishDictionary);
					break;
				case 2:
					// French Dictionary.
					DictionaryModifications(selection, userSelection, englishSpanishDictionary, frenchSpanishDictionary);
					break;
				default:
					// back to the previous menu.
					start();
					break;
			}	
		}
	}

    private static int selectionDictionary() {
		String[] options = {"Diccionario en ingles", "Diccionario en frances","Regresar"};
		boolean next_step = false;
		int selection = 0;
		
		for(int i = 0; i< options.length; i++) {
			System.out.println((i+1) + ". " + options[i]);
		}
		do {
			try {
				int AmountOptions = options.length;
				selection = Integer.parseInt(JOptionPane.showInputDialog("Ingrese una de las opciones: "));
				if(selection < 0 || selection > AmountOptions) {
					System.err.println("Ingrese una opcion valida");
				}else {
					next_step = true;
				}
			}catch(NumberFormatException e) {
				System.err.println("Ingrese un valor numerico");
			}
		}while(!next_step);
		return selection;
	}

    private static int OptionsMainMenu() {
		int selection = 0;
		boolean next_step = false;
		String[] options = {"Agregar una palabra al diccionario.", "Eliminar una palabra del diccionario.", "Traducir oracion", 
				"Listado de palabras en ingles" ,"Listado de palabras en frances","Salir."};
		
		for(int i = 0; i < options.length; i++) {
			System.out.println((i+1) +". " + options[i]);
		}
		do {
			try {
				int AmountOptions = options.length;
				selection = Integer.parseInt(JOptionPane.showInputDialog("Ingrese una de las opciones"));
				if(selection < 1 || selection > AmountOptions) {
					System.err.println("Ingrese una de las opciones dadas");
				}else {
					next_step = true;
				}
			}catch(NumberFormatException e) {
				System.err.println("Ingrese un valor numerico");
			}	
		}while(!next_step);
		return selection;
	}

    private static int translationOptions() {
		System.out.println("Seleccione un idioma para traducir");
		String[] options = {"Ingles", "Frances","Regresar"};
		boolean next_step = false;
		int selection = 0;
		
		for(int i = 0; i< options.length; i++) {
			System.out.println((i+1) + ". " + options[i]);
		}
		do {
			try {
				int AmountOptions = options.length;
				selection = Integer.parseInt(JOptionPane.showInputDialog("Ingrese una de las opciones: "));
				if(selection < 0 || selection > AmountOptions) {
					System.err.println("Ingrese una opcion valida");
				}else {
					next_step = true;
				}
			}catch(NumberFormatException e) {
				System.err.println("Ingrese un valor numerico");
			}
		}while(!next_step);
		return selection;	
	}

    private static ArrayList<ArrayList<String>> extracting_data()throws IOException {
		ArrayList<ArrayList<String>> words_dictionary = new ArrayList<ArrayList<String>>();
		openFiles files = new openFiles();
		
		if(files.verifyingFile("dictionary")) {
			words_dictionary = files.obtain_words("dictionary");
		}	
		return words_dictionary;
	}

    private static void wait(int wait_time) {
        try {
            Thread.sleep(wait_time);
        } catch (Exception e) {
            System.out.println("Algo salio mal :( )");
        }
    }
}