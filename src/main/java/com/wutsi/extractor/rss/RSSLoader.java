package com.wutsi.extractor.rss;

import com.wutsi.extractor.util.HttpHelper;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RSSLoader {
    public List<Item> load(URL url) throws IOException {
        HttpURLConnection cnn = (HttpURLConnection)url.openConnection();
        try {
            cnn.setRequestProperty("User-Agent", HttpHelper.USER_AGENT);
            try (final InputStream in = cnn.getInputStream()) {
                return load(in);
            }
        } finally {
            cnn.disconnect();
        }
    }

    public List<Item> load(InputStream in) throws IOException {
        try {
            final RSSFeedSAXHandler handler = new RSSFeedSAXHandler();
            final SAXParserFactory factory = SAXParserFactory.newInstance();
            final SAXParser sax = factory.newSAXParser();

            sax.parse(in, handler);
            return handler.getItems();
        } catch (SAXException | ParserConfigurationException e){
            throw new IOException("Invalid RSS file", e);
        }
    }
}
