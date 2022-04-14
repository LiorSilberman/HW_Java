import java.io.*;
import java.util.*;

import static kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames.string;

public class PolynomHandler {

    // check if number
    public static boolean isNumeric(String string) {
        double DoubleValue;

        if (string == null || string.equals("")) {
            return false;
        }
        try {
            DoubleValue = Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    // return list [real,image]
    public static ArrayList<Double> findRealAndImag(String s) {
        ArrayList<Double> arr = new ArrayList<Double>();
        int l = s.length();
        int i = 0;

        if (s.indexOf('+') != -1) {
            i = s.indexOf('+');
        }

        // else storing the index of '-'
        else {
            if (s.startsWith("-")) {
                i = s.indexOf('-', 1);
            }
        }

        // Finding the real part
        // of the complex number
        String real = s.substring(0, i);
        arr.add(Double.parseDouble(real));

        // Finding the imaginary part
        // of the complex number
        String imaginary = s.substring(i, l - 1);
        arr.add(Double.parseDouble(imaginary));
        return arr;
    }


    public static void main(String[] args) throws IOException {


        List<Polynom<Double>> listDouble = new ArrayList<Polynom<Double>>();
        List<Polynom<Complex>> listComplex = new ArrayList<Polynom<Complex>>();
        SortedSet<Polynom<Double>> setDouble = new TreeSet<>(new DoublePolComparator());
        SortedSet<Polynom<Complex>> setComplex = new TreeSet<>(new ComplexPolComparator());
        int lineNumber = 0;

        try {
            Scanner sc = new Scanner(new File(args[0]));
            Writer wr_error = new FileWriter("errors.txt");
            while (sc.hasNextLine()) {

                try {
                    String line = sc.nextLine();
                    String[] line2 = line.split("#");
                    lineNumber++;

                    if (line2.length <= 1)
                    {
                        String msg = "line number = " + lineNumber + ", input line = " + line + ", Error message = null";
                        throw new HW3Exceptions(msg);
                    }

                    if (!line2[1].startsWith("["))
                    {
                        String msg = "line number = " + lineNumber + ", input line = " + line +", Error message = Exception in scanning the polynomial string";
                        throw new HW3Exceptions(msg);
                    }

                    int temp = line.indexOf('#');
                    String newLine = line.substring(temp+1, line.length()-1);
                    if (newLine.contains("#"))
                    {
                        String msg = "line number = "+lineNumber+ ", input line= "+line+", Error message = Too many \"#\" in the input line.";
                        throw new HW3Exceptions(msg);
                    }

                    // handle complex lines
                    if (line2[0].startsWith("c")) {
                        SortedMap<Integer, FieldMember<Complex>> m = new TreeMap<Integer, FieldMember<Complex>>();

                        for (int i = 1; i < line2.length; i++) {
                            String[] ceof = line2[i].split("[,\\[\\]()]");
                            int exponent = 0;
                            for (String s : ceof) {
                                if (isNumeric(s)) {
                                    exponent = Integer.parseInt(s);
                                    if (exponent < 0)
                                    {
                                        String msg = "line number = "+lineNumber+ ", input line = "+line+", Error message = The degree should be non-negative";
                                        throw new HW3Exceptions(msg);
                                    }
                                }
                                if (s.endsWith("i")) {
                                    ArrayList<Double> c = findRealAndImag(s);
                                    FieldMember<Complex> complex = new ComplexFieldMember(new Complex(c.get(0), c.get(1)));
                                    if (c.get(0) != 0 || c.get(1) != 0) {
                                        m.put(exponent, complex);
                                    }
                                }

                                if (s.length() == 1 && !isNumeric(s))
                                {
                                    String msg = "line number = "+lineNumber+ ", input line = "+line+", Error message = Complex number should start with (";
                                    throw new HW3Exceptions(msg);
                                }
                            }
                        }
                        Polynom<Complex> pol = new Polynom<Complex>(m);
                        listComplex.add(pol);
                        setComplex.add(pol);
                    }

                    // handle double lines
                    if (line2[0].startsWith("d")) {
                        SortedMap<Integer, FieldMember<Double>> d = new TreeMap<Integer, FieldMember<Double>>();
                        for (int i = 1; i < line2.length; i++) {
                            String[] ceof = line2[i].split("[\\[\\]]");
                            ArrayList<Double> arr = new ArrayList<Double>();

                            for (String s : ceof) {
                                if (s != "") {
                                    int l = s.length();
                                    int index = s.indexOf(",");
                                    String ex_s = s.substring(0, index);
                                    int ex = Integer.parseInt(ex_s);

                                    if (ex < 0)
                                    {
                                        String msg = "line number = "+lineNumber+ ", input line = "+line+", Error message = The degree should be non-negative";
                                        throw new HW3Exceptions(msg);
                                    }

                                    String ceo_s = s.substring(index + 1, l);
                                    double ceo = Double.parseDouble(ceo_s);
                                    d.put(ex, new DoubleFieldMember(ceo));
                                }
                            }
                        }
                        Polynom<Double> pol2 = new Polynom<Double>(d);
                        listDouble.add(pol2);
                        setDouble.add(pol2);

                    }
                    if (!line2[0].startsWith("d") && !line2[0].startsWith("c")) {
                        String msg = "line number= " + lineNumber + ", input line= " + line + ", Error message = The type of the shape should be complex or double and not " + line2[0] + ".";
                        throw new HW3Exceptions(msg);
                    }



                }
                // catch exeption and write to errors.txt file.
                catch (HW3Exceptions e) {
                    wr_error.write(e.getMessage()+"\n");
                }
            }
            wr_error.flush();
            wr_error.close();
            sc.close();

                Writer wr = new FileWriter("outputList.txt");
                Writer wr2 = new FileWriter("outputSortedSet.txt");
                Writer wr3 = new FileWriter("outputSortedList.txt");


                Collections.reverse(listComplex);
                Collections.reverse(listDouble);
                for (Polynom<Complex> poly : listComplex) {
                    String s = poly.toString();
                    wr.write(s);
                    wr.write("\n");
                }
                for (Polynom<Double> poly : listDouble) {
                    String s = poly.toString();
                    wr.write(s);
                    wr.write("\n");
                }
                wr.flush();
                wr.close();

                for (Polynom<Complex> poly : setComplex) {
                    String s = poly.toString();
                    wr2.write(s);
                    wr2.write("\n");
                }
                for (Polynom<Double> poly : setDouble) {
                    String s = poly.toString();
                    wr2.write(s);
                    wr2.write("\n");
                }
                wr2.flush();
                wr2.close();

                Collections.sort(listComplex, new ComplexPolComparator().reversed());
                Collections.sort(listDouble, new DoublePolComparator().reversed());
                for (Polynom<Complex> poly : listComplex) {
                    String s = poly.toString();
                    wr3.write(s);
                    wr3.write("\n");
                }
                for (Polynom<Double> poly : listDouble) {
                    String s = poly.toString();
                    wr3.write(s);
                    wr3.write("\n");
                }
                wr3.flush();
                wr3.close();

        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
