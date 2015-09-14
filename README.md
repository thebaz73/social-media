Social Media App
--------------------------

Application is composed by following modules:

* social-media-fe-admin
* social-media-fe-asset
* social-media-fe-customer
* social-media-fe-signon
* social-media-fe-search
* social-media-fe-ui
* social-media-fe-ws

Each module is a web application is spring boot application. For testing convenience when you start **social-media-fe-ws** module a batch application data insert is executed.


To start each module with following command on a different linux box with Oracle Java 8 installer 

social-media-fe-admin
=====================
java -jar social-media-fe-admin-1.0-SNAPSHOT.jar --spring.data.mongodb.host=your_mongo_db_host

reach application at http://your_linux_box_ip_address/

social-media-fe-asset
=====================
java -jar social-media-fe-asset-1.0-SNAPSHOT.jar --spring.data.mongodb.host=your_mongo_db_host

reach application at http://your_linux_box_ip_address/

social-media-fe-customer
=====================
java -jar social-media-fe-customer-1.0-SNAPSHOT.jar --spring.data.mongodb.host=your_mongo_db_host

reach application at http://your_linux_box_ip_address/

social-media-fe-signon
=====================
java -jar social-media-fe-signon-1.0-SNAPSHOT.jar --spring.data.mongodb.host=your_mongo_db_host

reach application at http://your_linux_box_ip_address/

social-media-fe-search
=====================
java -jar social-media-fe-search-1.0-SNAPSHOT.jar --spring.data.mongodb.host=your_mongo_db_host

reach application at http://your_linux_box_ip_address/

social-media-fe-ui
=====================
java -jar social-media-fe-ui-1.0-SNAPSHOT.jar --spring.data.mongodb.host=your_mongo_db_host [full path to]/web_service.properties

reach application at http://your_linux_box_ip_address/

social-media-fe-ws
=====================
java -jar social-media-fe-ws-1.0-SNAPSHOT.jar --spring.data.mongodb.host=your_mongo_db_host

reach application at http://your_linux_box_ip_address/

Note: Here follows a sample of main application configuration file:

repository.hostname=localhost
repository.port=8000
search.hostname=localhost
search.port=8010
asset.hostname=localhost
asset.port=8020

Note: It is advised to start all application, but to test main application **social-media-fe-ui** start at least:

* social-media-fe-ui
* social-media-fe-search
* social-media-fe-ws


Note: If you want to start all apps  on same machine please use [ --server.port=8000 --management.port=8001 ] to change ports of app server.

Note: For all other specific configuration please refer to Spring Boot documentation.

Enjoy.
