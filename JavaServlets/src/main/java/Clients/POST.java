package Clients;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class POST {

    public static void main(String[] args) throws IOException {

        postMethod();
    }

    public static void postMethod() throws IOException {
        URL  url = new URL("http://localhost:8083/servlet");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);

        //Set request property content-type(as we choose what type we will use)
        urlConnection.setRequestProperty("Content-Type","application/json");
        urlConnection.setRequestProperty("Accept","application/json");

        OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());

        FileInputStream fis = new FileInputStream("D:\\Manik\\JavaServlets\\src\\main\\java\\Files\\send.json");

        // reads a byte at a time, if it reached end of the file, returns -1
        int content;
        while ((content = fis.read()) != -1) {
            osw.write(content);
            osw.flush();
        }
        System.out.println("Successfully sent");
        //close the connection
        osw.close();




    }
}
