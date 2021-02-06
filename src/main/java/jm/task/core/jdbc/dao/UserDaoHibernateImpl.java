package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Util util = new Util();
    private SessionFactory sessionFactory = util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        String userTable = "CREATE TABLE User " +
                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(15) NOT NULL, " +
                " lastName VARCHAR(15) NOT NULL, " +
                " age INTEGER NOT NULL, " +
                " PRIMARY KEY (id))";
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(userTable).executeUpdate();
            transaction.commit();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        String str = "DROP TABLE IF EXISTS User";

        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(str).executeUpdate();
            transaction.commit();
            session.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Session session  = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            users = (List<User>)session.createQuery("FROM User").list();
            transaction.commit();
            session.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String str = "DELETE FROM User";
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(str).executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
