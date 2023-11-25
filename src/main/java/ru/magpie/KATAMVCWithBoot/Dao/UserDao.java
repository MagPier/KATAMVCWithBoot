package ru.magpie.KATAMVCWithBoot.Dao;



import ru.magpie.KATAMVCWithBoot.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();

    void removeUserById(long id);

    void updateUser(User userinfo, long id);

    void initialAddUser();

    User readUser(long id);
}

