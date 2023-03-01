package com.espaharvesterproject.services;

import com.espaharvesterproject.model.Project;
import com.espaharvesterproject.model.ProjectSubProjects;
import com.espaharvesterproject.model.SubProject;
import com.espaharvesterproject.model.SubProjectInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/** RestTemplateService responsible for getting a json response from
 * the requested URLs with the appropriate parameters and mapping it to objects
 */
@Service
public class RestTemplateService {
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateService.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;


    public Project getProjectInfo(String vatNumber) throws JsonProcessingException {
        String URL = "https://anaptyxi.gov.gr/GetData.ashx?queryType=projects_v2&searchValue=" + vatNumber + "&searchField=4&includePollaplhs=true&outputFormat=json&pagesize=1000";
        String result = restTemplate.getForObject(URL, String.class);
        Project project = objectMapper.readValue(result, Project.class);

        if (project.getRecords().isEmpty()) {
            logger.error("Invalid vatNumber given.");
            return null;
        }

        return project;
    }

    public List<SubProject> getProjectSubProjects(Integer projectCode) throws JsonProcessingException {
        String URL = "https://anaptyxi.gov.gr/GetData.ashx?queryType=projectDetails&queryArgument=" + projectCode + "&outputFormat=json";
        String result = restTemplate.getForObject(URL, String.class);
        ProjectSubProjects projectSubProjects = objectMapper.readValue(result, ProjectSubProjects.class);

        if (projectSubProjects.getSubprojects().isEmpty()) {
            logger.warn("There are no subProjects available for this project.");
            return null;
        }

        return projectSubProjects.getSubprojects();

    }

    public SubProjectInfo getSubProjectInfo(Integer projectCode, Integer subProjectIndex) throws JsonProcessingException {
        String URL = "https://anaptyxi.gov.gr/GetData.ashx?queryType=subProjectDetails&queryArgument=" + projectCode + "&queryFilter=" + subProjectIndex + "&outputFormat=json";
        String result = restTemplate.getForObject(URL, String.class);
        return objectMapper.readValue(result, SubProjectInfo.class);
    }
}
