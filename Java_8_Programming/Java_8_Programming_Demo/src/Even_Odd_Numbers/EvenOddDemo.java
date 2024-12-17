package Even_Odd_Numbers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EvenOddDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Integer> nums = Arrays.asList(1,2,4,5,7,9,12,34,3);
		
		List even =nums.stream().filter(n->n%2==0).collect(Collectors.toList());
		
		even.forEach(n->System.out.println(n));
		
		System.out.println("=============================================================");
		
        List odd =nums.stream().filter(n->n%2!=0).collect(Collectors.toList());
		
		odd.forEach(n->System.out.println(n));
		
	}

}
