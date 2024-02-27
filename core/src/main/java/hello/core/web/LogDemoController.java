package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.logDemo.LogDemoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger logger;

    @RequestMapping("log-demo")
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURI().toString();
        logger.setRequestURL(requestURL);

        logger.log("controller test");
        logDemoService.logic("testID");
        return "ok";
    }
}
