package hello.servlet.web.servlet;

import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // test/html 오타로 인해 url 요청 시 다운로드 되는 에러있었음
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter w = resp.getWriter();
        w.write("<!DOC TYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "    <title>Title</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "<form action=\"/servlet/members/save\" method=\"post\">\n" +
        "    username: <input type=\"text\" name=\"username\" />\n" +
        "    age:      <input type=\"text\" name=\"age\" />\n" +
        " <button type=\"submit\">전송</button>\n" + "</form>\n" +
        "</body>\n" +
        "</html>\n");

    }
}
