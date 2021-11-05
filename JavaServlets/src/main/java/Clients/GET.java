package Clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is used to send GET Request
 */
public class GET {

    //execute the getMethod() method
    public static void main(String[] args) throws IOException {
        GET getMethod = new GET();
        getMethod.getMethod();
    }

    /**
     * Make a HTTP GET request
     * @throws IOException
     */
    public  void getMethod() throws IOException {
        //Set the URL
        URL url = new URL("http://localhost:8083/servlet");

        //Open a connection
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        //Set the request method to GET,POST,HEAD,PUT,DELETE
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Content-type", "application/xml");

        //Read and print the response
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
        }

        System.out.println("HTTP Response Code:");

        //HTTP response code
        System.err.println(urlConnection.getResponseCode());

        in.close();
    }
}