package main;

import java.util.ArrayList;


import output.Output;

public class Compression
{
	
	public static Output compress(ArrayList<Integer> input)
	{	
		boolean[] isTheInputNodeInTheOutput = new boolean[input.size()];
		int[] indexOfTheInputNodeInTheOutput = new int[input.size()];
		int indexOfTheOutput = 0;
		ArrayList<Integer> subTree;
		int firstOccurenceOFTheSubTreeInTheTree;
		int indexOfTheInput = 0;
		
		
		Output output = new Output();
		
		while (indexOfTheInput < input.size())
		{
			if (input.get(indexOfTheInput) == 0)
			{
				isTheInputNodeInTheOutput[indexOfTheInput] = true;	
				indexOfTheInputNodeInTheOutput[indexOfTheInput] = indexOfTheOutput;
				output.getOutput().add(0);
				indexOfTheOutput++;
				indexOfTheInput++;
			}
			else
			{
				subTree = getTheSubTreeArrayList(input, indexOfTheInput);
				
				firstOccurenceOFTheSubTreeInTheTree = showFirstOccurenceOFTheSubTreeInTheTree(input, indexOfTheInput, subTree);
				if (firstOccurenceOFTheSubTreeInTheTree < input.size())
				//poddrzewo jest wczesniej w drzewie
				{
					isTheInputNodeInTheOutput[indexOfTheInput] = false;
					output.getOutput().add((indexOfTheInputNodeInTheOutput[firstOccurenceOFTheSubTreeInTheTree] - indexOfTheOutput));
					indexOfTheOutput++;
					indexOfTheInput += subTree.size();
					
				}
				//poddrzewa nie ma wczesniej w drzewie
				else
				{
					isTheInputNodeInTheOutput[indexOfTheInput] = true;
					indexOfTheInputNodeInTheOutput[indexOfTheInput] = indexOfTheOutput;
					output.getOutput().add(input.get(indexOfTheInput));
					indexOfTheOutput++;
					indexOfTheInput++;
				}
			}
		}
	
		return output;
	}
	
	public static ArrayList<Integer> getTheSubTreeArrayList(ArrayList<Integer> input, int indexOfTheInput)
	{
		ArrayList<Integer> subTree = new ArrayList<Integer>();
		
		int numberOfZeros = 0;
		int numberOfNonZeros = 0;
		
		while(numberOfZeros != (numberOfNonZeros + 1))
		{
			subTree.add(input.get(indexOfTheInput));
			
			if(input.get(indexOfTheInput) == 0)
			{
				numberOfZeros++;
			}
			else
			{
				numberOfNonZeros++;
			}
			
			indexOfTheInput++;
		}
		
		return subTree;
	}
	
	public static int showFirstOccurenceOFTheSubTreeInTheTree(ArrayList<Integer> input, int indexOfTheInput, ArrayList<Integer> subTree)
	{
		// Algorytm na podstawie https://pl.wikipedia.org/wiki/Algorytm_Knutha-Morrisa-Pratta
		
		int subTreeIndex = 0;
		int treeIndex = 0;
		
		ArrayList<Integer> partialMatchArray = new ArrayList<Integer>();
		
		buildPartialMatchArray(subTree, partialMatchArray);
		
		while ((treeIndex + subTreeIndex) < indexOfTheInput)
		{
			if (subTree.get(subTreeIndex) == input.get(treeIndex + subTreeIndex))
			{
				subTreeIndex++;
				
				if (subTreeIndex == subTree.size())
				{
					return treeIndex;
				}
			}
			else
			{
				treeIndex = (treeIndex + subTreeIndex - partialMatchArray.get(subTreeIndex));
				
				if (subTreeIndex > 0)
				{
					subTreeIndex = partialMatchArray.get(subTreeIndex);
				}
			}
		}
		
		return input.size();		
	}
	
	public static void buildPartialMatchArray(ArrayList<Integer> subTree, ArrayList<Integer> partialMatchArray)
	{
		int subTreeIndex = 0;
		int partialMatchArrayIndex = 2;
		
		partialMatchArray.add(-1);
		partialMatchArray.add(0);
		
		while (partialMatchArrayIndex < subTree.size())
		{
			if (subTree.get(partialMatchArrayIndex - 1) == subTree.get(subTreeIndex))
			{
				partialMatchArray.add(subTreeIndex + 1);
				partialMatchArrayIndex++;
				subTreeIndex++;
			}
			else if (subTreeIndex > 0)
			{
				subTreeIndex = partialMatchArray.get(subTreeIndex);
			}
			else
			{
				partialMatchArray.add(0);
				partialMatchArrayIndex++;
			}			
		}	
	}
}
