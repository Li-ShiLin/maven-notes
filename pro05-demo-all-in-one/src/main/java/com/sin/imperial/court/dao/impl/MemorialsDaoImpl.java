package com.sin.imperial.court.dao.impl;

import com.sin.imperial.court.dao.BaseDao;
import com.sin.imperial.court.dao.api.MemorialsDao;
import com.sin.imperial.court.entity.Memorials;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MemorialsDaoImpl extends BaseDao<Memorials> implements MemorialsDao {

    @Override
    public List<Memorials> selectAllMemorialsDigest() {

/*
        SELECT
                memorials_id as memorialsId,
                memorials_title as memorialsTitle,
                concat(LEFT(memorials_content, 10),"...") as memorialsContent,
                emp_name as empName,
                memorials_emp as memorialsEmp,
                memorials_create_time as memorialsCreateTime,
                feedback_time as feedbackTime,
                feedback_content as feedbackContent,
                memorials_status as memorialsStatus
        FROM
        t_memorials m left join t_emp e on m.memorials_emp = e.emp_id

         */
        String sql = "SELECT\n" +
                "                memorials_id as memorialsId,\n" +
                "                memorials_title as memorialsTitle,\n" +
                "                concat(LEFT(memorials_content, 10),\"...\") as memorialsContent,\n" +
                "                emp_name as empName,\n" +
                "                memorials_emp as memorialsEmp,\n" +
                "                memorials_create_time as memorialsCreateTime,\n" +
                "                feedback_time as feedbackTime,\n" +
                "                feedback_content as feedbackContent,\n" +
                "                memorials_status as memorialsStatus\n" +
                "        FROM\n" +
                "        t_memorials m left join t_emp e on m.memorials_emp = e.emp_id";
        return getBeanList(sql,Memorials.class);
    }


    @Override
    public Memorials selectMemorialsById(String memorialsId) {
        String sql = "select memorials_id memorialsId,\n" +
                "       memorials_title memorialsTitle,\n" +
                "       memorials_content memorialsContent,\n" +
                "       emp_name memorialsEmpName,\n" +
                "       memorials_create_time memorialsCreateTime,\n" +
                "       memorials_status memorialsStatus,\n" +
                "       feedback_time feedbackTime,\n" +
                "       feedback_content feedbackContent\n" +
                "from t_memorials m left join  t_emp e on m.memorials_emp=e.emp_id " +
                "where memorials_id=?;";
        return getSingleBean(sql, Memorials.class, memorialsId);
    }


    @Override
    public void updateMemorialsStatusToRead(String memorialsId) {
        String sql = "update t_memorials set memorials_status=1 where memorials_id=?";
        update(sql, memorialsId);
    }


    @Override
    public void updateMemorialsFeedBack(String memorialsId, String feedbackContent) {
        String feedbackTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String sql = "update t_memorials set memorials_status=2,feedback_content=?,feedback_time=? where memorials_id=?";
        update(sql, feedbackContent, feedbackTime, memorialsId);
    }

}
