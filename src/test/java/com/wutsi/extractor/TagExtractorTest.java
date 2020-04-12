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
        String html = load("/extractor/tag_opengraph.html");
        List<String> tags = extractor.extract(html);
        assertEquals(16, tags.size());
        assertTrue(tags.contains("Ben Decca"));
        assertTrue(tags.contains("Dr. Nkeng Stephens"));
        assertTrue(tags.contains("Reniss"));
    }

    @Test
    public void testNone() throws Exception{
        String html = load("/extractor/tag_none.html");
        List<String> tags = extractor.extract(html);
        assertEquals(0, tags.size());
    }

    private String load(String path) throws IOException {
        InputStream in = getClass().getResourceAsStream(path);
        return IOUtils.toString(in);
    }
}
