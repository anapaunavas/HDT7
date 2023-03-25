import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class openFiles {
    public ArrayList<ArrayList<String>> obtain_sentences(String file_name) {
    	ArrayList<ArrayList<String>> sentences = new ArrayList<ArrayList<String>>();
        Path filePath = Paths.get("Archivos\\" + file_name + ".txt");
        try {
            // leer el archivo por medio de route
            BufferedReader br = Files.newBufferedReader(filePath);
            String line;
            // Lecture line by line of the file.
            while ((line = br.readLine()) != null) {
                // Store the values in the array, separating by comas.
                String[] dataOfLine = line.split(" ");
                ArrayList<String> datas_temp = new ArrayList<String>();
                for (String data : dataOfLine) {
                    datas_temp.add(data);
                }
                // agregar al array
                sentences.add(datas_temp);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return sentences;
    }

    public ArrayList<ArrayList<String>> obtain_words(String file_name) {
    	ArrayList<ArrayList<String>> words = new ArrayList<ArrayList<String>>();
    	Path filePath = Paths.get("Archivos\\"+ file_name + ".txt");
        try {
            // leer el archivo por medio de route
            BufferedReader br = Files.newBufferedReader(filePath);
            String line;
            // leer el archivo linea por linea
            while ((line = br.readLine()) != null) {
                // guardar los valores en el array separados por comas
                String[] dataOfLine = line.split(",");
                ArrayList<String> datas_temp = new ArrayList<String>();
                for (String data : dataOfLine) {
                    datas_temp.add(data);
                }
                // agregar al array
                words.add(datas_temp);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return words;
    }

    public void new_file(String file_name) {
        File File;
        try {
            // ruta archivos
            File = new File("Archivos\\" + file_name + ".txt");
            // verificar si existe
            if (File.exists()) {
                System.out.println("el archivo ya existe");
            } else if (File.createNewFile()) {// crear en caso de que no exista
                System.out.println("se ha creado el archivo");
            }
        } catch (IOException exception) {// throwable, excepcion padre (general) de todas las excepciones
            System.err.println("no se ha creado el archivo :( )");
        }
    }

    public boolean verifyingFile(String file_name) throws IOException {
    	File File;
    	// ruta archivos
		File = new File("Archivos\\" + file_name + ".txt");
		// verificar si existe
		return File.exists();
    }
}
