import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.*;

import Class.*;

/***
 * This was meant for Boot-camp: Java Fundamental 1 training.
 * @author Astri_A332
 * I still need to learn how to use Interface which implemented by Abstract class
 */
public class Main {

	public static void main(String[] args) {
		String welcomeText = getWelcomeText();
		String consoleInput = "";
		List<Employee> employees = new LinkedList<Employee>();
		SoftwareEngineer engineer = new SoftwareEngineer("John", 5);
		employees.add(engineer);
		Manager manager = new Manager("Manager Aldo");
		employees.add(manager);
		
		do {
			System.out.println(welcomeText);
			consoleInput = System.console().readLine();
			
			switch(consoleInput) {
			case "1":
				displayAllEmployee(employees);
				break;
			case "2":
				selectAnEmployee(employees);
				break;
			case "3":
				recruitAnEmployee(employees);
				break;
			case "4":
				filterEmployeeByName(employees);
				break;
			default:
				System.out.printf("No menu matched (%s) \n", consoleInput);	
			}
				
		} 
		while (consoleInput != null && !consoleInput.equalsIgnoreCase("exit"));
	}
	
	private static String getWelcomeText() {
		StringBuilder welcomeText = new StringBuilder()
				.append("============================================================================\n")
				.append("Welcome to Employee Operation, please select one of below menu number eg. 1 \n")
				.append("1. List of employee \n")
				.append("2. Select an employee \n")
				.append("3. Recruit new employee \n")
				.append("4. Find Employee by Name \n")
				.append("or type \"exit\" to terminate the program");
		
		return welcomeText.toString();
	}
	
	private static void displayAllEmployee(List<Employee> employees) {
		if(employees.size() == 0) {
			System.out.println("No Employee was found");
			return;
		}
		
		int counter = 1;		
		for(Employee empItem: employees) {
			System.out.println(counter++);
			empItem.displayProfile();
		}		
	} 
	
	private static void recruitAnEmployee(List<Employee> employees) {
		System.out.println("Please select the Job title number");
		System.out.println("1. Manager");
		System.out.println("2. Software Engineer");
		
		String inputKey = System.console().readLine();
		
		// early terminate for invalid input
		if (inputKey != null && !inputKey.equals("1") && !inputKey.equals("2"))
		{
			System.out.println("Invalid choice, program restarted");
			return;
		}
		
		System.out.println("please input employee name");
		String inputEmployeeName = System.console().readLine();
		
		if (inputEmployeeName == null) inputEmployeeName = String.format("New hire name %s \n", LocalDateTime.now().toString());
		
		Employee newHire = null;
		switch (inputKey) {
		case "1":
			newHire = new Manager(inputEmployeeName);
			break;
		case "2":
			int gradeNumber = inputGradeNumber();
			if(gradeNumber == 0) {
				break;
			}
			
			newHire = new SoftwareEngineer(inputEmployeeName, 10);
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
		
		if (newHire != null) {
			employees.add(newHire);
			System.out.printf("New Employee %s hired! \n", newHire.getName());
		}
	}
	
	private static int inputGradeNumber() {
		System.out.println("Please input grade number 1..22");
		String inputGradeNumber = System.console().readLine();
		int gradeNumber = 0;
		try {
			gradeNumber = Integer.parseInt(inputGradeNumber);
		}
		catch(Exception e) {
			System.out.printf("Invalid input %s \n", e.getMessage());
		}
		
		if(gradeNumber < 1 || gradeNumber > 22) {
			gradeNumber = 0;
			System.out.println("Please select number between 1 and 22");
		}
		
		return gradeNumber;
	}
	
	private static void selectAnEmployee(List<Employee> employees) {
		System.out.println("Please select an employee below by typing their order number");
		
		int iterator = 1;
		for(Employee empItem: employees) {
			System.out.printf("%s. %s \n", iterator++, empItem.getName());
		}
		
		String inputOrderNumber = System.console().readLine();
		int orderNumber = 0;
		
		try {
			orderNumber = Integer.parseInt(inputOrderNumber);
		}
		catch(Exception e) {
			System.out.printf("Invalid input %s \n", e.getMessage());
			return;
		}
		
		if (orderNumber < 1 || orderNumber > employees.size()) {
			System.out.printf("Input is beyond the range 1..%s \n", employees.size());
			return;			
		} 
		
		Employee selectedEmployee = employees.get(orderNumber - 1);
		System.out.println("Selected Employee:");
		selectedEmployee.displayProfile();
		
		System.out.println(getEmployeeMenu());
				
		String employeeInput = System.console().readLine();		
		switch(employeeInput) {
		case "1":
			selectedEmployee.promote();			
			break;
		case "2":
			selectedEmployee.terminate();
			break;
		default:
			System.out.printf("No menu matched (%s) \n", employeeInput);	
		}
		
		selectedEmployee.displayProfile();
	}
	
	private static void filterEmployeeByName(List<Employee> employees) {
		System.out.println("Please input name as keyword");
		String keywordInput = System.console().readLine();
		
		List<Employee> filteredEmployee = employees.stream()
				.filter(emp -> 
					emp.getName().toLowerCase().contains(keywordInput.toLowerCase()))
				.collect(Collectors.toList());
		
		System.out.printf("Result: %s Employee(s) \n", filteredEmployee.size());
		if(!filteredEmployee.isEmpty()) {
			displayAllEmployee(filteredEmployee);
		}		
	}
	
	private static String getEmployeeMenu() {
		StringBuilder welcomeText = new StringBuilder()
				.append("please select one of below menu number eg. 1 \n")
				.append("1. Promote \n")
				.append("2. Terminate \n");
		
		return welcomeText.toString();
	}
}
