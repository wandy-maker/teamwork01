package com.nep.repository;

import com.nep.entity.GridMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GridMemberRepository extends JpaRepository<GridMember, Integer> {

    /**
     * 根据登录编码和密码查询网格员
     */
    GridMember findByGmCodeAndPassword(String gmCode, String password);
}
