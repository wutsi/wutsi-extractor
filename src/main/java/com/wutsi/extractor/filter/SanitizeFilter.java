package com.wutsi.extractor.filter;

import com.wutsi.extractor.Filter;
import com.wutsi.extractor.util.JsoupHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;

/**
 * Remove polluting tags like SCRIPT, IMAGES, etc.
 */
public class SanitizeFilter implements Filter<String> {
    private static final List<String> ID_CSS_BLACKLIST = Arrays.asList(
            "footer",
            "comments",
            "menu-ay-side-menu-mine",
            "mashsb-container",
            "top-nav",
            "related_posts",
            "share-post",
            "navbar",
            "nav",
            "addthis_tool"
    );
    private static List<String> TAG_BLACKLIST = Arrays.asList(
            "head",
            "style",
            "script",
            "nav",
            "iframe",
            "noscript",
            "header",
            "footer"
    );


    //-- TextFilter overrides
    @Override
    public String filter(final String html) {

        final Document doc = Jsoup.parse(html);

        JsoupHelper.removeComments(doc.body());
        JsoupHelper.remove(doc, (i) -> reject(i));
        JsoupHelper.visit(doc, (i) -> empty(i));
        JsoupHelper.visit(doc, (i) -> cleanup(i));


        return doc.html();
    }

    private boolean cleanup(Element elt) {
        elt.removeAttr("id");
        elt.removeAttr("class");
        elt.removeAttr("style");
        elt.removeAttr("onclick");

        return true;
    }

    private boolean reject(Element elt) {
        return TAG_BLACKLIST.contains(elt.tagName())
                || isSocialLink(elt)
                || isBlacklistedClassOrId(elt)
                || isMenuItem(elt)
        ;
    }

    private boolean isSocialLink(final Element elt) {
        if (!"a".equals(elt.tagName())){
            return false;
        }

        String href = elt.attr("href");
        return href.contains("twitter.com/intent/tweet") ||
                href.contains("twitter.com/share") ||
                href.contains("facebook.com/share.php") ||
                href.contains("facebook.com/sharer.php") ||
                href.contains("plus.google.com/share") ||
                href.contains("linkedin.com/shareArticle") ||
                href.contains("linkedin.com/cws/share") ||
                href.contains("pinterest.com/pin/create/button")
        ;
    }

    private boolean isMenuItem(final Element elt) {
        Elements children = elt.children();
        if (children.isEmpty()){
            return false;
        }

        for (Element child : elt.children()) {
            if (!"a".equals(child.tagName())) {
                return false;
            }
        }
        return true;
    }

    private boolean isBlacklistedClassOrId(Element elt) {
        for (String clazz : elt.classNames()){
            if (ID_CSS_BLACKLIST.contains(clazz.toLowerCase())){
                return true;
            }
        }
        return ID_CSS_BLACKLIST.contains(elt.attr("id").toLowerCase());
    }

    private boolean empty(final Element elt) {
        if (!elt.hasText() && elt.children().isEmpty()){
            elt.remove();
            return true;
        }
        return false;
    }
}
