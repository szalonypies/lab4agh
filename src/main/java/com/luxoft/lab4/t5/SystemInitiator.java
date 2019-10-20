package com.luxoft.lab4.t5;

import com.luxoft.lab4.t5.configuration.FIXApplicationConfiguration;
import com.luxoft.lab4.t5.configuration.SeasonedApplicationConfiguration;

import java.util.List;

/**
 * Created by aniamamam on 2014-04-30.
 */
public class SystemInitiator {
    private ConfigurationReader configurationReader;

    private ApplicationConfigurer configurator;

    public void configureAll() {
        List<FIXApplicationConfiguration> fixConfig = configurationReader.readFixApplicationConfigs();
        List<SeasonedApplicationConfiguration> seasonedConfig = configurationReader.readSeasonedApplicationConfigs();

        // TODO uncomment these lines
        // configurator.configure(fixConfig);
        // configurator.configure(seasonedConfig);
    }

    public void setConfigurator(ApplicationConfigurer configurator) {
        this.configurator = configurator;
    }

    public void setConfigurationReader(ConfigurationReader configurationReader) {
        this.configurationReader = configurationReader;
    }

}
