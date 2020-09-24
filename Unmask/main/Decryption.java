package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import exceptions.FilePathException;
import exceptions.ImageReadException;
import exceptions.ImageWriteException;
import input.InputImage;
import output.Output;

public class Decryption
{
	public static void main(String[] args)
	{
		try
		{
			BufferedImage inputImage1 = InputImage.getImage(args[0]);
			BufferedImage inputImage2 = InputImage.getImage(args[1]);
			
			BufferedImage decryptedImage = decryptImage(inputImage1, inputImage2);
			
			String formatName = InputImage.getFormatName(args[0]);
			String filePathWithoutFormatName = InputImage.getFilePathWithoutFormatName(args[0]);
			
			Output.writeDecryptedImage(decryptedImage, filePathWithoutFormatName, formatName);
		}
		catch (ImageReadException exception)
		{
			System.out.println("Failed to read the image");
		}
		catch (FilePathException exception)
		{
			System.out.println("Wrong file path");
		}
		catch (ImageWriteException exception)
		{
			System.out.println("Failed to write the image");
		}
	}
	
	public static BufferedImage decryptImage(BufferedImage inputImage1, BufferedImage inputImage2)
	{
		BufferedImage decryptedImage = new BufferedImage(inputImage1.getWidth(), inputImage1.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);
		
		int white = new Color(255,255,255).getRGB();
		int black = new Color(0,0,0).getRGB();
		
		for (int width = 0; width < inputImage1.getWidth(); width++)
    	{
    		for (int height = 0; height < inputImage1.getHeight(); height++)
    		{
    			if (inputImage1.getRGB(width, height) == black)
    			{
    				decryptedImage.setRGB(width, height, black);
    			}
    			else if (inputImage2.getRGB(width, height) == black)
    			{
    				decryptedImage.setRGB(width, height, black);
    			}
    			else
    			{
    				decryptedImage.setRGB(width, height, white);
    			}
    		}
    	}
		return decryptedImage;
	}
}
