package com.espaharvesterproject.controllers;

import com.espaharvesterproject.model.ProjectSubProjectData;
import com.espaharvesterproject.services.CompanyProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Company controller
 */
@Controller
@RequestMapping(path = "/company")
public class CompanyController {
    @Autowired
    CompanyProjectService companyProjectService;

    /**
     * Returns a json response with a list of project and its subProjects info
     *
     * @param name      - the selected project name
     * @param vatNumber - the company code
     * @return a list with the project and all subprojects info
     * @throws JsonProcessingException
     */
    @GetMapping(path = "/{vat}/project/{name}")
    public @ResponseBody Iterable<ProjectSubProjectData> getCompanyProject(@PathVariable String name, @PathVariable("vat") String vatNumber) throws JsonProcessingException {
        return companyProjectService.getCompanyProjectSubProjectsInfo(name, vatNumber);
    }
}
