package org.mcsg.bot.drawing.painters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;

import org.mcsg.bot.drawing.AbstractPainter;
import org.mcsg.bot.drawing.ImageTools;
import org.mcsg.bot.util.MapWrapper;

public class Curves extends AbstractPainter{

	public Curves(BufferedImage img) {
		super(img);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(MapWrapper args) {
		g.setStroke(new BasicStroke(args.getInt("stroke-size", 2)));

		for(int u = 0; u < rand.nextInt(3) +1 ; u++) {
			int r=rand.nextInt(255);
			int gc=rand.nextInt(255);
			int b=rand.nextInt(255);
			int a=rand.nextInt(255);

			int x1 = rand.nextInt(width);
			int x2 = rand.nextInt(width);

			int y1 = rand.nextInt(height);
			int y2 = rand.nextInt(height);

			int ang1 = rand.nextInt(720) - 360;
			int ang2 = rand.nextInt(720) - 360;


			boolean limit = args.getBoolean("limit", rand.nextBoolean());
			int stop = args.getInt("amount", rand.nextInt(40000)) + args.getInt("amount-base", 10000);
			for(int aa = 0; aa < stop; aa++){


				g.setColor(randIncColor(g.getColor(), 7));

				x1 += rand.nextInt(13) - 6;
				x2 += rand.nextInt(13) - 6;
				y1 += rand.nextInt(13) - 6;
				y2 += rand.nextInt(13) - 6;

				ang1 += rand.nextInt(13) - 6;
				ang2 += rand.nextInt(13) - 6;
				if(limit){
					x1 = ImageTools.limit(x1, width, 0);
					x2 = ImageTools.limit(x2, width, 0);
					y1 = ImageTools.limit(y1, height, 0);
					y2 = ImageTools.limit(y2, height, 0);
				}
				g.drawArc(x1, y1, x2, y2, ang1, ang2);
			}
		}
		//		for(int a = 0; a < 100; a++) {
		//
		//			int startX=rand.nextInt(width);
		//			int startY=rand.nextInt(height);
		//
		//			int endX=rand.nextInt(width);
		//			int endY=rand.nextInt(height);
		//			g.setStroke(new BasicStroke(3));
		//
		//			
		//			g.drawArc(startX, startY, endX, endY, rand.nextInt(720) - 360, rand.nextInt(720) - 360);
		//
		////			int bezierX=rand.nextInt(500) - 250;
		////			int bezierY=rand.nextInt(500) - 250;
		////			
		////			int px = startX, py = startY;
		////			
		////			g.setColor(Color.white);
		////			for(double t=0.0;t<=1;t+=0.01)
		////			{
		////				
		////				int x = (int) (  (1-t)*(1-t)*startX + 2*(1-t)*t*bezierX+t*t*endX);
		////				int y = (int) (  (1-t)*(1-t)*startY + 2*(1-t)*t*bezierY+t*t*endY);
		////
		////				
		////				
		////				System.out.println(x + " : "+ y); 
		////
		////				g.drawLine(px, py, x, y);
		////				px = x; py = y;
		////
		////			}
		//		}

	}

}
