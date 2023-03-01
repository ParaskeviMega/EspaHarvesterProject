package com.espaharvesterproject.services;

import com.espaharvesterproject.model.Project;
import com.espaharvesterproject.model.ProjectSubProjectData;
import com.espaharvesterproject.model.Record;
import com.espaharvesterproject.model.SubProject;
import com.espaharvesterproject.model.SubProjectInfo;
import com.espaharvesterproject.repositories.CompanyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

/**
 * Service responsible to collect the project - subprojects info
 * for a company project and store them in the database
 */
@Service
public class CompanyProjectService {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateService.class);

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    RestTemplateService restTemplateService;

    public Iterable<ProjectSubProjectData> getCompanyProjectSubProjectsInfo(String name, String vatNumber) throws JsonProcessingException {
        Integer projectCode = getProjectCode(name, vatNumber);
        List<SubProject> subProjects = restTemplateService.getProjectSubProjects(projectCode);
        List<ProjectSubProjectData> projectSubProjectsData = getSubProjectsInfo(projectCode, subProjects);
        companyRepository.saveAll(projectSubProjectsData);

        return projectSubProjectsData;
    }

    public Integer getProjectCode(String name, String vatNumber) throws JsonProcessingException {
        Project project = restTemplateService.getProjectInfo(vatNumber);
        Record record = getProjectRecord(project, name);

        if (isNull(record.getKodikos())) {
            logger.error("Invalid project code given.");
            return null;
        }

        return record.getKodikos();
    }

    public Record getProjectRecord(Project project, String name){
        Record record = project.getRecords().stream().filter(x -> name.equals(x.getBody())).findAny().orElse(null);

        if (isNull(record)) {
            logger.error("Invalid name given.");
            return null;
        }

        return record;
    }

    public List<ProjectSubProjectData> getSubProjectsInfo(Integer projectCode, List<SubProject> subProjects) throws JsonProcessingException {
        List<ProjectSubProjectData> subProjectsInfo = new ArrayList<>();

        for (SubProject subProject : subProjects) {
            SubProjectInfo subProjectInfo = restTemplateService.getSubProjectInfo(projectCode, subProject.getIndex());

            ProjectSubProjectData company = ProjectSubProjectData.builder()
                    .projectId(projectCode)
                    .subProjectId(subProject.getIndex())
                    .payment(subProjectInfo.getPayments())
                    .totalBudget(subProjectInfo.getTotalBudget())
                    .budget(subProjectInfo.getBudget())
                    .singleSeller(subProjectInfo.getBodies().size() == 1 ? TRUE : FALSE)
                    .build();

            subProjectsInfo.add(company);
        }

        return subProjectsInfo;
    }
}
