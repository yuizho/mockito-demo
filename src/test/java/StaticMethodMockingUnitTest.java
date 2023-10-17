import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.*;

// https://www.baeldung.com/mockito-mock-static-methods
public class StaticMethodMockingUnitTest {
    @Test
    void givenStaticMethodWithNoArgs_whenMocked_thenReturnsMockSuccessfully() {
        assertThat(StaticUtils.name()).isEqualTo("Baeldung");

        try (MockedStatic<StaticUtils> util = Mockito.mockStatic(StaticUtils.class)) {
            util.when(StaticUtils::name).thenReturn("Eugen");
            assertThat(StaticUtils.name()).isEqualTo("Eugen");
        }

        assertThat(StaticUtils.name()).isEqualTo("Baeldung");
    }

    static class StaticUtils {
        private StaticUtils() {}

        public static String name() {
            return "Baeldung";
        }
    }
}
