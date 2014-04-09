package ifs;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class IFS {

	private Random r = new Random();
	
	private String name = null;
	private boolean isStandartFunction = false;
	
	private ArrayList<IFS_Function> functions = new ArrayList<IFS_Function>();

	int probablilities[];
	
	private IFS_Point point;

	private int height;
	private int width;

	public IFS(String name, int width, int height, ArrayList<IFS_Function> functions, boolean isStandartFunction) {
		setName(name);
		setWidth(width);
		setHeight(height);
		setFunctions(functions);
	
		setStandartFunction(isStandartFunction);
		
		probablilities = new int[functions.size()];
		
		for (int i = 0; i < probablilities.length; i++) {
			probablilities[i] = (int) (functions.get(i).getProbability() * 100);
		}
		
		chooseRandomPoint();
	}

	
	public int chooseFunction() {
		
		int random = r.nextInt(100 + 1);
		int choosenFunction = 0;
		int percentage = 100;
		
		if(functions.size() == 1) {
			return 0;
		}
		
		if(functions.size() == 2) {
			int a = percentage - probablilities[0];
			int b = a - probablilities[1];
			
			if (random >= percentage && random <= a) {
				choosenFunction = 0;
			}
			if (random >= b && random < a) {
				choosenFunction = 1;
			}
			return choosenFunction;
		}
		
		if(functions.size() == 3) {
			int a = percentage - probablilities[0];
			int b = a - probablilities[1];
			int c = b - probablilities[2];
			
			if (random >= percentage && random <= a) {
				choosenFunction = 0;
			}
			if (random >= b && random < a) {
				choosenFunction = 1;
			}
			if (random >= c && random < b) {
				choosenFunction = 2;
			}
			return choosenFunction;
		}
		
		if(functions.size() == 4) {
			int a = percentage - probablilities[0];
			int b = a - probablilities[1];
			int c = b - probablilities[2];
			int d = c - probablilities[3];
			
			if (random >= percentage && random <= a) {
				choosenFunction = 0;
			}
			if (random >= b && random < a) {
				choosenFunction = 1;
			}
			if (random >= c && random < b) {
				choosenFunction = 2;
			}
			if (random >= d && random < c) {
				choosenFunction = 3;
			}
			return choosenFunction;
		}
	
		return random;
	}

	// xn+1 = axn+byn+e
	// yn+1 = cxn+dyn+f
	
	public void doChoosenFunction(int choosenFunction) {
		if(functions.get(choosenFunction) != null) {
			double x = ((functions.get(choosenFunction).getA() * point.getX()) + (functions.get(choosenFunction).getB() * point.getY())) + functions.get(choosenFunction).getE();
			double y = ((functions.get(choosenFunction).getC() * point.getX()) + (functions.get(choosenFunction).getD() * point.getY())) + functions.get(choosenFunction).getF();
			;
			point.setX(x);
			point.setY(y);
		}
	}

	private void chooseRandomPoint() {
		point = new IFS_Point(Math.random() * getHeight(),
				(Math.random() * getWidth()), getWidth(), getHeight());
	}

	/**
	 * Method that calls the point's draw method.
	 * @param g Graphics
	 */
	public void drawPoint(Graphics g) {
		point.draw(g);
	}

	public boolean isStandartFunction() {
		return isStandartFunction;
	}


	public void setStandartFunction(boolean isStandartFunction) {
		this.isStandartFunction = isStandartFunction;
	}


	public String getName() {
		return this.name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	/*
	 * Mehtod to get the height.
	 */
	public int getHeight() {
		return height;
	}

	/*
	 * Mehtod to set the height.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/*
	 * Mehtod to get the width.
	 */
	public int getWidth() {
		return width;
	}

	/*
	 * Mehtod to set the width.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	public void setFunctions(ArrayList<IFS_Function> functions) {
		this.functions = functions;
	}
	
	/*
	 * Mehtod to get the point.
	 */
	public IFS_Point getPoint() {
		return this.point;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
	public String toStringForFile() {
		
//		String function = "";
//		for (int i = 0; i < functions.size(); i++) {
//			function += "Function " + (i + 1) + ": \n" + functions.get(i).toString() + "\n";
//		}
//		
//		return "IFS: " + this.getName() + "\n" + function;
		
		String function = "";
		
		for (int i = 0; i < functions.size(); i++) {
			function += "Function: \n" + functions.get(i).toStringForFile() + "\n";
		}
		
		return "IFS: " + this.getName() + "\n" + function + this.isStandartFunction;
	}
	
}
