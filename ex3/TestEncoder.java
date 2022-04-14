import java.io.*;

public class TestEncoder {
    public static void main(String[] args) throws IOException {
        try {
            // read the txt file
            File file = new File(args[0]);
            if (!file.exists()) {
                throw new IllegalArgumentException();
            }
            Reader r = new FileReader(file);
            char[] buf = new char[(int) file.length()];
            int start = 0;
            int len = (int) file.length();
            r.read(buf, start, len);

            // encoder the file
            Writer w = new FileWriter("encrypted_text.txt");
            EncryptorWriter encoder = new EncryptorWriter(w);
            encoder.write(buf, start, buf.length);
            encoder.flush();
            encoder.close();

            File encrypt_file = new File("encrypted_text.txt");
            r = new FileReader(encrypt_file);
            int i;

            // print encrypt file
            while ((i = r.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println("\n");

            // read encrypt file and decrypt him
            char[] buf2 = new char[(int) encrypt_file.length()];
            r = new FileReader(encrypt_file);
            DecryptorReader decoder = new DecryptorReader(r);
            decoder.read(buf2, 0, (int) encrypt_file.length());
            decoder.close();
            r = new FileReader("decrypted_text.txt");

            // print decrypt file
            while ((i = r.read()) != -1) {
                System.out.print((char) i);
            }

        } catch (IllegalArgumentException e) {
            System.err.println("There is a problem with the received argument.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
