import java.util.Comparator;
import java.util.Map;

public class DoublePolComparator implements Comparator<Polynom<Double>> {
    @Override
    public int compare(Polynom<Double> pol1, Polynom<Double> pol2) {
        for (Map.Entry<Integer, FieldMember<Double>> ceo : pol1.getCoefficients().entrySet())
        {
            for (Map.Entry<Integer, FieldMember<Double>> ceo2 : pol2.getCoefficients().entrySet())
            {
                if (ceo.getKey() == ceo2.getKey())
                {

                    if (ceo.getValue().getValue() != ceo2.getValue().getValue())
                    {
                        return (int) (ceo.getValue().getValue() - ceo2.getValue().getValue());
                    }
                }
            }
        }
        return pol1.degree() - pol2.degree();
    }
}
