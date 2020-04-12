package com.wutsi.extractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TagExtractor {
    public List<String> extract(String html) {
        Document doc = Jsoup.parse(html);
        Elements tags = doc.select("meta[property=article:tag]");
        if (!tags.isEmpty()){
            return tags
                    .stream()
                    .map(tag -> tag.attr("content"))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
