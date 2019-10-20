package com.luxoft.lab4.t5;

import com.luxoft.lab4.t5.configuration.FIXApplicationConfiguration;
import com.luxoft.lab4.t5.configuration.SeasonedApplicationConfiguration;

import java.util.List;

/**
 * Created by aniamamam on 2014-04-30.
 */
public abstract class ConfigurationReader {
    public abstract List<FIXApplicationConfiguration> readFixApplicationConfigs();
    public abstract List<SeasonedApplicationConfiguration> readSeasonedApplicationConfigs();
}
