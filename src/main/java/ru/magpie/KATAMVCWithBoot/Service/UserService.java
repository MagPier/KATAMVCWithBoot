package ru.magpie.KATAMVCWithBoot.Service;



import ru.magpie.KATAMVCWithBoot.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    void removeUserById(long id);

    void updateUser(User userinfo, long id);

    User readUser(long id);
}
