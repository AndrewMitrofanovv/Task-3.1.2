package com.spring.example.web.DAO;

import com.spring.example.web.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class UserDAOImpl implements UserDAO {


    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> resultList = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        return resultList;
    }

    public void save(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User user = findUser(id);
        if (user == null) {
            System.err.println("Пользователь не найден");
        }
        entityManager.remove(user);
        entityManager.flush();
    }

    @Transactional
    @Override
    public void update(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Transactional
    @Override
    public User findUser(Long id) {
        return entityManager.find(User.class, id);
    }
}
