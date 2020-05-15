  package ru.otus.db.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.app.common.Serializers;
import ru.otus.core.service.DbServiceUser;
import ru.otus.domain.User;
import ru.otus.messagesystem.Message;
import ru.otus.messagesystem.MessageType;
import ru.otus.messagesystem.RequestHandler;

import java.util.Optional;


  public class CreateUserRequestHandler implements RequestHandler {
      public static final Logger logger = LoggerFactory.getLogger(CreateUserRequestHandler.class);

      private final DbServiceUser dbService;

      public CreateUserRequestHandler(DbServiceUser dbService) {
          this.dbService = dbService;
      }

      @Override
      public Optional<Message> handle(Message msg) {
          User data = Serializers.deserialize(msg.getPayload(), User.class);
          long id = dbService.save(data);
          logger.info("Requested message from {} to {}, inserted user with id: {}", msg.getFrom(), msg.getTo(), id);
          return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.CREATE_USER.getValue(), Serializers.serialize(id)));
      }

  }
