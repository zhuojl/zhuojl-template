## archetype:create-from-project

### quick start

1. cd 当前目录
2. mvn archetype:create-from-project
3. cd target/generated-sources/archetype
4. mvn install
5. cd 项目目录
6. 运行以下命令，创建项目

``` 
mvn org.apache.maven.plugins:maven-archetype-plugin:2.4:generate -B \
-DarchetypeGroupId=com.zjl.archetype \
-DarchetypeArtifactId=archetype-web \
-DarchetypeVersion=1.0.0-SNAPSHOT \
-DgroupId=com.zjl \
-DartifactId=demo \
-Dpackage=com.zjl.demo1 \
-Dversion=1.0.0-SNAPSHOT
```

7. 使用idea 打开项目
8. 运行项目

### 概览

该工程即是demo，也是模板工程，通过maven 插件 archetype:create-from-project
可生成archetype，通过archetype可生成具体项目。
![Archetype 生命周期](https://maven.apache.org/archetype/maven-archetype-plugin/images/archetype-overview.png)

archetype:create-from-project 本质是通过替换 模板代码
中各项变量来完成模板参数的替换，再通过archetype:generate 传入的参数替换，
从而生成一个新的项目

### 关键参数说明

#### archetype.properties

这个[文件](./archetype.properties)中的archetype.groupId, archetype.artifactId, archetype.version
只决定archetype
的版本信息，与模板代码、根据archetype生成的代码都没有关系。更多配置见：[create-from-project](https://maven.apache.org/archetype/maven-archetype-plugin/create-from-project-mojo.html#defaultEncoding)

#### 模板代码中关键参数

模板代码中的参数影响较大，主要是 groupId, artifactId, package，在create-from-project时，会使用这三个参数进行字符替换，
替换内容见下表。替换的优先级是 package,groupId,artifactId。

| 字段         | 影响内容                                    |
|------------|-----------------------------------------|
| package    | java文件路径，引用包路径                          |
| groupId    | 引用包路径（当package不能匹配时），替换字符               |
| artifactId | 多模块前缀定位（只有当模块前缀为artifactId，才会替换），代码字符替换 |

### 常见问题

1. 修改包结构后重新生成archetype时，注意mvn clean，不然打jar包会全打进去，generate时，会异常

### 参考

- [官网](https://maven.apache.org/archetype/maven-archetype-plugin/)
- [create-from-project](https://maven.apache.org/archetype/maven-archetype-plugin/create-from-project-mojo.html#defaultEncoding)

