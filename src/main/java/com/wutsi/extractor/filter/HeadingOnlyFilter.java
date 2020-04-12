package com.wutsi.extractor.filter;

import com.wutsi.extractor.Filter;
import com.wutsi.extractor.util.HtmlHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * If the document has only a heading element (h1,h2,..,h6), this filter will return empty string.
 */
public class HeadingOnlyFilter implements Filter<String> {
    public String filter(final String html) {
        final Document doc = Jsoup.parse(html);
        final Element body = doc.body();
        return body.children().size() == 1 && HtmlHelper.isHeading(body.child(0))
                ? ""
                : html;
    }
}
