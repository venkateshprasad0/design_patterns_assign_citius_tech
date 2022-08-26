package com.citius.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citius.domain.ProjectDetailsDO;

@Repository
public interface ProjectManagementRepo extends JpaRepository<ProjectDetailsDO, Integer> {

}
