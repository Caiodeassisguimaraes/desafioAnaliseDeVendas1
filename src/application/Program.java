package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
				listSales.add(new Sales(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
			}

			System.out.println();
			System.out.println("Cinco primeiras vendas de 2016 de maior preço médio ");
			listSales.stream().filter(s -> s.getYear().equals(2016))
					.sorted((sales1, sales2) -> -sales1.averagePrice().compareTo(sales2.averagePrice())).limit(5)
					.collect(Collectors.toList()).forEach(System.out::println);
			System.out.println();
			System.out.printf("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = %.2f",
					listSales.stream().filter(s -> s.getSeller().equals("Logan"))
							.filter(s -> s.getMonth().equals(1) || s.getMonth().equals(7))
							.mapToDouble(s -> s.getTotal()).sum());

		}

		catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

		finally {
			sc.close();
		}
	}
}
