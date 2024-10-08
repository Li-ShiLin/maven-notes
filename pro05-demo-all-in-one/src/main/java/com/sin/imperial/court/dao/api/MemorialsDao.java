package com.sin.imperial.court.dao.api;


import com.sin.imperial.court.entity.Memorials;
import java.util.List;
public interface MemorialsDao {

    List<Memorials> selectAllMemorialsDigest();

    Memorials selectMemorialsById(String memorialsId);

    void updateMemorialsStatusToRead(String memorialsId);

    void updateMemorialsFeedBack(String memorialsId, String feedbackContent);
}