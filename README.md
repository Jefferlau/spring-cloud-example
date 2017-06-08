# spring-cloud-example
Spring Cloud 示例

## 服务注册 (Eureka)
[Eureka](https://github.com/Netflix/eureka/wiki/Eureka-at-a-glance) 是 Netflix 开源的一个 RESTful 服务，主要用于服务的注册发现。Eureka 由两个组件组成：Eureka 服务器和 Eureka 客户端。

Eureka 服务端对应本示例的 discovery-service 模块。

host 文件添加
```
127.0.0.1 discovery configserver gateway user
```

## 配置中心

基于 Git 仓库对配置进行版本管理，使用默认实现的基本的 HttpBasic 的认证。

使用下列命令看不同的 profiles 配置。
```bash
curl -u cloudapp:cloudapp http://localhost:8888/user/user-development.yml
curl -u cloudapp:cloudapp http://localhost:8888/user/user-production.yml
curl -u cloudapp:cloudapp http://localhost:8888/user/user-test.yml
```

[配置示例仓库](https://github.com/Jefferlau/spring-cloud-example-config)

[参考文件](https://blog.coding.net/blog/spring-cloud-config?utm_source=tuicool&utm_medium=referral)

## users-service

模拟服务提供者。

浏览器打开[http://localhost:9000/add?a=1&b=3]()
或者运行
```bash
curl http://localhost:9000/add\?a\=1\&b\=3
```
验证接口。

## api-gateway

接口网关。

浏览器打开[http://localhost:10000/user/add?a=1&b=3]()
或者运行
```bash
curl http://localhost:10000/user/add\?a\=1\&b\=3
```
验证接口通过网关调用。

## Hystrix Dashboard

浏览器打开[http://localhost:7979/hystrix.stream]()
输入``localhost:8080/hystrix.stream``，点击"Monitor Stream"。


# 附加组件

## 使用 Docker 部署 Git 服务

获取镜像。
```bash
docker pull jefferlau/gitbucket-server:4.13
# or
docker pull jefferlau/gitbucket-server:lastest
```
使用此镜像部署服务。
```bash
mkdir 4.13
docker run -p 18080:8080 --name jefferlau-gitbucket -v $PWD/4.13:/root/.gitbucket -d jefferlau/gitbucket-server:4.13
```

- 端口任意定制
- $PWD 指代当前目录，即将宿主机当前目录下的文件夹4.13挂载到 Docker 容器的 /root/.gitbucket 目录
- Go to http://[hostname]:18080/ and log in with ID: root / Pass: root.

Docker-Compose

编辑你的 docker-compose.yml 文件，然后使用 ``docker-compose`` 运行这个容器：

```bash
$ docker-compose up -d
```

资源
- 轻量级 Git 服务器：[gitbucket](https://github.com/gitbucket/gitbucket/releases)
- 本例 gitbucket-server Docker 镜像源码传送门：[Jefferlau/gitbucket-server-docker](https://github.com/Jefferlau/gitbucket-server-docker)

## 使用证书加密
### 密钥创建及配置
创建密钥
```bash
keytool -genkeypair -alias serverkey -keyalg RSA \
  -dname "CN=Jeffer Lau,OU=jefferlau.me,O=jefferlau.me,L=Beijing,S=Beijing,C=CN" \
  -keypass jefferlau -keystore server.jks -storepass jefferlzu
```
bootstrap.yml 增加如下内容
```yaml
encrypt:
  keyStore:
    location: classpath:/server.jks
    password: jefferlzu
    alias: serverkey
    secret: jefferlau

security:
  user:
    name: cloudapp
    password: '{cipher}AQBn3lvzsC1XT1rGHQ3g+g+nTDElDm5nUU2AqxwXUjENAXDyOUzXhqk/fbVjTbwZgQGL7OFrit27m14jAb3kYn+1+7YLj9Pnoq/E77RRwz+O2ejQ8YC73l372kmRsd1IiE0v4mxemkUcOeO5WVfFtBsTIWsqsK88UVA0FxfnSZaq0x8EDBYj4aPHPIQWQa/pFFjfKg0LJAJTDbtF816L/vWMJBCARIBtUOpeXRRBBnG0mJg2euzqJ6T/A2wBW/A8Dz7S98cKxz5HGjFg3MbmYgxlxvTCfPYFM68HlSNTNimuYUfUu5mVaH1SgrP2vuvx756DQ4Ne/1ECCHbQgeDPv76CPzWKSjW7+YKL75sQEqdHlmy/JpWluAdTDpw3w73pT8I='
```
### 加解密
#### 使用服务端点加解密
加密
```bash
curl -u cloudapp:cloudapp localhost:8888/encrypt -d cloudapp
```
解密
```bash
curl -u cloudapp:cloudapp localhost:8888/decrypt -d AQBshMnTfFCHLYu6iLtobA4fgh2CJnJqc/so1kk9hrqHIfm4lfJz5qN5oJCIhXgadAZCHlOz+wJMFyi6gl5v6WNMjusN9xeqd+9RELyBwuA990mHh6Q1v3VCqk7iMWJ2j28ejQTfAIkDbsvGiNDkOEU28+g6HfKKVpDCsGTIdXO5jIwiOGBf53IHXxTOwgo+jpahm2C2+vHEEJTrqUl5W6vdoRA2hSTfZ7/RaiIWOlUnN9GjpcpZwo6XmiOGsVZwVPmR/GQ86G0YYXLGZhzyw/dcKdNSgg1FiEnOQVCl5o0AANI5rH4Quiqi1YvWBHT9KqEp16f/QHyrZPd9aoIW8L1F+l9Ks+fvuggWlkyp9lRFYZRkWAXZhQnH2etTLH3VY58=
```
#### 命令行加解密
安装了 [Spring Cloud CLI](https://cloud.spring.io/spring-cloud-cli/) 扩展(需要先安装[Spring Boot CLI](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started-installing-spring-boot.html))可以用于加密和解密

```bash
spring encrypt cloudapp --key @/Users/liutiyang/project/java/laboratory/spring-cloud-example/hystrix-dashboard/src/main/resources/server.jks
spring encrypt cloudapp --key ${YOUR_KEY_PATH}/server.jks
spring decrypt AQBn3lvzsC1XT1rGHQ3g+g+nTDElDm5nUU2AqxwXUjENAXDyOUzXhqk/fbVjTbwZgQGL7OFrit27m14jAb3kYn+1+7YLj9Pnoq/E77RRwz+O2ejQ8YC73l372kmRsd1IiE0v4mxemkUcOeO5WVfFtBsTIWsqsK88UVA0FxfnSZaq0x8EDBYj4aPHPIQWQa/pFFjfKg0LJAJTDbtF816L/vWMJBCARIBtUOpeXRRBBnG0mJg2euzqJ6T/A2wBW/A8Dz7S98cKxz5HGjFg3MbmYgxlxvTCfPYFM68HlSNTNimuYUfUu5mVaH1SgrP2vuvx756DQ4Ne/1ECCHbQgeDPv76CPzWKSjW7+YKL75sQEqdHlmy/JpWluAdTDpw3w73pT8I= --key ${YOUR_KEY_PATH}/server.jks
```

- *如果要加密的值具有需要进行URL编码的字符，则应使用 --data-urlencode 选项 curl 来确保它们已正确编码。*

#### 注意事项及未解决问题

1. Eureka client 和 config 发现配置要放在 bootstrap.yml 文件里
1. Eureka client 密码不能加密
    - com.netflix.discovery.shared.resolver.aws.ConfigClusterResolver
    - 在调用时 Spring 的解密功能尚未实例化
1. Config server uri 默认值是 ``http://localhost:8888``
    - org.springframework.cloud.config.client.ConfigClientProperties
    - org.springframework.cloud.config.client.ConfigServicePropertySourceLocator
