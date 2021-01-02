package com.example.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private DataSource dataSource;
    private Connection c;
    private User user;
    public void setDataSource(DataSource dataSource) {
        this.dataSource=dataSource;
    }
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values (?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = dataSource.getConnection();


        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        this.user = new User();
        this.user.setId(rs.getString("id"));
        this.user.setName(rs.getString("name"));
        this.user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return this.user;
    }
    public void deleteAll() throws SQLException{
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("Delete from users");
        ps.executeUpdate();
        ps.close();
        c.close();
    }
    public int getCount() throws SQLException{
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("select count(*) from users");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count= rs.getInt(1);
        rs.close();
        ps.close();
        c.close();
        return count;
    }


}
