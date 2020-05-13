package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.db.DBService;
import ru.otus.db.DBServiceImpl;
import ru.otus.db.handlers.GetUserDataRequestHandler;
import ru.otus.front.FrontendService;
import ru.otus.front.FrontendServiceImpl;
import ru.otus.front.handlers.GetUserDataResponseHandler;
import ru.otus.messagesystem.MessageSystem;
import ru.otus.messagesystem.MessageType;
import ru.otus.messagesystem.MsClient;
import ru.otus.messagesystem.MsClientImpl;

@Configuration
@ComponentScan
public class FrontConfig {
    private static final Logger logger = LoggerFactory.getLogger(FrontConfig.class);

    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    @Bean
    public MsClient databaseMsClient(MessageSystem messageSystem) {
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem);
        DBService dbService = new DBServiceImpl();
        databaseMsClient.addHandler(MessageType.USER_DATA, new GetUserDataRequestHandler(dbService));
        messageSystem.addClient(databaseMsClient);
        return databaseMsClient;
    }

    @Bean
    public FrontendService frontendService(MessageSystem messageSystem) {
        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);
        FrontendService frontendService = new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
        frontendMsClient.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(frontendService));
        messageSystem.addClient(frontendMsClient);
        return frontendService;
    }

}
