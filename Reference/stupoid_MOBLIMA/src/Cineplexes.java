import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Cineplexes {
	static List<Cineplex> list;
	static File cineplexesJSON = new File("cineplexes.json");
	static Gson gson = new Gson(); 
	static Scanner sc = new Scanner(System.in);
	Type Cineplexlist = new TypeToken<ArrayList<Cineplex>>() { }.getType();

	
	public Cineplexes() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(cineplexesJSON));
			list = new Gson().fromJson(reader.readLine(), Cineplexlist);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Cineplex> getList() {
		return list;
	}
	
	public void listCineplexes() {
		System.out.println("=======Listing Cinplexes======");
		for (int i=0; i<list.size(); i++) {
			System.out.println(i+1 + ": " + list.get(i).name);
		}
	}

}
