package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;

public class ImageEncryption
{   
    public static BufferedImage generateRandomBlackWhiteImage(BufferedImage blackWhiteImage)
    {
    	BufferedImage randomBlackWhiteImage = new BufferedImage((blackWhiteImage.getWidth() * 2),
    			(blackWhiteImage.getHeight() * 2), BufferedImage.TYPE_BYTE_BINARY);
    	
    	int black = new Color(0,0,0).getRGB();
    	int white = new Color(255,255,255).getRGB();
    		
    	SecureRandom random = new SecureRandom();
    	
    	for (int width = 0; width < blackWhiteImage.getWidth(); width++)
    	{
    		for (int height = 0; height < blackWhiteImage.getHeight(); height++)
    		{
    			int randomFrom0To5 = random.nextInt(6);
    	    	
    	    	switch (randomFrom0To5)
    	    	{
    	    		case 0:
    	    			randomBlackWhiteImage.setRGB((2 * width), (2 * height), white);
    	    			randomBlackWhiteImage.setRGB(((2 * width) + 1), (2 * height), white);
    	    			randomBlackWhiteImage.setRGB((2 * width), ((2 * height) + 1), black);
    	    			randomBlackWhiteImage.setRGB(((2 * width) + 1), ((2 * height) + 1), black);
    	    			break;
    	    		case 1:
    	    			randomBlackWhiteImage.setRGB((2 * width), (2 * height), white);
    	    			randomBlackWhiteImage.setRGB(((2 * width) + 1), (2 * height), black);
    	    			randomBlackWhiteImage.setRGB((2 * width), ((2 * height) + 1), white);
    	    			randomBlackWhiteImage.setRGB(((2 * width) + 1), ((2 * height) + 1), black);
    	    			break;
    	    		case 2:
    	    			randomBlackWhiteImage.setRGB((2 * width), (2 * height), white);
    	    			randomBlackWhiteImage.setRGB(((2 * width) + 1), (2 * height), black);
    	    			randomBlackWhiteImage.setRGB((2 * width), ((2 * height) + 1), black);
    	    			randomBlackWhiteImage.setRGB(((2 * width) + 1), ((2 * height) + 1), white);
    	    			break;
    	    		case 3:
    	    			randomBlackWhiteImage.setRGB((2 * width), (2 * height), black);
    	    			randomBlackWhiteImage.setRGB(((2 * width) + 1), (2 * height), white);
    	    			randomBlackWhiteImage.setRGB((2 * width), ((2 * height) + 1), black);
    	    			randomBlackWhiteImage.setRGB(((2 * width) + 1), ((2 * height) + 1), white);
    	    			break;
    	    		case 4:
    	    			randomBlackWhiteImage.setRGB((2 * width), (2 * height), black);
    	    			randomBlackWhiteImage.setRGB(((2 * width) + 1), (2 * height), black);
    	    			randomBlackWhiteImage.setRGB((2 * width), ((2 * height) + 1), white);
    	    			randomBlackWhiteImage.setRGB(((2 * width) + 1), ((2 * height) + 1), white);
    	    			break;
    	    		case 5:
    	    			randomBlackWhiteImage.setRGB((2 * width), (2 * height), black);
    	    			randomBlackWhiteImage.setRGB(((2 * width) + 1), (2 * height), white);
    	    			randomBlackWhiteImage.setRGB((2 * width), ((2 * height) + 1), white);
    	    			randomBlackWhiteImage.setRGB(((2 * width) + 1), ((2 * height) + 1), black);
    	    			break;
    	    	}
    		}
    	}
    	return randomBlackWhiteImage;  	
    }
    
    public static BufferedImage generateEncryptedBlackWhiteImage(BufferedImage blackWhiteImage,
    		BufferedImage randomBlackWhiteImage)
    {
    	BufferedImage encryptedBlackWhiteImage = new BufferedImage((blackWhiteImage.getWidth() * 2),
    			(blackWhiteImage.getHeight() * 2), BufferedImage.TYPE_BYTE_BINARY);
    	
    	int white = new Color(255,255,255).getRGB();
    	
    	for (int width = 0; width < blackWhiteImage.getWidth(); width++)
    	{
    		for (int height = 0; height < blackWhiteImage.getHeight(); height++)
    		{
    			if (blackWhiteImage.getRGB(width, height) == white)
    			{
    				encryptedBlackWhiteImage.setRGB((2 * width), (2 * height),
    						randomBlackWhiteImage.getRGB((2 * width), (2 * height)));
    				encryptedBlackWhiteImage.setRGB(((2 * width) + 1), (2 * height),
    						randomBlackWhiteImage.getRGB(((2 * width) + 1), (2 * height)));
    				encryptedBlackWhiteImage.setRGB((2 * width), ((2 * height) + 1),
    						randomBlackWhiteImage.getRGB((2 * width), ((2 * height) + 1)));
    				encryptedBlackWhiteImage.setRGB(((2 * width) + 1), ((2 * height) + 1),
    						randomBlackWhiteImage.getRGB(((2 * width) + 1), ((2 * height) + 1)));
    			}
    			else
    			{
    				encryptedBlackWhiteImage.setRGB((2 * width), (2 * height),
    						returnOppositeColor(randomBlackWhiteImage.getRGB((2 * width), (2 * height))));
    				encryptedBlackWhiteImage.setRGB(((2 * width) + 1), (2 * height),
    						returnOppositeColor(randomBlackWhiteImage.getRGB(((2 * width) + 1), (2 * height))));
    				encryptedBlackWhiteImage.setRGB((2 * width), ((2 * height) + 1),
    						returnOppositeColor(randomBlackWhiteImage.getRGB((2 * width), ((2 * height) + 1))));
    				encryptedBlackWhiteImage.setRGB(((2 * width) + 1), ((2 * height) + 1),
    						returnOppositeColor(randomBlackWhiteImage.getRGB(((2 * width) + 1), ((2 * height) + 1))));
    			}
    		}
    	}
    	return encryptedBlackWhiteImage;
    }
    
    private static int returnOppositeColor(int color)
    {
    	if(color == new Color(0,0,0).getRGB())
    	{
    		return new Color(255,255,255).getRGB();
    	}
    	else
    	{
    		return new Color(0,0,0).getRGB();
    	}
    }
}
