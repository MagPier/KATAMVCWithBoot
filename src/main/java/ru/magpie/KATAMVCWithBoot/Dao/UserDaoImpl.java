package ru.magpie.KATAMVCWithBoot.Dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.magpie.KATAMVCWithBoot.model.User;



import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void add(User user) {


        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public User readUser(long id) {
        return entityManager.find(User.class, id);
    }
    @Transactional
    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
    @Transactional
    @Override
    public void removeUserById(long id) {
        User user = readUser(id);
        if (null == user) {
            throw new NullPointerException("User not found");
        }
        entityManager.remove(user);
        entityManager.flush();
    }
    @Transactional
    @Override
    public void updateUser(User userinfo,long id) {
      User old =  entityManager.find(User.class, id);
      old.setLastName(userinfo.getLastName());
      old.setFirstName(userinfo.getFirstName());
      old.setEmail(userinfo.getEmail());
      entityManager.flush();
    }
    @Transactional
    @Override
    public void initialAddUser() {

        add(new User("Ivan", "Ivanov", "ivan@internet.ru"));
        add(new User("Petr", "Petrov", "petr@internet.ru"));
        add(new User("Semen", "Semenov", "semen@internet.ru"));
        add(new User("Sidor", "Sidorov", "sidor@internet.ru"));
    }
}
