package Servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * This class handles main methods of Java Servlet(init(), service(), doGet(), doPut(), destroy();)
 */
@WebServlet(name = "helloServlet", value = "/servlet")
public class GetAndPut extends HttpServlet {

    public void init() {}

    /**
     * Implement the service() method
     * Check for incoming type of request and determine which method execute(handle)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String req_method = req.getMethod().trim();

        System.out.println(req_method);

        if ("PUT".equals(req_method)){
            doPut(req,resp);
        }
        else if ("GET".equals(req_method)){
            doGet(req,resp);
        }
    }

    /**
     * Handling the doGet() method
     * @param request
     * @param response
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //set the content type
        response.setContentType("application/json");

        //specify the file name
        File file = new File("D:\\Manik\\JavaServlets\\src\\main\\java\\ReceivedFiles\\servletfile.xml");

        //read the content of the file
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                //show the content on browser(this is a server response to the client)
                PrintWriter out = response.getWriter();
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Handling the doPut() method
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //allow put requests
        response.addHeader("Allow","PUT");

        //get the inputStream
        InputStream bodyStream = request.getInputStream();


        //reading all remaining byte from the input stream
        byte []b = bodyStream.readAllBytes();
        String toWrite = new String(b);

        //Write the content of the incoming data into a file
        try {
            //Save the incoming data to a file
            FileWriter putWriter = new FileWriter("D:\\Manik\\JavaServlets\\src\\main\\java\\ReceivedFiles\\servletfile.xml");
            putWriter.write(toWrite);

            //close the connection
            putWriter.close();
            System.out.println("Received file from client: \n" + toWrite);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}