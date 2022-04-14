import java.util.Comparator;
import java.util.Map;

public class ComplexPolComparator implements Comparator<Polynom<Complex>> {

    @Override
    public int compare(Polynom<Complex> pol1, Polynom<Complex> pol2) {

        for (Map.Entry<Integer, FieldMember<Complex>> ceo : pol1.getCoefficients().entrySet())
        {
            for (Map.Entry<Integer, FieldMember<Complex>> ceo2 : pol2.getCoefficients().entrySet())
            {
                    if (ceo.getKey() == ceo2.getKey())
                {
                    if (pol1.getCoefficients().size() != pol2.getCoefficients().size())
                    {
                        return pol1.getCoefficients().size() - pol2.getCoefficients().size();
                    }
                    if (!ceo.getValue().getValue().equals(ceo2.getValue().getValue()))
                    {
                        return ceo.getValue().getValue().compareTo(ceo2.getValue().getValue());
                    }
                }
            }
        }
        return pol1.degree() - pol2.degree();
    }
}
