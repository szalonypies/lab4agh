package com.luxoft.lab4.t5;

import com.luxoft.lab4.t5.configuration.ApplicationConfiguration;

import java.util.List;

/**
 * Created by aniamamam on 2014-04-30.
 */
public class ApplicationConfigurer {
    private List<Application> applications;

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public void configure(List<ApplicationConfiguration> configurations) {
        for (ApplicationConfiguration config : configurations) {
            for (Application application: applications) {
                config.configure(application);
            }
        }
    }



}
