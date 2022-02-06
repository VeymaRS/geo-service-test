package netology.geo;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceImplTest {
    GeoServiceImpl sut;

    @BeforeEach
    public void startEach() {
        System.out.println("One test started");
        sut = new GeoServiceImpl();
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finishEach() {
        System.out.println("One test finished");
    }

    @AfterAll
    public static void finished() {
        System.out.println("Tests finished");
    }

    @ParameterizedTest
    @MethodSource({"argsIP"})
    public void testLocationbyIp(String ip, Location loc) {

        assertEquals(sut.byIp(ip).getCountry(), loc.getCountry());

    }

    private static Stream<Arguments> argsIP() {
        return Stream.of(Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", new Location("New York", Country.USA, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)));
    }
}
