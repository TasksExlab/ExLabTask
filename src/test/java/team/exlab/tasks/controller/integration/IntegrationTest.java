package team.exlab.tasks.controller.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.ExLabTasksApplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@SpringBootTest(classes = ExLabTasksApplication.class)
@Transactional
public @interface IntegrationTest {
}


