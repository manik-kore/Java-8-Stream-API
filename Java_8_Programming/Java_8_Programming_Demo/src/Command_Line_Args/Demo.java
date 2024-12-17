package Command_Line_Args;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
   
		if(args.length>0)
		{
			System.out.println("Enter Arguments-: ");
			//int[] nums=new int[args.length];
			for(String arg:args)
			{
				System.out.println(arg);
				
				
			}
		}else
		{ 
			System.out.println("No command line arguments provided."); 
	    }
		
	}

}
