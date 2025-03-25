import net.rankedproject.common.data.domain.RankedPlayer;
import net.rankedproject.common.rest.RestClient;
import net.rankedproject.common.rest.impl.RankedPlayerRestClient;
import net.rankedproject.common.rest.provider.RestProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

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

    @Test
    @DisplayName("Should be able to retrieve player's information via RestClient")
    void testIfGetRequestWorks() {
        RankedPlayer player = RestProvider
                .get(RankedPlayerRestClient.class)
                .getPlayer(UUID.fromString("c2d3d415-dbdd-4d1a-92a5-33badd11d4be"));

        assertNotNull(player);
    }
}
