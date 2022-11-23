import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Movies {
	static List<Movie> list;
	static File moviesJSON = new File("movies.json");
	static Gson gson = new Gson(); 
	Type Movielist = new TypeToken<ArrayList<Movie>>() { }.getType(); // needed for gson to load into Arraylist
	
	public Movies() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(moviesJSON));
			list = new Gson().fromJson(reader.readLine(), Movielist);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Movie> getList() {
		return list;
	}
	
	public int getNumMovies() {
		return list.size();
	}
	
	public void listMovies() {
		System.out.println("=========Listing Movies=======");
		for (int i=0; i<list.size(); i++) {
			System.out.println(" " + (i+1) + ": " + list.get(i).title);
		}
		System.out.println();
	}
	
	public void viewMovieDetails(int movieIndex) {
		if (movieIndex < 0 || movieIndex >= list.size()) {
			System.out.println("Invalid Index");
		} else {
			list.get(movieIndex).viewDetails();
		}
	}
	
	public void editMovie(int index) {
		Scanner sc = new Scanner(System.in);
		Movie movie = list.get(--index);
		int choice = 0;
		
		while (choice != -1) {
			System.out.println("Select detail to edit");
			System.out.println(" 1: Title: " + movie.title);
			System.out.println(" 2: Rating: " + movie.rating);
			System.out.println(" 3: Status: " + movie.status);
			System.out.println(" 4: Cast: " + movie.cast);
			System.out.println(" 5: Director: " + movie.director);
			System.out.println(" 6: Genre: " + movie.genre);
			System.out.println(" 7: Synopsis: " + movie.synopsis);
			System.out.print("Choice: ");
			choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					System.out.print("New Title: ");
					String title = sc.nextLine();
					list.get(index).title = title;
			}
			
			
		}
		System.out.println();
	}
	
	public void addMovie() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Movie Title: ");
		String title = sc.nextLine();
		
		String[] ratings = {"G", "PG", "PG13", "NC16", "M18", "R21"};
		System.out.println("Choose the rating: ");
		for (int i=0; i<ratings.length; i++) {
			System.out.println(i+": "+ ratings[i]);
		}
		
		String rating = ratings[sc.nextInt()];
		sc.nextLine();
		
		System.out.println("Rating: " + rating);
		
		System.out.println("Cast(separate by , ): ");
		List<String> cast = Arrays.asList(sc.nextLine().split(","));
		
		System.out.print("Director: ");
		String director = sc.nextLine();
		
		System.out.print("Genre: ");
		String genre = sc.nextLine();
		
		System.out.print("Synopsis: ");
		String synopsis = sc.nextLine();
		
		String[] statuses = {"Coming Soon", "Preview", "Now Showing", "End Of Showing"};
		System.out.println("Choose the movie status: ");
		for (int i=0; i<statuses.length; i++) {
			System.out.println(i+": "+ statuses[i]);
		}
		String status = statuses[sc.nextInt()];
		sc.nextLine();
		System.out.println("Status: " + status);
		
		Movie movie = new Movie(title, status, synopsis, director, cast, genre, rating);
		list.add(movie);
		
		FileWriter writer;
		
		try {
			writer = new FileWriter(moviesJSON);
			writer.write(gson.toJson(list));
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}


}
