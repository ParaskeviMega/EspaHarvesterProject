package com.espaharvesterproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * A list of the subprojects of a project
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectSubProjects {
    private List<SubProject> subprojects;
}
