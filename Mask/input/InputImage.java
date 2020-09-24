package input;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import exceptions.ImageReadException;
import exceptions.FilePathException;

public class InputImage
{	
	public static BufferedImage getImage(String filePath) throws ImageReadException
	{
		try
		{
			File imageFile = new File(filePath);
			BufferedImage image = ImageIO.read(imageFile);
			return image;
		}
		catch (IOException exception)
	    {
	       exception.printStackTrace();
	       throw new ImageReadException();
	    }
	}
	
	public static String getFormatName(String filePath) throws FilePathException
	{
		int lastPeriod = filePath.lastIndexOf(".");
		StringBuilder formatName = new StringBuilder();
		
		if (lastPeriod < 0)
		{
			FilePathException exception = new FilePathException();
			exception.printStackTrace();
			throw exception;
		}
		
		for (int index = (lastPeriod + 1); index < filePath.length(); index++)
		{
			formatName.append(filePath.charAt(index));
		}
		
		return formatName.toString();
	}
	
	public static String getFilePathWithoutFormatName(String filePath) throws FilePathException
	{
		int lastPeriod = filePath.lastIndexOf(".");
		StringBuilder filePathWithoutFormatName = new StringBuilder();
		
		if (lastPeriod < 0)
		{
			FilePathException exception = new FilePathException();
			exception.printStackTrace();
			throw exception;
		}
		
		for (int index = 0; index < lastPeriod; index++)
		{
			filePathWithoutFormatName.append(filePath.charAt(index));
		}
		
		return filePathWithoutFormatName.toString();
	}
}
