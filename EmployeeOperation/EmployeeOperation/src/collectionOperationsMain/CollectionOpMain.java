package collectionOperationsMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import employeeClass.*;

public class CollectionOpMain {

	public static void main(String[] args) {
		/* 
		 * Each below collection will implement add, remove, filter, and sort
		 * */
		// Employees instances
		Employee budi = new SoftwareEngineer("Budi", 10);
		Employee abdi = new SoftwareEngineer("Abdi", 11);
		Employee dian = new SoftwareEngineer("Dian", 5);
		Employee amru = new Manager("Amru");
		Employee toyyib = new Manager("Toyyib");
		Employee rene = new Manager("Rene");
		
		List<Employee> listEmployee = new LinkedList<Employee>();
		listEmployee.add(budi);
		listEmployee.add(abdi);
		listEmployee.add(dian);
		listEmployee.add(amru);
		listEmployee.add(toyyib);
		listEmployee.add(rene);
		
		// Create list of employee
		listOperations(listEmployee);
		
		// create map of employee
		mapOperations(listEmployee);
				
		// create set of employee
		hashSetOperations(listEmployee);
		
		// create stack of employeeClass/
		stackOperations(listEmployee);
	}

	private static void listOperations(List<Employee> employees) {
		System.out.println("============================================================================");
		System.out.println("Instantiate Employee list");
		List<Employee> listEmployee = new LinkedList<Employee>();
		System.out.println("Add item to list");
		for(Employee empItem: employees) {
			listEmployee.add(empItem);
		}
		displayAllEmployee(listEmployee);		
		
		System.out.println("============================================================================");
		System.out.println("Add Duplicate");
		listEmployee.add(employees.get(0));
		listEmployee.add(employees.get(2));
		displayAllEmployee(listEmployee);
		
		System.out.println("============================================================================");
		System.out.println("Remove Amru from list");
		listEmployee.removeIf(emp -> emp.getName().equalsIgnoreCase("Amru"));
		displayAllEmployee(listEmployee);
		
		System.out.println("============================================================================");
		System.out.println("Filter employee that contains \"di\"");
		List<Employee> filteredEmployee = listEmployee.stream()
				.filter(emp -> 
					emp.getName().toLowerCase().contains("di"))
				.collect(Collectors.toList());
		displayAllEmployee(filteredEmployee);
		
		System.out.println("============================================================================");
		System.out.println("Sort employee");
		listEmployee.sort(Comparator.comparing(Employee::getName));
		displayAllEmployee(listEmployee);
	}
	
	private static void hashSetOperations(List<Employee> employees) {
		System.out.println("============================================================================");
		System.out.println("Instantiate Employee hash set");
		Set<Employee> hashSetEmployee = new HashSet<Employee>(); 
		
		System.out.println("Add item to hash set");
		for(Employee empItem: employees) {
			hashSetEmployee.add(empItem);
		}
		displayAllEmployee(hashSetEmployee);		
		
		System.out.println("============================================================================");
		System.out.println("Add Duplicate, compiler and runtime won't throw any error but no changes happen after adding duplicate");
		hashSetEmployee.add(employees.get(0));
		hashSetEmployee.add(employees.get(2));
		displayAllEmployee(hashSetEmployee);
		
		System.out.println("============================================================================");
		System.out.println("Remove Amru from list");
		hashSetEmployee.removeIf(emp -> emp.getName().equalsIgnoreCase("Amru"));
		displayAllEmployee(hashSetEmployee);
		
		System.out.println("============================================================================");
		System.out.println("Filter employee that contains \"di\"");
		List<Employee> filteredEmployee = hashSetEmployee.stream()
				.filter(emp -> 
					emp.getName().toLowerCase().contains("di"))
				.collect(Collectors.toList());
		displayAllEmployee(filteredEmployee);		

		System.out.println("============================================================================");
		System.out.println("Has set cannot be sorted");
	}

	private static void mapOperations(List<Employee> employees) {
		System.out.println("============================================================================");
		System.out.println("Instantiate Employee Map");
		Map<Integer, Employee> mapEmployee = new HashMap<Integer, Employee>();
		System.out.println("Add item to list");
		
		int counter = 1;	
		for(Employee empItem: employees) {
			mapEmployee.put(counter++, empItem);
		}
		
		displayAllEmployee(mapEmployee);	
		
		System.out.println("============================================================================");
		System.out.println("Remove Amru from list");
		List<Integer> keysToRemove= new ArrayList<Integer>();
		for(Map.Entry<Integer, Employee> empItem: mapEmployee.entrySet()) {
			if(empItem.getValue().getName().equalsIgnoreCase("Amru")) {
				keysToRemove.add(empItem.getKey());
			}
		}	
		
		keysToRemove.forEach(keys -> mapEmployee.remove(keys));
		
		displayAllEmployee(mapEmployee);
		
		System.out.println("============================================================================");
		System.out.println("Filter employee that contains \"di\"");
		Map<Integer, Employee> filteredEmployee = mapEmployee.entrySet().stream()
				.filter(emp -> 
					emp.getValue().getName().toLowerCase().contains("di"))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		displayAllEmployee(filteredEmployee);
		
		System.out.println("============================================================================");
		System.out.println("Sort employee, it must done manually so I skipped it");		
	}
	
	private static void stackOperations(List<Employee> employees) {
		System.out.println("============================================================================");
		System.out.println("Instantiate Employee stack");
		Stack<Employee> stackEmployee = new Stack<Employee>();
		
		employees.forEach(e -> stackEmployee.push(e));
		displayAllEmployee(stackEmployee);
		
		System.out.println("============================================================================");
		System.out.println("Pop Employee stack");
		stackEmployee.pop();
		displayAllEmployee(stackEmployee);
		
		System.out.println("============================================================================");
		System.out.println("Peek Employee stack");
		stackEmployee.peek().displayProfile();
		
		System.out.println("============================================================================");
		Employee searchedEmployee = employees.get(2);
		System.out.printf("Search employee with index 2 (%s) from input \n", searchedEmployee.getName());
		System.out.printf("Distance object on the stack %s \n", stackEmployee.search(searchedEmployee));
		
	}
	
	private static void displayAllEmployee(Collection<Employee> employees) {
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
	
	private static void displayAllEmployee(Map<Integer, Employee> employees) {
		if(employees.size() == 0) {
			System.out.println("No Employee was found");
			return;
		}
		
		for(Map.Entry<Integer, Employee> empItem: employees.entrySet()) {
			System.out.println(empItem.getKey());
			empItem.getValue().displayProfile();
		}	
	}

}
