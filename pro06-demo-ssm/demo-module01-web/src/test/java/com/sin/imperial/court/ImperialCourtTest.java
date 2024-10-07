package com.sin.imperial.court;

import com.sin.imperial.court.entity.Emp;
import com.sin.imperial.court.entity.EmpExample;
import com.sin.imperial.court.mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//Spring 和 junit5 整合
@ExtendWith(SpringExtension.class)
@ContextConfiguration(value = {"classpath:spring-persist.xml"})
public class ImperialCourtTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println("connection=" + connection);
    }


    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testEmpMapper() {
        List<Emp> empList = empMapper.selectByExample(new EmpExample());
        for (Emp emp : empList) {
            System.out.println("emp = " + emp);
        }
    }


}
