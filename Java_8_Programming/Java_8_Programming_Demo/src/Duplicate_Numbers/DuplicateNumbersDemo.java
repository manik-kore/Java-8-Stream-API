package Duplicate_Numbers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateNumbersDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Integer> nums = Arrays.asList(1,2,4,6,7,8,2,4,7,2,2);
		
		Map<Integer, Long>duplicates = nums.stream()
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
				.entrySet().stream()
				.filter(entry->entry.getValue()>1)
				.collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
		
		System.out.println(duplicates);
		
		System.out.println("============================================================");
		
		List<String> str = Arrays.asList("Akash","Meena","Ajay","Rani","Akash","Rani");
		
		Map<String,Long> duplicateNames =str.stream()
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
				.entrySet().stream()
				.filter(entry->entry.getValue()>1)
				.collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue ));
		System.out.println("Duplicate Names Are-: "+duplicateNames);
		
		
	}

}
