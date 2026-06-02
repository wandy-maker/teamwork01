package com.nep.repository;

import com.nep.entity.AqiFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AqiFeedbackRepository extends JpaRepository<AqiFeedback, Integer> {

    /**
     * 根据网格员ID和状态查询反馈列表
     * state: 0=未指派, 1=已指派, 2=已确认
     */
    List<AqiFeedback> findByGmIdAndStateOrderByAfDateDescAfTimeDesc(Integer gmId, Integer state);

    /**
     * 更新反馈信息状态
     */
    @Modifying
    @Transactional
    @Query("UPDATE AqiFeedback SET state = :state WHERE afId = :afId")
    int updateStateById(@Param("afId") Integer afId, @Param("state") Integer state);
}
