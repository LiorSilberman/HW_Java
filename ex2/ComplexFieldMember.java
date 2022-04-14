public class ComplexFieldMember extends FieldMember<Complex> {

	public ComplexFieldMember(Complex complex) {
		super(complex);
	}

	@Override
	public FieldMember<Complex> copy() {
		return new ComplexFieldMember(getValue());
	}

	@Override
	public FieldMember<Complex> add(Complex num)
	{
		return new ComplexFieldMember(getValue().add(num));
	}

	@Override
	public FieldMember<Complex> mult(Complex num) {
		return new ComplexFieldMember(getValue().mult(num));
	}

	@Override
	public FieldMember<Complex> getNegative() {
		return new ComplexFieldMember(getValue().negative());
	}

	@Override
	public FieldMember<Complex> getInverse() {
		return new ComplexFieldMember(getValue().inverse());
	}

	@Override
	public FieldMember<Complex> getIdentity() {
		return new ComplexFieldMember(Complex.identity());
	}

	@Override
	public String toString() {
		return getValue().toString();
	}
}