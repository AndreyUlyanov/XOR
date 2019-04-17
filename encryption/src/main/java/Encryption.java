//package encryption.src

import java.io.*;

public class Encryption {

    private final String inputFile;

    private final String key;

    public Encryption(String inputName, String key) {
        this.inputFile = inputName;
        this.key = key;
    }

    public byte[] encrypt(InputStream in, OutputStream out) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out)) {

                String text = reader.toString();
                byte[] arr = text.getBytes();

                writer.write(arr.);
                writer.write("\n\n\n");

                byte[] keyarr = key.getBytes();
                byte[] result = new byte[arr.length];
                for (int i = 0; i < arr.length; i++) {
                    result[i] = arr[i];
                    //result[i] = (byte) (arr[i] ^ keyarr[i % keyarr.length]);

                    //writer.write(result[i]);
                }

                //result = arr;

                if (arr == result) writer.write("!!!!!!!!!!!!!!!!!!!");

                writer.write(result.toString());
                writer.write("\n\n\n");
                writer.write(arr.toString());
                return result;
            }
        }
    }

    public byte[] encrypt(String key, String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                return encrypt(inputStream, outputStream);
            }
        }
    }
}