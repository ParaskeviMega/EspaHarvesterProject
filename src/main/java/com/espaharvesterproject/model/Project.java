package com.espaharvesterproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Pojo for holding information for the projects of a company
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    @JsonProperty("Records")
    private List<Record> records;
}
