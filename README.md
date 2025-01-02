# qywx-spring-boot-starter
企业微信API封装

springboot3.x
``` 
<dependency>
  <groupId>com.github.shuaidd</groupId>
  <artifactId>qywx-spring-boot-starter</artifactId>
  <version>5.1.0</version>
</dependency>

```

springboot2.x
``` 
<dependency>
  <groupId>com.github.shuaidd</groupId>
  <artifactId>qywx-spring-boot-starter</artifactId>
  <version>4.4.2</version>
</dependency>
```

依赖说明
``` 
项目依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

openfeign与springboot 需要搭配合理的版本，具体可参考https://spring.io/projects/spring-cloud#overview
```

使用示例
``` 
配置application.yml

qywx:
  corp-id: xxxx（企业号）
  application-list:
  - secret: Kx4sovYN5C0_MEzPY0cOymwbMhGmqdA9VjMFHrAKjdE
    agentId: 1000003
    application-name: little-helper
  - secret: DXB-FXVZNkLGUlLaIJy6CK67WD-dpN1HnPLIzNPo0N4
    agentId: 1000004
    application-name: reporter
  - secret: AfjvAed_ulqhK0OqTprDQ6xOSnqaT34ll2LsRe0D2NA
    application-name: address-book
  url: https://qyapi.weixin.qq.com
  public-path: cgi-bin

```
``` 
public class WeChatTest extends BaseTest {

    /**
    * 查询用户信息
    */
    @Test
    public void getUser(){
        weChatManager.addressBookService().getUser("13259220281", "address-book");
    }
}

实例 可以查看 qywx-spring-boot-example 模块
```

### 回调配置
![回调配置](https://upload-images.jianshu.io/upload_images/26817983-13eab16b4f158217.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 更新记录
``` 
2023-07-28 支持多企业号
2022-10-24 异步上传临时素材接口
2022-10-20 新增管理商品图册接口 管理聊天敏感词接口
2022-09-21 新增企业互联接口 电子发票接口，企业邮箱接口 【未实测，本人测试的企业微信没有权限-_-】
2022-09-04 新增企业微信微盘管理相关接口
2022-09-03 新增接口 支持知识库分组管理，支持知识库问答管理，支持企业为打卡人员补卡 
2022-08-31 新增获取子部门ID列表接口，获取成员ID列表接口，回调小优化返回解密后的xml
2022-01-12 优化细节，支持spring boot 高版本
2021-11-25 新增微信客服模块接口[客服账号管理，接待人员管理 会话分配 消息收发 客户信息获取等]
2021-11-24 新增通讯录异步导出接口-[导出成员，导出成员详情，导出部门，导出标签成员，获取导出结果，导出任务完成通知]，使用方式详见测试用例 AddressBookTest
2021-08-05 修复应用消息回调空指针问题 issue#9 
2021-07-20 统一回调处理 例子见example模块
2021-07-20 已实现模块 通讯录管理，客户联系，身份认证，应用管理，消息推送，素材管理，OA【除自建审批流外】，效率工具
2021-07-15 补充OA模块新增的接口
2021-07-14 处理企业微信回调数据统一解析处理
```

### 待完成 [来源企业微信变更日志]
```
如果有未支持到的接口，而您又刚好想用，欢迎issue，看到后，会根据情况，同步新增

客户联系-客户管理-获取规则组列表
客户联系-在职继承
客户联系-客户群管理-客户群opengid转换
客户联系-联系我与客户入群方式-客户群「加入群聊」管理
客户联系-客户朋友圈-获取规则组列表
客户联系-消息推送-获取企业的全部群发记录版本2
```
