# ZKConfig - a zookeeper configuration manager
## Introduction
ZKConfig is a simple demo which manages configuration of distributed system. All properties are classified by business and stored in */config/$business_name$*. Users can acquire them via *$business_name$Data.getXXX()*.

Below is description for some necessary tools:

- *CodeBuilder.java* - Generate java codes of Data classes and GET methods according to *config-list.xml*
- *ConfigExporter* - Show all properties in ZooKeeper
- *ConfigImporter* - Import properties from *config-import.properties* (they'll cover the old ones).

## Reference
http://sysgears.com/articles/managing-configuration-of-distributed-system-with-apache-zookeeper/
