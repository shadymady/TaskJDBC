package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        List<User> user;
        UserDaoJDBCImpl table = new UserDaoJDBCImpl();
        table.createUsersTable();
        table.saveUser("Zara", "Ali", (byte) 18);
        table.saveUser("Faruk", "Nari", (byte) 24);
        table.saveUser("Makmandu", "Piri", (byte) 22);
        table.saveUser("Zambi", "Kukulu", (byte) 16);
        user = table.getAllUsers();
        Iterator<User> iterator = user.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        table.cleanUsersTable();
        table.dropUsersTable();
        }
}


