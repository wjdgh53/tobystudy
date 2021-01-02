package com.example.demo;

import jdk.jfr.StackTrace;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
//import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserDaoTest {

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("userDao",UserDao.class);

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

}

