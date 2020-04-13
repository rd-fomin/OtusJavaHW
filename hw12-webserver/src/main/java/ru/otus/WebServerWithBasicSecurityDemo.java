package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.security.LoginService;
import org.hibernate.SessionFactory;
import ru.otus.core.dao.*;
import ru.otus.core.service.DBServiceUser;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.core.sessionmanager.SessionManagerHibernate;
import ru.otus.core.model.User;
import ru.otus.server.UsersWebServer;
import ru.otus.server.UsersWebServerWithBasicSecurity;
import ru.otus.services.InMemoryLoginServiceImpl;
import ru.otus.services.TemplateProcessor;
import ru.otus.services.TemplateProcessorImpl;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/users

    // REST сервис
    http://localhost:8080/api/user/3
*/
public class WebServerWithBasicSecurityDemo {
    private static final int WEB_SERVER_PORT = 8085;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new InDbMemoryUserDao(sessionManager);
        DBServiceUser serviceUser = new DbServiceUserImpl(userDao);

        serviceUser.save(new User(1L, "Крис Гир", "user1", "11111"));
        serviceUser.save(new User(2L, "Ая Кэш", "user2", "11111"));
        serviceUser.save(new User(3L, "Десмин Боргес", "user3", "11111"));
        serviceUser.save(new User(4L, "Кетер Донохью", "user4", "11111"));
        serviceUser.save(new User(5L, "Стивен Шнайдер", "user5", "11111"));
        serviceUser.save(new User(6L, "Джанет Вэрни", "user6", "11111"));
        serviceUser.save(new User(7L, "Брэндон Смит", "user7", "11111"));

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        LoginService loginService = new InMemoryLoginServiceImpl(serviceUser);

        UsersWebServer usersWebServer = new UsersWebServerWithBasicSecurity(WEB_SERVER_PORT,
                loginService, serviceUser, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
