package com.wutsi.extractor.filter;

import com.wutsi.extractor.Filter;

public class TrimFilter implements Filter<String> {
    @Override
    public String filter(final String str) {
        final String html = str.trim();
        return html.startsWith("|") ? html.substring(1) : html;
    }
}
