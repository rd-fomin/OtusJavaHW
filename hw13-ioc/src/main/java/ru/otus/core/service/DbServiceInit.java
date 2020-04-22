package ru.otus.core.service;

import ru.otus.core.service.database.DbServiceUser;
import ru.otus.domain.User;

public class DbServiceInit {

    private final DbServiceUser dbServiceUser;

    public DbServiceInit(DbServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    public void init(DbServiceUser serviceUser) {
        serviceUser.save(new User("Крис Гир", "user1", "11111"));
        serviceUser.save(new User("Ая Кэш", "user2", "11111"));
        serviceUser.save(new User("Десмин Боргес", "user3", "11111"));
        serviceUser.save(new User("Кетер Донохью", "user4", "11111"));
        serviceUser.save(new User("Стивен Шнайдер", "user5", "11111"));
        serviceUser.save(new User("Джанет Вэрни", "user6", "11111"));
        serviceUser.save(new User("Брэндон Смит", "user7", "11111"));
    }

}
