package com.wutsi.extractor.filter;

import org.junit.Test;

public class SanitizeFilterTest extends AbstractFilterTest {
    private final SanitizeFilter filter = new SanitizeFilter();

    @Test
    public void document() throws Exception {
        validateHtml("/sanitizer/document", filter);
    }

    @Test
    public void social() throws Exception {
        validateHtml("/sanitizer/social", filter);
    }

    @Test
    public void menu() throws Exception {
        validateHtml("/sanitizer/menu", filter);
    }
}
