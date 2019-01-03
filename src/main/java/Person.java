public class Person implements Comparable<Person> {

private	String firstName;
private	String lastName;
private	String gender;
private	int age;
private	String city;
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", age=" + age
				+ ", city=" + city + "]";
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public int compareTo(Person o) {
		
		if( this.age < o.age ) {
			
			return -1;
		} else if ( this.age > o.age) {
			return 1;
			
		}
		
		return 0;
	}
	
	
	
	
}
