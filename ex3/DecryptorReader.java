import java.io.*;

public class DecryptorReader extends Reader {

    private Reader r;

    public DecryptorReader(Reader r)
    {
        this.r = r;
    }




    @Override
    public int read(char[] array, int start, int len) throws IOException {

        char[] temp2 = new char[5];
        String str = "";
        Writer wr = new FileWriter("decrypted_text.txt");
        int index = 0;
        int s = 0;

        while (s < len*5) {

            r.read(temp2, start, 5);
            for (int i = 0; i < temp2.length; i++)
            {
                str += Character.toString(temp2[i]);
            }
            char x = getMaxOccuringChar(str);
            array[index] = x;
            index++;
            s+=5;
            str = "";
        }

        wr.write(array, 0, len/5);
        wr.flush();
        wr.close();
        return array.length;
    }


    @Override
    public void close() throws IOException {
        r.close();
    }

    static final int ASCII_SIZE = 256;
    static char getMaxOccuringChar(String str)
    {
        // Create array to keep the count of individual
        // characters and initialize the array as 0
        int count[] = new int[ASCII_SIZE];

        // Construct character count array from the input
        // string.
        int len = str.length();
        for (int i=0; i<len; i++)
            count[str.charAt(i)]++;

        int max = -1;  // Initialize max count
        char result = ' ';   // Initialize result

        // Traversing through the string and maintaining
        // the count of each character
        for (int i = 0; i < len; i++) {
            if (max < count[str.charAt(i)]) {
                max = count[str.charAt(i)];
                result = str.charAt(i);
            }
        }
        if (max < 3)
        {
            return (char)-1;
        }
        return result;
    }
}