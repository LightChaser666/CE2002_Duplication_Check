package moblima;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PublicHolidayDatabase implements Database{
	
	private static ArrayList<PublicHoliday> phList = new ArrayList<PublicHoliday>();
	private static File file = new File ("PublicHolidays.dat");
	

	public static void printPublicHolidayMenu(Scanner scanner){
		System.out.println("\n=====================\n"
				+ "Public Holidays Settings\n"
				+ "=====================\n"
				+ "Current List of Holidays:");
		
		System.out.println("=============================================");
		System.out.println("ID\tDD-MM\tDESCRIPTION");
		System.out.println("=============================================");
		
		int i = 1;
		for(PublicHoliday ph : PublicHolidayDatabase.phList){
			System.out.print(i + ".\t");
			System.out.println(ph.getDate().format(DateTimeFormatter.ofPattern("dd-MM")) + "\t" + ph.getDescription());
			i++;
		}
		System.out.println("=============================================");
		
		System.out.print(""
				+ "1. Add Public Holiday\n"
				+ "2. Remove Public Holiday\n"
				+ "3. Back\n"
				+ "Please enter choice: ");
		try{
			MainMenuManager.choice = scanner.nextInt();
			scanner.nextLine();
			switch (MainMenuManager.choice) {
			case 1:
				addPH(scanner);
				break;
			case 2:
				removePH(scanner);
				break;
			case 3:
				break;
			default:
				System.out.println("\n===========");
				System.out.println("Error Input!");
				System.out.println("===========\n");
				break;
			}
		}catch(Exception e){
			System.out.println("Invalid input.");
			scanner.nextLine();
		}
	}
	
	public static void addPH(Scanner scanner){
		int month = 0, dayOfMonth = 0;
		String description;
		try{
			System.out.print("Enter the month of the holiday: ");
			month = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Enter the date of the holiday: ");
			dayOfMonth = scanner.nextInt();
			scanner.nextLine();
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.print("Enter description: ");
		description = scanner.nextLine();
		MonthDay holiday = MonthDay.of(month, dayOfMonth);
		phList.add(new PublicHoliday(description, holiday));

		Collections.sort(phList);
		updateHolidays();
	}

	public static void removePH(Scanner scanner){

		int index;
		System.out.print("Enter ID to remove: ");
		try{
			index = scanner.nextInt();
			scanner.nextLine();
			PublicHolidayDatabase.phList.remove(index - 1);
		} catch (Exception e){
			e.printStackTrace();
		}
		updateHolidays();
	}
	
	public static void updateHolidays(){
		try{
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(PublicHoliday ph: phList){
				output.writeObject(ph);
			}
			fo.close();
			output.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public static void fetchHolidays(){
		try{
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream input = new ObjectInputStream(fi);
			
			try{
				while(true){
					PublicHoliday ph = (PublicHoliday)input.readObject();
					phList.add(ph);
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
	
	public static ArrayList<PublicHoliday> getArrayList(){
		return phList;
	}

	@Override
	public void updateDatabase() {
		try{
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for(PublicHoliday ph: phList){
				output.writeObject(ph);
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
		try{
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream input = new ObjectInputStream(fi);
			
			try{
				while(true){
					PublicHoliday ph = (PublicHoliday)input.readObject();
					phList.add(ph);
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
