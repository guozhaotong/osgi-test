# osgi 解决 jar 包冲突

## 前提准备

在本地工程中，安装两个不同版本具有冲突的jar，命令：`mvn install`

## 模块说明
三个模块
- `moduleone` 用了 myself 的1.0版本的一个函数
- `moduletwo` 用了 myself 的2.0版本的一个函数
- `modulecenter` 分别调用`moduleone` `moduletwo`，测试是否存在问题

### 1.安装和打包子模块

由于`modulecenter`需要依赖1和2，所以`moduleone` `moduletwo` 必须`mvn install` 到本地仓库而`modulecenter`仅仅需要运行就可以了，所以install没有必要，可以只执行`mvn package`

#### 打包 bundle

```
# 在工程的根目录下：

# 打包模块1
cd moduleone
mvn install
# 打包模块2
cd ../moduletwo
mvn install
# 打包中心模块
# 因为中心模块依赖了他的父工程，所以父工程也要添加的本地仓库
cd ..
mvn install

cd modulecenter
mvn package
```

### 2. felix中运行

#### 启动 osgi 容器

```
# 一定要进入osgi容器的目录中：
cd /home/tong/software/felix/
# 一定要在felix目录下才能执行成功！
java -jar bin/felix.jar
```

但是必须在启动`modulecenter`之前，先启动`moduleone` `moduletwo`

#### 安装 与 运行 bundle
```
install ../../osgi/osgiconflicttest/moduleone/target/moduleone-1.0-SNAPSHOT.jar  
install ../../osgi/osgiconflicttest/moduletwo/target/moduletwo-1.0-SNAPSHOT.jar
# 显示目前有哪些boudle
lb
```

接下来启动 bundle，根据bundle的id
```
start 14
start 15
start 16
```


## 遇到的坑

1. `moduleone` `moduletwo` 必须export 需要的接口所在的package，而且package中只能有接口，不能有实现!!!
1. `modulecenter` 必须 import `moduleone` `moduletwo`  刚export的package，同时还有其他（因为不写import的时候会自动import一些默认的，但是我们覆盖了默认的，所以必须把默认的再写上，可以尝试`*`,如：`<Import-Package>com.guozhaotong.moduleone.interfaces,com.guozhaotong.moduletwo.interfaces,org.osgi.framework,*</Import-Package>`）

## 好玩的
- 不小心把固有的bundle停止了，报错了？什么指令都不能运行了？
- 没事，去felix的目录下，删掉felix-cache里面的那些东西，就ok了

## 其他命令


现有的名字空间有三种：

- felix - 关于felix框架的核心命令，比如列出所有bundle，安装/卸载bundle等等
- gogo - 包含grep，cat，echo这类的指令，并且gogo是个Felix的子项目，他是参照RFC 147来实现的，这个标准定义了OSGi环境的shell应该是怎么样的。
- obr - 关于OSGi Bundle Repository功能的指令

命令有三个大类，目前我们暂时只使用了其中一种 `felix`,我们输入`help` 可以看到所有的命令

- `help` 查看有哪些命令
- `uninstall` 取消安装 bundle
- `exit 1` 退出 osgi 容器 
- `lb` 显示目前有哪些 bundle
- `ss` 显示已安装的bundles的状态信息，信息包括bundle ID，短名，状态等等。
- `start` 启动一个bundle
- `stop`  关闭一个bundle
- `update`  载入一个新的JAR文件更新一个bundle
- `install`  安装一个新的bundle到容器中
- `uninstall`  卸载一个已在容器中的bundle

##  MANIFEST.MF 说明
- `Bundle-Name`  给bundle定义一个短名，方便人员阅读
- `Bundle-SymbolicName` 给bundle定义一个唯一的非局部名。方便分辨。
- `Bundle-Activator` 声明在start和stop事件发生时会被通知的监听类的名字。
- `Import-Package` 定义bundle的导入包。


# 参考

https://www.cnblogs.com/doit8791/p/7745862.html

https://github.com/linbaosfire/blog-src/blob/e36d95a66922cdf90f3a2b6340bc40c44dd72252/source/_posts/OSGi%E5%BC%80%E5%8F%91%E4%B8%AD%E7%9A%84%E4%B8%80%E4%BA%9B%E8%AE%A4%E8%AF%86%E5%92%8C%E6%80%BB%E7%BB%93.md

# Jetty
启动jetty的话需要先启动
- jetty-6.1.7.jar
- jetty-util-6.1.7.jar
- pax-web-service-0.5.1.jar
- osgi.cmpn.jar
- pax-web-jsp-0.5.1.jar