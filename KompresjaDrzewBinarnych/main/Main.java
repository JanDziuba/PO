package main;

import input.Input;
import output.Output;

public class Main {

	public static void main(String[] args)
	{
		Input input = new Input();
		
		Output output;
		
		output = Compression.compress(input.getInput());
		
	    Output.writeOutput(output);

	}

}
