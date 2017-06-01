# spring-cloud-example
Spring Cloud 示例

## 服务注册 (Eureka)
[Eureka](https://github.com/Netflix/eureka/wiki/Eureka-at-a-glance) 是 Netflix 开源的一个 RESTful 服务，主要用于服务的注册发现。Eureka 由两个组件组成：Eureka 服务器和 Eureka 客户端。

Eureka 服务端对应本示例的 discovery-service 模块。

## 配置中心

基于 Git 仓库对配置进行版本管理，使用默认实现的基本的 HttpBasic 的认证。

使用下列命令看不同的 profiles 配置。
```bash
curl -u cloudapp:FD3A86C4-3822-4551-9903-3E0B55B1EE65 http://localhost:8888/user/user-development.yml
curl -u cloudapp:FD3A86C4-3822-4551-9903-3E0B55B1EE65 http://localhost:8888/user/user-production.yml
curl -u cloudapp:FD3A86C4-3822-4551-9903-3E0B55B1EE65 http://localhost:8888/user/user-test.yml
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
