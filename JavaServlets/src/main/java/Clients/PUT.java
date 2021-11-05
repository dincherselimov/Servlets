package Clients;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class makes HTTP PUT request
 */
public class PUT {

    public static void main(String[] args) throws IOException {
        PUT putMethod = new PUT();
        putMethod.putMethod();
    }

    public void putMethod() throws IOException {
        //specify the url address
        URL  url = new URL("http://localhost:8083/servlet");

        //open a new connection channel
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        //set the request method and set the doOutput = true
        urlConnection.setRequestMethod("PUT");
        urlConnection.setDoOutput(true);

        //Set request property content-type(as we choose what type we will use)
        urlConnection.setRequestProperty("Content-Type","application/xml");
        urlConnection.setRequestProperty("Accept","application/xml");

        OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());

        //specify which file(data) to send
        FileInputStream fis = new FileInputStream("D:\\Manik\\JavaServlets\\src\\main\\java\\FilesToSend\\servletfile.xml");

        // reads a byte at a time, if it reached end of the file, returns -1
        int content;
        while ((content = fis.read()) != -1) {
            osw.write(content);
            osw.flush();
        }
        System.out.println("Successfully sent");
        System.out.println("HTTP Response Code:");

        //HTTP response code
        System.err.println(urlConnection.getResponseCode());

        //close the connection
        osw.close();
    }
}