package Comparator;

public class Employee {

	
	private int age;
	private String name;
	private String email;
	private String city;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int age, String name, String email, String city) {
		super();
		this.age = age;
		this.name = name;
		this.email = email;
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Employee [age=" + age + ", name=" + name + ", email=" + email + ", city=" + city + "]";
	}
	
	
	
}
