package com.sin.imperial.court.service.impl;

import com.sin.imperial.court.dao.api.MemorialsDao;
import com.sin.imperial.court.dao.impl.MemorialsDaoImpl;
import com.sin.imperial.court.entity.Memorials;
import com.sin.imperial.court.service.api.MemorialsService;
import java.util.List;
public class MemorialsServiceImpl implements MemorialsService {

    private final MemorialsDao memorialsDao = new MemorialsDaoImpl();


    @Override
    public List<Memorials> getAllMemorialsDigest() {
        return memorialsDao.selectAllMemorialsDigest();
    }

    @Override
    public Memorials getMemorialsDetailById(String memorialsId) {
        return memorialsDao.selectMemorialsById(memorialsId);
    }


    @Override
    public void updateMemorialsStatusToRead(String memorialsId) {
        memorialsDao.updateMemorialsStatusToRead(memorialsId);
    }

    @Override
    public void updateMemorialsFeedBack(String memorialsId, String feedbackContent) {
        memorialsDao.updateMemorialsFeedBack(memorialsId, feedbackContent);
    }
}
