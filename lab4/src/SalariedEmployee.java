
public class SalariedEmployee extends Employee {

	public SalariedEmployee() {
		
	}

	public SalariedEmployee(String firstName, String lastName, String birthDate, int employeeID, String jobTitle,
			String company, double annualSalary) {
		super(firstName, lastName, birthDate, employeeID, jobTitle, company);
		this.annualSalary = annualSalary;
	}

protected double annualSalary;

	public double getAnnualSalary() {
		
		return annualSalary;
	}

}
