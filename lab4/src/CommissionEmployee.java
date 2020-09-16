
public class CommissionEmployee extends SalariedEmployee {

	public CommissionEmployee() {
		// TODO Auto-generated constructor stub
	}

	public CommissionEmployee(String firstName, String lastName, String birthDate, int employeeID, String jobTitle,
			String company, double annualSalary, double salesTotal, double commisionPercentage) {
		super(firstName, lastName, birthDate, employeeID, jobTitle, company, annualSalary);
		this.salesTotal = salesTotal;
		this.commisionPercentage = commisionPercentage;
	}
	private double salesTotal,  commisionPercentage;
		
	public double getAnnualSalary() {
		return annualSalary + (salesTotal * commisionPercentage);
	}
}
