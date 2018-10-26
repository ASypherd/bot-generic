package org.mcsg.bot.drawing.painters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import org.mcsg.bot.drawing.AbstractPainter;
import org.mcsg.bot.drawing.ImageTools;
import org.mcsg.bot.drawing.painters.shapes.Circle;
import org.mcsg.bot.util.MapWrapper;

public class Vaporwave extends AbstractPainter {


	//determines whether or not the picture has a landscape (not used in this version)
	boolean hasLandscape = true;

	int maxGridHeight = rand.nextInt(height / 4) + (height / 2);

	//Circle sun = new Circle();

	public Vaporwave(BufferedImage img) {
		super(img);
	}

	@Override
	public void paint(MapWrapper args) {

		drawSun();
		int gridLine = drawGrid();

		if(hasLandscape == true) {
			drawLandscape(args, gridLine);
		}

	}


	private void drawLandscape(MapWrapper args, int gridLine) {


		//AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.DST_IN, 0);

		//g.setComposite(composite);


		int landscapeBottom = gridLine;
		int landscapeTop = maxGridHeight - (rand.nextInt(height - gridLine));
		int landscapeReturn = maxGridHeight + rand.nextInt((height - landscapeTop) / 2);

		g.setStroke(new BasicStroke(4));
		g.setColor(Color.WHITE);

		int x = -10;
		int y = landscapeTop + rand.nextInt(landscapeBottom - landscapeTop);
		int startY = y;
		int nextX = x;
		int nextY = y;


		/*
		int[] ridgeX = new int[200];
		int[] ridgeY = new int[200];
		int ridgeNPoints = 0;

		int[] valleyX = new int[200];
		int[]valleyY = new int[200];
		int valleyNPoints = 0;

		 */



		Polygon landscape = new Polygon();

		landscape.addPoint(x, y);


		/*
		ridgeX[ridgeNPoints] = x;
		ridgeY[ridgeNPoints] = y;
		 */



		while(x <= width) {

			nextX = x + rand.nextInt(20);
			nextY = y + rand.nextInt(20) - 10;

			if(nextY < landscapeTop) {
				nextY = y + (Math.abs(nextY - landscapeTop));
			} else if (nextY > landscapeBottom) {
				nextY = y - (nextY - landscapeBottom);
			}

			landscape.addPoint(nextX, nextY);

			x = nextX;
			y = nextY;

			/*
			ridgeNPoints++;
			ridgeX[ridgeNPoints] = nextX;
			ridgeY[ridgeNPoints] = nextY;
			 */
		}

		/*valleyX[valleyNPoints] = nextX;
		valleyY[valleyNPoints] = nextY;*/

		landscape.addPoint(nextX, gridLine);

		while(x >= 0) {

			nextX = x - rand.nextInt(40);
			nextY = y - rand.nextInt(20) + 10;

			if(nextY < landscapeBottom) {
				nextY = y + (Math.abs(nextY - landscapeBottom));
			} else if (nextY > landscapeReturn) {
				nextY = y - (nextY - landscapeReturn);
			}


			landscape.addPoint(nextX, nextY);

			x = nextX;
			y = nextY;


			/*
			valleyNPoints++;
			valleyX[valleyNPoints] = nextX;
			valleyY[valleyNPoints] = nextY;
			 */
		}



		/*
		int[] ridgeXPoints = new int[ridgeNPoints];
		int[] ridgeYPoints = new int[ridgeNPoints];

		int fillSep = 10;

		int ridgeArray = 0;

		while(ridgeX[ridgeArray] <= width) {
			ridgeXPoints[ridgeArray] = ridgeX[ridgeArray];
			ridgeYPoints[ridgeArray] = ridgeY[ridgeArray];
			ridgeArray++;
		}*/


		/*landscape.addPoint(width + 5, maxGridHeight);
		landscape.addPoint(-5, maxGridHeight);
		landscape.addPoint(-5, startY);*/

		g.draw(landscape);
		g.setColor(Color.BLACK);
		g.fill(landscape);




		/*int highestLine = highest;
		int lowestLine = lowest;
		g.setStroke(new BasicStroke(1));
		g.setColor(Color.WHITE);
		g.setClip(landscape);

		while(highestLine < lowest) {
			for(int i = 0; i < ridgeYPoints.length; i++) {
				ridgeYPoints[i] = ridgeYPoints[i] + fillSep;
			}
			g.drawPolyline(ridgeXPoints, ridgeYPoints, ridgeNPoints);
			highestLine += fillSep;
		}
		System.out.println(highestLine);
		System.out.println(lowest);*/

		drawLandscapeShapes(args, landscape);

	}

	private void drawLandscapeShapes(MapWrapper args, Polygon shape) {

		//AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.DST_IN, 1);
		//g.setComposite(composite);

		g.setClip(shape);



		int mid = rand.nextInt((int)((height  +0.0) / 2.0));

		mid = 500;
		System.out.println("mid " + mid);

		paintMnts(height,0 , args);
		paintFlat(mid, 0, args);


		/*Landscape landscape = new Landscape(img);
		landscape.paint(args);

		g.setColor(Color.BLACK);*/

		/*for(int i = 1; i <width; i++) {
			for(int j = 1; j < height; j++) {
				if(!shape.contains(i, j)) {
					g.drawLine(i, j, i, j);
				}
			}
		}*/

		//g.setColor(Color.PINK);
		//g.fillRect(0, 0, width, height);



	}

