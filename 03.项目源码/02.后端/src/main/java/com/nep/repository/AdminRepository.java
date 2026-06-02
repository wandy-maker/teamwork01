package com.nep.repository;

import com.nep.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByAdminCodeAndPassword(String adminCode, String password);
}
