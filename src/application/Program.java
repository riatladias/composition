package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) {
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter department's name: ");
		Department department = new Department(sc.nextLine());

		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String name = sc.nextLine();

		System.out.print("Level: ");
		WorkerLevel workerLevel = WorkerLevel.valueOf(sc.nextLine());

		System.out.print("Base salary: ");
		Double baseSalary = sc.nextDouble();
		Worker worker = new Worker(department, name, workerLevel, baseSalary);

		System.out.print("How many contracts to this worker? ");
		int n = sc.nextInt();

		for (int i = 1; i <= n; i++) {
			System.out.println("Enter contract #" + i + " data:");
			System.out.print("Date (DD/MM/YYYY): ");
			String d = sc.next();
			LocalDate date = LocalDate.parse(d, sdf);

			System.out.print("Value per hour: ");
			Double value = sc.nextDouble();

			System.out.print("Duration (hours): ");
			int duration = sc.nextInt();

			HourContract contract = new HourContract(date, value, duration);
			worker.addContract(contract);
		}
		sc.nextLine();
		
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		String date_s = sc.nextLine();
		Integer month = Integer.parseInt(date_s.substring(0, 2));
		Integer year = Integer.parseInt(date_s.substring(3));
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.printf("Income for %s: %.2f", date_s, worker.income(month, year));

		sc.close();
	}

}