	private Circle drawSun() {

		Color sunColor = null;

		switch(rand.nextInt(1)) {
		case 0: sunColor = Color.RED;
		break;
		}

		int sunDiam;

		boolean hasScanLines = true;

		if (hasLandscape == false) {
			sunDiam = rand.nextInt(height - maxGridHeight) + 200;
		} else {
			sunDiam = rand.nextInt( maxGridHeight /* - maxLandscapeHeight*/) + 100;
		}

		int sunX = rand.nextInt(width - (sunDiam * 2) + sunDiam);
		int sunY = (maxGridHeight / 2) - (sunDiam / 2);

		g.setColor(sunColor);

		Circle sun = new Circle();

		sun.draw(g, sunX, sunY, sunDiam, sunDiam);

		/*if(hasScanLines == true) {
			int scanNum = rand.nextInt(10) + 2;
			System.out.println(scanNum);
			int scanMinHeight = sunDiam + sunY;
			System.out.println(scanMinHeight);
			int scanMaxHeight = scanMinHeight - rand.nextInt((sunDiam));
			System.out.println(scanMaxHeight);
			int scanMaxWidth = rand.nextInt((scanMinHeight - scanMaxHeight) / (scanNum / 2));
			int scanMinWidth = rand.nextInt(scanMaxWidth / 2) + 20;
			int scanIncrease = ((scanMaxWidth - scanMinWidth) / (scanNum - 1));
			int scanTotal = 0;
			System.out.println(scanMinHeight + " " + scanMaxHeight);

			int[] color = new int[] {0, 0, 0, 100};

			g.setColor(new Color(color[0], color[1], color[2], color[3]));

			for(int i = 0; i < scanNum; i++) {
				scanTotal = scanTotal + scanIncrease;
			}

			int scanSpace = ((scanMinHeight - scanMaxHeight) - scanTotal) / scanNum;

			int x1 = sunX;
			int y1 = scanMinHeight;
			int x2 = sunX + sunDiam;
			int y2 = scanMinHeight;

			g.setStroke(new BasicStroke(50));

			for(int i = 0; i < scanNum; i++) {
				g.drawLine(x1, y1, x2, y2);

				int scanWidth = (i + 1) * scanIncrease;
				y1 -= scanSpace;
				y2 -= scanSpace;
				g.setStroke(new BasicStroke(scanWidth));
			}
			System.out.println("total " + scanTotal);
			System.out.println("space " + scanSpace);
			System.out.println("min wid " + scanMinWidth);
			System.out.println("max wid " + scanMaxWidth);
			System.out.println("increase " + scanIncrease);
		}*/


		if(hasScanLines == true) {
			int scanNum = rand.nextInt(5) + 2;
			int scanMinHeight = sunDiam + sunY;
			int scanMaxHeight = scanMinHeight - (sunDiam / 2);
			int scanMinWidth = rand.nextInt((scanMinHeight - scanMaxHeight) / scanNum);

			int[] color = new int[] {0, 0, 0, 255};

			g.setColor(new Color(color[0], color[1], color[2], color[3]));

			int x1 = sunX;
			int y1 = scanMinHeight;
			int x2 = sunX + sunDiam;
			int y2 = scanMinHeight;


			int scanWidth = scanMinWidth;

			for(int i = 0; i < scanNum; i++) {
				if(y1 - scanWidth > sunY) {
					g.drawLine(x1, y1, x2, y2);
				}

				g.setStroke(new BasicStroke(scanWidth));


				y1 -= scanWidth;
				y2 -= scanWidth;
				scanWidth *= 2;
			}
		}

		return sun;

	}

