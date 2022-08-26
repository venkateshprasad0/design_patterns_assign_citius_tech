package com.citius.service;

import java.util.List;
import java.util.Optional;

import com.citius.domain.ProjectDetailsDO;
import com.citius.exception.ProjectManagementException;

public interface ProjectMangementService {

	ProjectDetailsDO saveOrUpdateProjectDetails(ProjectDetailsDO detailsDO) throws ProjectManagementException;

	void deleteProjectDetails(Integer projectId) throws ProjectManagementException;

	List<ProjectDetailsDO> listProjectDetails();

	Optional<ProjectDetailsDO> findById(Integer projectId);
}
