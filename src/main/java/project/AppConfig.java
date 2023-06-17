package project;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.persistence.PersistenceContext;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    
    @PersistenceContext
    private final EntityManager em;

    @Bean
    public JPAQueryFactory qf() {
        return new JPAQueryFactory(em);
    }

}