  package ru.otus.db.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.app.common.Serializers;
import ru.otus.core.service.DbServiceUser;
import ru.otus.domain.User;
import ru.otus.messagesystem.Message;
import ru.otus.messagesystem.MessageType;
import ru.otus.messagesystem.RequestHandler;

import java.util.List;
import java.util.Optional;

public class GetAllRequestHandler implements RequestHandler {
      public static final Logger logger = LoggerFactory.getLogger(GetAllRequestHandler.class);

      private final DbServiceUser dbService;

      public GetAllRequestHandler(DbServiceUser dbService) {
          this.dbService = dbService;
      }

      @Override
      public Optional<Message> handle(Message msg) {
          List<User> users = dbService.findAll();
          logger.info("Requested message from {} to {}, inserted user with id: {}", msg.getFrom(), msg.getTo(), users);
          return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.GET_ALL.getValue(), Serializers.serialize(users)));
      }

}
