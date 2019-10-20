package com.luxoft.lab4.t5;

import com.luxoft.lab4.t5.configuration.FIXApplicationConfiguration;
import com.luxoft.lab4.t5.configuration.SeasonedApplicationConfiguration;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * Created by aniamamam on 2014-04-30.
 */
public class SystemConfigurationIntegrationTest {

    private SeasonedApplicationConfiguration seasonedApplicationConfiguration;
    private FIXApplicationConfiguration fixApplicationConfiguration;
    private ConfigurationReader configurationReader;

    @Test
    public void testConfiguration() {
        setupMocksAndSpies();

        ApplicationConfigurer configurator = new ApplicationConfigurer();
        Application application1 = new Application("application1");
        Application application2 = new Application("application2");
        configurator.setApplications(Arrays.asList(application1, application2));

        initializeSystem(configurator);

        verify(fixApplicationConfiguration).configure(application1);
        verify(fixApplicationConfiguration).configure(application2);
        verify(seasonedApplicationConfiguration).configure(application1);
        verify(seasonedApplicationConfiguration).configure(application2);

    }

    private void initializeSystem(ApplicationConfigurer configurator) {
        SystemInitiator initiator = new SystemInitiator();
        initiator.setConfigurator(configurator);
        initiator.setConfigurationReader(configurationReader);
        initiator.configureAll();
    }

    private void setupMocksAndSpies() {
        configurationReader = mock(ConfigurationReader.class);
        fixApplicationConfiguration = spy(new FIXApplicationConfiguration());
        seasonedApplicationConfiguration = spy(new SeasonedApplicationConfiguration());
        when(configurationReader.readFixApplicationConfigs()).thenReturn(Arrays.asList(fixApplicationConfiguration));
        when(configurationReader.readSeasonedApplicationConfigs()).thenReturn(Arrays.asList(seasonedApplicationConfiguration));
    }
}
