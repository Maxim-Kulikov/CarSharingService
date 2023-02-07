package org.example.servlets;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*String username = req.getParameter("username"),
                password = req.getParameter("password");

        System.out.println(username);
        System.out.println(password);

        if(username == null) username = "maks";
        if(password == null) password = "1234";
*/       resp.setContentType("text/html");

        String username = "maks", password = "1234";
        PrintWriter pw = resp.getWriter();

        String htmlResponse = "<html>";
        htmlResponse += "<h2>Your username is: " + username + "<br/>";
        htmlResponse += "Your password is: " + password + "</h2>";
        htmlResponse += "</html>";

        // return response
        pw.println(htmlResponse);
        //pw.flush();
    }
}
