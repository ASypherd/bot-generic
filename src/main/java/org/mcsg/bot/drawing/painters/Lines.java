package org.mcsg.bot.drawing.painters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;

import org.mcsg.bot.drawing.AbstractPainter;
import org.mcsg.bot.drawing.ImageTools;
import org.mcsg.bot.util.MapWrapper;

public class Lines extends AbstractPainter{

	int r=rand.nextInt(255);
	int gc=rand.nextInt(255);
	int b=rand.nextInt(255);
	int a=rand.nextInt(255);

	int x1 = rand.nextInt(width);
	int x2 = rand.nextInt(width);

	int y1 = rand.nextInt(height);
	int y2 = rand.nextInt(height);


	public Lines(BufferedImage img) {
		super(img);
	}


	public void paint(MapWrapper args) {		
		g.setStroke(new BasicStroke(args.getInt("stroke-size", 2)));

		boolean limit = args.getBoolean("limit", rand.nextBoolean());
		int stop = args.getInt("amount", rand.nextInt(40000)) + args.getInt("amount-base", 10000);
		for(int aa = 0; aa < stop; aa++){


			g.setColor(randIncColor(g.getColor(), 7));

			x1 += rand.nextInt(13) - 6;
			x2 += rand.nextInt(13) - 6;
			y1 += rand.nextInt(13) - 6;
			y2 += rand.nextInt(13) - 6;

			if(limit){
				x1 = ImageTools.limit(x1, width, 0);
				x2 = ImageTools.limit(x2, width, 0);
				y1 = ImageTools.limit(y1, height, 0);
				y2 = ImageTools.limit(y2, height, 0);
			}
			g.drawLine(x1, y1, x2, y2);
		}

	}
}
