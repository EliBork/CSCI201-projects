public class Person {
protected String firstName;

protected String lastName;

protected String birthDate;


public Person() {
}

public Person(String firstName, String lastName, String birthDate) {

this.firstName = firstName;
this.lastName = lastName;
this.birthDate = birthDate;
}

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}

public String getBirthdate() {
return birthDate;
}

public void setBirthDate(String birthDate) {
this.birthDate = birthDate;
}

}
