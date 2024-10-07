package com.sin.imperial.court;


import com.sin.imperial.entity.Emp;
import com.sin.imperial.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @FeignClient 注解将当前接口标记为服务暴露接口
// name 属性：指定被暴露服务的微服务名称
@FeignClient(name = "demo06-mysql-data-provider")
public interface MySQLProvider {

    @RequestMapping("/remote/get/emp/by/login/info")
    ResultEntity<Emp> getEmpByLoginInfo(
            // @RequestParam 无论如何不能省略
            @RequestParam("loginAccount") String loginAccount, @RequestParam("loginPassword") String loginPassword);
}
