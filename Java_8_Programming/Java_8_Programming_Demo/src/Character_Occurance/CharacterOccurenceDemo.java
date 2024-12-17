package Character_Occurance;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterOccurenceDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s="abaacccdfccaaacc";
		
		Character mostFrequent = s.chars()
				.mapToObj(c->(char)c)
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
				.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey)
				.orElse(null);
		
		System.out.println(mostFrequent);
		
		
		
	}

}
