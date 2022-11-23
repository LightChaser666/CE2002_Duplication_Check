package moblima;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Boundary Class that saves and retrieves cinema layouts, and static fields of entities like <code>Show</code>'s <code>basePrices</code> 
 * and <code>Cinema</code>'s <code>premium</code>.
 *
 */

public class CSVBoundary {
	
	/**
	 * Reads the csv file based on the user's choice, and return the seat layout in the form of a 2D Array.
	 * @param choice <code>int</code> to indicate which layout the staff chose.
	 * @return seatLayout 2Dchar array which holds the arrangement of seats for the chosen layout.
	 */
	public static char[][] getLayout(int choice) {
		char[][] seatLayout;
		String fileName = "layout"+choice+".csv";
		try {
			BufferedReader br =  new BufferedReader(new FileReader(fileName));
			String temp;
			int height = 0;
			while (br.readLine() != null) {
				height++;
			}
			br =  new BufferedReader(new FileReader(fileName));
			int i = 0;
			temp = br.readLine();
			temp = temp.replaceAll(",", "");
			int width = temp.length();
			seatLayout = new char[width][height];
			seatLayout[i++] =  temp.toCharArray();				
			while ((temp = br.readLine())!= null) {
				temp = temp.replaceAll(",", "");
				seatLayout[i++] =  temp.toCharArray();	
			}
			br.close();
			return seatLayout;
		}
		catch (FileNotFoundException e) {
			System.out.println("Choice not valid, setting default layout1");
			try {
				BufferedReader br =  new BufferedReader(new FileReader("layout1.csv"));
				String temp;
				int height = 0;
				while (br.readLine() != null) {
					height++;
				}
				br =  new BufferedReader(new FileReader(fileName));
				int i = 0;
				temp = br.readLine();
				temp = temp.replaceAll(",", "");
				int width = temp.length();
				seatLayout = new char[width][height];
				seatLayout[i++] =  temp.toCharArray();				
				while ((temp = br.readLine())!= null) {
					temp = temp.replaceAll(",", "");
					seatLayout[i++] =  temp.toCharArray();	
				}
				br.close();
				return seatLayout;
			}
			catch (FileNotFoundException e2) {}
			catch (IOException e2) {}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return new char[1][1];
	}
	
	/**
	 * Calls the <code>ConsoleBoundary</code> to prints out all the available layouts for the staff to chose during Cinema construction.
	 * Reads through all layout csv files to get all the layouts.
	 */
	public static void printAllLayout() {
		int c = 1;
		while(true) {
			String fileName = "layout" + c + ".csv";
			char[][] seatLayout;
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				String temp;
				int height = 0;
				while (br.readLine() != null) {
					height++;
				}
				br =  new BufferedReader(new FileReader(fileName));
				temp = br.readLine();
				temp = temp.replaceAll(",", "");
				int width = temp.length();
				seatLayout = new char[width][height];
				int i = 0;
				seatLayout[i++] =  temp.toCharArray();	
				while ((temp = br.readLine()) != null) {
					temp = temp.replaceAll(",", "");
					seatLayout[i++] =  temp.toCharArray();
				}
				ConsoleBoundary.printEmptyLayout(seatLayout, height, width, c);
				c++;
			}
			catch (FileNotFoundException e) {
				break;
			}
			catch (IOException e) {}
		}
		
	}
	
	/**
	 * Reads the csv containing the basePrices of the different types of shows.
	 * @return basePrices float array containing the different basePrices for the four different types of shows.
	 */
	public static float[] retrieveBasePrices() {
		try{
			BufferedReader br =  new BufferedReader(new FileReader("basePrices.csv"));
			String temp = br.readLine();
			String[] tempArray = temp.split(",");
			float[] basePrices = new float[tempArray.length];
			for (int i = 0; i<tempArray.length; i++) {
				basePrices[i] = Float.parseFloat(tempArray[i]);
			}
			return basePrices;
		}
		catch (FileNotFoundException e) {
			System.out.println("No base prices found. Please set base prices");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		float[] basePrices = {6, 8, 12, 15} ;
		return basePrices;
	}
	
	/**
	 * Reads the csv containing the premiums of the different types of cinemas.
	 * @return cinemaPremiums float array containing the different premiums for the three different types of cinemas.
	 */
	public static float[] retrieveCinemaPremium() {
		try{
			BufferedReader br =  new BufferedReader(new FileReader("cinemaPremiums.csv"));
			String temp = br.readLine();
			String[] tempArray = temp.split(",");
			float[] cinemaPremiums = new float[tempArray.length];
			for (int i = 0; i<tempArray.length; i++) {
				cinemaPremiums[i] = Float.parseFloat(tempArray[i]);
			}
			return cinemaPremiums;
		}
		catch (FileNotFoundException e) {
			System.out.println("No premiums found. Please set cinema premiums.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		float[] cinemaPremiums = {0, 15, 20} ;
		return cinemaPremiums;
	}
	
	/**
	 * Updates the Cinema premiums in the csv file.
	 * Called every time a cinema premium is changed
	 */
	public static void saveCinemaPremium() {
		try {
			File file = new File("cinemaPremiums.csv");
			if (!file.exists())
				file.createNewFile();
			float[] cinemaPremiums = new float[3];
			cinemaPremiums[0] = Cinema.getPremium();
			cinemaPremiums[1] = CinemaGold.getPremium();
			cinemaPremiums[2] = CinemaPlatinum.getPremium();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			String temp = cinemaPremiums[0] + ", " + cinemaPremiums[1] + ", " + cinemaPremiums[2];
			bw.write(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates the Show basePrices in the csv file.
	 * Called every time a show basePrice is changed
	 */
	public static void saveBasePrices() {
		try {
			File file = new File("basePrices.csv");
			if (!file.exists())
				file.createNewFile();
			float[] basePrices = new float[4];
			basePrices[0] = Show.getBasePriceForSaving();
			basePrices[1] = ShowDigital.getBasePriceForSaving();
			basePrices[2] = Show3D.getBasePriceForSaving();
			basePrices[3] = ShowIMAX.getBasePriceForSaving();
 				
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			String temp = basePrices[0] + ", " + basePrices[1] + ", " + basePrices[2] + ", " + basePrices[3];
			bw.write(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
