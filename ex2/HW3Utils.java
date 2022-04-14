
public class HW3Utils {
	
	public static String formatDouble(double d)
	{
		String res  = String.format("%.2f", d);
		if (res.equals("-0.00"))
			res = "0.00";
		return res;
	}
	
	
}
