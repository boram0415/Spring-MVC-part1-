package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="responseHeaderServlet" , urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //[status-line]
        resp.setStatus(HttpServletResponse.SC_OK);
        //[response-header]
        resp.setHeader("Content-type", "test/plain;charset=utf-8");
        resp.setHeader("Cache-Control", "no-cache, no-store , must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("my-header", "hi");

        resp.getWriter().write("안녕");
    }
}


