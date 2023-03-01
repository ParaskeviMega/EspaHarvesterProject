package com.espaharvesterproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * The index of the subproject
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubProject {
    private Integer index;
}
