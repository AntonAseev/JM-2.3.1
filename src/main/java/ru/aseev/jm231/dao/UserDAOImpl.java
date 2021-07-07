package ru.aseev.jm231.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aseev.jm231.model.User;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Component
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void saveNewUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<User> allMyUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Transactional
    @Override
    public User uniqueMyUser(int id) {
        TypedQuery<User> queryUser = sessionFactory.getCurrentSession().createQuery("from User WHERE id = :id")
                .setParameter("id", id);
        return queryUser.getSingleResult();
    }

    @Transactional
    @Override
    public void changeUser(int id, User updatedUser) {
        sessionFactory.getCurrentSession().createQuery("update User set name = :nameParam, age = :ageParam where id = :id")
                .setParameter("id", id)
                .setParameter("nameParam", updatedUser.getName())
                .setParameter("ageParam", updatedUser.getAge())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void dropUser(int id) {
        sessionFactory.getCurrentSession().createQuery("delete User WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
//    public static int USERS_COUNT;
//
//    private List<User> usersList;
//
//    {
//        usersList = new ArrayList<>();
//
//        usersList.add(new User(++USERS_COUNT, "Anton", 27));
//        usersList.add(new User(++USERS_COUNT, "Svetlana", 25));
//        usersList.add(new User(++USERS_COUNT, "Ivan", 18));
//        usersList.add(new User(++USERS_COUNT, "Gleb", 101));
//    }
//
//    public List<User> allMyUsers() {
//        return usersList;
//    }
//
//    public User uniqueMyUser(int id) {
//        User uniqueUser = null;
//        for(User user : usersList) {
//            if (user.getId() == id) {
//                uniqueUser = user;
//            }
//        }
//        return uniqueUser;
//    }
//
////    public void saveNewUser(User user) {
////        user.setId(++USERS_COUNT);
////        usersList.add(user);
////    }
//
//    public void changeUser(int id, User updatedUser) {
//        User changedUser = uniqueMyUser(id);
//        changedUser.setName(updatedUser.getName());
//        changedUser.setAge(updatedUser.getAge());
//    }
//
//    public void dropUser(int id) {
//        usersList.removeIf(x -> x.getId() == id);
//    }
//}
