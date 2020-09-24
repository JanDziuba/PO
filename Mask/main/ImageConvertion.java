package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageConvertion
{
	public static BufferedImage convertImageToBlackWhite(BufferedImage inputImage)
	{
		BufferedImage blackWhiteImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);
		
		Graphics2D graphic = blackWhiteImage.createGraphics();
		graphic.drawImage(inputImage, 0, 0, Color.WHITE, null);
		graphic.dispose();
		
		return blackWhiteImage;
	}

}
