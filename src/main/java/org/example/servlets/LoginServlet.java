package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        String username = req.getParameter("username"),
                password = req.getParameter("password");

        System.out.println(username);
        System.out.println(password);

        if(username == null) username = "maks";
        if(password == null) password = "1234";

        PrintWriter pw = resp.getWriter();

        String htmlResponse = "<html>";
        htmlResponse += "<h2>Your username is: " + username + "<br/>";
        htmlResponse += "Your password is: " + password + "</h2>";
        htmlResponse += "</html>";

        // return response
        pw.println(htmlResponse);
        pw.flush();
    }
}
