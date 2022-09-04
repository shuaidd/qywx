# qywx-spring-boot-starter
企业微信API封装
``` 
<dependency>
  <groupId>com.github.shuaidd</groupId>
  <artifactId>qywx-spring-boot-starter</artifactId>
  <version>4.0.1</version>
</dependency>

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
2022-08-31  新增获取子部门ID列表接口，获取成员ID列表接口，回调小优化返回解密后的xml
2022-01-12  优化细节，支持spring boot 高版本
2021-11-25  新增微信客服模块接口[客服账号管理，接待人员管理 会话分配 消息收发 客户信息获取等]
2021-11-24  新增通讯录异步导出接口-[导出成员，导出成员详情，导出部门，导出标签成员，获取导出结果，导出任务完成通知]，使用方式详见测试用例 AddressBookTest
2021-08-05  修复应用消息回调空指针问题 issue#9 
2021-07-20  统一回调处理 例子见example模块
2021-07-20  已实现模块 通讯录管理，客户联系，身份认证，应用管理，消息推送，素材管理，OA【除自建审批流外】，效率工具
2021-07-15  补充OA模块新增的接口
2021-07-14  处理企业微信回调数据统一解析处理
```

### 待完成 [来源企业微信变更日志]
```
新增接口 支持知识库分组管理 已完成
新增接口 支持知识库问答管理 已完成
变更接口 支持菜单消息插入换行和文本 

新增事件 微信用户撤回消息事件
新增事件 接待人员撤回消息事件

变更接口 获取企业假期管理配置，新增返回额度计算规则与假期过期规则 
变更接口 获取成员假期余额，新增返回假期的实际发放时长 
新增接口 支持企业为打卡人员补卡 

新增接口 支持将文件分块上传到微盘
```
