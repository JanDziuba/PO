package output;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import exceptions.ImageWriteException;

public class Output
{
	public static void writeDecryptedImage(BufferedImage decryptedImage, String filePathWithoutFormatName,
			String formatName) throws ImageWriteException
	{
        try
        {
        	File output1 = new File(filePathWithoutFormatName + "_decrypted." + formatName);
            ImageIO.write(decryptedImage, formatName, output1);
        }
        catch (IOException exception)
        {
        	exception.printStackTrace();
        	throw new ImageWriteException();
        }
	}
}
