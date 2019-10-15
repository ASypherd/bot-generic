package org.mcsg.bot.drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.mcsg.bot.util.MapWrapper;

public abstract class AbstractPainter implements Painter {

	protected BufferedImage img;
	protected Graphics2D g;
	protected static Random rand = new Random();

	protected int width;
	protected int height;

	public AbstractPainter(BufferedImage img){
		this.img = img;

		this.height = img.getHeight();
		this.width = img.getWidth();
		this.g = (Graphics2D)img.getGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	}

	@Override
	public abstract void paint(MapWrapper args);

	public void setBackground(Color color) {
		Color bcolor = g.getColor();

		g.setColor(color);
		g.fillRect(0, 0, width, height);

		g.setColor(bcolor);
	}

	public Color getRandomColor(boolean a){
		return new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255),!a ? 255 : rand.nextInt(255));
	}

	public void setRandomColor(boolean a){
		g.setColor(getRandomColor(a));
	}

	public void drawCircle(int x, int y, int size){
		g.fillOval(x - size /2 , y - size /2, size, size);
	}

	public Color brigher(Color color, int inc){
		return incColor(color, inc, inc, inc, 0);
	}

	public Color darker(Color color, int inc){
		return incColor(color, -inc, -inc, -inc, 0);
	}

	public Color incColor(Color color, int rinc, int ginc, int binc, int ainc){
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		int a = color.getAlpha();
		r += rinc;
		b += binc;
		g += ginc;
		a += ainc;
		r = ImageTools.limit(r, 255, 0); 
		b = ImageTools.limit(b, 255, 0); 
		g = ImageTools.limit(g, 255, 0); 
		a = ImageTools.limit(a, 255, 0); 
		return new Color(r, g, b, a);
	}

	public Color randIncColor(Color color, int inc){
		return randIncColor(color, inc, true);
	}
	
	public Color randIncColor(Color color, int inc, boolean alpha){
		int r = (int) (rand.nextDouble() * (inc * 2) - inc);
		int g = (int) (rand.nextDouble() * (inc * 2) - inc);
		int b = (int) (rand.nextDouble() * (inc * 2) - inc);
		int a = 0;
		if(alpha) {
			a = (int) (rand.nextDouble() * (inc * 2) - inc);
		} 

		return incColor(color, r,g, b, a);
	}

}

