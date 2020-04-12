package com.wutsi.extractor;

import com.wutsi.extractor.filter.ContentFilter;
import com.wutsi.extractor.filter.HeadingOnlyFilter;
import com.wutsi.extractor.filter.SanitizeFilter;
import com.wutsi.extractor.filter.TrimFilter;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ContentExtractor {
    private static final String USER_AGENT = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1";

    private final List<Filter<String>> filters;

    public ContentExtractor(List<Filter<String>> filters) {
        this.filters = filters;
    }

    public static ContentExtractor create()  {
        return new ContentExtractor(Arrays.asList(
                new SanitizeFilter(),
                new ContentFilter(1),
                new HeadingOnlyFilter(),
                new TrimFilter()
        ));
    }

    public String extract(URL url) throws IOException {
        String html = download(url);
        return extract(html);
    }

    public String extract(String html) throws IOException {
        for (final Filter<String> filter : filters) {
            html = filter.filter(html);
        }
        return html;
    }

    private String download(URL url) throws IOException {
        return Jsoup
                .connect(url.toString())
                .userAgent(USER_AGENT)
                .validateTLSCertificates(false)
                .get()
                .html();
    }
}
