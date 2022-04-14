import java.util.*;

public class Polynom<E> implements IPolynom<E>{

	private SortedMap<Integer, FieldMember<E>> coefficients;

	/**
	 * constructor: create the zero polynomial.
	 */
	public Polynom()
	{
		this.coefficients = new TreeMap<Integer, FieldMember<E>>();
		new Polynom(this.coefficients);
	}

	/**
	 * @param coefficients
	 * constructor: create the polynomial according to the parameter coefficients.
	 */
	public Polynom(Map<Integer, ? extends FieldMember<E>> coefficients) {
		this.coefficients = new TreeMap<Integer, FieldMember<E>>(coefficients);
	}

	/**
	 * @param poly
	 * Copy constructor.
	 */
	public Polynom(IPolynom<E> poly) {
		this.coefficients = new TreeMap<Integer, FieldMember<E>>(poly.getCoefficients());
	}

	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (coefficients.isEmpty()) {
			return sb.toString();
		}
		sb.append("Polynom: ");
		int lastKey = coefficients.lastKey();
		for (Map.Entry<Integer, FieldMember<E>> monom : coefficients.entrySet()) {
			sb.append(monom.getValue());
			if (monom.getKey() != 0)
				sb.append("x^" + monom.getKey());
			if (monom.getKey() != lastKey) {
				sb.append(" + ");
			}
		}
		return sb.toString();
	}

	@Override
	public FieldMember<E> getCoefficient(int n) {
		for (Map.Entry<Integer, FieldMember<E>> ceo : coefficients.entrySet())
		{
			if (ceo.getKey() == n)
			{
				return ceo.getValue();
			}
		}
		return null;
	}

	@Override
	public Map<Integer, FieldMember<E>> getCoefficients()
	{
		return coefficients;
	}

	@Override
	public int degree() {
		return coefficients.lastKey();
	}

	@Override
	public FieldMember<E> calculate(FieldMember<E> x)
	{
		FieldMember<E> temp = x.copy();
		ArrayList<FieldMember<E>> arr = new ArrayList<FieldMember<E>>();

		for (Map.Entry<Integer, FieldMember<E>> ceo : coefficients.entrySet())
		{
			if (ceo.getKey() == 0)
			{
				arr.add(ceo.getValue());
				continue;
			}
			temp = x.exponent(ceo.getKey());
			FieldMember<E> field = ceo.getValue().mult(temp);
			arr.add(field);
		}

		FieldMember<E> z = x.copy();
		z = z.sub(x);

		for ( FieldMember<E> y : arr)
		{
			z = z.add(y);
		}
		return z;
	}

	@Override
	public void addMonom(int deg, FieldMember<E> coefficient) {

		if ((coefficient.add(coefficient).getValue().equals(coefficient.getValue())))
		{
			return;
		}

		if (coefficients.isEmpty())
		{
			coefficients.put(deg, coefficient);
			return;
		}

		for (Map.Entry<Integer, FieldMember<E>> ceo : coefficients.entrySet())
		{
			if (ceo.getKey() == deg) {
				FieldMember<E> temp =  ceo.getValue().add(coefficient.getValue());

				if ((temp.add(temp).getValue().equals(temp.getValue())))
				{
					coefficients.remove(ceo.getKey());
					return;
				}
				coefficients.put(deg, ceo.getValue().add(coefficient.getValue()));
			}
		}

		if (deg > coefficients.lastKey() || deg < coefficients.firstKey())
		{
			coefficients.put(deg, coefficient);
		}

		if (!coefficients.containsKey(deg))
		{
			coefficients.put(deg, coefficient);
		}


	}

	@Override
	public FieldMember<E> removeMonom(int deg) {
		if (getCoefficient(deg) != null) {
			return coefficients.remove(deg);
		}
		return null;
	}

	@Override
	public IPolynom<E> add(IPolynom<E> poly)
	{
		SortedMap<Integer, FieldMember<E>> m = new TreeMap<Integer, FieldMember<E>>();
		m = (SortedMap<Integer, FieldMember<E>>) this.coefficients;
		Polynom<E> pol = new Polynom<E>(m);
		for (Map.Entry<Integer, FieldMember<E>> ceo : poly.getCoefficients().entrySet())
		{
			pol.addMonom(ceo.getKey(), ceo.getValue());
		}
		return pol;
	}

	@Override
	public IPolynom<E> sub(IPolynom<E> poly)
	{
		SortedMap<Integer, FieldMember<E>> m = new TreeMap<Integer, FieldMember<E>>();
		m = (SortedMap<Integer, FieldMember<E>>) this.coefficients;
		Polynom<E> pol = new Polynom<E>(m);
		for (Map.Entry<Integer, FieldMember<E>> ceo : poly.getCoefficients().entrySet())
		{
			pol.addMonom(ceo.getKey(), ceo.getValue().getNegative());
		}
		return pol;
	}

	@Override
	public IPolynom<E> mult(IPolynom<E> poly)
	{
		SortedMap<Integer, FieldMember<E>> m = new TreeMap<Integer, FieldMember<E>>();
		SortedMap<Integer, FieldMember<E>> n = new TreeMap<Integer, FieldMember<E>>();
		m = (SortedMap<Integer, FieldMember<E>>) this.coefficients;

		for (Map.Entry<Integer, FieldMember<E>> ceo1 : m.entrySet())
		{
			for (Map.Entry<Integer, FieldMember<E>> ceo2 : poly.getCoefficients().entrySet())
			{
				FieldMember<E> field = ceo1.getValue().mult(ceo2.getValue());
				int exponent = ceo1.getKey() + ceo2.getKey();
				if (n.containsKey(exponent))
				{

					n.put(exponent,n.get(exponent).add(field));
				}
				else {
					n.put(exponent, field);
				}
			}

		}
		Polynom<E> pol = new Polynom<E>(n);
		return pol;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Polynom<?> polynom = (Polynom<?>) o;
		return Objects.equals(coefficients, polynom.coefficients);
	}

	@Override
	public int hashCode() {
		return Objects.hash(coefficients);
	}
}
