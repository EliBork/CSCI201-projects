
public class HourlyEmployee extends Employee {

	public HourlyEmployee() {
		// TODO Auto-generated constructor stub
	}

	public HourlyEmployee(String firstName, String lastName, String birthDate, int employeeID, String jobTitle,
			String company, int hourlyRate, int numberHoursPerWeek) {
		super(firstName, lastName, birthDate, employeeID, jobTitle, company);
		this.hourlyRate = hourlyRate;
		this.numberHoursPerWeek = numberHoursPerWeek;
		
	}

	private int hourlyRate;
	private int numberHoursPerWeek;
	
	public double getAnnualSalary() {
		int sal = hourlyRate * numberHoursPerWeek * 52;
		return sal;
	}

}
