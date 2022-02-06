package netology.sender;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MessageSenderImplTest {


    @BeforeEach
    public void startEach() {
        System.out.println("One test started");
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finishEach() {
        System.out.println("\nOne test finished");
    }

    @AfterAll
    public static void finished() {
        System.out.println("Tests finished");
    }

    @ParameterizedTest
    @MethodSource("args")
    public void testSend(String ip, String sendsMessage) {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.any()))
                .thenReturn(new Location("New York", Country.USA, null, 0));
        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Mockito.any()))
                .thenReturn("Welcome");
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender sender = new MessageSenderImpl(geoService, localizationService);

        String result = sender.send(headers);

        assertEquals(sendsMessage, result);
    }

    private static Stream<Arguments> args() {
        return Stream.of(Arguments.of("96.44.183.149", "Welcome"),
                Arguments.of("172.0.32.11", "Добро пожаловать"));
    }
}
