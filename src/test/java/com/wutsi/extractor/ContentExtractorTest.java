package com.wutsi.extractor;

import org.junit.Test;

import java.net.URL;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContentExtractorTest {

    @Test
    public void shouldExtractContent() throws Exception {
        // Given
        final Filter f1 = mock(Filter.class);
        when(f1.filter(any())).thenReturn("a");

        final Filter f2 = mock(Filter.class);
        when(f2.filter(any())).thenReturn("b");

        final Filter f3 = mock(Filter.class);
        when(f3.filter(any())).thenReturn("c");

        // When
        final ContentExtractor extractor = new ContentExtractor(Arrays.asList(f1, f2, f3));

        // Then
        assertThat(extractor.extract("hello world")).isEqualTo("c");
    }

    @Test
    public void testKamerKongossa() throws Exception {
        extract("https://kamerkongossa.cm/2020/01/07/a-yaounde-on-rencontre-le-sous-developpement-par-les-chemins-quon-emprunte-pour-leviter/");
    }

    private void extract(String url) throws Exception {
        String html = ContentExtractor.create().extract(new URL(url));
        System.out.println(html);

    }
}
