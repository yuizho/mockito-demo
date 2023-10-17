import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class HogeTest {
    @Test
    public void test() {
        var a1 = "a";
        var a2 = "a";

        assertThat(a1).isEqualTo(a2);
    }
}
