Cache Wrapper for Java
======================

This is a generic wrapper to manage any target cache system. At this moment it only one target implementation:
- Iron.io cache -> Iron.io (http://www.iron.io/) is cloud-based message queueing and async task processing. 
Iron has its own cache system which is helpful for the applications you deploy under iron.io environment. To integrate with Iron's
cache system I make use of this java client: <a href="https://github.com/mrcritical/ironcache">ironcache</a>.

Installation:
  - Open a GIT client tool (eg. GIT bash)  
  - Clone the project located at: https://github.com/rfvallina/cache-wrapper
  - Open a command line window and go to cache-wrapper root folder
  - Install it via maven run:  mvn clean install
 
Dependencies
  - ironcache (included in maven pom.xml file)

Configuration
  1. config.properties allows to select the default cache implementation to be used.
  2. iron.properties is the configuration for Iron.io
  
How to add a new target cache system
  1. Create a new package for the new cache system (e.g. memcache)
  2. Write a custom class that implements Cache.java interface and save it in the newly created package
  3. Add a new type in CacheType class for your new cache implementation
  4. Add a new case in the getCache method of ApplicationCache class
  5. Add a new properties file for the new cache system configuration (only if necessary)
  
Requirements
  1. Maven
  2. JDK 1.7
 
  

  


