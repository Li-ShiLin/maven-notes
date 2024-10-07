package com.sin.imperial.court.service.impl;

import com.sin.imperial.court.constant.ImperialCourtConst;
import com.sin.imperial.court.dao.api.EmpDao;
import com.sin.imperial.court.dao.impl.EmpDaoImpl;
import com.sin.imperial.court.entity.Emp;
import com.sin.imperial.court.exception.LoginFailedException;
import com.sin.imperial.court.service.api.EmpService;
import com.sin.imperial.court.util.MD5Util;
public class EmpServiceImpl implements EmpService {

    private final EmpDao empDao = new EmpDaoImpl();

    @Override
    public Emp getEmpByLoginAccount(String loginAccount, String loginPassword) {

        // 1、对密码执行加密
        String encodedLoginPassword = MD5Util.encode(loginPassword);

        // 2、根据账户和加密密码查询数据库
        Emp emp = empDao.selectEmpByLoginAccount(loginAccount, encodedLoginPassword);

        // 3、检査 Emp 对象是否为 null
        if (emp != null) {
            // 4.不为null
            return emp;
        } else {
            throw new LoginFailedException(ImperialCourtConst.LOGIN_FAILED_MESSAGE);
        }
    }
}
