# mybatis-learn

> [mybatis-3](https://github.com/mybatis/mybatis-3) 的源代码阅读学习<br>
> 本项目学习使用的是 [gradle](https://gradle.org/) 构建，JDK 版本为 17，阅读的源代码分支为主分支*master*

这是我第一次阅读源代码，我没有专门去学习该如何阅读源代码，所以我只是按照自己的理解去阅读，如果有不对的地方，欢迎指正。

我的阅读方法是以单元测试为入口，从单元测试开始阅读，然后根据单元测试中的方法调用关系，逐步深入阅读。并将其“抄写”一遍
加深理解，过程会对各个函数的文档注释进行翻译，以便更好的理解。

<!-- TOC -->

* [mybatis-learn](#mybatis-learn)
    * [io](#io)
        * [ClassLoaderWrapper](#classloaderwrapper)

<!-- TOC -->

## io

### ClassLoaderWrapper

这是我第一个开始阅读的类

* [ClassLoaderWrapperTest.java](src/test/java/com/ahogek/ibatis/io/ClassLoaderWrapperTest.java)
* [ClassLoaderWrapper.java](src/main/java/com/ahogek/ibatis/io/ClassLoaderWrapper.java)

它是 *Mybatis-3* io模块下的类，io模块主要是用于读取资源相关工具的包，而`ClassLoaderWrapper`类是用于包装类加载器的类,
它的作用主要是用于获取类加载器，以及获取类加载器下的资源。

在编写`ClassLoaderWrapper`类时，我在每个包加了*package-info.java*，这是一个包注释文件，它的作用是用于描述包的信息

另外修改了 `build.gradle` 文件上的一些配置，例如 `group 'com.ahogek'` 表示项目的组名，`version '1.0-SNAPSHOT'` 表示项目的版本号

```groovy
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
```

上面的表示使用的是 *JDK17* 版本

编写中还添加了 `BaseDataTest.java` 主要是配置一些io包中测试需要用到的一些基础数据，在阅读`ClassLoaderWrapper`时还没有特别丰富的内容

`ClassLoaderWrapper` 主要包括通过全路径类名获取类，指定类加载器的同时通过全路将类名获取类，*URL*获取资源，通过流获取资源，以及相关指定
类加载器的同时的获取方法