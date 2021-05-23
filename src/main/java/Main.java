import service.UserServicelmpl;

public class Main {
    public static void main(String[] args) {
        UserServicelmpl userSer = new UserServicelmpl();
        userSer.createUsersTable();
        userSer.saveUser("misha","ivanov",(byte) 25);
        userSer.saveUser("vasya","vasiliev",(byte) 24);
        userSer.saveUser("dmitrii","antonov",(byte) 23);
        userSer.saveUser("andrei","andreev",(byte) 22);
        System.out.println(userSer.getAllUsers());
        userSer.cleanUsersTable();
        userSer.dropUsersTable();
    }
}
