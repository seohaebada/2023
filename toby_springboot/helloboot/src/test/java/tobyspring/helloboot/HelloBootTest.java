package tobyspring.helloboot;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
// 스프링 Context 를 이용하는 스프링 Container 테스트 가능
@ExtendWith(SpringExtension.class)
// Bean 가져오기
// 모든 빈 구성정보를 가져오는 시작점 클래스를 적어서 모든 빈을 가져온다.
@ContextConfiguration(classes = HellobootApplication.class)
// application.properties 적용은 스프링부트 초기화 과정에서 추가해주므로 자동으로 추가되지 않아서 설정이 필요하다.
@TestPropertySource("classpath:/application.properties")
@Transactional
public @interface HelloBootTest {
}
