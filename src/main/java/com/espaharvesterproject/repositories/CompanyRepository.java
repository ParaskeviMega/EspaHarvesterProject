package com.espaharvesterproject.repositories;

import com.espaharvesterproject.model.ProjectSubProjectData;
import com.espaharvesterproject.model.ProjectSubProjectId;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for performing CRUD operations on ProjectSubProjectData
 */
public interface CompanyRepository extends CrudRepository<ProjectSubProjectData, ProjectSubProjectId> {
}
