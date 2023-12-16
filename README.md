cola原始文档，参见[官网说明](https://github.com/alibaba/COLA)

### 对骨架调整说明

原始骨架种结构如下，infra以来了整个domain，其实只依赖了接口定义，而domain却有很多领域操作，感觉不合理

#### 调整前

![cola](https://img-blog.csdnimg.cn/20201209192258840.png)

#### 调整后

![调整后](https://img-blog.csdnimg.cn/5011a420879e4eeaa60f761b4a6cd5bf.png)
调整结构后，app（改名biz） -> domain -> infra,并且 由bootstrap层组合各实现module。

1. client是对外暴露的jar包，方便外部接入
2. 为了更加的面向接口编程，对各层分别增加了接口定义层，各层对下层的依赖，只依赖接口定义module，
3. 分层，面向接口编程，意味着需要多层实体，DTO爆炸，为了减少一层，这里client层依赖biz层（主要考虑biz层还会被consumer层调起）
4. 当业务逻辑简单，或者项目前期，考虑去掉domain层，减少实体定义和透传层

### 其他改动

~~1. 增加parent，便于统一规范公共版本，统一升级等~~

2. 单元测试，infra 采用h2等验证sql；其他层采用PowerMockito，只做单元测试，不做集成测试；client层只做参数转换处理，不测试
3. 
