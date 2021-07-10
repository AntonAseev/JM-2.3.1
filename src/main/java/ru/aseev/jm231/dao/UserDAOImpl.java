package ru.aseev.jm231.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aseev.jm231.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Component
@Transactional(readOnly = true)
public class UserDAOImpl {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveNewUser(User user){
        em.persist(em.merge(user));
    }

    @Transactional
    public List<User> allMyUsers() {
        return em.createQuery("SELECT e FROM User e", User.class).getResultList();
    }

    @Transactional
    public User uniqueMyUser(long id) {
        return em.find(User.class, id);
    }

    @Transactional
    public void changeUser (long id, User updatedUser) {
        User changedUser = em.find(User.class, id);
        changedUser.setName(updatedUser.getName());
        changedUser.setAge(updatedUser.getAge());
        em.merge(changedUser);
    }

    @Transactional
    public void dropUser(long id){
        em.remove(uniqueMyUser(id));
    }
}
