package com.wutsi.extractor;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertFalse;

public class DownloaderTest {
    private Downloader downloader = new Downloader();

    @Test
    public void testKamerKongossa() throws Exception {
        String url = "https://kamerkongossa.cm/2020/01/07/a-yaounde-on-rencontre-le-sous-developpement-par-les-chemins-quon-emprunte-pour-leviter/";
        String html = downloader.download(new URL(url));

        assertFalse(html.isEmpty());
    }

    @Test(expected = IOException.class)
    public void test404() throws Exception {
        String url = "https://kamerkongossa.cm/flfkldkf";
        downloader.download(new URL(url));
    }
}
