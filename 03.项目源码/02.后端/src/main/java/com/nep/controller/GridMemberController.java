package com.nep.controller;

import com.nep.entity.GridMember;
import com.nep.service.GridMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 网格员接口
 *
 * 前端调用: POST /gridMember/getGridMemberByCodeByPass
 * Body: { "gmCode": "xxx", "password": "xxx" }
 * 返回: GridMember JSON (含关联省市区) 或 空字符串
 */
@RestController
@RequestMapping("/gridMember")
public class GridMemberController {

    @Autowired
    private GridMemberService gridMemberService;

    /**
     * 网格员登录
     */
    @PostMapping("/getGridMemberByCodeByPass")
    public GridMember getGridMemberByCodeByPass(@RequestBody Map<String, Object> params) {
        String gmCode = (String) params.get("gmCode");
        String password = (String) params.get("password");
        return gridMemberService.getGridMemberByCodeByPass(gmCode, password);
    }
}
