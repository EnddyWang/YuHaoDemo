FROM registry.cn-hangzhou.aliyuncs.com/doumob-aliyun/jdk-private:8-jre-alpine

COPY demo-0.0.1-SNAPSHOT.jar hello.jar

EXPOSE 5060

ENTRYPOINT ["java","-jar","/hello.jar"]
