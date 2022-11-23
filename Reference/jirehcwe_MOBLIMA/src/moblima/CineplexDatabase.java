package moblima;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CineplexDatabase implements Database{
	
	public static ArrayList<Cineplex> cineplexList;
	private static File cineplexFile = new File("Cineplex.dat");
	
	public static ArrayList<Cineplex> getArrayList() {
		return cineplexList;
	}
	
	// Fetching infomation of all cineplexes.
	public static void fetchCineplexes(){
		cineplexList = new ArrayList<Cineplex>();
		try{
			FileInputStream fi = new FileInputStream(cineplexFile);
			ObjectInputStream input = new ObjectInputStream(fi);
				
			try{
				while(true){
					Cineplex c = (Cineplex)input.readObject();
					cineplexList.add(c);
				}
			} catch (EOFException e){
			} catch (ClassNotFoundException e) {
					e.printStackTrace();
			}
			
			fi.close();
			input.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
		
	public static void updateCineplexes(){
		try{
			FileOutputStream fo = new FileOutputStream(cineplexFile);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(Cineplex c: cineplexList){
				output.writeObject(c);
			}
			fo.close();
			output.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}	
	
	public static void printAllCineplexes() {
		int i=1;
			System.out.println("=~=~=~=~=~=~=~=~=~=~=~=~=~=~");
		for (Cineplex cp: getArrayList()) {
			System.out.println("Cineplex [" + i + "]: "+ cp.getName());
			i++;
			}
		}

	public static void addShowTime(Scanner scanner){
		int i = 1;
		int movieIndex;
		int cineplexIndex;
		int cinemaIndex;
		System.out.println("===============================");
		for(Cineplex cp : cineplexList){
			System.out.println(i + ".\t" + cp.getName());
			System.out.println("===============================");
			i++;
		}
		System.out.print("Select cineplex: ");
		cineplexIndex = scanner.nextInt();
		scanner.nextLine();
		
		i = 1;
		System.out.println("\n===============================");
		for(Cinema c : cineplexList.get(cineplexIndex-1).getCinemaList()){
			System.out.println(i + ".\t" + c.getCinemaID());
			System.out.println("===============================");
			i++;
		}
		System.out.print("Select cinema: ");
		cinemaIndex = scanner.nextInt();
		scanner.nextLine();
		
		i = 1;
		System.out.println("\n===============================");
		for(Movie m : MovieDatabase.getArrayList()){
			System.out.println(i + ".\t" + m.getTitle());
			System.out.println("===============================");
			i++;
		}
		System.out.print("Select movie: ");
		movieIndex = scanner.nextInt();
		scanner.nextLine();
		
		int month, dayOfMonth, hour, minute;
		
		System.out.print("Enter month: ");
		month = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter day of month: ");
		dayOfMonth = scanner.nextInt();
		scanner.nextLine(
				);
		
		System.out.print("Enter hour: ");
		hour = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter minute: ");
		minute = scanner.nextInt();
		scanner.nextLine();
		
		LocalDateTime tempDateTime = LocalDateTime.of(2017, month, dayOfMonth, hour, minute);
		
		cineplexList.get(cineplexIndex - 1).getCinemaList().get(cinemaIndex-1)
		.getCinemaShowList().add(new CinemaShow(MovieDatabase.getArrayList().get(movieIndex-1), tempDateTime));
		
		Collections.sort(cineplexList.get(cineplexIndex-1).getCinemaList().get(cinemaIndex-1).getCinemaShowList());
		
		updateCineplexes();
	}
	
	public static void printAllShowtimes() {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		
		int showtimeIndex =1;
		
		for(Cineplex cp : getArrayList()){
			System.out.println("		"+cp.getName());											//Cineplex Name
			System.out.println("Cinema Hall ");
			System.out.println(															"-------------------------------------------------------");
			for(Cinema c : cp.getCinemaList()){									
				System.out.println(													  "\n-------------------------------------------------------");
				System.out.println(c.getCinemaID() + "\t\t" + c.getClassOfCinema());		//Cinema Name		Cinema Type
				System.out.println(														"-------------------------------------------------------");	
				for(CinemaShow cs : c.getCinemaShowList()){
					System.out.println(                                                 "                                                    [" + showtimeIndex + "]");
					System.out.println(													"Movie:		" + cs.getMovie().getTitle());
					System.out.println(													"Date:		" + cs.getShowtime().format(formatter));
					System.out.println(													"Time:		" + cs.getShowtime().toLocalTime());
					System.out.println(													"- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				 showtimeIndex++;
				}
				
			}
			System.out.println(															"=======================================================");
		}
	}
		
	public static void removeShowTime(Scanner scanner){
		int showtimeIndex = 1;
		int showtimeChoice = 0;
		
		System.out.print("Enter showtime to remove: ");
		try{
			showtimeChoice = scanner.nextInt();
			scanner.nextLine();
			for(Cineplex cp : getArrayList()){
				for(Cinema c : cp.getCinemaList()){									
					for(CinemaShow cs : c.getCinemaShowList()){
						if(showtimeIndex == showtimeChoice){
							c.getCinemaShowList().remove(cs);
							break;
						}
						showtimeIndex++;
					}
				}
			}

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void editShowTime(Scanner scanner){
		int showtimeIndex = 1;
		int month, day, hour, minute;
		int showtimeChoice = 0;
		
		CinemaShow editShow = null;
		
		System.out.print("Select showtime: ");
		try{
			showtimeChoice = scanner.nextInt();
			scanner.nextLine();
		} catch (Exception e){
			e.printStackTrace();
		}

		for(Cineplex cp : getArrayList()){
			for(Cinema c : cp.getCinemaList()){									
				for(CinemaShow cs : c.getCinemaShowList()){
					if(showtimeIndex == showtimeChoice){
						editShow = cs;
					}
				 showtimeIndex++;
				}
			}
		}
		
		if(editShow != null){
			int choice = 0;
			while(choice < 1 || choice > 3){
				System.out.print("Select parameter to edit: "
						+ "\n1. Schedule"
						+ "\n2. Movie"
						+ "\n3. Back"
						+ "Enter your choice: ");
				choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
				case 1:
					System.out.print("Change to month: ");
					month = scanner.nextInt();
					scanner.nextLine();
					
					System.out.print("Change to day of month: ");
					day = scanner.nextInt();
					scanner.nextLine();
					
					System.out.print("Change to hour (24 hour format): ");
					hour = scanner.nextInt();
					scanner.nextLine();
					
					System.out.print("Change to minute: ");
					minute = scanner.nextInt();
					scanner.nextLine();
					
					editShow.setShowtime(LocalDateTime.of(2017, month, day, hour, minute));
					break;
				case 2:
					MovieDatabase.printFullMovieList();
					System.out.print("Select movie to change to: ");
					int movieIndex = scanner.nextInt();
					scanner.nextLine();
					editShow.setMovie(MovieDatabase.getArrayList().get(movieIndex-1));
					break;
				case 3:
					break;
				default:
					break;
				}
			}
		} else {
			System.out.println("Index out of bound.");
		}
//		Sorry i have no idea what these lines of codes are about. So i comment out temporarily.
		
		
		
//		String keepSeatingPlan = "o";
//		
//		System.out.print("Do you want to keep the original seating plan? Y/N ");
//		keepSeatingPlan = scanner.next();
//		scanner.nextLine();
//			
//		do {
//			switch(keepSeatingPlan) {
//			case "y":
//			case "Y":
//				cineplexList.get(cineplexIndex - 1).getCinemaList().get(cinemaIndex-1)
//				.getCinemaShowList().get(showtimeIndex-1).setShowtime(tempDateTime);
//			
//				cineplexList.get(cineplexIndex - 1).getCinemaList().get(cinemaIndex-1)
//				.getCinemaShowList().get(showtimeIndex-1).setMovie(MovieDatabase.getArrayList().get(movieIndex-1));
//				break;
//			case "n":
//			case "N": 
//				CinemaShow tempCinemaShow = new CinemaShow(MovieDatabase.getArrayList().get(movieIndex-1), tempDateTime);
//				cineplexList.get(cineplexIndex - 1).getCinemaList().get(cinemaIndex-1)
//				.getCinemaShowList().set(showtimeIndex-1, tempCinemaShow);
//				break;	
//			default: 
//				System.out.println("Invalid input entered.");
//				break;
//			}
//		} while (keepSeatingPlan.toUpperCase() != "Y" || keepSeatingPlan.toUpperCase() != "N");
		
		for(Cineplex cp : cineplexList){
			for(Cinema c : cp.getCinemaList()){
				Collections.sort(c.getCinemaShowList());
			}
		}
		
		updateCineplexes();
		}
	
	public void printShowtimeOfCinema(ArrayList<CinemaShow> cinemaShowList) {
		for(CinemaShow cs : cinemaShowList){
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
			
			System.out.println("Movie:		" + cs.getMovie().getTitle());
			System.out.println("Date:		" + cs.getShowtime().format(formatter));
			System.out.println("Time:		" + cs.getShowtime().toLocalTime());
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		}
	}

	public static int showTimeCount() {
		int temp = 0;
		
		for (Cineplex cp: CineplexDatabase.getArrayList()) {
			for (Cinema c: cp.getCinemaList()){
				for (CinemaShow cs: c.getCinemaShowList()) {
					cs.getClass();
					temp++;
					} 
			}
		}
		return temp;
	}

	
	@Override
	public void updateDatabase() {
		try{
			FileOutputStream fo = new FileOutputStream(cineplexFile);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(Cineplex c: cineplexList){
				output.writeObject(c);
			}
			fo.close();
			output.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	
	@Override
	public void fetchDatabase() {
		cineplexList = new ArrayList<Cineplex>();
		try{
			FileInputStream fi = new FileInputStream(cineplexFile);
			ObjectInputStream input = new ObjectInputStream(fi);
				
			try{
				while(true){
					Cineplex c = (Cineplex)input.readObject();
					cineplexList.add(c);
				}
			} catch (EOFException e){
			} catch (ClassNotFoundException e) {
					e.printStackTrace();
			}
			
			fi.close();
			input.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
}
