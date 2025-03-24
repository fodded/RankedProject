import net.rankedproject.common.rest.RestClient;
import net.rankedproject.common.rest.impl.RankedPlayerRestClient;
import net.rankedproject.common.rest.provider.RestProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RestClientTest {

    private RestClient<?> restClient;

    @BeforeEach
    public void setup() {
        restClient = RestProvider.get(RankedPlayerRestClient.class);
    }

    @Test
    @DisplayName("RestClient of RankedPlayerRestClient can't be null")
    void testExistence() {
        assertNotNull(restClient);
    }
}
