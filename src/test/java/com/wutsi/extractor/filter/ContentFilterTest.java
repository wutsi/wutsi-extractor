package com.wutsi.extractor.filter;

import org.junit.Test;

public class ContentFilterTest extends AbstractFilterTest {
    private final ContentFilter filter = new ContentFilter();

    @Test
    public void simple() throws Exception {
        validateText("/content/simple", filter);
    }

    @Test
    public void style() throws Exception {
        validateText("/content/style", filter);
    }

    @Test
    public void quote() throws Exception {
        validateText("/content/quote", filter);
    }

    @Test
    public void figure() throws Exception {
        validateText("/content/figure", filter);
    }
}
