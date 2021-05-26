package dao;

import model.User;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() { // создание таблицы
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS testrt" +
                    "(id int not null auto_increment," +
                    " name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age int, " +
                    "PRIMARY KEY (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() { //удаление таблицы
        try (Statement statement = Util.getConnection().createStatement();) {
            statement.executeUpdate("DROP TABLE IF EXISTS testrt");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) { // сохранение user в таблицы
        try (PreparedStatement prepaSt = Util.getConnection().prepareStatement("INSERT INTO testrt(name, lastName, age) VALUES (?, ?, ?)");) {
            prepaSt.setString(1, name);
            prepaSt.setString(2, lastName);
            prepaSt.setString(3, String.valueOf(age));
            prepaSt.executeUpdate();
            System.out.println("User с именем " + name + " добавлен базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) { //удаление по id
        try (PreparedStatement prSt = Util.getConnection().prepareStatement("DELETE FROM testrt WHERE id=?");) {
            prSt.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() { // вывод всех user
        List<User> user = new ArrayList<>();
        try (ResultSet res = Util.getConnection().createStatement().executeQuery("SELECT * FROM testrt")) {

            while (res.next()) {
                User user1 = new User();
                user1.setId(res.getLong("id"));
                user1.setName(res.getString("name"));
                user1.setLastName(res.getString("lastname"));
                user1.setAge(res.getByte("age"));
                user.add(user1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void cleanUsersTable() { // очистка таблицы
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE testrt");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}