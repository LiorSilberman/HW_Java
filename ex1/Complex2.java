public class Complex2 implements IComplex {
    private double radius;
    private double angle;

    public Complex2()
    {
        this(0,0);
    }

    public Complex2(double radius, double angle)
    {
        this.radius = radius;
        this.angle = angle;
    }

    public Complex2(IComplex c)
    {
        this.radius = c.getRadius();
        this.angle = c.getAngle();
    }

    @Override
    public double getReal() {
        return Math.cos(this.angle) * this.radius;
    }

    @Override
    public double getImage() {
        return Math.sin(this.angle) * this.radius;
    }

    @Override
    public double getAngle() {
        return this.angle;
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    @Override
    public void setReal(double real) {
        double radius = Math.sqrt(real * real + getImage() * getImage());
        double angle = Math.atan(getImage() / real);
        this.radius = radius;
        this.angle = angle;
    }

    @Override
    public void setImage(double img) {
        double radius = Math.sqrt(getReal() * getReal() + img * img);
        double angle = Math.atan(img / getReal());
        this.radius = radius;
        this.angle = angle;
    }

    @Override
    public void setAngle(double ang) {
        angle = ang;
    }

    @Override
    public void setRadius(double r) {
        radius = r;
    }

    @Override
    public IComplex conjugate() {
        IComplex conjugate = new Complex2(this.radius, -this.angle);
        return conjugate;
    }

    @Override
    public IComplex add(IComplex c) {
        double real = getReal() + c.getReal();
        double image = getImage() + c.getImage();
        double radius = Math.sqrt(real*real + image*image);
        double angle;
        if (real > 0)
            angle = Math.atan(image / real);
        esle:
            angle = Math.atan(image / real) + 180;
        IComplex add = new Complex2(radius, angle);
        return add;
    }

    @Override
    public IComplex sub(IComplex c) {
        double real = getReal() - c.getReal();
        double image = getImage() - c.getImage();
        double radius = Math.sqrt(real*real + image*image);
        double angle;
        if (real > 0)
            angle =  Math.atan(image / real);
        else
            angle = Math.atan(image / real) + 180;

        IComplex sub = new Complex2(radius, angle);
        return sub;
    }

    @Override
    public IComplex mul(IComplex c) {
        double radius = this.radius * c.getRadius();
        double angle = this.angle + c.getAngle();
        IComplex mul = new Complex2(radius, angle);
        return mul;
    }

    @Override
    public IComplex mulScalar(double d) {
        IComplex mulScalar = new Complex2(radius*d, angle);
        return mulScalar;
    }

    @Override
    public IComplex div(IComplex c) {
        double radius = this.radius / c.getRadius();
        double angle = this.angle - c.getAngle();
        IComplex div = new Complex2(radius, angle);
        return div;
    }

    @Override
    public boolean equalTo(IComplex other) {
        return this.radius == other.getRadius() && this.angle == other.getAngle();
    }

    @Override
    public String toString() {
         if (getImage() < 0)
            return String.format("Complex: %.2f - %.2fi", getReal(), Math.abs(getImage()));

        return String.format("Complex: %.2f + %.2fi", getReal(), getImage());
    }
}
