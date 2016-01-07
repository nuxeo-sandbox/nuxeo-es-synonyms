# nuxeo-es-synonyms
===================

This plug-in enables the administration of synonyms through the admin interface

## List of Features (details below)

Elasticsearch analyzer which use the synonyms filter
Nuxeo operations to read and write synonyms and to run a reindexation of Elasticsearch
A UI based on Web Components to visualize and edit synonyms through the admin interface
Important: if you're running ElasticSearch as a standalone you need to do 2 things.
1- Copy the file nuxeo-es-synonyms/nuxeo-es-synonyms-mp/src/main/resources/install/templates/es/config/synonyms.txt into the folder etc/elasticsearch/
2- Add an entry in the nuxeo.conf file for the property org.nuxeo.synonyms.path= /the/path/to/the/synonyms.txt

## Build

Assuming maven is correctly setup on your computer:

```
mvn package
```

## About Nuxeo

Nuxeo provides a modular, extensible Java-based [open source software platform for enterprise content management](http://www.nuxeo.com/en/products/ep) and packaged applications for [document management](http://www.nuxeo.com/en/products/document-management), [digital asset management](http://www.nuxeo.com/en/products/dam) and [case management](http://www.nuxeo.com/en/products/case-management). Designed by developers for developers, the Nuxeo platform offers a modern architecture, a powerful plug-in model and extensive packaging capabilities for building content applications.

More information at <http://www.nuxeo.com/>
