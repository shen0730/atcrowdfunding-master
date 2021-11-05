package com.sht.crowd.manager.service.impl;

import com.sht.crowd.manager.dao.TestDao;
import com.sht.crowd.manager.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public void insert() {

        Map map = new HashMap();
        map.put("name" ,"zhang3");
        testDao.insert(map);
    }
}
