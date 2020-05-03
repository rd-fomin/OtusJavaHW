package ru.otus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.otus.core.service.db.DbServiceUser;
import ru.otus.core.sessionmanager.SessionManagerHibernate;
import ru.otus.core.utils.HibernateUtils;
import ru.otus.domain.User;
import ru.otus.message.front.FrontendService;
import ru.otus.message.front.FrontendServiceImpl;
import ru.otus.message.front.handlers.GetUserDataResponseHandler;
import ru.otus.message.handlers.GetUserDataRequestHandler;
import ru.otus.message.messagesystem.MessageSystem;
import ru.otus.message.messagesystem.MessageType;
import ru.otus.message.messagesystem.MsClient;
import ru.otus.message.messagesystem.MsClientImpl;

@Configuration
@ComponentScan
@EnableWebMvc
public class DatabaseConfig implements WebMvcConfigurer {
    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    @Bean
    public SessionManagerHibernate sessionManager() {
        var sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class);
        return new SessionManagerHibernate(sessionFactory);
    }

    @Bean
    public MsClient databaseMsClient(MessageSystem messageSystem, DbServiceUser dbServiceUser) {
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem);
        databaseMsClient.addHandler(MessageType.GET_ALL, new GetUserDataRequestHandler(dbServiceUser));
        databaseMsClient.addHandler(MessageType.GET_BY_ID, new GetUserDataRequestHandler(dbServiceUser));
        databaseMsClient.addHandler(MessageType.GET_BY_LOGIN, new GetUserDataRequestHandler(dbServiceUser));
        databaseMsClient.addHandler(MessageType.SAVE_USER, new GetUserDataRequestHandler(dbServiceUser));
        messageSystem.addClient(databaseMsClient);
        return databaseMsClient;
    }

    @Bean
    public FrontendService frontendService(MessageSystem messageSystem) {
        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);
        FrontendService frontendService = new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
        frontendMsClient.addHandler(MessageType.GET_ALL, new GetUserDataResponseHandler(frontendService));
        frontendMsClient.addHandler(MessageType.GET_BY_ID, new GetUserDataResponseHandler(frontendService));
        frontendMsClient.addHandler(MessageType.GET_BY_LOGIN, new GetUserDataResponseHandler(frontendService));
        frontendMsClient.addHandler(MessageType.SAVE_USER, new GetUserDataResponseHandler(frontendService));
        messageSystem.addClient(frontendMsClient);
        return frontendService;
    }

}
