package com.sht.crowd.manager.service.impl;

import com.sht.crowd.bean.User;
import com.sht.crowd.manager.dao.AdvertisementMapper;
import com.sht.crowd.manager.service.AdvertService;
import com.sht.crowd.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Override
    public Page queryPage(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));
        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<User> datas = advertisementMapper.pageQuery(paramMap);
        page.setDatas(datas);
        Integer totalsize = advertisementMapper.queryCount(paramMap);
        page.setTotalsize(totalsize);
        return page;
    }
}
