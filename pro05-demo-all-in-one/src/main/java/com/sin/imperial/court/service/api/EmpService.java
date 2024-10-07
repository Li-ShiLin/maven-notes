package com.sin.imperial.court.service.api;

import com.sin.imperial.court.entity.Emp;

public interface EmpService {

    Emp getEmpByLoginAccount(String loginAccount, String loginPassword);

}
