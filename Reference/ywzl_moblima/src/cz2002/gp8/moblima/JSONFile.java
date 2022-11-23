package cz2002.gp8.moblima;

/**
 JSONFile interface
 Allows the Object to load and save to a JSON File
 @author Kelvin Koh
 @version 1.0
 @since 2015-11-10
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.Gson;

public interface JSONFile {
	
	/**
	 * Loads the JSON File into a file type
	 * @param JSONFile file to load from
	 * @param type type of object to create from file
	 * @return
	 */
	public default Object load(File JSONFile, Type type) {
		Object object = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(JSONFile));
			object = new Gson().fromJson(reader.readLine(), type);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return object;
	}

	/**
	 * Save the Object into a JSON File
	 * @param JSONFile file to write to
	 * @param object to write the file from
	 */
	public default void save(File JSONFile, Object object) {
		FileWriter writer; 
		try {
			writer = new FileWriter(JSONFile);
			writer.write(new Gson().toJson(object));
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
