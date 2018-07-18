package employeeClass;

import java.util.*;

public class Manager extends Employee {
	private static final String _managerJobTitle = new String("Manager");
	
	public Manager(String name) {
		super(name);
		setJobTitle(_managerJobTitle);
	}
	
	@Override
	public void promote() {
		setJobTitle("Senior Manager");
	}
	
	@Override
	public void displayProfile() {
		super.displayProfile();
		System.out.printf("Employee current Job title is %s \n", _managerJobTitle);
	}
	
	public static Manager toManager(List<Employee> currentEmployee, Employee employee) {
		if(employee instanceof Manager) {
			System.out.println("Employee is already a Manager");			
		}
		
		Employee selectedEmployee = null;
		for(Employee empItem: currentEmployee) {
			boolean isSelectedEmployee = empItem.getName().equalsIgnoreCase(employee.getName()); 
			if(isSelectedEmployee) {
				selectedEmployee = empItem;
				break;
			}
		}
		
		if(selectedEmployee == null) {
			System.out.println("Employee was not found, no changes was made");
			return null;
		}
		
		// Delete existing instance
		currentEmployee.remove(selectedEmployee);
		
		// Create new instance
		Manager newManager = new Manager(employee.getName());
		
		// Add the new manager to current list
		currentEmployee.add(newManager);
		System.out.println("Employee current status");
		newManager.displayProfile();
		
		return newManager;
	}
}