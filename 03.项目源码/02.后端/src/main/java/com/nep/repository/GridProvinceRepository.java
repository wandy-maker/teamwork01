package com.nep.repository;

import com.nep.entity.GridProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GridProvinceRepository extends JpaRepository<GridProvince, Integer> {
}
