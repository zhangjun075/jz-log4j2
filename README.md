# 采用log4j2进行日期的记录
# 关闭默认的springboot的记录方式
```
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <version>1.5.1.RELEASE</version>
      <exclusions>
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
```
# log4j2的配置[参考](https://logging.apache.org/log4j/2.x/)

# 异步日志需要引入
```
<dependency>
      <groupId>com.lmax</groupId>
      <artifactId>disruptor</artifactId>
      <version>3.3.6</version>
    </dependency>
```
