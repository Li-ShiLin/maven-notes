package com.sin.imperial.court.impl;

import com.sin.imperial.court.api.EmpService;
import com.sin.imperial.court.mapper.EmpMapper;
import com.sin.imperial.entity.Emp;
import com.sin.imperial.util.ImperialCourtConst;
import com.sin.imperial.util.LoginFailedException;
import com.sin.imperial.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public Emp getEmpByLoginInfo(String loginAccount, String loginPassword) {
        // 执行密码加密
        String encodedLoginPassword = MD5Util.encode(loginPassword);
        // 创建 Example 对象，用于封装查询条件
        Example example = new Example(Emp.class);
        // 封装查询条件
        example.createCriteria().andEqualTo("loginAccount", loginAccount).andEqualTo("loginPassword", encodedLoginPassword);

        List<Emp> empList = empMapper.selectByExample(example);
        if (empList == null || empList.size() == 0) {
            throw new LoginFailedException(ImperialCourtConst.LOGIN_FAILED_MESSAGE);
        }
        return empList.get(0);
    }
}
