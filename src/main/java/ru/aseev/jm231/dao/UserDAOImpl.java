package ru.aseev.jm231.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aseev.jm231.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Component
@Transactional(readOnly = true)
public class UserDAOImpl {

    @PersistenceContext
    private EntityManager em;

//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public UserDAOImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
    @Transactional
    public void saveNewUser(User user){
        em.persist(em.merge(user));
    }

//    @Transactional
//    public void saveNewUser(User user) {
//        sessionFactory.getCurrentSession().save(user);
//    }
//
    @Transactional
    public List<User> allMyUsers() {
        return em.createQuery("SELECT e FROM User e", User.class).getResultList();
    }

//    @Transactional
//    @SuppressWarnings("unchecked")
//    public List<User> allMyUsers() {
//        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
//        return query.getResultList();
//    }
//
    @Transactional
    public User uniqueMyUser(long id) {
        return em.find(User.class, id);
    }

//    @Transactional
//    public User uniqueMyUser(int id) {
//        TypedQuery<User> queryUser = sessionFactory.getCurrentSession().createQuery("from User WHERE id = :id")
//                .setParameter("id", id);
//        return queryUser.getSingleResult();
//    }

    @Transactional
    public void changeUser (long id, User updatedUser) {
        User changedUser = em.find(User.class, id);
        changedUser.setName(updatedUser.getName());
        changedUser.setAge(updatedUser.getAge());
        em.merge(changedUser);
    }

//
//    @Transactional
//    public void changeUser(int id, User updatedUser) {
//        sessionFactory.getCurrentSession().createQuery("update User set name = :nameParam, age = :ageParam where id = :id")
//                .setParameter("id", id)
//                .setParameter("nameParam", updatedUser.getName())
//                .setParameter("ageParam", updatedUser.getAge())
//                .executeUpdate();
//    }

    @Transactional
    public void dropUser(long id){
        em.remove(uniqueMyUser(id));
    }
//
//    @Transactional
//    public void dropUser(int id) {
//        sessionFactory.getCurrentSession().createQuery("delete User WHERE id = :id")
//                .setParameter("id", id)
//                .executeUpdate();
//    }
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
