package bdd.test;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.stop.Stop;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by p0a00hg on 11/11/22
 **/

@RunWith(SpringRunner.class)
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BddTestApplication.class)
@AutoConfigureWireMock(port = 9999)
public class CucumberSpringConfiguration {

    protected static String tenantId = "";
    protected static int mock_server_port = 10180;

    private static ClientAndServer proxy;
    private static ClientAndServer mockServer;

    static {
        String tenant = System.getProperty("tenant-id");
        String mock_srvr_port = System.getProperty("mock_server_port");
        if (null != tenant && !tenant.isEmpty()) {
            tenantId = tenant;
        }
        if (null != mock_srvr_port && !mock_srvr_port.isEmpty() && mock_srvr_port.matches("\\d+")) {
            mock_server_port = Integer.valueOf(mock_srvr_port);
        }
        System.out.println("Startin mock server....");
        setUp();
    }

    public static void startProxy() {
        proxy = ClientAndServer.startClientAndServer();
    }

    public static void stopProxy() {
        Stop.stopQuietly(proxy);
    }

    public static void setUp() {
        mockServer = ClientAndServer.startClientAndServer(mock_server_port);
        startProxy();
        proxy.reset();
    }

    @AfterClass
    public static void tearDown() {
        Stop.stopQuietly(mockServer);
        stopProxy();
    }


}
