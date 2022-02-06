package netology.i18n;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.entity.Country.*;

public class LocalizationServiceImplTest {
    LocalizationService sut;

    @BeforeEach
    public void startEach() {
        System.out.println("One test started");
        sut = new LocalizationServiceImpl();
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
    @MethodSource({"argsCountry"})
    public void locale(Country country, String loc) {

        assertEquals(sut.locale(country), loc);

    }

    private static Stream<Arguments> argsCountry() {
        return Stream.of(Arguments.of(RUSSIA, "Добро пожаловать"),
                Arguments.of(GERMANY, "Welcome"));
    }
}
