package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// WebServlet 으로 선언된 것은 중복되면 안됨
@WebServlet(name ="helloServlet",urlPatterns = "/hello")
public class HelloServlet  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("req = " + req);
        System.out.println("resp = " + resp);
        String username = req.getParameter("username");
        System.out.println(username);
        // 헤더 정보에 들어가는 정보들
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        //

        resp.getWriter().write("hello " + username);

    }
}
