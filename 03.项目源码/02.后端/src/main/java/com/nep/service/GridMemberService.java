package com.nep.service;

import com.nep.entity.GridMember;
import com.nep.repository.GridMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 网格员业务逻辑
 */
@Service
public class GridMemberService {

    @Autowired
    private GridMemberRepository gridMemberRepository;

    /**
     * 网格员登录 — 根据登录编码和密码验证
     * 密码明文传输，前端传入 {gmCode, password}
     *
     * @param gmCode   登录编码
     * @param password 密码
     * @return 网格员对象（含关联省市区），失败返回 null
     */
    public GridMember getGridMemberByCodeByPass(String gmCode, String password) {
        return gridMemberRepository.findByGmCodeAndPassword(gmCode, password);
    }
}