	private int drawGrid()  {

		//AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.DST_OVER, 1);
		//g.setComposite(composite);
		Color color = null;

		switch(rand.nextInt(5)) {
		case 0: color = Color.MAGENTA;
		break;
		case 1: color = Color.CYAN;
		break;
		case 2: color = Color.GREEN;
		break;
		case 3: color = Color.PINK;
		break;
		case 4: color = Color.WHITE;
		break;
		}

		g.setStroke(new BasicStroke(4));
		g.setColor(color);

		//sets the dimensions for the objects in scene
		int squareDepth = rand.nextInt(maxGridHeight / 8) + 50;
		int squareWidth = squareDepth + rand.nextInt(squareDepth);
		float scale = 0.85f;

		//sets the x and y points for the polygons that the grid will be made up of
		int[] xPoints = new int[] {width / 2, width / 2, (int) ((width / 2) - (squareWidth * scale)), (width / 2) - squareWidth};
		int[] yPoints = new int[] {height, height - squareDepth, height - squareDepth, height};

		int nPoints = 4;

		//keeps track of completed rows to scale the polygons as distance increases
		int rowCounter = 0;

		//loops until the maxGridHeight is met
		do {

			//loops until the grid is the length of the screen
			do {

				//troubleshooting
				//System.out.println(xPoints[0] + " " + xPoints[1] + " " + xPoints[2] + " " + xPoints[3] + "x");

				//draws polygon
				g.setColor(color);
				g.drawPolygon(xPoints, yPoints, nPoints);
				g.setColor(Color.BLACK);
				g.fillPolygon(xPoints, yPoints, nPoints);

				//creates a mirrored polygon on the other side of the screen
				mirror(xPoints, yPoints, nPoints, color);


				//updates x coordinate values of next polygon to connect to the polygon before it
				xPoints = new int[] {(int) (xPoints[0] - (squareWidth * Math.pow(scale, rowCounter))),
						(int) (xPoints[1] - (squareWidth * Math.pow(scale, rowCounter + 1))),
						(int) (xPoints[2] - (squareWidth * Math.pow(scale, rowCounter + 1))),
						(int) (xPoints[3] - (squareWidth * Math.pow(scale, rowCounter)))};


			}	while (xPoints[1] > 0);

			int yMove = (int) (squareDepth * Math.pow(scale, rowCounter + 1));
			//System.out.println(yMove);
			if(yMove == 0) {
				maxGridHeight = yPoints[0];
			}

			//updates x coordinates to move up a row
			yPoints = new int[] {(int) (yPoints[0] - (squareDepth * Math.pow(scale, rowCounter))),
					(int) (yPoints[1] - (squareDepth * Math.pow(scale, rowCounter + 1))),
					(int) (yPoints[2] - yMove),
					(int) (yPoints[3] - (squareDepth * Math.pow(scale, rowCounter)))};
			//System.out.println(yPoints[0] + " " + yPoints[1] + " " + yPoints[2] + " " + yPoints[3] + "y");

			rowCounter++;

			//resets x coordinates to be in the center of the screen
			xPoints = new int[] {width / 2, width / 2,
					(int) ((width / 2) - (squareWidth * Math.pow(scale, rowCounter + 1))),
					(int) ((width / 2) - (squareWidth * Math.pow(scale, rowCounter)))};

			//System.out.println(xPoints[0] + " " + xPoints[1] + " " + xPoints[2] + " " + xPoints[3] + "x");

		}	while (yPoints[0] > maxGridHeight);

		return yPoints[0];

	}

	//mirrors each polygon onto the other side of the screen (since we start drawing from the middle)
	private void mirror(int[] xPoints, int[] yPoints, int nPoints, Color color) {

		int[] xPointsMirror = new int[xPoints.length];

		for(int i = 0; i <= xPoints.length - 1; i++) {
			xPointsMirror[i] = Math.abs(xPoints[i] - width);
		}

		g.setColor(color);
		g.drawPolygon(xPointsMirror, yPoints, nPoints);
		g.setColor(Color.BLACK);
		g.fillPolygon(xPointsMirror, yPoints, nPoints);		
	}
	//}











	public void paintFlat(int top, int bottom, MapWrapper args) {
		int diff = top - bottom;
		double noise [][] = ImageTools.createNoise(diff, diff, width,(int)(diff * args.getDouble("flatscale", 1)), (int)(diff * args.getDouble("flatscale", 1)),args.getDouble("flatpersist", .1));

		for(int a = 0;  a < diff; a += diff / args.getDouble("divisor", 200)) {
			int []y = new int [width + 4];
			int []x = new int [width + 4];

			for(int i = 0;  i < width; i++) {
				x[i] = i;
				y[i] = height - (((int) (noise[a][i] * diff)) - (a / diff * bottom) - top);
			}
			int i = width;

			y[i] = height;
			x[i] = width;

			i++;
			y[i] = height;
			x[i] = 0;

			i++;
			y[i] = y[0];
			x[i] = 0;

			g.setColor(Color.black);
			g.drawPolygon(x, y, i);
			g.setColor(Color.white);
			g.drawPolygon(x, y, i);
		}
	}



	public void paintMnts(int top, int bottom, MapWrapper args) {
		int diff = top - bottom;

		double noise [][] = ImageTools.createNoise(diff, diff, width,(int)(diff * args.getDouble("mntscale", 1)), (int)(diff * args.getDouble("mntscale", 1)),args.getDouble("mntpersist", .4));

		for(int a = 0;  a < diff ; a += diff / args.getDouble("divisor", diff / 2)) {
			int []y = new int [width + 4];
			int []x = new int [width + 4];

			for(int i = 0;  i < width; i++) {
				x[i] = i;
				y[i] = (height - (diff - a)) - (int) (noise[a][i] * diff) ;
			}
			int i = width;

			y[i] = height;
			x[i] = width;

			i++;
			y[i] = height;
			x[i] = 0;

			i++;
			y[i] = y[0];
			x[i] = 0;

			g.setColor(Color.black);
			g.drawPolygon(x, y, i);
			g.setColor(Color.white);
			g.drawPolygon(x, y, i);
		}
	}



}




