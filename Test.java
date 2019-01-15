import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Test {
	
	public static void main(String[] args) throws IOException
	{
		int h,w;
		BufferedImage img=null;
		  
	
		img=ImageIO.read(new File("C:\\Users\\Rakib bhai\\Downloads\\datafile\\ibtd\\Mask\\0000.bmp"));
		h=img.getHeight();
		w=img.getWidth();
		System.out.println("height "+h+" wid "+w);
		for(int i=0;i<w;i++)
			for(int j=0;j<h;j++)
			{
				int pixel= img.getRGB(i, j);
				printPixel(pixel);
			}
	}
	public static String printPixel(int pixel)
	{
		int red,green,blue,sk,nSk;
		String s = null;
		red=(pixel >> 16) & 0xff;
		green=(pixel >> 8) & 0xff;
		blue=(pixel) & 0xff;
		s=red+","+green+","+blue;
		if((red > 220) && (green > 220) && (blue > 220))  
		
		System.out.println(red+"    "+green+"     "+"   "+blue);
	}

}
