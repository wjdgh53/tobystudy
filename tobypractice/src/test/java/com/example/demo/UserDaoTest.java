package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-applicationContext.xml")
public class UserDaoTest {

    private UserDao dao;
    @Before
    public void setUp(){
        dao = new UserDao();
        DataSource dataSource = new SingleConnectionDataSource(
                "jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC","root","U82taco@",true);
        dao.setDataSource(dataSource);
    }
    @Test
    public void count() throws SQLException, ClassNotFoundException {


        User user1 = new User("test1","Jeongho","1");
        User user2 = new User("test2","Seulgi","2");
        User user3 = new User("test3","Minji","3");
        dao.deleteAll();
        assertThat(dao.getCount(),is(0));
        dao.add(user1);
        assertThat(dao.getCount(),is(1));
        dao.add(user2);
        assertThat(dao.getCount(),is(2));
        dao.add(user3);
        assertThat(dao.getCount(),is(3));
    }
    @Test
    public void addAndGet() throws SQLException {
        User user1 = new User("test1","Jeongho","1");
        User user2 = new User("test2","Seulgi","2");
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));
        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));
        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(),is(user1.getName()));
        assertThat(userget1.getPassword(),is(user1.getPassword()));
        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(),is(user2.getName()));
        assertThat(userget2.getPassword(),is(user2.getPassword()));
    }
    @Test(expected= EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException{
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));
        dao.get("unkonwn_id");
    }
}

