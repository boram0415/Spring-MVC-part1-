package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaVeControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaVeControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 리팩토링 시 구조적인 개선 이후 테스트 하고 그 이후 세밀한 개선이 필요함

@WebServlet(name="frontControllerServletV4" , urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaVeControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("FrontControllerServletV4.service");
        String requestURL = req.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURL);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // controller 에 request 값과 빈 model 넘겨줌
        Map<String, String> paramMap = createParamMap(req);
        Map<String, Object> model = new HashMap<>(); // V3 과 비교 했을 때는 이부분만 수정

        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);
        view.render(model,req,resp);


    }

    private static MyView viewResolver(String viewName) {
        MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return view;
    }

    private static String getViewName(ModelView mv) {
        return mv.getViewName();
    }

    // 디테일한 로직은 별도 메소드로 뽑는게 좋음
    private static Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        // request 에 있는 모든 key 값들을 뽑아와 순회한며
        req.getParameterNames().asIterator()
                // param 이라는 이름을 변수(key)로 지정하여 값을 paramMap 에 put
                .forEachRemaining(param -> paramMap.put(param, req.getParameter(param)));
        return paramMap;
    }
}
