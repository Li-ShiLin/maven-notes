package com.sin.imperial.court.servlet.module;

import com.sin.imperial.court.constant.ImperialCourtConst;
import com.sin.imperial.court.entity.Emp;
import com.sin.imperial.court.exception.LoginFailedException;
import com.sin.imperial.court.service.api.EmpService;
import com.sin.imperial.court.service.impl.EmpServiceImpl;
import com.sin.imperial.court.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends ModelBaseServlet {

    private final EmpService empService = new EmpServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1、获取请求参数
            String loginAccount = request.getParameter("loginAccount");
            String loginPassword = request.getParameter("loginPassword");


            // 2、调用 EmpService 方法执行登录逻辑  e.getEmpByLoginAccount()
            Emp emp = empService.getEmpByLoginAccount(loginAccount, loginPassword);

            // 3.通过request 获取HttpSession 对象
            HttpSession session = request.getSession();

            // 4.将查询到的 Emp 对象存入 session 域
            session.setAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME, emp);

            // 5.前往指定页面视图

//            // 前往临时页面
//            String templateName = "memorials-list";
//            processTemplate(templateName, request, response);

            // 前往正式的目标地址
            String templateName = "memorials-list";
            response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");
        } catch (Exception e) {
            e.printStackTrace();
            // 6.判断此处捕获到的异常是否是登录失败异常
            if (e instanceof LoginFailedException) {
                // 7.如果是登录失败异常则跳转回登录页面
                // 7.1 将异常信息存入请求域
                request.setAttribute("message", e.getMessage());
                // 7.2 处理视图：index
                processTemplate("index", request, response);
            } else {
                // 8.如果不是登录异常则封装为运行时异常继续抛出
                throw new ServletException(e);
            }


        }

    }


    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1、通过request 对象获取 Httpsession 对象
        HttpSession session = request.getSession();


        // 2、将 Httpsession 对象强制失效
        session.invalidate();

        // 3、回到首页
        String templateName = "index";
        processTemplate(templateName, request, response);

    }
}
