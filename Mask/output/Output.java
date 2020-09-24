package output;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import exceptions.ImageWriteException;
import javax.imageio.ImageIO;

public class Output
{
	public static void writeEncryptedImages(BufferedImage randomBlackWhiteImage,
			BufferedImage encryptedBlackWhiteImage, String filePathWithoutFormatName, String formatName)
					throws ImageWriteException
	{  
        try
        {
        	File output1 = new File(filePathWithoutFormatName + "1." + formatName);
            ImageIO.write(randomBlackWhiteImage, formatName, output1);
            
            File output2 = new File(filePathWithoutFormatName + "2." + formatName);
            ImageIO.write(encryptedBlackWhiteImage, formatName, output2);
        }
        catch (IOException exception)
        {
        	exception.printStackTrace();
        	throw new ImageWriteException();
        }
	}
}
