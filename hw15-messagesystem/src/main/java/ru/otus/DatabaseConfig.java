package ru.otus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.core.sessionmanager.SessionManagerHibernate;
import ru.otus.core.utils.HibernateUtils;
import ru.otus.domain.User;

@Configuration
@ComponentScan
public class DatabaseConfig {

    @Bean
    public SessionManagerHibernate sessionManager() {
        var sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class);
        return new SessionManagerHibernate(sessionFactory);
    }

}
