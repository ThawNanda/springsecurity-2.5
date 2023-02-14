package com.nexcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexcode.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
