package Stream_API_Functions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFunctionDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> str = Arrays.asList("Akash","Meena","Ajay","Rani","Ram","Smita");
		
		List<String> startsWith = startsWithName(str,'A');
		System.out.println("Names Starts With Character A-: "+startsWith);
		
	}

	public static List<String> startsWithName(List<String> names,char c)
	{
		return names.stream().filter(n->n.startsWith(String.valueOf(c))).collect(Collectors.toList());
	}
}
