package com.sin.maven;

import com.sin.imperial.court.dao.BaseDao;
import com.sin.imperial.court.entity.Emp;
import com.sin.imperial.court.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class ImperialCourtTest {

    private final BaseDao<Emp> baseDao = new BaseDao<>();

    @Test
    public void testGetConnection() {
        Connection connection = JDBCUtils.getConnection();
        System.out.println("connection = " + connection);
        JDBCUtils.releaseConnection(connection);
    }


    /**
     * 测试baseDao：查询单个对象
     */
    @Test
    public void testGetSingleBean() {
        String sql = "select emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from t_emp where emp_id = ? ";
        Emp emp = baseDao.getSingleBean(sql, Emp.class, 1);
        System.out.println("emp: " + emp);
        // emp: Emp{empId=1, empName='爱新觉罗·玄烨', empPosition='emperor', loginAccount='xiaoxuanzi1654', loginPassword='25325C896624D444B2E241807DCAC98B'}
    }


    /**
     * 测试baseDao：查询多个对象
     */
    @Test
    public void testGetBeanList() {
        String sql = "select emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from t_emp";
        List<Emp> empList = baseDao.getBeanList(sql, Emp.class);
        for (Emp emp : empList) {
            System.out.println("emp: " + emp);
        }
    }


    /**
     * 测试baseDao：通用的增删改查方法
     */
    @Test
    public void testUpdate() {
        String sql = "update t_emp set emp_position = ? where emp_id = ?";
        String empPosition = "minister";
        String empId = "3";
        int affectedRowNumber = baseDao.update(sql, empPosition, empId);
        System.out.println("affectedRowNumber =" + affectedRowNumber);
    }

}
