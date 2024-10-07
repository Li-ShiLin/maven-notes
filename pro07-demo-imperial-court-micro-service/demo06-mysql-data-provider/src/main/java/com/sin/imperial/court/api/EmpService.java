package com.sin.imperial.court.api;


import com.sin.imperial.entity.Emp;

public interface EmpService {
    Emp getEmpByLoginInfo(String loginAccount, String loginPassword);
}
