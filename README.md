`wutsi-extractor` is a HTML content extractor

![](https://github.com/wutsi/wutsi-extractor/workflows/master/badge.svg)
![](https://img.shields.io/badge/jdk-1.8-brightgreen.svg)
![](https://img.shields.io/badge/language-java-blue.svg)

# Content Extractors
This library provide several HTML [extractors]((https://github.com/wutsi/wutsi-extractor/blob/master/src/main/java/com/wutsi/extractor/)):
  - Content Extractor - based on [eatiht](https://rodricios.github.io/eatiht/#the-original-algorithm) algorithm
  - Main Image Extractor
  - Tags Extractor
  - Site name Extractor
  - Title Extractor
  - URL Extractor
  - Published Date Extractors
  

# The tagbot
The `tagbot` is a bot that can scan a website, and extract all the tags contained in all pages of that website and will store them in CSV file.


#### Running the tagbot.
- Open the project in your IDE
- Open the class ``com.wutsi.extractor.bot.TagBot``
- Goto the the method `main`
- Set the value of the variable `url`. It represent the name of the site to scan
- Run the class. The tags fill be stored in the file <domain-name>.csv in your home directory.
