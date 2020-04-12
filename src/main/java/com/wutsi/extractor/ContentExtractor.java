package com.wutsi.extractor;

import com.wutsi.extractor.filter.ContentFilter;
import com.wutsi.extractor.filter.SanitizeFilter;
import com.wutsi.extractor.filter.TrimFilter;

import java.util.Arrays;
import java.util.List;

public class ContentExtractor {
    private final List<Filter<String>> filters;

    public ContentExtractor(List<Filter<String>> filters) {
        this.filters = filters;
    }

    public static ContentExtractor create()  {
        return new ContentExtractor(Arrays.asList(
                new SanitizeFilter(),
                new ContentFilter(1),
                new TrimFilter()
        ));
    }

    public String extract(String html) {
        for (final Filter<String> filter : filters) {
            html = filter.filter(html);
        }
        return html;
    }
}
