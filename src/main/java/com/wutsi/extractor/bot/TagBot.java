package com.wutsi.extractor.bot;

import com.wutsi.extractor.Downloader;
import com.wutsi.extractor.TagExtractor;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TagBot {
    private static final int DEFAULT_MAX_DURATION = 30;

    private TagExtractor extractor = new TagExtractor();
    private Downloader downloader = new Downloader();
    private String domain;
    private int maxDurationMinutes = 10;
    private int maxDepth = 5;
    private long startTime;

    public TagBot(String domain, int maxDepth, int maxDurationMinutes) {
        this.domain = domain;
        this.maxDepth = maxDepth;
        this.maxDurationMinutes = maxDurationMinutes;
    }

    public static void main(String[] args) throws Exception {
        String url = "https://mondoblog.org/tous-les-articles/";
        String domain = new URL(url).getHost();
        int maxDuration = 2*DEFAULT_MAX_DURATION;
        File file = new File(System.getProperty("user.home"), domain + ".csv");

        try (OutputStream out = new FileOutputStream(file)) {
            new TagBot(domain, 5, maxDuration).navigate(url, new PrintStream(out));
        }
    }

    public void navigate(String url, PrintStream out) {
        navigate(url, maxDepth, out);
    }

    private void navigate(String url, int depth, PrintStream out) {
        startTime = System.currentTimeMillis();

        Map<String, Integer> tagMap = new HashMap<>();
        Set<String> visited = new HashSet<>();
        navigate(url, depth, tagMap, visited);
        write(tagMap, out);
    }

    private void navigate(String u, int depth, Map<String, Integer> tagMap, Set<String> visited) {
        long duration = duration();
        if (depth < 0 || visited.contains(u) || duration>maxDurationMinutes) {
            return;
        }

        System.out.println(u + " - " + tagMap.size() + " tag(s) - depth=" + depth + " - duration=" + duration);
        visited.add(u);
        try {
            /* extract the tags */
            URL url = new URL(u);
            String html = downloader.download(url);
            List<String> tags = extractor.extract(html);
            tags.forEach(it -> increment(it, tagMap));

            /* Navigation sub pages */
            Jsoup.parse(html).select("a").stream()
                    .filter(it -> accept(it.attr("href")))
                    .forEach(it ->
                            navigate(it.attr("href"), depth - 1, tagMap, visited)
                    );
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void increment(String tag, Map<String, Integer> tagMap) {
        String key = tag.toLowerCase();
        if (tagMap.containsKey(key)){
            tagMap.put(key, 1+tagMap.get(key));
        } else {
            tagMap.put(key, 1);
        }
    }

    private boolean accept(String url) {
        if (url.endsWith(".png")
                || url.endsWith(".jpg")
                || url.endsWith(".gif")
                || url.endsWith(".svg")
        ) {
            return false;
        }

        try {
            return new URL(url).getHost().endsWith(domain);
        } catch (Exception e) {
            return false;
        }
    }

    private long duration() {
        long duration = System.currentTimeMillis() - startTime;
        return duration/(60*1000);
    }

    private void write(Map<String, Integer> tagMap, PrintStream out) {
        List<String> keys = new ArrayList<>();
        keys.addAll(tagMap.keySet());
        Collections.sort(keys, (c1, c2) -> tagMap.get(c2) - tagMap.get(c1));

        keys.forEach(it -> out.println(it + "," + tagMap.get(it)));
    }
}
