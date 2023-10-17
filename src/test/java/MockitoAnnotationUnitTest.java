import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.baeldung.com/mockito-annotations
@ExtendWith(MockitoExtension.class)
public class MockitoAnnotationUnitTest {
    @Mock
    List<String> mockedList;

    @Spy
    List<String> spiedList = new ArrayList<String>();

    @Captor
    ArgumentCaptor<String> argCaptor;

    @BeforeEach
    public void init() {
        // Mockito annotationsを有効化
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenUseMockAnnotation_thenMockIsInjected() {
        mockedList.add("one");
        Mockito.verify(mockedList).add("one");
        assertThat(mockedList.size()).isEqualTo(0);

        Mockito.when(mockedList.size()).thenReturn(100);
        assertThat(mockedList.size()).isEqualTo(100);
    }

    @Test
    public void whenUseSpyAnnotaion_thenSpyIsInjectedCorrectly() {
        spiedList.add("one");
        spiedList.add("two");

        Mockito.verify(spiedList).add("one");
        Mockito.verify(spiedList).add("two");

        assertThat(spiedList.size()).isEqualTo(2);

        Mockito.doReturn(100).when(spiedList).size();
        assertThat(spiedList.size()).isEqualTo(100);
    }

    @Test
    public void whenUseCaptorAnnotation_thenTheSame() {
        mockedList.add("one");

        Mockito.verify(mockedList).add(argCaptor.capture());

        assertThat(argCaptor.getValue()).isEqualTo("one");
    }

    @Mock
    Map<String, String> wordMap;

    @InjectMocks
    MyDictionary dic = new MyDictionary();

    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect() {
        Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

        assertThat(dic.getMeaning("aWord")).isEqualTo("aMeaning");
    }

    public static class MyDictionary {
        Map<String, String> wordMap;

        public MyDictionary() {
            wordMap = new HashMap<String, String>();
        }
        public void add(final String word, final String meaning) {
            wordMap.put(word, meaning);
        }
        public String getMeaning(final String word) {
            return wordMap.get(word);
        }
    }
}
