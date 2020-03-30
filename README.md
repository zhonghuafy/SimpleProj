# SimpleProj
A Simple Spring boot Project for learning java technology by myself. 

Nothing else.

Thanks

By Joshua<br/>
twitter: @zhonghuafy <br/>
blog: zhonghuafy.wordpress.com

************
Representation below shows every module's usage.

sp-basic:<br/>
This is a data access module for database, including mybatis mappers, data models and services. But now it has nothing.

sp-common:<br/>
Public module including utils, global error codes and an global exception handler.

sp-ehcache:<br/>
An example to use ehcache. One thread generating and saving 'package'(just some data) into ehcache, 
while another thread reading and removing it.<br/>
ehcache: http://www.ehcache.org/

sp-elastic:<br/>
A very simple usage of Elastic Search.<br/>
elastic search: https://www.elastic.co/

sp-jms:<br/>
Java message service module.
Now it just have Kafka. So this is an application to send package to kafka and there's a listener checks the new data and consumes it.<br/>
kafka: http://kafka.apache.org/

sp-model:<br/>
Public module for data object encapsulations.

sp-redis:<br/>
Redis silly demo.<br/>
redis: https://redis.io/

sp-service:<br/>
An service module for all logic process services. It is an middle level up sp-basic and will be used by controllers or apis.
