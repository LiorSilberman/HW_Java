import java.util.Objects;

public abstract class FieldMember<E> {

	private E value;

	public FieldMember(E value) {
		this.value = value;
	}

	public E getValue() {
		return value;
	}

	/**
	 * @param num
	 * @return the sum of this and num
	 * the current object is not changed
	 */
	public FieldMember<E> add(FieldMember<E> num) {
		return add(num.getValue());
	}

	/**
	 * @param num
	 * @return the result of subtraction num of this.
	 * the current object is not changed
	 */
	public FieldMember<E> sub(FieldMember<E> num) {
		return add(num.getNegative());
	}

	/**
	 * @param num
	 * @return the multiplication by num
	 * the current object is not changed
	 */
	public FieldMember<E> mult(FieldMember<E> num) {
		return mult(num.getValue());
	}

	/**
	 * @param num
	 * @return the result of this divided by num
	 * the current object is not changed
	 */
	public FieldMember<E> div(FieldMember<E> num) {
		return mult(num.getInverse());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FieldMember<?> that = (FieldMember<?>) o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	/**
	 * @param n
	 * @return the the exponent n of this (value^n).
	 * the current object is not changed
	 */
	public FieldMember<E> exponent(int n) {
		if (n == 0)
			return copy();
		FieldMember<E> res = copy();
		for (int i = 1; i < n; i++)
			res = res.mult(getValue());
		return res;
	}
	
	@Override
	public String toString() {
		return getValue().toString();
	}

	/**
	 * @return a copy of the object
	 */
	public abstract FieldMember<E> copy();

	/**
	 * @param num
	 * @return the sum of this and num
	 * the current object is not changed
	 */
	public abstract FieldMember<E> add(E num);

	/**
	 * @param num
	 * @return the multiplication by num
	 * the current object is not changed
	 */
	public abstract FieldMember<E> mult(E num);

	/**
	 * @return the negative number
	 * the current object is not changed
	 */
	public abstract FieldMember<E> getNegative();

	/**
	 * @return the inverse number
	 * the current object is not changed
	 */
	public abstract FieldMember<E> getInverse();

	/**
	 * @return the multiplication identity
	 * the current object is not changed
	 */
	public abstract FieldMember<E> getIdentity();

}