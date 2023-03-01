package com.espaharvesterproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.json.simple.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Pojo for holding the subprojects' information
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubProjectInfo {
    private BigDecimal payments;
    private BigDecimal totalBudget;
    private BigDecimal budget;

    // ArrayList that includes the seller of the subproject
    private ArrayList<JSONObject> bodies;
}
