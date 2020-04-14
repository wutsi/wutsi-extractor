package com.wutsi.extractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.stream.Collectors;

public class TagExtractor {
    public List<String> extract(String html) {
        Document doc = Jsoup.parse(html);
        List<String> tags = extractFromOpenGraph(doc);
        if (tags.isEmpty()){
            tags = extractFromClass(doc);
        }
        if (tags.isEmpty()) {
            tags = extractFromRel(doc);
        }
        return tags;
    }

    private List<String> extractFromOpenGraph(Document doc) {
        return doc.select("meta[property=article:tag]")
                .stream()
                .map(it -> it.attr("content"))
                .collect(Collectors.toList());
    }

    private List<String> extractFromClass(Document doc) {
        return doc.select(".tag")
                .stream()
                .map(it -> it.text())
                .collect(Collectors.toList());
    }

    private List<String> extractFromRel(Document doc) {
        return doc.select("meta[rel*=tag]")
                .stream()
                .map(it -> it.text())
                .collect(Collectors.toList());
    }

}
