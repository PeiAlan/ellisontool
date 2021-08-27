# EllisonTool是一个本人使用的云端工具类项目

## 引言
每当项目要使用各种工具时，是用别人的工具类总是不尽人意，所有就总结了一套常用的工具类，以后也会不断更新。

## 项目介绍
### 常用工具类
- Bean操作工具类（EllisonBeanUtil.java）
- BigDecimal工具类（EllisonBigDecimalUtil.java）
- Date工具类（EllisonDateUtil.java.java）
- 加密工具类（EllisonDigestUtil.java）
- Excel操作工具类（EllisonEasyPoiExcelUtil.java）
- Http工具类（EllisonHttpUtil.java）
- IP工具类（EllisonIPUtil.java）
- MD5工具类（EllisonMD5Util.java）
- Quartz工具类（EllisonQuartzUtil.java）
- Regex正则使用工具类（EllisonRegexUtil.java）
- Email工具类（EllisonSendMailUtil.java）
- String工具类（EllisonStringUtil.java）

### 线程使用相关配置类
- 线程Executor配置工具类（EllisonExecutorConfig.java）
- ThreadFactory自定义类（EllisonThreadFactory.java）
- ThreadPoolTaskExecutor自定义类（VisibleThreadPoolTaskExecutor.java）

## 使用方法
### `maven`按照下面信息加入到你的`pom.xml`文件
#### Step1: Add the JitPack repository to your build file
```pom
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories> 
```
#### Step2: Add the dependency
```pom
<dependency>
    <groupId>com.github.PeiAlan</groupId>
    <artifactId>ellisontool</artifactId>
    <version>1.0.0</version>
</dependency>
```