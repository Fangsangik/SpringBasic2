package hello.core.config;

import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter (type = FilterType.ANNOTATION, classes =
        Configuration.class) //filter -> 걸러서 scan
)
public class AutoAppConfig {

}
