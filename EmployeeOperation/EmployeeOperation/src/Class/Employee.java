package Class;

import java.math.BigDecimal;
import java.time.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Employee {
	private BigDecimal _salary;
	private String _jobTitle = "new hire";
	private String _name;
	private LocalDate _hireDate;
	private LocalDate _lastWorkingDate = null;
	
	// new employee means new recruit
	public Employee(String name) {
		_name = name;
		_hireDate = getRandomHireDate();
	}
	
	public String getName() {
		return _name;
	}
	
	public String getJobTitle() {
		return _jobTitle;
	}
	
	public void setSalary(BigDecimal salary) {
		_salary = salary;
	}		
	
	public void viewSalary() {
		System.out.printf("Employee salary is %s \n", _salary.toString());
	}
	
	public void terminate() {
		_lastWorkingDate = LocalDate.now();
	} 
	
	public void displayProfile() {
		System.out.printf("Employee name is %s \n", _name);
		System.out.printf("Employee job title is %s \n", _jobTitle);
		
		Period workPeriod = Period.between((LocalDate)_hireDate, LocalDate.now()); 
		System.out.printf("Employee has been working for %s year %s month  \n", workPeriod.getYears(), workPeriod.getMonths());
		
		if(_lastWorkingDate != null) {
			System.out.printf("%s = %tB %tC", "Employee's last working day is at %s \n", _lastWorkingDate);
		}
	}
	
	protected void setJobTitle(String title) {
		_jobTitle = title;
	}
	
	public abstract void promote();
	
	private LocalDate getRandomHireDate() {
		LocalDate endDate = LocalDate.now();
		LocalDate startDate = endDate.plusYears(-20);
		
		long random = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay());
	    LocalDate hiredDate =  LocalDate.ofEpochDay(random);
	    
	    return hiredDate;
	}
}
