package com.sin.imperial.court.service.api;

import com.sin.imperial.court.entity.Memorials;

import java.util.List;

public interface MemorialsService {

    List<Memorials> getAllMemorialsDigest();

    Memorials getMemorialsDetailById(String memorialsId);

    void updateMemorialsStatusToRead(String memorialsId);

    void updateMemorialsFeedBack(String memorialsId, String feedbackContent);
}
