package com.sin.imperial.court.dao.impl;

import com.sin.imperial.court.dao.BaseDao;
import com.sin.imperial.court.dao.api.EmpDao;
import com.sin.imperial.court.entity.Emp;

public class EmpDaoImpl extends BaseDao<Emp> implements EmpDao {

    @Override
    public Emp selectEmpByLoginAccount(String loginAccount, String encodedLoginPassword) {

        // 1.编写SQL语句
        String sql = "select emp_id empId," +
                "emp_name empName," +
                "emp_position empPosition," +
                "login_account loginAccount," +
                "login_password loginPassword " +
                "from t_emp where login_account = ? and login_password = ? ";

        // 2、调用父类方法查询单个对象
        return super.getSingleBean(sql, Emp.class, loginAccount, encodedLoginPassword);
    }

}