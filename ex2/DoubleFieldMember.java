public class DoubleFieldMember extends FieldMember<Double> {

    public DoubleFieldMember(Double value) {
        super(value);
    }

    @Override
    public FieldMember<Double> copy() {
        return new DoubleFieldMember(getValue());
    }

    @Override
    public FieldMember<Double> add(Double num) {
        return new DoubleFieldMember(getValue() + num);
    }

    @Override
    public FieldMember<Double> mult(Double num) {
        return new DoubleFieldMember(getValue() * num);
    }

    @Override
    public FieldMember<Double> getNegative() {
        return new DoubleFieldMember(getValue() * -1);
    }

    @Override
    public FieldMember<Double> getInverse() {
        return new DoubleFieldMember(getNegative().getValue());
    }

    @Override
    public FieldMember<Double> getIdentity() {

        return new DoubleFieldMember(1.0);
    }

    @Override
    public String toString() {
        String value = HW3Utils.formatDouble(getValue());
        return value;
    }
}