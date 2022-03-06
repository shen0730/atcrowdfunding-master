package com.sht.crowd.potal.service.impl;

import com.sht.crowd.bean.Member;
import com.sht.crowd.potal.dao.MemberMapper;
import com.sht.crowd.potal.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Member queryMemberlogin(Map<String, Object> paramMap) {
        return memberMapper.queryMebmerlogin(paramMap);
    }
}
