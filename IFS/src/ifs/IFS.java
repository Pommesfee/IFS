package ifs;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is the Skeleton for an new Iterated-Function-System.
 * You can define a new IFS by providing at least one {@link IFS_Function} for the IFS.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class IFS {

	private Random r = new Random();
	
	private String name = null;
	private boolean isStandartIFS = false;
	
	private ArrayList<IFS_Function> functions = new ArrayList<IFS_Function>();

	private int probablilities[];
	
	private IFS_Point point;

	private int height;
	private int width;

	/**
	 * Constructor for an new Iterated-Function-System.
	 * 
	 * @param name How this IFS is called.
	 * @param width With of the Component this IFS is drawn to.
	 * @param height Height of the Component this IFS is drawn to.
	 * @param functions The functions that are used by this IFS. 
	 * @param isStandartIFS If this IFS should be a standard IFS.
	 */
	public IFS(String name, int width, int height, ArrayList<IFS_Function> functions, boolean isStandartIFS) {
		setName(name);
		setWidth(width);
		setHeight(height);
		setFunctions(functions);
	
		setStandartIFS(isStandartIFS);
		
		probablilities = new int[functions.size()];
		
		for (int i = 0; i < probablilities.length; i++) {
			probablilities[i] = (int) (functions.get(i).getProbability() * 100);
		}
		
		chooseRandomPoint();
	}

	/**
	 * This method is responsible of choosing a function from the
	 * IFS that should be executed in the next step.
	 * To calculate which function should be executed, this method uses the
	 * probability that is provided by each function.
	 * 
	 * @return The selected function.
	 */
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
	
	/**
	 * This methods purpose is to execute the function,
	 * that has been selected in the first step.
	 * 
	 * @param choosenFunction Function that should be executed.
	 */
	public void doChoosenFunction(int choosenFunction) {
		if(functions.get(choosenFunction) != null) {
			double x = ((functions.get(choosenFunction).getA() * point.getX()) + (functions.get(choosenFunction).getB() * point.getY())) + functions.get(choosenFunction).getE();
			double y = ((functions.get(choosenFunction).getC() * point.getX()) + (functions.get(choosenFunction).getD() * point.getY())) + functions.get(choosenFunction).getF();
			;
			point.setX(x);
			point.setY(y);
		}
	}

	/**
	 * A method to choose coordinates for a random Point
	 * when a new IFS is initialized.
	 */
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

	/**
	 * Check if this IFS is a standard IFS.
	 * @return True if standard IFS
	 */
	public boolean isStandartIFS() {
		return isStandartIFS;
	}

	/**
	 * Change this IFS from a normal IFS to a standard IFS or
	 * the other way round.
	 * @param isStandartIFS Whether this IFS should be standard or not. 
	 */
	public void setStandartIFS(boolean isStandartIFS) {
		this.isStandartIFS = isStandartIFS;
	}

	/*
	 * Method to get the name of this IFS.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Method to set(change) the name of an IFS
	 * @param name
	 */
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

	/**
	 * Method to set the functions that are used by this IFS.
	 * @param functions Functions.
	 */
	public void setFunctions(ArrayList<IFS_Function> functions) {
		this.functions = functions;
	}
	
	/*
	 * Mehtod to get the point.
	 */
	public IFS_Point getPoint() {
		return this.point;
	}
	
	/**
	 * To String.
	 */
	@Override
	public String toString() {
		return this.getName();
	}
	
	/**
	 * To String for file.
	 * @return How the IFS can be easily saved.
	 */
	public String toStringForFile() {
		
		String function = "";
		
		for (int i = 0; i < functions.size(); i++) {
			function += "Function: \n" + functions.get(i).toStringForFile() + "\n";
		}
		
		return "IFS: " + this.getName() + "\n" + function + this.isStandartIFS;
	}
	
}
