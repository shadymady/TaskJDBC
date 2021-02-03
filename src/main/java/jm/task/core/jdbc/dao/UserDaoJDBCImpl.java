package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private PreparedStatement pstatement;
    private Util util = new Util();
    private Statement statement;
    private ResultSet resultSet;
    private Connection conn = util.getConnection();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String userTable = "CREATE TABLE User " +
                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(15) NOT NULL, " +
                " lastName VARCHAR(15) NOT NULL, " +
                " age INTEGER NOT NULL, " +
                " PRIMARY KEY (id))";
        try{
            statement = conn.createStatement();
            statement.executeUpdate(userTable);
        }catch(SQLException e){
                e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE User";
        try{
            statement = conn.createStatement();
            statement.executeUpdate(SQL);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void saveUser(String name, String lastName, byte age){
        String insert = "INSERT INTO User (name, lastName, age) VALUES(?, ?, ?)";
        try {
            pstatement = conn.prepareStatement(insert);
            pstatement.setString(1, name);
            pstatement.setString(2, lastName);
            pstatement.setByte(3, age);
            pstatement.execute();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public void removeUserById(long id) {
            String removeById = "DELETE FROM User WHERE id = ?";
            try {
                pstatement = conn.prepareStatement(removeById);
                pstatement.setLong(1, id);
                pstatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String str = "SELECT * FROM User";
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(str);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                users.add(user);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sqlClean = "DELETE FROM User";
        try{
            statement = conn.createStatement();
            statement.executeUpdate(sqlClean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
