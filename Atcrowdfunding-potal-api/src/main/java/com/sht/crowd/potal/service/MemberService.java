package com.sht.crowd.potal.service;

import com.sht.crowd.bean.Member;

import java.util.Map;

public interface MemberService {

    Member queryMemberlogin(Map<String, Object> paramMap);

}
