package ifs;

public class IFS_Function {

	double a = 0;
	double b = 0;
	double c = 0;
	double d = 0;
	double e = 0;
	double f = 0;
	
	double probability = 0;

	public IFS_Function(double a, double b, double c, double d, double e, double f, double probability) {
		setA(a);
		setB(b);
		setC(c);
		setD(d);
		setE(e);
		setF(f);
		setProbability(probability);
	}

	public double getA() {
		return a;
	}

	private void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	private void setB(double b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	private void setC(double c) {
		this.c = c;
	}

	public double getD() {
		return d;
	}

	private void setD(double d) {
		this.d = d;
	}

	public double getE() {
		return e;
	}

	private void setE(double e) {
		this.e = e;
	}

	public double getF() {
		return f;
	}

	private void setF(double f) {
		this.f = f;
	}

	public double getProbability() {
		return probability;
	}

	private void setProbability(double probability) {
		this.probability = probability;
	}
	
	@Override
	public String toString() {
		return " a: " + getA() + "\n b: " + getB() + "\n c: " + getC() + "\n d: " + getD() + "\n e: " + getE() + "\n f: " + getF() + "\n prob: " + getProbability();
	}
	
	public String toStringForFile() {
		return getA() + "\n" + getB() + "\n" + getC() + "\n" + getD() + "\n" + getE() + "\n" + getF() + "\n" + getProbability();
	}
	
}
