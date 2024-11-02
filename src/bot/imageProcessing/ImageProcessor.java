package bot.imageProcessing;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;

import exceptions.ImageProcessorException;
import recording.areaselection.AreaSelection;


public class ImageProcessor implements Runnable{
	
	private Robot robot = null;
	private int[][] img2D = null;
	
	public ImageProcessor(Robot robot) {
		
		this.robot = robot;
		
	}
	public void captureSelection(AreaSelection sel) {
		
		BufferedImage img = robot.createScreenCapture(sel.getRectangle());
		img2D = convertTo2D(img);
		
	}
	
	public void captureFullScreen() {
		
		BufferedImage img = robot.createScreenCapture(new Rectangle(0, 0, (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		img2D = convertTo2D(img);
		
	}
	
	public int[][] getCroppedImage(AreaSelection sel) {
		
		int[][] newImg2D = new int[sel.getWidth()][sel.getHeight()];
		
		for(int i = 0; i < newImg2D.length; i++)
			for(int j = 0; j < newImg2D[i].length; j++)
				newImg2D[i][j] = img2D[sel.getX()+i][sel.getY()+j];
		
		return newImg2D;
		
	}
	
	public boolean compareImages(ImageInstance otherImg) throws ImageProcessorException {
		
		if(img2D == null)
			throw new ImageProcessorException("Could not compare images: Primary image is null");
		if(otherImg == null)
			throw new ImageProcessorException("Could not compare images: Passed image is null");
		
		/*
	    if (img.getWidth() == otherImg.getWidth() && img.getHeight() == otherImg.getHeight()) {
	    	
	        for (int x = 0; x < img.getWidth(); x++) 
	            for (int y = 0; y < img.getHeight(); y++) 
	                if (img.getRGB(x, y) != otherImg.getRGB(x, y))
	                    return false;
	        
	    } else
	   */
	    	return false;
	    
	    //return true;
	    
	}
	
	/*
	public int compareImagesPercent(BufferedImage otherImg) throws ImageProcessorException {
		
		if(img == null)
			throw new ImageProcessorException("Could not compare images: Primary image is null");
		if(otherImg == null)
			throw new ImageProcessorException("Could not compare images: Passed image is null");
		
		int currentGrade = 0, maxGrade = 0;
	    if (img.getWidth() == otherImg.getWidth() && img.getHeight() == otherImg.getHeight()) {
	    	
	    	maxGrade = img.getWidth()*img.getHeight();
	        for (int x = 0; x < img.getWidth(); x++)
	            for (int y = 0; y < img.getHeight(); y++) 
	                if (img.getRGB(x, y) != otherImg.getRGB(x, y))
	                    currentGrade++;
	        
	    } else
	    	
	    	return -1;
	    
	    return currentGrade/maxGrade;
	    
	}
	*/
	/*
	public boolean compareCroppedImages(BufferedImage otherImg) {
		
		return false;
		
	}
	*/
	
	public int[][] locatePixel(int pixelValue) {
		
		ArrayList<int[]> collection = new ArrayList<int[]>();
		
		for(int i = 0; i < img2D.length; i++) 
			for(int j = 0; j < img2D[i].length; j++) 
				if(img2D[i][j] == pixelValue)
					collection.add(new int[] {i, j});
		
		return (int[][])collection.toArray();
		
	}
	
	private int[][] convertTo2D(BufferedImage image) {

	      final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	      final int width = image.getWidth();
	      final int height = image.getHeight();
	      final boolean hasAlphaChannel = image.getAlphaRaster() != null;

	      int[][] result = new int[height][width];
	      if (hasAlphaChannel) {
	         final int pixelLength = 4;
	         for (int pixel = 0, row = 0, col = 0; pixel + 3 < pixels.length; pixel += pixelLength) {
	            int argb = 0;
	            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
	            argb += ((int) pixels[pixel + 1] & 0xff); // blue
	            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
	            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
	            result[row][col] = argb;
	            col++;
	            if (col == width) {
	               col = 0;
	               row++;
	            }
	         }
	      } else {
	         final int pixelLength = 3;
	         for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
	            int argb = 0;
	            argb += -16777216; // 255 alpha
	            argb += ((int) pixels[pixel] & 0xff); // blue
	            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
	            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
	            result[row][col] = argb;
	            col++;
	            if (col == width) {
	               col = 0;
	               row++;
	            }
	         }
	      }

	      return result;
	 }

	@Override
	public void run() {
		
		while(true) {
			
			try {
				
				Thread.sleep(17);
				
			} catch (InterruptedException e) {
				
				System.out.println(e.getMessage());
				
			}
			
		}
		
	}
}
