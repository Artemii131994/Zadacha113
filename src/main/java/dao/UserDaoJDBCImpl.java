package dao;

import model.User;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection conn = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() { // создание таблицы
        try(Statement statement = conn.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS testrt" +
                    "(id int not null auto_increment," +
                    " name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age int, " +
                    "PRIMARY KEY (id))");
          //  System.out.println("create table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() { //удаление таблицы
        try (Statement statement = conn.createStatement();) {
            statement.executeUpdate("DROP TABLE IF EXISTS testrt");
       //     System.out.println("drop table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) { // сохранение user в таблицы
        try (PreparedStatement prepaSt = conn.prepareStatement("INSERT INTO testrt(name, lastName, age) VALUES (?, ?, ?)");){
            prepaSt.setString(1, name);
            prepaSt.setString(2, lastName);
            prepaSt.setString(3, String.valueOf(age));
            prepaSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void removeUserById(long id) { //удаление по id
            try (PreparedStatement prSt = conn.prepareStatement("DELETE FROM testrt WHERE id=?");) {

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public List<User> getAllUsers() { // вывод всех user
        List<User> user = new ArrayList<>();
        try (ResultSet res = conn.createStatement().executeQuery("SELECT * FROM testrt");){

                        while (res.next()){
                User user1=new User();
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
        this.dropUsersTable();
        this.createUsersTable();
    }
}