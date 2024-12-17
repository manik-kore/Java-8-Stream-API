package Comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class ComaratorDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Employee>employee = Arrays.asList(
				
				new Employee(24,"Vishal","Pune","ajay@gmail.com"),
				new Employee(26,"Sakshi","Mumbai","sakshi@gmail.com"),
				new Employee(32,"Ram","Satara","ram@gmail.com"),
				new Employee(22,"Punam","Pune","punam@gmail.com"),
				new Employee(25,"Akash","Sangli","akash@gmail.com")
				
				);
		
		List<String>words =Arrays.asList("Akash","Meenakshi","Veena","Ajay");
		
		// Employees Sort By Names
		List<Employee> sortByNames = employee.stream()
				.sorted(Comparator.comparing(Employee::getName)).collect(Collectors.toList());
		
		// Employees Sort By Age In Descending Order
		List<Employee> sortByAge = employee.stream()
				.sorted(Comparator.comparing(Employee::getAge).reversed()).collect(Collectors.toList());
		
		// Second Highest Age Of Employee
		Employee secondHighestAge = employee.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).skip(1).findFirst().get();
		
//		Optional longWord = words.stream()
//				.max(Comparator.comparing(String::length));
		
		Optional longWord = words.stream()
				.sorted(Comparator.comparing(String::length).reversed()).skip(1)
				.findFirst();
		
		
		
		System.out.println(sortByNames);
		sortByNames.forEach(e->System.out.println(e));
		
		System.out.println("================================================================");
		
		System.out.println(secondHighestAge);
		
		
        System.out.println("================================================================");
		
		System.out.println("Second Highest Age Of Employee Is -:"+secondHighestAge);
		
        System.out.println("================================================================");
		
		
		if(longWord.isPresent())
		{
			System.out.println("Longest Word Is -:"+longWord.get());
		}
		
		
		
	}

}
