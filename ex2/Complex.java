import java.util.Objects;

public class Complex implements Comparable<Complex>{

	double real;
	double img;

	public Complex() {
		this(0.0, 0.0);
	}

	public Complex(Complex c) {
		this(c.getReal(), c.getImg());
	}

	public Complex(double real, double img) {
		this.real = real;
		this.img = img;
	}

	public double getReal() {
		return real;
	}

	public double getImg() {
		return img;
	}

	/**
	 * @return a copy of the object
	 */
	public Complex copy() {
		return new Complex(real, img);
	}

	/**
	 * @param c
	 * @return the multiplication of the Complex number and c the current object is
	 *         not changed
	 */
	public Complex mult(Complex c) {
		double real = getReal() * c.getReal() - getImg() * c.getImg();
		double img = getReal() * c.getImg() + getImg() * c.getReal();
		return new Complex(real, img);
	}

	/**
	 * @param d
	 * @return the multiplication of the Complex number and scalar the current
	 *         object is not changed
	 */
	public Complex mulScalar(double d) {
		return new Complex(getReal() * d, getImg() * d);
	}

	/**
	 * @param c
	 * @return the sum of the Complex number and c the current object is not changed
	 */
	public Complex add(Complex c) {
		double real = getReal() + c.getReal();
		double img = getImg() + c.getImg();
		return new Complex(real, img);
	}

	/**
	 * @return the negative of the Complex number the current object is not changed
	 */
	public Complex negative() {
		return mulScalar(-1);
	}

	/**
	 * @return the inverse of the Complex number the current object is not changed
	 */
	public Complex inverse() {
		double d = getReal() * getReal() + getImg() * getImg();
		double real = getReal() / d;
		double img = ((-1) * getImg()) / d;
		return new Complex(real, img);
	}

	/**
	 * @return the multiplication identity.
	 */
	public static Complex identity() {
		return new Complex(1, 0);
	}

	@Override
	public String toString() {
		String opString = "+";
		String realString = HW3Utils.formatDouble(getReal());

		String imgString = null;

		if (img < 0) {
			imgString = HW3Utils.formatDouble(getImg() * (-1));
			opString = "-";
		} else {
			imgString = HW3Utils.formatDouble(getImg());
		}
		if (imgString.equals("0.00"))
			opString = "+";

		return "(" + realString + opString + imgString + "i)";
	}
	
	@Override
	public int compareTo(Complex other) {
		int res = Double.valueOf(real).compareTo(other.real);
		if ( res == 0 )
			res = Double.valueOf(img).compareTo(other.img);
		return res;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Complex complex = (Complex) o;
		return Double.compare(complex.real, real) == 0 && Double.compare(complex.img, img) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(real, img);
	}
}
