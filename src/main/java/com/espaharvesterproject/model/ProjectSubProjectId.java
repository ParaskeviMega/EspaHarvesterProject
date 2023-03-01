package com.espaharvesterproject.model;

import lombok.Data;

import java.io.Serializable;

/**
 * The key of the project_subProject_info table
 */
@Data
public class ProjectSubProjectId implements Serializable {
    private Integer projectId;
    private Integer subProjectId;
}
