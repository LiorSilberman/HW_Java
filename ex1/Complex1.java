public class Complex1 implements IComplex{
    private double real;
    private double image;

    public Complex1()
    {
        this(0,0);
    }

    public Complex1(double real, double image) {
        this.real = real;
        this.image = image;
    }

    public Complex1(IComplex c)
    {
        this.real = c.getReal();
        this.image = c.getImage();
    }

    @Override
    public double getReal() {
        return this.real;
    }

    @Override
    public double getImage() {
        return this.image;
    }

    @Override
    public double getAngle() {
        if (this.real > 0)
            return Math.atan(this.image / this.real);

        return Math.atan(this.image / this.real) + 180;
    }

    @Override
    public double getRadius() {
        return Math.sqrt(this.real * this.real + this.image * this.image);
    }

    @Override
    public void setReal(double real) {
        this.real = real;
    }

    @Override
    public void setImage(double img) {
        this.image = image;
    }

    @Override
    public void setAngle(double ang) {
        double r = getRadius() * Math.cos(getAngle());
        double i = getRadius() * Math.sin(getAngle());
        real = r;
        image = i;
    }

    @Override
    public void setRadius(double r) {
        double real = r * Math.cos(getAngle());
        double image = r * Math.sin(getAngle());
        this.real = real;
        this.image = image;

    }

    @Override
    public IComplex conjugate() {
        IComplex conj = new Complex1(this.real, -this.image);
        return conj;
    }

    @Override
    public IComplex add(IComplex c) {
        double r = this.real + c.getReal();
        double i = this.image + c.getImage();
        IComplex add = new Complex1(r, i);
        return add;
    }

    @Override
    public IComplex sub(IComplex c) {
        double r = this.real - c.getReal();
        double i = this.image - c.getImage();
        IComplex sub = new Complex1(r, i);
        return sub;
    }

    @Override
    public IComplex mul(IComplex c) {
        double r = this.real * c.getReal() - this.image * c.getImage();
        double i = this.real * c.getImage() + this.image * c.getReal();
        IComplex mul = new Complex1(r, i);
        return mul;
    }

    @Override
    public IComplex mulScalar(double d) {
        double r = d * this.real;
        double i = d * this.image;
        IComplex mulScalar = new Complex1(r, i);
        return mulScalar;
    }

    @Override
    public IComplex div(IComplex c) {
        IComplex numerator = c.conjugate().mul(this);
        IComplex denominator = c.conjugate().mul(c);
        IComplex div = new Complex1(numerator.getReal() / denominator.getReal(), numerator.getImage() / denominator.getReal());
        return div;
    }

    @Override
    public boolean equalTo(IComplex other) {
        return real == other.getReal() && image == other.getImage();
    }

    @Override
    public String toString() {
        if (image < 0)
            return String.format("Complex: %.2f - %.2fi", real,Math.abs(image));

        return String.format("Complex: %.2f + %.2fi", real, image);
    }
}
