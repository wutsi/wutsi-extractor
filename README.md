`wutsi-extractor` is a web content extractor based on [eatiht](https://rodricios.github.io/eatiht/#the-original-algorithm) algorithm

![](https://github.com/wutsi/wutsi-extractor/workflows/master/badge.svg)
![](https://img.shields.io/badge/jdk-1.8-brightgreen.svg)
![](https://img.shields.io/badge/language-java-blue.svg)

This library provide the following features:
- [Extractors](https://github.com/wutsi/wutsi-extractor/blob/master/src/main/java/com/wutsi/extractor/): Classes for extracting informations from an HTML page
  - Content
  - Image
  - Tags
  - Site name
  - Title
  - URL
  - Published Date
- [Bots]((https://github.com/wutsi/wutsi-extractor/blob/master/src/main/java/com/wutsi/extractor/bot)): web bot for extracting contents from website 
  
# The tagbot
The `tagbot` is a bot for extracting all the tags from a given website.

To run the tagbot
- Open the project in your IDE
- Open the class ``com.wutsi.extractor.bot.TagBot``
- Goto the the method `main`
- Set the value of the variable `url`. It represent the name of the site to scan
- Run the class. The tags fill be stored in the file <domain-name>.csv in your home directory.

Ex: If you scan `https://zenueacademie.com` the file generated will be `zenueacademie.com.csv`. 

