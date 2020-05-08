package com.wutsi.extractor;

import com.wutsi.extractor.filter.AbstractFilterTest;
import org.junit.Test;

public class ContentExtractorTest extends AbstractFilterTest {
    ContentExtractor extractor = ContentExtractor.create(20);

    @Test
    public void kamerkongossa() throws Exception {
        test("/extractor/kamerkongossa");
    }

    @Test
    public void ntrjack() throws Exception {
        test("/extractor/ntrjack");
    }

    @Test
    public void monquotidien() throws Exception {
        test("/extractor/monquotidien");
    }

    @Test
    public void yohedahealth() throws Exception {
        test("/extractor/yohedahealth");
    }

    private void test(String path) throws Exception{
        super.validateHtml(path, (s) -> extractor.extract(s));
    }
}
