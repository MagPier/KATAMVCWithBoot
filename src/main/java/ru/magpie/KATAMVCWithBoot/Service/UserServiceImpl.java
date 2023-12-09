package ru.magpie.KATAMVCWithBoot.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.magpie.KATAMVCWithBoot.Dao.UserDao;
import ru.magpie.KATAMVCWithBoot.model.User;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);

    }

    @Transactional
    @Override
    public void updateUser(User userinfo, long id) {
        User old = userDao.readUser(id);
        old.setLastName(userinfo.getLastName());
        old.setFirstName(userinfo.getFirstName());
        old.setEmail(userinfo.getEmail());
        userDao.updateUser(old);
    }

    @Override
    public User readUser(long id) {
        return userDao.readUser(id);
    }

}

