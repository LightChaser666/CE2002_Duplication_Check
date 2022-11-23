import java.util.Scanner;

public class Admin { 
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Movies movies = new Movies();
		Cineplexes cineplexes = new Cineplexes();
		int choice = 0;
		
		while(choice != -1) {
			System.out.println("Welcome to MOBLIMA Admin panel");
			System.out.println("===========Main Menu==========");
			System.out.println(" 1: View Movies");
			System.out.println(" 2: View Cineplexes");
			System.out.println("-1: Exit");
			System.out.print("Choice: ");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					movieMenu();
					break;
				
			}
			
			
		}
		
		System.out.println("Exiting...");
	}
	
	public static void movieMenu() {
		Movies movies = new Movies();
		int choice = 0;
		while(choice != -1) {
			System.out.println("==========Movie Menu==========");
			movies.listMovies();
			System.out.println(" 1: Edit Movie");
			System.out.println(" 2: Add Movie");
			System.out.println("-1: Exit");
			System.out.print("Choice: ");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					System.out.print("Movie to Edit: ");
					choice = sc.nextInt();
					movies.editMovie(choice);
					break;
					
				case 2:
					movies.addMovie();
					break;
				
			}
			
			
		}
	}
}
