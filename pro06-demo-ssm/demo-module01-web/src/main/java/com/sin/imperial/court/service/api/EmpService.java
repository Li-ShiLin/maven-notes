package com.sin.imperial.court.service.api;

import com.sin.imperial.court.entity.Emp;

public interface EmpService {
    Emp getEmpByLogin(String loginAccount, String loginPassword);
}
