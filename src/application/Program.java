package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Sales;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Sales> list = new ArrayList<>();

		System.out.print("Enter the file path: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
						
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Sales (Integer.parseInt(fields[0]),Integer.parseInt(fields[1]),fields[2],Integer.parseInt(fields[3]),Double.parseDouble(fields[4])));
				line = br.readLine();
			}
		} 
		
		// Processing data	
				
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
