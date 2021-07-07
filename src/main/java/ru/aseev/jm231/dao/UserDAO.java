package ru.aseev.jm231.dao;

import ru.aseev.jm231.model.User;

import java.util.List;

public interface UserDAO {
    void saveNewUser(User user);
    List<User> allMyUsers();
    User uniqueMyUser(int id);
    void changeUser(int id, User updatedUser);
    void dropUser(int id);

}
