package service;

import dao.UserDaoJDBCImpl;
import model.User;

import java.util.List;

public class UserServicelmpl implements UserService {
    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() {

        userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {

        userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {

        userDaoJDBC.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {

        userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {

        return userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {

        userDaoJDBC.cleanUsersTable();
    }
}