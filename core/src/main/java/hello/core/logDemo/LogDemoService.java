package hello.core.logDemo;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final MyLogger logger;

    public void logic(String id){
        logger.log("service id :" + id);
    }
}
