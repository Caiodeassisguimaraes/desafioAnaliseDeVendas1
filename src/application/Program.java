package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Sales;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Sales> listSales = new ArrayList<>();

		System.out.print("Enter the file path: ");
		String path = sc.nextLine();
		
		
		

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
						
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				listSales.add(new Sales (Integer.parseInt(fields[0]),Integer.parseInt(fields[1]),fields[2],Integer.parseInt(fields[3]),Double.parseDouble(fields[4])));
				line = br.readLine();
			}
			
			listSales.stream().filter(s -> s.getYear().equals(2016)).sorted((sales1, sales2) -> -sales1.averagePrice().compareTo(sales2.averagePrice())).collect(Collectors.toList()).forEach(System.out::println);
			
			//.limit(5)
			
			//listSales.sort((sales1, sales2) -> -sales1.averagePrice().compareTo(sales2.averagePrice()));
			//listSales.stream().forEach(s -> System.out.println(s));
			//listSales.forEach(System.out::println);
			
			/*for (Sales s : listSales) {
				System.out.println(s);
			}*/
			
			 //listSales.stream().filter(sales -> (sales.getSeller().equals("Logan") && sales.getMonth() == 1) || (sales.getSeller().equals("Logan") && sales.getMonth() == 7).);
			
		} 
								
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
