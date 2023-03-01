package com.espaharvesterproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Entity that holds the information for a selected
 * project of a company and the subproject of the project
 */
@Data
@Builder
@Entity
@Table(name = "project_subProject_info")
@IdClass(ProjectSubProjectId.class)
public class ProjectSubProjectData {
    @Id
    private Integer projectId;
    @Id
    private Integer subProjectId;
    private BigDecimal payment;
    private BigDecimal totalBudget;
    private BigDecimal budget;
    private boolean singleSeller;

    public ProjectSubProjectData() {

    }

    public ProjectSubProjectData(Integer projectId, Integer subProjectId, BigDecimal payment, BigDecimal totalBudget, BigDecimal budget, boolean singleSeller) {
        this.projectId = projectId;
        this.subProjectId = subProjectId;
        this.payment = payment;
        this.totalBudget = totalBudget;
        this.budget = budget;
        this.singleSeller = singleSeller;
    }
}