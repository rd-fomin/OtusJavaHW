package ru.otus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.core.service.DbServiceUser;
import ru.otus.core.sessionmanager.SessionManagerHibernate;
import ru.otus.core.utils.HibernateUtils;
import ru.otus.db.handlers.CreateUserRequestHandler;
import ru.otus.db.handlers.GetAllRequestHandler;
import ru.otus.domain.User;
import ru.otus.messagesystem.MessageSystem;
import ru.otus.messagesystem.MessageType;
import ru.otus.messagesystem.MsClient;
import ru.otus.messagesystem.MsClientImpl;

@Configuration
public class DatabaseConfig {
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    @Bean
    public SessionManagerHibernate sessionManager() {
        var sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class);
        return new SessionManagerHibernate(sessionFactory);
    }

    @Bean
    public MsClient databaseMsClient(MessageSystem messageSystem, DbServiceUser dbService) {
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem);
        databaseMsClient.addHandler(MessageType.CREATE_USER, new CreateUserRequestHandler(dbService));
        databaseMsClient.addHandler(MessageType.GET_ALL, new GetAllRequestHandler(dbService));
        messageSystem.addClient(databaseMsClient);
        return databaseMsClient;
    }

}
