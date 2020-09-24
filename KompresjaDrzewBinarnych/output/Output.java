package output;

import java.util.ArrayList;

public class Output
{
	private ArrayList<Integer> output;
	
	public static void writeOutput(Output output)
	{
		for (int i = 0; i < output.output.size(); i++)
		{
	        System.out.println(output.output.get(i));
	    }
	}
	
	public Output()
	{
		this.output = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getOutput()
	{
		return output;
	}
}
