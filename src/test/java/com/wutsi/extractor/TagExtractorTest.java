package com.wutsi.extractor;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TagExtractorTest {
    private TagExtractor extractor = new TagExtractor();

    @Test
    public void testOpenGraph() throws Exception{
        String html = load("/tag/opengraph.html");
        List<String> tags = extractor.extract(html);
        assertEquals(16, tags.size());
        assertTrue(tags.contains("Ben Decca"));
        assertTrue(tags.contains("Dr. Nkeng Stephens"));
        assertTrue(tags.contains("Reniss"));
    }

    @Test
    public void testClass() throws Exception{
        String html = load("/tag/class.html");
        List<String> tags = extractor.extract(html);
        assertEquals(5, tags.size());
        assertTrue(tags.contains("adultère"));
        assertTrue(tags.contains("amour"));
        assertTrue(tags.contains("couple"));
        assertTrue(tags.contains("fidélité"));
        assertTrue(tags.contains("Infidélité"));
    }

    @Test
    public void testRel() throws Exception{
        String html = load("/tag/rel.html");
        List<String> tags = extractor.extract(html);
        assertEquals(5, tags.size());
//        assertTrue(tags.contains("Entrepreneuriat"));
//        assertTrue(tags.contains("Workflow"));
//        assertTrue(tags.contains("Jokkolabs Douala"));
//        assertTrue(tags.contains("Tourisme et voyages"));
//        assertTrue(tags.contains("Podcast"));
    }

    @Test
    public void testNone() throws Exception{
        String html = load("/tag/none.html");
        List<String> tags = extractor.extract(html);
        assertEquals(0, tags.size());
    }

    private String load(String path) throws IOException {
        InputStream in = getClass().getResourceAsStream(path);
        return IOUtils.toString(in);
    }
}
