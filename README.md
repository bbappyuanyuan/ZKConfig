# ZKConfig - a zookeeper configuration manager
## Introduction
ZKConfig is a simple demo which manages configuration of distributed system. All properties are classified by business and stored in */config/$business_name$*. Users can acquire them via *$business_name$Data.getXXX()*.

<img src="https://raw.githubusercontent.com/bbappyuanyuan/ZKConfig/master/images/structure.png"/>

## Description
*Test.java* - A complete demo.

*CodeBuilder.java* - Generate java codes of Data classes and GET methods according to *config-list.xml*.

*ConfigExporter.java* - Show all properties in ZooKeeper.

*ConfigImporter.java* - Import properties from *config-import.properties* (they'll cover the old ones).

*log.html* - Logs.

## Reference
http://sysgears.com/articles/managing-configuration-of-distributed-system-with-apache-zookeeper/
