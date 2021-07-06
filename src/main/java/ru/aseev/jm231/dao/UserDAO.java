package ru.aseev.jm231.dao;

import org.springframework.stereotype.Component;
import ru.aseev.jm231.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static int USERS_COUNT = 0;
    private List<User> usersList;

    {
        usersList = new ArrayList<>();

        usersList.add(new User(++USERS_COUNT, "Anton", 27));
        usersList.add(new User(++USERS_COUNT, "Svetlana", 25));
        usersList.add(new User(++USERS_COUNT, "Ivan", 18));
        usersList.add(new User(++USERS_COUNT, "Gleb", 101));
    }

    public List<User> allMyUsers() {
        return usersList;
    }

    public User uniqueMyUser(int id) {
        User uniqueUser = null;
        for(User user : usersList) {
            if (user.getId() == id) {
                uniqueUser = user;
            }
        }
        return uniqueUser;
    }

    public void saveNewUser(User user) {
        user.setId(++USERS_COUNT);
        usersList.add(user);
    }

    public void changeUser(int id, User updatedUser) {
        User changedUser = uniqueMyUser(id);
        changedUser.setName(updatedUser.getName());
        changedUser.setAge(updatedUser.getAge());
    }

    public void dropUser(int id) {
        usersList.removeIf(x -> x.getId() == id);
    }
}
