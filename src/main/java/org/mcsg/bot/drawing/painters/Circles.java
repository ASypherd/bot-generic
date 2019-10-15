package org.mcsg.bot.drawing.painters;

import java.awt.BasicStroke;
import java.awt.image.BufferedImage;

import org.mcsg.bot.drawing.AbstractPainter;
import org.mcsg.bot.util.MapWrapper;

public class Circles extends AbstractPainter {

	public Circles(BufferedImage img) {
		super(img);
	}

	@Override
	public void paint(MapWrapper args) {


		g.setStroke(new BasicStroke(4));

		for(int a = 0; a < rand.nextInt(20) + 10; a++){ 
			setRandomColor(true);
			int size = rand.nextInt(450) + 100;
			int x = rand.nextInt(width) ;
			int y = rand.nextInt(height);
			
			g.fillOval(x, y, size, size);
			g.setColor(g.getColor().brighter());
			g.drawOval(x, y, size, size);
		}
		
	}

}
