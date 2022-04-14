import java.util.Map;

public interface IPolynom<E> {
	
	/**
	 * @param n
	 * @return the coefficient of the monom with exponent n.
	 * If the coefficient is zero - return null.
	 */
	public FieldMember<E> getCoefficient(int n);

	
	/**
	 * @return the coefficients - map from exponent to the corresponding coefficient.
	 */
	public Map<Integer, FieldMember<E>> getCoefficients();

	/**
	 * @return the degree of the polynom
	 */
	public int degree();

	/**
	 * @param x
	 * @return the result of p(x) where p is the polynom.
	 * Note that x should not be changed.
	 */
	public FieldMember<E> calculate(FieldMember<E> x);
	
	/**
	 * @param deg
	 * @param coefficient
	 * add the monom: coefficient*x^(deg) to the polynom
	 * if the deg has already appear in the polynom, 
	 * make add the parameter coefficient to the current coefficient of degree deg.
	 */
	public void addMonom(int deg, FieldMember<E> coefficient);

	
	/**
	 * @param deg
	 * @return the removed coefficient, or null if the coefficient is zero.
	 * remove the monom corresponding to degree deg.
	 */
	public FieldMember<E> removeMonom(int deg) ;


	/**
	 * @param poly
	 * @return the new polynon which is the sum of this and poly.
	 * The current polynom will not be changed
	 */
	public IPolynom<E> add(IPolynom<E> poly);

	/**
	 * @param poly
	 * @return the new polynon which is the result of subtraction of poly from this.
	 * The current polynom will not be changed
	 */
	public IPolynom<E> sub(IPolynom<E> poly);

	/**
	 * @param poly
	 * @return the new polynon which is the multuplication of this and poly.
	 * The current polynom will not be changed
	 */
	public IPolynom<E> mult(IPolynom<E> poly);

}