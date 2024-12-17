package Sum_Of_Array_Numbers;

import java.util.Arrays;
import java.util.List;

public class SumOfArrayNumbers {

	public static void main(String[] args) {
		
		List<Integer> nums = Arrays.asList(1,2,4,5,7,9,12,34,3);
		
		int sum =nums.stream().mapToInt(Integer::intValue).sum();
		
		System.out.println("Total Array Numbers Sum Is-: "+sum);
		System.out.println("=======================================================");
		
		int max = nums.stream().mapToInt(Integer::intValue).max().orElseThrow();
		
		System.out.println("Maximum Number Is-: "+max);
//		if(max.isPresent())
//		{
//			System.out.println("Maximum Number Is-: "+max.getAsInt());
//		}
	}
	
}
