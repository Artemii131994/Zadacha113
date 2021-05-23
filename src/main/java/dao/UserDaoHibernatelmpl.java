package dao;

import model.User;

import java.util.List;

class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {// создание таблицы

    }

    @Override
    public void dropUsersTable() {//удаление таблицы

    }

        @Override
    public void saveUser(String name, String lastName, byte age) {// сохранение таблицы

    }

    @Override
    public void removeUserById(long id) {//удаление по id

    }

    @Override
    public List<User> getAllUsers() { // вывод всех user
        return null;
    }

    @Override
    public void cleanUsersTable() { // очистка таблицы

    }
}