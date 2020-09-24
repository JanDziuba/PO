package input;

import java.util.Scanner;
import java.util.ArrayList;

public class Input
{
	private ArrayList<Integer> input;
	
	public Input()
	{
		Scanner scanner = new Scanner(System.in);
		
		int numberOfZeros = 0;
		int numberOfNonZeros = 0;
		int inputNumber;
		
		this.input = new ArrayList<Integer>();
		
		while(numberOfZeros != (numberOfNonZeros + 1))
		{
			inputNumber = scanner.nextInt();
			input.add(inputNumber);
			
			if (inputNumber == 0)
			{
				numberOfZeros++;
			}
			else
			{
				numberOfNonZeros++;
			}
		}
		
		scanner.close();
		
	}
	
	public ArrayList<Integer> getInput()
	{
		return input;
	}
}
