import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class EncryptorWriter extends Writer{

    private Writer w;

    public EncryptorWriter (Writer w)
    {
        this.w = w;
    }

    @Override
    public void write(char[] buf, int offSet, int len) throws IOException {
        int i,k = 0;
        int count = 0;

        int newLen = buf.length*5 ;

        for (i =0; i < buf.length; i++)
        {
            if (buf[i] == '\r')
            {
                k++;
            }
        }
        char[] temp = new char[newLen];
        if (k>0)
        {
            temp = new char[newLen - k*2-6];
        }

        for (i =0; i < buf.length; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (buf[i] == '\r')
                {
                    newLen-=10;
                    continue;
                }
                temp[count] = buf[i];
                count++;
            }
        }
        buf = temp;
        w.write(buf);
    }

    @Override
    public void flush() throws IOException {
        w.flush();
    }

    @Override
    public void close() throws IOException {
        w.close();
    }
}
