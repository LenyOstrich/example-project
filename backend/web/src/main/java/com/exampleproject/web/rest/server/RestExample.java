package com.exampleproject.web.rest.server;

import com.exampleproject.database.entities.Organization;
import com.exampleproject.engine.services.OrganizationService;
import com.exampleproject.model.shared.TestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestExample {

    private final ApplicationContext applicationContext;
    private final OrganizationService organizationService;

    @Autowired
    public RestExample(ApplicationContext applicationContext, OrganizationService organizationService) {
        this.applicationContext = applicationContext;
        this.organizationService = organizationService;
    }

    @RequestMapping("/test")
    public TestDto test() {
        TestDto dto = createDto();
        dto.setMessage("It's a test string from server");
        return dto;
    }

    @RequestMapping("/testDb")
    public boolean testDB() {
        try {
            List<Organization> list = organizationService.getAll();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @RequestMapping("/test/{someText}")
    public TestDto testAdditional(@PathVariable String someText) {
        TestDto dto = createDto();
        dto.setMessage("It's a test string from server and you've given me " + someText);
        return dto;
    }

    protected TestDto createDto() {
        return applicationContext.getBean(TestDto.class);
    }

}
