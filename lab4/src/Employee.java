
public abstract class Employee extends Person {

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String firstName, String lastName, String birthDate, int employeeID, String jobTitle, String company) {
		super(firstName, lastName, birthDate);
		this.employeeID = employeeID;
		this.jobTitle = jobTitle;
		this.company = company;
		
	}

	protected int employeeID;
	
	protected String jobTitle;
	protected String company;

	public Integer getEmployeeID() {
	return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
	this.employeeID = employeeID;
	}

	public String getJobTitle() {
	return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
	this.jobTitle = jobTitle;
	}

	public String getCompany() {
	return company;
	}

	public void setCompany(String company) {
	this.company = company;
	}

	public abstract double getAnnualSalary();
}
