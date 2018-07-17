package Class;

public class SoftwareEngineer extends Employee {
	private static final String _seJobTitle = new String("Software Engineer");
	private int _grade;
	
	public SoftwareEngineer(String name, int grade) {
		super(name);
		_grade = grade;
		setJobTitle(_seJobTitle);
	}
	
	@Override
	public void promote() {
		promote(1);
	}
	
	@Override
	public void displayProfile() {
		super.displayProfile();
		System.out.printf("Employee current Job title is %s at grade %s \n", _seJobTitle, _grade);
	}
	
	// Overload
	public void promote(int increment) {
		_grade += increment;
		System.out.printf("Software Engineer has been promoted to grade %s \n", _grade);
	}
	
	public int getGrade() {
		return _grade;
	}
}