package main;

import java.awt.image.BufferedImage;
import input.InputImage;
import output.Output;
import exceptions.ImageReadException;
import exceptions.FilePathException;
import exceptions.ImageWriteException;


public class Main
{

	public static void main(String[] args)
	{
		try
		{
			BufferedImage inputImage = InputImage.getImage(args[0]);
			BufferedImage blackWhiteImage = ImageConvertion.convertImageToBlackWhite(inputImage);
			BufferedImage randomBlackWhiteImage = ImageEncryption.generateRandomBlackWhiteImage(blackWhiteImage);
			BufferedImage encryptedBlackWhiteImage = ImageEncryption.generateEncryptedBlackWhiteImage(blackWhiteImage,
					randomBlackWhiteImage);
			
			String formatName = InputImage.getFormatName(args[0]);
			String filePathWithoutFormatName = InputImage.getFilePathWithoutFormatName(args[0]);
			
			Output.writeEncryptedImages(randomBlackWhiteImage, encryptedBlackWhiteImage, filePathWithoutFormatName,
					formatName);	
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

}
