package com.luxoft.lab4.t5.configuration;

import com.luxoft.lab4.t5.Application;

/**
 * Created by aniamamam on 2014-04-30.
 */
public class FIXApplicationConfiguration implements ApplicationConfiguration {

    @Override
    public void configure(Application application) {
        System.err.println("Configuring configuration "+application+" for FIX connection");
        // doing the configuration
    }

}
