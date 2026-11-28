# 企微服务端 API 覆盖度清单

## 核对口径

- 核对日期：2026-11-21。
- 官方接口目录：https://developer.work.weixin.qq.com/document/path/90664 。
- 更新日志目录：https://developer.work.weixin.qq.com/document/path/93221 。
- 核对对象是官方侧边栏当前暴露的 738 个文档页；空的“分类1”“分类13”“分类22”不单独列行。
- 本仓库范围是企业微信服务端 REST API 对接；不管理回调接入配置，也不覆盖前端 JSSDK。官方更新日志处理结果另见 `CHANGELOG-PROCESSED.md`。
- 状态口径：
  - ✅ 已对接：该文档页的接口已在 `qywx-core` Feign Client/Service 中有对应实现（6.0 前为 `qywx-spring-boot-api`）。
  - ⚠️ 部分已对接：一个文档页包含多条路由，其中至少一条已实现，但未全部实现。
  - ❌ 未对接：属于服务端 API，但当前代码未找到对应 Feign 路由。
  - ⛔ 范围外：概念说明、接入指引、回调事件、前端/客户端能力、官方 SDK/专区程序、调试资料或非 REST 内容。
- 核对方法：官方侧边栏标题 + 已成功抓取的官方页路由 + `src/main/java/com/github/shuaidd/client/*.java` 中 574 个 Feign 方法静态比对。官方站点抓取触发频控后未绕过验证码；未抓到真实路由的页面按分类、页面标题和代码方法判断。
- 一个文档页可能包含多个路由或多个操作；“已对接”证据列只列主要 Feign Client/方法。

## 总体统计

| 状态 | 文档页数 |
| --- | ---: |
| ✅ 已对接 | 488 |
| ⚠️ 部分已对接 | 2 |
| ❌ 未对接 | 70 |
| ⛔ 范围外 | 178 |
| 合计 | 738 |

> 统计单位是官方文档页，不是路由数。一个页面可能聚合多个 REST 操作；例如“管理企业标签”页覆盖查询、新增、编辑、删除等多个路由。

## 待补齐的重点模块

1. 消息/机器人：剩余机器人与消息相关缺口。
2. 上下游：通讯录、对接规则和关联客户信息接口。
3. 安全管理：文件防泄漏、设备、截屏录屏、操作日志。
4. 企业支付与小程序对外收款。
5. 家校沟通/家校应用、政民沟通。
6. 邮件发送、收件箱、应用邮箱账号、高级功能账号等。
7. 智能文档、收集表、文档素材和智能表格编组。
8. 日程重复日程/参与者，会议统计与高级会控/Webinar/录制/Rooms/MRA。
9. 高级功能账号。


## 开发指南

状态：✅ 3；⚠️ 0；❌ 0；⛔ 3

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 开发指南 | 开发前必读 | [90664](https://developer.work.weixin.qq.com/document/path/90664) | ⛔ 范围外 | — |
|  | 基本概念介绍 | [90665](https://developer.work.weixin.qq.com/document/path/90665) | ⛔ 范围外 | — |
|  | 获取access_token | [91039](https://developer.work.weixin.qq.com/document/path/91039) | ✅ 已对接 | WeChatClient.gettoken |
|  | 回调配置 | [90930](https://developer.work.weixin.qq.com/document/path/90930) | ⛔ 范围外 | — |
|  | 获取企业微信接口IP段 | [92520](https://developer.work.weixin.qq.com/document/path/92520) | ✅ 已对接 | WeChatClient.apiDomainIp（get_api_domain_ip） |
|  | 获取企业微信回调IP段 | [92521](https://developer.work.weixin.qq.com/document/path/92521) | ✅ 已对接 | WeChatClient.callBackIp（getcallbackip） |

## 账号ID

状态：✅ 1；⚠️ 2；❌ 0；⛔ 1

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 账号ID | 概述 | [98728](https://developer.work.weixin.qq.com/document/path/98728) | ⛔ 范围外 | — |
|  | 自建应用与第三方应用的对接 | [95884](https://developer.work.weixin.qq.com/document/path/95884) | ⚠️ 部分已对接 | 部分：WeChatClient.openUserId2UserId（batch/openuserid_to_userid） |
|  | 自建应用与智能机器人的对接 | [101521](https://developer.work.weixin.qq.com/document/path/101521) | ⚠️ 部分已对接 | 部分：WeChatClient.openUserId2UserId（batch/openuserid_to_userid） |
|  | tmp_external_userid的转换 | [98729](https://developer.work.weixin.qq.com/document/path/98729) | ✅ 已对接 | WeChatClient.convertTmpExternalUserId（idconvert/convert_tmp_external_userid） |

## 通讯录管理

状态：✅ 37；⚠️ 0；❌ 3；⛔ 11

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 通讯录管理 | 概述 | [90193](https://developer.work.weixin.qq.com/document/path/90193) | ⛔ 范围外 | — |
| 成员管理 | 成员扩展属性 | [100067](https://developer.work.weixin.qq.com/document/path/100067) | ⛔ 范围外 | — |
|  | 创建成员 | [90195](https://developer.work.weixin.qq.com/document/path/90195) | ✅ 已对接 | AddressBookClient.createUser（user/create） |
|  | 读取成员 | [90196](https://developer.work.weixin.qq.com/document/path/90196) | ✅ 已对接 | AddressBookClient.getUser（user/get） |
|  | 更新成员 | [90197](https://developer.work.weixin.qq.com/document/path/90197) | ✅ 已对接 | AddressBookClient.updateUser（user/update） |
|  | 删除成员 | [90198](https://developer.work.weixin.qq.com/document/path/90198) | ✅ 已对接 | AddressBookClient.deleteUser（user/delete） |
|  | 批量删除成员 | [90199](https://developer.work.weixin.qq.com/document/path/90199) | ✅ 已对接 | AddressBookClient.batchDelete（user/batchdelete） |
|  | 获取部门成员 | [90200](https://developer.work.weixin.qq.com/document/path/90200) | ✅ 已对接 | AddressBookClient.getDepartmentUser（user/simplelist） |
|  | 获取部门成员详情 | [90201](https://developer.work.weixin.qq.com/document/path/90201) | ✅ 已对接 | AddressBookClient.getDepartmentUserDetail（user/list） |
|  | userid与openid互换 | [90202](https://developer.work.weixin.qq.com/document/path/90202) | ✅ 已对接 | AddressBookClient.convertToOpenId / convertToUserId |
|  | 登录二次验证 | [90203](https://developer.work.weixin.qq.com/document/path/90203) | ✅ 已对接 | AddressBookClient.authSucc（user/authsucc） |
|  | 邀请成员 | [90975](https://developer.work.weixin.qq.com/document/path/90975) | ✅ 已对接 | AddressBookClient.invite（batch/invite） |
|  | 获取加入企业二维码 | [91714](https://developer.work.weixin.qq.com/document/path/91714) | ✅ 已对接 | AddressBookClient.getJoinQrCode |
|  | 手机号获取userid | [95402](https://developer.work.weixin.qq.com/document/path/95402) | ✅ 已对接 | AddressBookClient.getUserIdByMobile（user/getuserid） |
|  | 邮箱获取userid | [95895](https://developer.work.weixin.qq.com/document/path/95895) | ✅ 已对接 | AddressBookClient.getUserIdByEmail（user/get_userid_by_email） |
|  | 获取成员ID列表 | [96067](https://developer.work.weixin.qq.com/document/path/96067) | ✅ 已对接 | AddressBookClient.getUserIds（user/list_id） |
| 部门管理 | 创建部门 | [90205](https://developer.work.weixin.qq.com/document/path/90205) | ✅ 已对接 | AddressBookClient.createDepartment |
|  | 更新部门 | [90206](https://developer.work.weixin.qq.com/document/path/90206) | ✅ 已对接 | AddressBookClient.updateDepartment |
|  | 删除部门 | [90207](https://developer.work.weixin.qq.com/document/path/90207) | ✅ 已对接 | AddressBookClient.deleteDepartment |
|  | 获取部门列表 | [90208](https://developer.work.weixin.qq.com/document/path/90208) | ✅ 已对接 | AddressBookClient.departmentList（department/list） |
|  | 获取子部门ID列表 | [95350](https://developer.work.weixin.qq.com/document/path/95350) | ✅ 已对接 | AddressBookClient.departmentSimpleList |
|  | 获取单个部门详情 | [95351](https://developer.work.weixin.qq.com/document/path/95351) | ✅ 已对接 | AddressBookClient.getDepartmentDetail（department/get） |
| 标签管理 | 创建标签 | [90210](https://developer.work.weixin.qq.com/document/path/90210) | ✅ 已对接 | AddressBookClient.createTag |
|  | 更新标签名字 | [90211](https://developer.work.weixin.qq.com/document/path/90211) | ✅ 已对接 | AddressBookClient.updateTag |
|  | 删除标签 | [90212](https://developer.work.weixin.qq.com/document/path/90212) | ✅ 已对接 | AddressBookClient.deleteTag |
|  | 获取标签成员 | [90213](https://developer.work.weixin.qq.com/document/path/90213) | ✅ 已对接 | AddressBookClient.getTagUser |
|  | 增加标签成员 | [90214](https://developer.work.weixin.qq.com/document/path/90214) | ✅ 已对接 | AddressBookClient.addTagUsers |
|  | 删除标签成员 | [90215](https://developer.work.weixin.qq.com/document/path/90215) | ✅ 已对接 | AddressBookClient.deleteTagUsers |
|  | 获取标签列表 | [90216](https://developer.work.weixin.qq.com/document/path/90216) | ✅ 已对接 | AddressBookClient.getTagList |
| 通讯录查看权限管理 | 创建规则 | [101536](https://developer.work.weixin.qq.com/document/path/101536) | ✅ 已对接 | AddressBookClient.createContactRule |
|  | 读取规则列表 | [101537](https://developer.work.weixin.qq.com/document/path/101537) | ✅ 已对接 | AddressBookClient.getContactRuleList |
|  | 修改规则 | [101539](https://developer.work.weixin.qq.com/document/path/101539) | ✅ 已对接 | AddressBookClient.updateContactRule |
|  | 删除规则 | [101540](https://developer.work.weixin.qq.com/document/path/101540) | ✅ 已对接 | AddressBookClient.deleteContactRule |
| 异步导入接口 | 概述 | [90979](https://developer.work.weixin.qq.com/document/path/90979) | ⛔ 范围外 | — |
|  | 增量更新成员 | [90980](https://developer.work.weixin.qq.com/document/path/90980) | ✅ 已对接 | AddressBookClient.asyncBatchUpdateUser（batch/syncuser） |
|  | 全量覆盖成员 | [90981](https://developer.work.weixin.qq.com/document/path/90981) | ✅ 已对接 | AddressBookClient.fullCoverUser |
|  | 全量覆盖部门 | [90982](https://developer.work.weixin.qq.com/document/path/90982) | ✅ 已对接 | AddressBookClient.fullCoverDepartment |
|  | 获取异步任务结果 | [90983](https://developer.work.weixin.qq.com/document/path/90983) | ✅ 已对接 | AddressBookClient.jobResult（batch/getresult） |
| 异步导出接口 | 概述 | [94850](https://developer.work.weixin.qq.com/document/path/94850) | ⛔ 范围外 | — |
|  | 导出成员 | [94849](https://developer.work.weixin.qq.com/document/path/94849) | ✅ 已对接 | AddressBookClient.exportUser（export/simple_user） |
|  | 导出成员详情 | [94851](https://developer.work.weixin.qq.com/document/path/94851) | ✅ 已对接 | AddressBookClient.exportUserDetail（export/user） |
|  | 导出部门 | [94852](https://developer.work.weixin.qq.com/document/path/94852) | ✅ 已对接 | AddressBookClient.exportDepartment |
|  | 导出标签成员 | [94853](https://developer.work.weixin.qq.com/document/path/94853) | ✅ 已对接 | AddressBookClient.exportTagUser |
|  | 获取导出结果 | [94854](https://developer.work.weixin.qq.com/document/path/94854) | ✅ 已对接 | WeChatClient.getExportResult |
|  | 导出任务完成通知 | [94946](https://developer.work.weixin.qq.com/document/path/94946) | ⛔ 范围外 | — |
| 通讯录回调通知 | 概述 | [90967](https://developer.work.weixin.qq.com/document/path/90967) | ⛔ 范围外 | — |
|  | 成员变更通知 | [90970](https://developer.work.weixin.qq.com/document/path/90970) | ⛔ 范围外 | — |
|  | 部门变更通知 | [90971](https://developer.work.weixin.qq.com/document/path/90971) | ⛔ 范围外 | — |
|  | 标签变更通知 | [90972](https://developer.work.weixin.qq.com/document/path/90972) | ⛔ 范围外 | — |
|  | 异步任务完成通知 | [90973](https://developer.work.weixin.qq.com/document/path/90973) | ⛔ 范围外 | — |
|  | 通讯录同步接口调整 | [96079](https://developer.work.weixin.qq.com/document/path/96079) | ⛔ 范围外 | — |

## 身份验证

状态：✅ 6；⚠️ 0；❌ 0；⛔ 5

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 网页授权登录 | 开始开发 | [91335](https://developer.work.weixin.qq.com/document/path/91335) | ⛔ 范围外 | — |
|  | 构造网页授权链接 | [91022](https://developer.work.weixin.qq.com/document/path/91022) | ⛔ 范围外 | — |
|  | 获取访问用户身份 | [91023](https://developer.work.weixin.qq.com/document/path/91023) | ✅ 已对接 | AddressBookClient.getAuthentication / AuthClient.getUserInfo（user/getuserinfo） |
|  | 获取访问用户敏感信息 | [95833](https://developer.work.weixin.qq.com/document/path/95833) | ✅ 已对接 | AuthClient.getUserDetail（auth/getuserdetail） |
| 企业微信Web登录 | 开始开发 | [98151](https://developer.work.weixin.qq.com/document/path/98151) | ⛔ 范围外 | — |
|  | Web登录组件 | [98152](https://developer.work.weixin.qq.com/document/path/98152) | ⛔ 范围外 | — |
|  | 获取用户登录身份 | [98176](https://developer.work.weixin.qq.com/document/path/98176) | ✅ 已对接 | AuthClient.getUserInfo（auth/getuserinfo） |
| 二次验证 | 概述 | [99519](https://developer.work.weixin.qq.com/document/path/99519) | ⛔ 范围外 | — |
|  | 获取用户二次验证信息 | [99499](https://developer.work.weixin.qq.com/document/path/99499) | ✅ 已对接 | AuthClient.getUserTfaInfo（auth/get_tfa_info） |
|  | 登录二次验证 | [99521](https://developer.work.weixin.qq.com/document/path/99521) | ✅ 已对接 | AddressBookClient.authSucc（user/authsucc） |
|  | 使用二次验证 | [99500](https://developer.work.weixin.qq.com/document/path/99500) | ✅ 已对接 | AuthClient.useUserTfa（user/tfa_succ） |

## 企业互联

状态：✅ 3；⚠️ 0；❌ 0；⛔ 1

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 企业互联 | 概述 | [93360](https://developer.work.weixin.qq.com/document/path/93360) | ⛔ 范围外 | — |
|  | 获取应用共享信息 | [93403](https://developer.work.weixin.qq.com/document/path/93403) | ✅ 已对接 | EnterpriseInterConnectionClient.appShareInfo |
|  | 获取下级/下游企业的access_token | [93359](https://developer.work.weixin.qq.com/document/path/93359) | ✅ 已对接 | EnterpriseInterConnectionClient.getToken |
|  | 获取下级/下游企业小程序session | [93355](https://developer.work.weixin.qq.com/document/path/93355) | ✅ 已对接 | EnterpriseInterConnectionClient.transferSession |

## 上下游

状态：✅ 3；⚠️ 0；❌ 13；⛔ 4

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 上下游 | 概述 | [97213](https://developer.work.weixin.qq.com/document/path/97213) | ⛔ 范围外 | — |
| 基础接口 | 获取应用共享信息 | [95813](https://developer.work.weixin.qq.com/document/path/95813) | ✅ 已对接 | EnterpriseInterConnectionClient.appShareInfo |
|  | 获取下级/下游企业的access_token | [95816](https://developer.work.weixin.qq.com/document/path/95816) | ✅ 已对接 | EnterpriseInterConnectionClient.getToken |
|  | 获取下级/下游企业小程序session | [95817](https://developer.work.weixin.qq.com/document/path/95817) | ✅ 已对接 | EnterpriseInterConnectionClient.transferSession |
|  | 上下游关联客户信息-已添加客户 | [95818](https://developer.work.weixin.qq.com/document/path/95818) | ❌ 未对接 | — |
|  | 上下游关联客户信息-未添加客户 | [97357](https://developer.work.weixin.qq.com/document/path/97357) | ❌ 未对接 | — |
| 上下游通讯录管理 | 获取上下游信息 | [95820](https://developer.work.weixin.qq.com/document/path/95820) | ❌ 未对接 | — |
|  | 批量导入上下游联系人 | [95821](https://developer.work.weixin.qq.com/document/path/95821) | ❌ 未对接 | — |
|  | 获取异步任务结果 | [95823](https://developer.work.weixin.qq.com/document/path/95823) | ❌ 未对接 | — |
|  | 移除企业 | [95822](https://developer.work.weixin.qq.com/document/path/95822) | ❌ 未对接 | — |
|  | 查询成员自定义id | [97441](https://developer.work.weixin.qq.com/document/path/97441) | ❌ 未对接 | — |
|  | 获取下级企业加入的上下游 | [97442](https://developer.work.weixin.qq.com/document/path/97442) | ❌ 未对接 | — |
| 上下游规则 | 获取对接规则id列表 | [95631](https://developer.work.weixin.qq.com/document/path/95631) | ❌ 未对接 | — |
|  | 删除对接规则 | [95632](https://developer.work.weixin.qq.com/document/path/95632) | ❌ 未对接 | — |
|  | 获取对接规则详情 | [95633](https://developer.work.weixin.qq.com/document/path/95633) | ❌ 未对接 | — |
|  | 新增对接规则 | [95634](https://developer.work.weixin.qq.com/document/path/95634) | ❌ 未对接 | — |
|  | 更新对接规则 | [95635](https://developer.work.weixin.qq.com/document/path/95635) | ❌ 未对接 | — |
| 回调事件 | 概述 | [95794](https://developer.work.weixin.qq.com/document/path/95794) | ⛔ 范围外 | — |
|  | 上下游变更回调 | [95796](https://developer.work.weixin.qq.com/document/path/95796) | ⛔ 范围外 | — |
|  | 异步任务完成通知 | [95797](https://developer.work.weixin.qq.com/document/path/95797) | ⛔ 范围外 | — |

## 安全管理

状态：✅ 6；⚠️ 0；❌ 3；⛔ 1

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 安全管理 | 文件防泄漏 | [98079](https://developer.work.weixin.qq.com/document/path/98079) | ✅ 已对接 | SecurityClient.listFileOperRecords（security/get_file_oper_record） |
|  | 设备管理 | [98920](https://developer.work.weixin.qq.com/document/path/98920) | ✅ 已对接 | SecurityClient.importTrustDevices、listTrustDevices、getTrustDevicesByUser、deleteTrustDevices、approveTrustDevices、rejectTrustDevices（security/trustdevice/import、/list、/get_by_user、/delete、/approve、/reject） |
|  | 截屏/录屏管理 | [100128](https://developer.work.weixin.qq.com/document/path/100128) | ✅ 已对接 | SecurityClient.listScreenOperRecords（security/get_screen_oper_record） |
| 高级功能账号管理 | 分配高级功能账号 | [99503](https://developer.work.weixin.qq.com/document/path/99503) | ✅ 已对接 | SecurityClient.submitBatchAddVipAccounts / getBatchAddVipJobResult（security/vip/submit_batch_add_job、batch_add_job_result） |
|  | 取消高级功能账号 | [99505](https://developer.work.weixin.qq.com/document/path/99505) | ✅ 已对接 | SecurityClient.submitBatchDeleteVipAccounts / getBatchDeleteVipJobResult（security/vip/submit_batch_del_job、batch_del_job_result） |
|  | 获取高级功能账号列表 | [99506](https://developer.work.weixin.qq.com/document/path/99506) | ✅ 已对接 | SecurityClient.listVipAccounts（security/vip/list） |
| 操作日志 | 获取成员操作记录 | [100178](https://developer.work.weixin.qq.com/document/path/100178) | ✅ 已对接 | SecurityClient.listMemberOperLog / SecurityService.listMemberOperLog（security/member_oper_log/list） |
|  | 获取管理端操作日志 | [100179](https://developer.work.weixin.qq.com/document/path/100179) | ✅ 已对接 | SecurityClient.listAdminOperLog / SecurityService.listAdminOperLog（security/admin_oper_log/list） |
|  | 获取企业微信域名IP信息 | [100079](https://developer.work.weixin.qq.com/document/path/100079) | ✅ 已对接 | SecurityClient.getServerDomainIp / SecurityService.getServerDomainIp（security/get_server_domain_ip） |
|  | 回调通知 | [100080](https://developer.work.weixin.qq.com/document/path/100080) | ⛔ 范围外 | — |

## 消息接收与发送

状态：✅ 7；⚠️ 0；❌ 5；⛔ 15

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 消息接收与发送 | 概述 | [90235](https://developer.work.weixin.qq.com/document/path/90235) | ⛔ 范围外 | — |
|  | 发送应用消息 | [90236](https://developer.work.weixin.qq.com/document/path/90236) | ✅ 已对接 | MessageClient.sendMessage（message/send） |
|  | 更新模版卡片消息 | [94888](https://developer.work.weixin.qq.com/document/path/94888) | ✅ 已对接 | MessageClient.updateTemplateCard（message/update_template_card） |
|  | 撤回应用消息 | [94867](https://developer.work.weixin.qq.com/document/path/94867) | ✅ 已对接 | MessageClient.recallMessage（message/recall） |
| 接收消息与事件 | 概述 | [90238](https://developer.work.weixin.qq.com/document/path/90238) | ⛔ 范围外 | — |
|  | 消息格式 | [90239](https://developer.work.weixin.qq.com/document/path/90239) | ⛔ 范围外 | — |
|  | 事件格式 | [90240](https://developer.work.weixin.qq.com/document/path/90240) | ⛔ 范围外 | — |
|  | 被动回复消息格式 | [90241](https://developer.work.weixin.qq.com/document/path/90241) | ⛔ 范围外 | — |
| 应用发送消息到群聊会话 | 概述 | [90244](https://developer.work.weixin.qq.com/document/path/90244) | ⛔ 范围外 | — |
|  | 创建群聊会话 | [90245](https://developer.work.weixin.qq.com/document/path/90245) | ✅ 已对接 | MessageClient.createAppChat |
|  | 修改群聊会话 | [98913](https://developer.work.weixin.qq.com/document/path/98913) | ✅ 已对接 | MessageClient.updateAppChat |
|  | 获取群聊会话 | [98914](https://developer.work.weixin.qq.com/document/path/98914) | ✅ 已对接 | MessageClient.searchAppChat |
|  | 应用推送消息 | [90248](https://developer.work.weixin.qq.com/document/path/90248) | ✅ 已对接 | MessageClient.sendAppChatMessage（appchat/send） |
| 家校消息推送 | 发送「学校通知」 | [91609](https://developer.work.weixin.qq.com/document/path/91609) | ✅ 已对接 | MessageClient.sendSchoolNotification（externalcontact/message/send） |
| 消息推送（原“群机器人”） | 消息推送配置说明 | [99110](https://developer.work.weixin.qq.com/document/path/99110) | ⛔ 范围外 | — |
| 智能机器人 | 概述 | [101039](https://developer.work.weixin.qq.com/document/path/101039) | ⛔ 范围外 | — |
|  | 接收消息 | [100719](https://developer.work.weixin.qq.com/document/path/100719) | ⛔ 范围外 | — |
|  | 接收事件 | [101027](https://developer.work.weixin.qq.com/document/path/101027) | ⛔ 范围外 | — |
|  | 被动回复消息 | [101031](https://developer.work.weixin.qq.com/document/path/101031) | ⛔ 范围外 | — |
|  | 模板卡片类型 | [101032](https://developer.work.weixin.qq.com/document/path/101032) | ⛔ 范围外 | — |
|  | 回调和回复的加解密方案 | [101033](https://developer.work.weixin.qq.com/document/path/101033) | ⛔ 范围外 | — |
|  | 主动回复消息 | [101138](https://developer.work.weixin.qq.com/document/path/101138) | ✅ 已对接 | MessageClient.responseAiBot（aibot/response） |
|  | 智能机器人长连接 | [101463](https://developer.work.weixin.qq.com/document/path/101463) | ⛔ 范围外 | — |
|  | API模式机器人文档使用说明 | [101468](https://developer.work.weixin.qq.com/document/path/101468) | ⛔ 范围外 | — |
| 智能表格自动化创建的群聊 | 获取群聊列表 | [100989](https://developer.work.weixin.qq.com/document/path/100989) | ✅ 已对接 | WechatSmartSheetClient.getAutomationGroupChatList（wedoc/smartsheet/groupchat/list） |
|  | 获取群聊会话 | [101028](https://developer.work.weixin.qq.com/document/path/101028) | ✅ 已对接 | MessageClient.searchAppChat |
|  | 修改群聊会话 | [101029](https://developer.work.weixin.qq.com/document/path/101029) | ✅ 已对接 | MessageClient.updateAppChat |

## 应用管理

状态：✅ 5；⚠️ 0；❌ 1；⛔ 1

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 应用管理 | 概述 | [90226](https://developer.work.weixin.qq.com/document/path/90226) | ⛔ 范围外 | — |
|  | 获取应用 | [90227](https://developer.work.weixin.qq.com/document/path/90227) | ✅ 已对接 | WorkApplicationClient.getApplication（agent/get） |
|  | 设置应用 | [90228](https://developer.work.weixin.qq.com/document/path/90228) | ✅ 已对接 | WorkApplicationClient.applicationSetting |
| 自定义菜单 | 创建菜单 | [90231](https://developer.work.weixin.qq.com/document/path/90231) | ✅ 已对接 | WorkApplicationClient.createApplicationButton |
|  | 获取菜单 | [90232](https://developer.work.weixin.qq.com/document/path/90232) | ✅ 已对接 | WorkApplicationClient.getApplicationButtons |
|  | 删除菜单 | [90233](https://developer.work.weixin.qq.com/document/path/90233) | ✅ 已对接 | WorkApplicationClient.deleteApplicationButtons |
|  | 设置工作台自定义展示 | [92535](https://developer.work.weixin.qq.com/document/path/92535) | ✅ 已对接 | WorkApplicationClient 工作台模板/数据 5 条路由 |

## 素材管理

状态：✅ 5；⚠️ 0；❌ 0；⛔ 1

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 素材管理 | 概述 | [91054](https://developer.work.weixin.qq.com/document/path/91054) | ⛔ 范围外 | — |
|  | 上传临时素材 | [90253](https://developer.work.weixin.qq.com/document/path/90253) | ✅ 已对接 | WeChatMediaClient.uploadMaterial（media/upload） |
|  | 上传图片 | [90256](https://developer.work.weixin.qq.com/document/path/90256) | ✅ 已对接 | WeChatMediaClient.uploadImage（media/uploadimg） |
|  | 获取临时素材 | [90254](https://developer.work.weixin.qq.com/document/path/90254) | ✅ 已对接 | WeChatMediaClient.downloadFile（media/get） |
|  | 获取高清语音素材 | [90255](https://developer.work.weixin.qq.com/document/path/90255) | ✅ 已对接 | WeChatMediaClient.downloadJsMediaFile（media/get） |
|  | 异步上传临时素材 | [96219](https://developer.work.weixin.qq.com/document/path/96219) | ✅ 已对接 | WeChatClient.uploadByUrl / getUploadByUrlResult |

## 电子发票

状态：✅ 4；⚠️ 0；❌ 0；⛔ 1

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 电子发票 | 概述 | [90283](https://developer.work.weixin.qq.com/document/path/90283) | ⛔ 范围外 | — |
|  | 查询电子发票 | [90284](https://developer.work.weixin.qq.com/document/path/90284) | ✅ 已对接 | ElectronicInvoiceClient.getInvoice |
|  | 更新发票状态 | [90285](https://developer.work.weixin.qq.com/document/path/90285) | ✅ 已对接 | ElectronicInvoiceClient.updateInvoice |
|  | 批量更新发票状态 | [90286](https://developer.work.weixin.qq.com/document/path/90286) | ✅ 已对接 | ElectronicInvoiceClient.batchUpdateInvoice |
|  | 批量查询电子发票 | [90287](https://developer.work.weixin.qq.com/document/path/90287) | ✅ 已对接 | ElectronicInvoiceClient.getInvoices |

## 数据与智能专区

状态：✅ 0；⚠️ 0；❌ 9；⛔ 43

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 数据与智能专区 | 概述 | [99941](https://developer.work.weixin.qq.com/document/path/99941) | ⛔ 范围外 | — |
|  | 基本概念介绍 | [99942](https://developer.work.weixin.qq.com/document/path/99942) | ⛔ 范围外 | — |
|  | 接入指引 | [99871](https://developer.work.weixin.qq.com/document/path/99871) | ⛔ 范围外 | — |
|  | 专区程序开发指引 | [100057](https://developer.work.weixin.qq.com/document/path/100057) | ⛔ 范围外 | — |
|  | 专区程序示例 | [100052](https://developer.work.weixin.qq.com/document/path/100052) | ⛔ 范围外 | — |
|  | 专区程序SDK和示例下载 | [100249](https://developer.work.weixin.qq.com/document/path/100249) | ⛔ 范围外 | — |
|  | 镜像文件配置指引 | [99955](https://developer.work.weixin.qq.com/document/path/99955) | ⛔ 范围外 | — |
|  | 会话展示组件 | [100076](https://developer.work.weixin.qq.com/document/path/100076) | ⛔ 范围外 | — |
|  | 文件内容存档 | [101501](https://developer.work.weixin.qq.com/document/path/101501) | ⛔ 范围外 | — |
| 基础接口 | 设置公钥 | [99961](https://developer.work.weixin.qq.com/document/path/99961) | ❌ 未对接 | — |
|  | 获取会话存档授权成员列表 | [99962](https://developer.work.weixin.qq.com/document/path/99962) | ❌ 未对接 | — |
|  | 设置专区接收回调事件 | [99963](https://developer.work.weixin.qq.com/document/path/99963) | ⛔ 范围外 | — |
|  | 会话组件敏感信息隐藏设置 | [100139](https://developer.work.weixin.qq.com/document/path/100139) | ❌ 未对接 | — |
|  | 设置日志打印级别 | [100108](https://developer.work.weixin.qq.com/document/path/100108) | ❌ 未对接 | — |
|  | 上传临时文件到专区 | [100174](https://developer.work.weixin.qq.com/document/path/100174) | ❌ 未对接 | — |
|  | 获取文件内容存档授权成员列表 | [101873](https://developer.work.weixin.qq.com/document/path/101873) | ❌ 未对接 | — |
| 应用调用专区程序 | 概述 | [99964](https://developer.work.weixin.qq.com/document/path/99964) | ⛔ 范围外 | — |
|  | 应用同步调用专区程序 | [99965](https://developer.work.weixin.qq.com/document/path/99965) | ❌ 未对接 | — |
|  | 应用异步调用专区程序 | [99966](https://developer.work.weixin.qq.com/document/path/99966) | ❌ 未对接 | — |
| 专区程序调用sdk | 概述 | [99967](https://developer.work.weixin.qq.com/document/path/99967) | ⛔ 范围外 | — |
|  | 获取会话记录 | [99968](https://developer.work.weixin.qq.com/document/path/99968) | ⛔ 范围外 | — |
|  | 获取会话同意情况 | [99969](https://developer.work.weixin.qq.com/document/path/99969) | ⛔ 范围外 | — |
|  | 获取内部群信息 | [99970](https://developer.work.weixin.qq.com/document/path/99970) | ⛔ 范围外 | — |
|  | 会话名称搜索 | [99971](https://developer.work.weixin.qq.com/document/path/99971) | ⛔ 范围外 | — |
|  | 会话消息搜索 | [99972](https://developer.work.weixin.qq.com/document/path/99972) | ⛔ 范围外 | — |
|  | 员工或客户名称搜索 | [100243](https://developer.work.weixin.qq.com/document/path/100243) | ❌ 未对接 | — |
|  | 关键词规则管理 | [99973](https://developer.work.weixin.qq.com/document/path/99973) | ⛔ 范围外 | — |
|  | 获取命中关键词规则的会话记录 | [99974](https://developer.work.weixin.qq.com/document/path/99974) | ⛔ 范围外 | — |
|  | 管理知识集 | [99975](https://developer.work.weixin.qq.com/document/path/99975) | ⛔ 范围外 | — |
|  | 通用模型 | [99875](https://developer.work.weixin.qq.com/document/path/99875) | ⛔ 范围外 | — |
|  | 话术推荐模型 | [99977](https://developer.work.weixin.qq.com/document/path/99977) | ⛔ 范围外 | — |
|  | 客户标签模型 | [99979](https://developer.work.weixin.qq.com/document/path/99979) | ⛔ 范围外 | — |
|  | 会话摘要模型 | [99980](https://developer.work.weixin.qq.com/document/path/99980) | ⛔ 范围外 | — |
|  | 情感分析模型 | [99983](https://developer.work.weixin.qq.com/document/path/99983) | ⛔ 范围外 | — |
|  | 自有模型分析 | [99985](https://developer.work.weixin.qq.com/document/path/99985) | ⛔ 范围外 | — |
|  | 会话反垃圾分析 | [99986](https://developer.work.weixin.qq.com/document/path/99986) | ⛔ 范围外 | — |
|  | 会话内容导出 | [99987](https://developer.work.weixin.qq.com/document/path/99987) | ⛔ 范围外 | — |
|  | 异步调用自有分析程序 | [99988](https://developer.work.weixin.qq.com/document/path/99988) | ⛔ 范围外 | — |
|  | 上报异步任务结果 | [99989](https://developer.work.weixin.qq.com/document/path/99989) | ⛔ 范围外 | — |
|  | 专区通知应用 | [99990](https://developer.work.weixin.qq.com/document/path/99990) | ⛔ 范围外 | — |
| 专区程序接收事件通知 | 概述 | [99992](https://developer.work.weixin.qq.com/document/path/99992) | ⛔ 范围外 | — |
|  | 客户同意进行聊天内容存档事件回调 | [99993](https://developer.work.weixin.qq.com/document/path/99993) | ⛔ 范围外 | — |
|  | 产生会话回调通知 | [99994](https://developer.work.weixin.qq.com/document/path/99994) | ⛔ 范围外 | — |
|  | 命中关键词规则通知 | [99995](https://developer.work.weixin.qq.com/document/path/99995) | ⛔ 范围外 | — |
|  | 知识集管理回调 | [99996](https://developer.work.weixin.qq.com/document/path/99996) | ⛔ 范围外 | — |
|  | 会话内容导出完成通知 | [99997](https://developer.work.weixin.qq.com/document/path/99997) | ⛔ 范围外 | — |
| 应用接收专区通知 | 应用接收专区通知 | [99998](https://developer.work.weixin.qq.com/document/path/99998) | ⛔ 范围外 | — |
| 专区调试模式 | 调试说明 | [100086](https://developer.work.weixin.qq.com/document/path/100086) | ⛔ 范围外 | — |
|  | 开启专区调试模式 | [100087](https://developer.work.weixin.qq.com/document/path/100087) | ⛔ 范围外 | — |
|  | 关闭专区调试模式 | [100088](https://developer.work.weixin.qq.com/document/path/100088) | ⛔ 范围外 | — |
|  | 获取专区调试模式状态 | [100113](https://developer.work.weixin.qq.com/document/path/100113) | ⛔ 范围外 | — |
|  | 常见问题 | [100142](https://developer.work.weixin.qq.com/document/path/100142) | ⛔ 范围外 | — |

## 客户联系

状态：✅ 42；⚠️ 0；❌ 0；⛔ 6

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 客户联系 | 概述 | [92109](https://developer.work.weixin.qq.com/document/path/92109) | ⛔ 范围外 | — |
|  | 成员对外信息 | [92230](https://developer.work.weixin.qq.com/document/path/92230) | ✅ 已对接 | AddressBookClient.createUser/updateUser/getUser 中 external_position/external_profile/extattr 模型 |
| 企业服务人员管理 | 获取配置了客户联系功能的成员列表 | [92571](https://developer.work.weixin.qq.com/document/path/92571) | ✅ 已对接 | ExternalContactClient.getFollowUserList |
| 客户管理 | 获取客户列表 | [92113](https://developer.work.weixin.qq.com/document/path/92113) | ✅ 已对接 | ExternalContactClient.getCustomList |
|  | 获取客户详情 | [92114](https://developer.work.weixin.qq.com/document/path/92114) | ✅ 已对接 | ExternalContactClient.getExternalContact |
|  | 批量获取客户详情 | [92994](https://developer.work.weixin.qq.com/document/path/92994) | ✅ 已对接 | ExternalContactClient.getBatchExternalContact |
|  | 修改客户备注信息 | [92115](https://developer.work.weixin.qq.com/document/path/92115) | ✅ 已对接 | ExternalContactClient.updateCustomRemark |
|  | 客户联系规则组管理 | [94883](https://developer.work.weixin.qq.com/document/path/94883) | ✅ 已对接 | ExternalContactClient.getCustomerStrategyList / getCustomerStrategy / getCustomerStrategyRange / createCustomerStrategy / editCustomerStrategy / deleteCustomerStrategy |
| 客户标签管理 | 管理企业标签 | [92117](https://developer.work.weixin.qq.com/document/path/92117) | ✅ 已对接 | ExternalContactClient.getCorpTagList / addCorpTag / editCorpTag / delCorpTag |
|  | 管理企业规则组下的客户标签 | [94882](https://developer.work.weixin.qq.com/document/path/94882) | ✅ 已对接 | ExternalContactClient.getCustomerStrategyTagList / createCustomerStrategyTag / editCustomerStrategyTag / deleteCustomerStrategyTag |
|  | 编辑客户企业标签 | [92118](https://developer.work.weixin.qq.com/document/path/92118) | ✅ 已对接 | ExternalContactClient.markTag |
| 在职继承 | 分配在职成员的客户 | [92125](https://developer.work.weixin.qq.com/document/path/92125) | ✅ 已对接 | ExternalContactClient.transfer |
|  | 查询客户接替状态 | [94088](https://developer.work.weixin.qq.com/document/path/94088) | ✅ 已对接 | ExternalContactClient.getTransferResult |
|  | 分配在职成员的客户群 | [95703](https://developer.work.weixin.qq.com/document/path/95703) | ✅ 已对接 | ExternalContactClient.groupChatTransfer |
| 离职继承 | 获取待分配的离职成员列表 | [92124](https://developer.work.weixin.qq.com/document/path/92124) | ✅ 已对接 | ExternalContactClient.unassignedList |
|  | 分配离职成员的客户 | [94081](https://developer.work.weixin.qq.com/document/path/94081) | ✅ 已对接 | ExternalContactClient.transfer |
|  | 查询客户接替状态 | [94082](https://developer.work.weixin.qq.com/document/path/94082) | ✅ 已对接 | ExternalContactClient.getTransferResult |
|  | 分配离职成员的客户群 | [92127](https://developer.work.weixin.qq.com/document/path/92127) | ✅ 已对接 | ExternalContactClient.groupChatTransfer |
| 客户群管理 | 获取客户群列表 | [92120](https://developer.work.weixin.qq.com/document/path/92120) | ✅ 已对接 | ExternalContactClient.groupChatList |
|  | 获取客户群详情 | [92122](https://developer.work.weixin.qq.com/document/path/92122) | ✅ 已对接 | ExternalContactClient.groupChatDetail |
|  | 客户群opengid转换 | [94822](https://developer.work.weixin.qq.com/document/path/94822) | ✅ 已对接 | ExternalContactClient.openGidToChatId |
| 联系我与客户入群方式 | 客户联系「联系我」管理 | [92228](https://developer.work.weixin.qq.com/document/path/92228) | ✅ 已对接 | ExternalContactClient.add/get/update/delete ContactWay |
|  | 客户群「加入群聊」管理 | [92229](https://developer.work.weixin.qq.com/document/path/92229) | ✅ 已对接 | ExternalContactClient.add/get/update/deleteGroupChatJoinWay |
| 客户朋友圈 | 概述 | [93506](https://developer.work.weixin.qq.com/document/path/93506) | ⛔ 范围外 | 概念说明页，无服务端 REST 路由 |
|  | 企业发表内容到客户的朋友圈 | [95094](https://developer.work.weixin.qq.com/document/path/95094) | ✅ 已对接 | ExternalContactClient.addMomentPublishTask / getMomentPublishTaskResult |
|  | 停止发表企业朋友圈 | [97612](https://developer.work.weixin.qq.com/document/path/97612) | ✅ 已对接 | ExternalContactClient.cancelMomentPublishTask |
|  | 获取客户朋友圈全部的发表记录 | [93333](https://developer.work.weixin.qq.com/document/path/93333) | ✅ 已对接 | ExternalContactClient.getMomentList / getMomentTask / getMomentCustomerList / getMomentSendResult / getMomentComments |
|  | 客户朋友圈规则组管理 | [94890](https://developer.work.weixin.qq.com/document/path/94890) | ✅ 已对接 | ExternalContactClient.getMomentStrategyList / getMomentStrategy / getMomentStrategyRange / createMomentStrategy / editMomentStrategy / deleteMomentStrategy |
| 获客助手 | 获客链接管理 | [97297](https://developer.work.weixin.qq.com/document/path/97297) | ✅ 已对接 | ExternalContactClient.getCustomerAcquisitionLinkList / getCustomerAcquisitionLink / createCustomerAcquisitionLink / updateCustomerAcquisitionLink / deleteCustomerAcquisitionLink |
|  | 获取由获客链接添加的客户信息 | [97298](https://developer.work.weixin.qq.com/document/path/97298) | ✅ 已对接 | ExternalContactClient.getCustomerAcquisitionCustomerList（externalcontact/customer_acquisition/customer） |
|  | 获客助手额度管理与使用统计 | [97375](https://developer.work.weixin.qq.com/document/path/97375) | ✅ 已对接 | ExternalContactClient.getCustomerAcquisitionQuota / getCustomerAcquisitionStatistic（externalcontact/customer_acquisition_quota / customer_acquisition/statistic） |
|  | 提升广告有效率 | [99596](https://developer.work.weixin.qq.com/document/path/99596) | ⛔ 范围外 | 官方页为广告运营指引，未提供服务端 REST 接口 |
|  | 获取成员多次收消息详情 | [100130](https://developer.work.weixin.qq.com/document/path/100130) | ✅ 已对接 | ExternalContactClient.getCustomerAcquisitionChatInfo（externalcontact/customer_acquisition/get_chat_info） |
| 消息推送 | 创建企业群发 | [92135](https://developer.work.weixin.qq.com/document/path/92135) | ✅ 已对接 | ExternalContactClient.addMsgTemplate |
|  | 提醒成员群发 | [97610](https://developer.work.weixin.qq.com/document/path/97610) | ✅ 已对接 | ExternalContactClient.remindGroupMsgSend |
|  | 停止企业群发 | [97611](https://developer.work.weixin.qq.com/document/path/97611) | ✅ 已对接 | ExternalContactClient.cancelGroupMsgSend |
|  | 获取企业的全部群发记录 | [93338](https://developer.work.weixin.qq.com/document/path/93338) | ✅ 已对接 | ExternalContactClient.getGroupMsgList / getGroupMsgListV2 |
|  | 发送新客户欢迎语 | [92137](https://developer.work.weixin.qq.com/document/path/92137) | ✅ 已对接 | ExternalContactClient.sendWelcomeMsg |
|  | 入群欢迎语素材管理 | [92366](https://developer.work.weixin.qq.com/document/path/92366) | ✅ 已对接 | ExternalContactClient.add/edit/get/delete GroupWelcomeTemplate |
| 统计管理 | 获取「联系客户统计」数据 | [92132](https://developer.work.weixin.qq.com/document/path/92132) | ✅ 已对接 | ExternalContactClient.getUserBehaviorData |
|  | 获取「群聊数据统计」数据 | [92133](https://developer.work.weixin.qq.com/document/path/92133) | ✅ 已对接 | ExternalContactClient.getGroupChatStatistic / getGroupChatStatisticByDay |
| 回调通知 | 概述 | [92129](https://developer.work.weixin.qq.com/document/path/92129) | ⛔ 范围外 | — |
|  | 事件格式 | [92130](https://developer.work.weixin.qq.com/document/path/92130) | ⛔ 范围外 | — |
|  | 获客助手事件通知 | [97299](https://developer.work.weixin.qq.com/document/path/97299) | ⛔ 范围外 | — |
|  | 管理商品图册 | [95096](https://developer.work.weixin.qq.com/document/path/95096) | ✅ 已对接 | ExternalContactClient.add/get/list/update/delete ProductAlbum |
|  | 管理聊天敏感词 | [95097](https://developer.work.weixin.qq.com/document/path/95097) | ✅ 已对接 | ExternalContactClient.add/list/detail/update/delete InterceptRule |
|  | 上传附件资源 | [95098](https://developer.work.weixin.qq.com/document/path/95098) | ✅ 已对接 | WeChatMediaClient.uploadAttachment（media/upload_attachment） |
|  | 获取已服务的外部联系人 | [99434](https://developer.work.weixin.qq.com/document/path/99434) | ✅ 已对接 | ExternalContactClient.getServedContactList |

## 微信客服

状态：✅ 18；⚠️ 0；❌ 0；⛔ 2

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 微信客服 | 概述 | [94638](https://developer.work.weixin.qq.com/document/path/94638) | ⛔ 范围外 | — |
| 客服账号管理 | 添加客服账号 | [94662](https://developer.work.weixin.qq.com/document/path/94662) | ✅ 已对接 | WeChatCallCenterClient.addKfAccount |
|  | 删除客服账号 | [94663](https://developer.work.weixin.qq.com/document/path/94663) | ✅ 已对接 | WeChatCallCenterClient.delKfAccount |
|  | 修改客服账号 | [94664](https://developer.work.weixin.qq.com/document/path/94664) | ✅ 已对接 | WeChatCallCenterClient.updateKfAccount |
|  | 获取客服账号列表 | [94661](https://developer.work.weixin.qq.com/document/path/94661) | ✅ 已对接 | WeChatCallCenterClient.kfAccountLIst |
|  | 获取客服账号链接 | [94665](https://developer.work.weixin.qq.com/document/path/94665) | ✅ 已对接 | WeChatCallCenterClient.kfContactWay |
| 接待人员管理 | 添加接待人员 | [94646](https://developer.work.weixin.qq.com/document/path/94646) | ✅ 已对接 | WeChatCallCenterClient.addServicer |
|  | 删除接待人员 | [94647](https://developer.work.weixin.qq.com/document/path/94647) | ✅ 已对接 | WeChatCallCenterClient.delServicer |
|  | 获取接待人员列表 | [94645](https://developer.work.weixin.qq.com/document/path/94645) | ✅ 已对接 | WeChatCallCenterClient.getServicerList |
| 会话分配与消息收发 | 分配客服会话 | [94669](https://developer.work.weixin.qq.com/document/path/94669) | ✅ 已对接 | WeChatCallCenterClient.getServiceState / changeServiceState |
|  | 接收消息和事件 | [94670](https://developer.work.weixin.qq.com/document/path/94670) | ✅ 已对接 | WeChatCallCenterClient.syncMsg（kf/sync_msg） |
|  | 发送消息 | [94677](https://developer.work.weixin.qq.com/document/path/94677) | ✅ 已对接 | WeChatCallCenterClient.sendMsg |
|  | 发送欢迎语等事件响应消息 | [95122](https://developer.work.weixin.qq.com/document/path/95122) | ✅ 已对接 | WeChatCallCenterClient.sendMsgOnEvent |
|  | 「升级服务」配置 | [94674](https://developer.work.weixin.qq.com/document/path/94674) | ✅ 已对接 | WeChatCallCenterClient.getUpgradeServiceConfig / upgradeService / cancelUpgradeService |
| 其他基础信息获取 | 获取客户基础信息 | [95159](https://developer.work.weixin.qq.com/document/path/95159) | ✅ 已对接 | WeChatCallCenterClient.getCustomer |
| 统计管理 | 获取「客户数据统计」企业汇总数据 | [95489](https://developer.work.weixin.qq.com/document/path/95489) | ✅ 已对接 | WeChatCallCenterClient.getCorpStatistic（kf/get_corp_statistic） |
|  | 获取「客户数据统计」接待人员明细数据 | [95490](https://developer.work.weixin.qq.com/document/path/95490) | ✅ 已对接 | WeChatCallCenterClient.getServicerStatistic（kf/get_servicer_statistic） |
| 机器人管理 | 知识库分组管理 | [95971](https://developer.work.weixin.qq.com/document/path/95971) | ✅ 已对接 | WeChatCallCenterClient.add/mod/del/list KnowledgeGroup |
|  | 知识库问答管理 | [95972](https://developer.work.weixin.qq.com/document/path/95972) | ✅ 已对接 | WeChatCallCenterClient.add/mod/del/list KnowledgeIntent |
|  | 回调通知 | [97712](https://developer.work.weixin.qq.com/document/path/97712) | ⛔ 范围外 | — |

## 企业支付

状态：✅ 7；⚠️ 0；❌ 4；⛔ 4

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 企业支付 | 概述 | [90273](https://developer.work.weixin.qq.com/document/path/90273) | ⛔ 范围外 | — |
| 企业红包 | 发放企业红包 | [90275](https://developer.work.weixin.qq.com/document/path/90275) | ❌ 未对接 | — |
|  | 查询红包记录 | [90276](https://developer.work.weixin.qq.com/document/path/90276) | ❌ 未对接 | — |
| 向员工付款 | 向员工付款 | [90278](https://developer.work.weixin.qq.com/document/path/90278) | ❌ 未对接 | — |
|  | 查询付款记录 | [90279](https://developer.work.weixin.qq.com/document/path/90279) | ❌ 未对接 | — |
|  | 向员工收款 | [90280](https://developer.work.weixin.qq.com/document/path/90280) | ⛔ 范围外 | JS-SDK 签名说明，不属于服务端 REST 接入范围 |
| 对外收款 | 概述 | [93665](https://developer.work.weixin.qq.com/document/path/93665) | ⛔ 范围外 | — |
|  | 收款商户号管理 | [93666](https://developer.work.weixin.qq.com/document/path/93666) | ✅ 已对接 | PaymentClient.getMerchant / setMerchantUseScope（externalpay/getmerchant、externalpay/set_mch_use_scope） |
|  | 获取对外收款记录 | [93667](https://developer.work.weixin.qq.com/document/path/93667) | ✅ 已对接 | PaymentClient.getBillList（externalpay/get_bill_list） |
|  | 获取收款项目的商户单号 | [95944](https://developer.work.weixin.qq.com/document/path/95944) | ✅ 已对接 | PaymentClient.getPaymentInfo（externalpay/get_payment_info） |
|  | 获取资金流水 | [98100](https://developer.work.weixin.qq.com/document/path/98100) | ✅ 已对接 | PaymentClient.getFundFlow（externalpay/get_fund_flow） |
|  | 签名算法 | [90281](https://developer.work.weixin.qq.com/document/path/90281) | ⛔ 范围外 | — |
| 创建对外收款账户 | 提交创建对外收款账户的申请单 | [98973](https://developer.work.weixin.qq.com/document/path/98973) | ✅ 已对接 | PaymentClient.applyMch（miniapppay/apply_mch） |
|  | 查询申请单状态 | [98974](https://developer.work.weixin.qq.com/document/path/98974) | ✅ 已对接 | PaymentClient.getApplymentStatus（miniapppay/get_applyment_status） |
|  | 提交图片 | [98972](https://developer.work.weixin.qq.com/document/path/98972) | ✅ 已对接 | PaymentUploadClient.uploadImage（miniapppay/upload_image） |

## 小程序接入对外收款

状态：✅ 7；⚠️ 0；❌ 0；⛔ 4

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 小程序接入对外收款 | 概述 | [98723](https://developer.work.weixin.qq.com/document/path/98723) | ⛔ 范围外 | — |
| 普通支付 | 小程序下单 | [97322](https://developer.work.weixin.qq.com/document/path/97322) | ✅ 已对接 | PaymentClient.createOrder（miniapppay/create_order） |
|  | 查询订单 | [97323](https://developer.work.weixin.qq.com/document/path/97323) | ✅ 已对接 | PaymentClient.getOrder（miniapppay/get_order） |
|  | 关闭订单 | [97324](https://developer.work.weixin.qq.com/document/path/97324) | ✅ 已对接 | PaymentClient.closeOrder（miniapppay/close_order） |
|  | 获取支付签名 | [98130](https://developer.work.weixin.qq.com/document/path/98130) | ✅ 已对接 | PaymentClient.getSign（miniapppay/get_sign） |
| 退款 | 申请退款 | [97333](https://developer.work.weixin.qq.com/document/path/97333) | ✅ 已对接 | PaymentClient.refund（miniapppay/refund） |
|  | 查询退款 | [97352](https://developer.work.weixin.qq.com/document/path/97352) | ✅ 已对接 | PaymentClient.getRefundDetail（miniapppay/get_refund_detail） |
| 回调通知 | 概述 | [97478](https://developer.work.weixin.qq.com/document/path/97478) | ⛔ 范围外 | — |
|  | 支付通知 | [97335](https://developer.work.weixin.qq.com/document/path/97335) | ⛔ 范围外 | 支付回调通知，超出服务端 REST 调用范围 |
|  | 退款通知 | [97338](https://developer.work.weixin.qq.com/document/path/97338) | ⛔ 范围外 | 退款回调通知，超出服务端 REST 调用范围 |
| 账单 | 交易账单申请 | [98115](https://developer.work.weixin.qq.com/document/path/98115) | ✅ 已对接 | PaymentClient.getBill（miniapppay/get_bill） |

## 会话内容存档

状态：✅ 0；⚠️ 0；❌ 0；⛔ 10

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 会话内容存档 | 概述 | [91360](https://developer.work.weixin.qq.com/document/path/91360) | ⛔ 范围外 | — |
|  | 使用前帮助 | [91361](https://developer.work.weixin.qq.com/document/path/91361) | ⛔ 范围外 | — |
|  | 获取会话内容 | [91774](https://developer.work.weixin.qq.com/document/path/91774) | ⛔ 范围外 | — |
|  | 开发案例演示 | [91551](https://developer.work.weixin.qq.com/document/path/91551) | ⛔ 范围外 | — |
|  | 常见问题解答 | [91552](https://developer.work.weixin.qq.com/document/path/91552) | ⛔ 范围外 | — |
|  | 获取会话内容存档开启成员列表 | [91614](https://developer.work.weixin.qq.com/document/path/91614) | ⛔ 范围外 | — |
|  | 获取会话同意情况 | [91782](https://developer.work.weixin.qq.com/document/path/91782) | ⛔ 范围外 | — |
|  | 客户同意进行聊天内容存档事件回调 | [92005](https://developer.work.weixin.qq.com/document/path/92005) | ⛔ 范围外 | — |
|  | 获取会话内容存档内部群信息 | [92951](https://developer.work.weixin.qq.com/document/path/92951) | ⛔ 范围外 | — |
|  | 产生会话回调事件 | [95039](https://developer.work.weixin.qq.com/document/path/95039) | ⛔ 范围外 | — |

## 家校沟通

状态：✅ 29；⚠️ 0；❌ 0；⛔ 6

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 家校沟通 | 概述 | [91638](https://developer.work.weixin.qq.com/document/path/91638) | ⛔ 范围外 | — |
| 基础接口 | 获取「学校通知」二维码 | [92320](https://developer.work.weixin.qq.com/document/path/92320) | ✅ 已对接 | ExternalContactClient/SchoolClient#92320 相关路由与 SchoolService |
|  | 管理「学校通知」的关注模式 | [92318](https://developer.work.weixin.qq.com/document/path/92318) | ✅ 已对接 | SchoolClient#92318 相关路由与 SchoolService |
|  | 发送「学校通知」 | [92321](https://developer.work.weixin.qq.com/document/path/92321) | ✅ 已对接 | ExternalContactClient#sendSchoolNotification 与 CustomContactService#sendSchoolNotification |
|  | 管理「班级群创建方式」 | [92430](https://developer.work.weixin.qq.com/document/path/92430) | ✅ 已对接 | SchoolClient#92430 相关路由与 SchoolService |
|  | 外部联系人openid转换 | [92323](https://developer.work.weixin.qq.com/document/path/92323) | ✅ 已对接 | SchoolClient#92323 相关路由与 SchoolService |
|  | 获取可使用的家长范围 | [94895](https://developer.work.weixin.qq.com/document/path/94895) | ✅ 已对接 | SchoolClient#94895 相关路由与 SchoolService |
| 网页授权登录 | 开始开发 | [91856](https://developer.work.weixin.qq.com/document/path/91856) | ⛔ 范围外 | — |
|  | 构造网页授权链接 | [91857](https://developer.work.weixin.qq.com/document/path/91857) | ⛔ 范围外 | — |
|  | 获取访问用户身份 | [91707](https://developer.work.weixin.qq.com/document/path/91707) | ✅ 已对接 | AuthClient#getUserInfo 与 AuthenticationService#getUserInfo |
|  | 获取家校访问用户身份 | [95791](https://developer.work.weixin.qq.com/document/path/95791) | ✅ 已对接 | SchoolClient#95791 相关路由与 SchoolService |
| 学生与家长管理 | 创建学生 | [92325](https://developer.work.weixin.qq.com/document/path/92325) | ✅ 已对接 | SchoolClient#92325 相关路由与 SchoolService |
|  | 删除学生 | [92326](https://developer.work.weixin.qq.com/document/path/92326) | ✅ 已对接 | SchoolClient#92326 相关路由与 SchoolService |
|  | 更新学生 | [92327](https://developer.work.weixin.qq.com/document/path/92327) | ✅ 已对接 | SchoolClient#92327 相关路由与 SchoolService |
|  | 批量创建学生 | [92328](https://developer.work.weixin.qq.com/document/path/92328) | ✅ 已对接 | SchoolClient#92328 相关路由与 SchoolService |
|  | 批量删除学生 | [92329](https://developer.work.weixin.qq.com/document/path/92329) | ✅ 已对接 | SchoolClient#92329 相关路由与 SchoolService |
|  | 批量更新学生 | [92330](https://developer.work.weixin.qq.com/document/path/92330) | ✅ 已对接 | SchoolClient#92330 相关路由与 SchoolService |
|  | 创建家长 | [92331](https://developer.work.weixin.qq.com/document/path/92331) | ✅ 已对接 | SchoolClient#92331 相关路由与 SchoolService |
|  | 删除家长 | [92332](https://developer.work.weixin.qq.com/document/path/92332) | ✅ 已对接 | SchoolClient#92332 相关路由与 SchoolService |
|  | 更新家长 | [92333](https://developer.work.weixin.qq.com/document/path/92333) | ✅ 已对接 | SchoolClient#92333 相关路由与 SchoolService |
|  | 批量创建家长 | [92334](https://developer.work.weixin.qq.com/document/path/92334) | ✅ 已对接 | SchoolClient#92334 相关路由与 SchoolService |
|  | 批量删除家长 | [92335](https://developer.work.weixin.qq.com/document/path/92335) | ✅ 已对接 | SchoolClient#92335 相关路由与 SchoolService |
|  | 批量更新家长 | [92336](https://developer.work.weixin.qq.com/document/path/92336) | ✅ 已对接 | SchoolClient#92336 相关路由与 SchoolService |
|  | 读取学生或家长 | [92337](https://developer.work.weixin.qq.com/document/path/92337) | ✅ 已对接 | SchoolClient#92337 相关路由与 SchoolService |
|  | 获取部门学生详情 | [92338](https://developer.work.weixin.qq.com/document/path/92338) | ✅ 已对接 | SchoolClient#92338 相关路由与 SchoolService |
|  | 设置家校通讯录自动同步模式 | [92345](https://developer.work.weixin.qq.com/document/path/92345) | ✅ 已对接 | SchoolClient#92345 相关路由与 SchoolService |
|  | 获取部门家长详情 | [92446](https://developer.work.weixin.qq.com/document/path/92446) | ✅ 已对接 | SchoolClient#92446 相关路由与 SchoolService |
| 部门管理 | 创建部门 | [92340](https://developer.work.weixin.qq.com/document/path/92340) | ✅ 已对接 | SchoolClient.createSchoolDepartment 与 SchoolService |
|  | 更新部门 | [92341](https://developer.work.weixin.qq.com/document/path/92341) | ✅ 已对接 | SchoolClient.updateSchoolDepartment 与 SchoolService |
|  | 删除部门 | [92342](https://developer.work.weixin.qq.com/document/path/92342) | ✅ 已对接 | SchoolClient.deleteSchoolDepartment 与 SchoolService |
|  | 获取部门列表 | [92343](https://developer.work.weixin.qq.com/document/path/92343) | ✅ 已对接 | SchoolClient.listSchoolDepartments 与 SchoolService |
|  | 标准年级对照表 | [92344](https://developer.work.weixin.qq.com/document/path/92344) | ✅ 已对接 | SchoolService.getStandardGrades |
|  | 修改自动升年级的配置 | [92949](https://developer.work.weixin.qq.com/document/path/92949) | ⛔ 范围外 | — |
| 家校通讯录变更回调 | 成员变更事件 | [92032](https://developer.work.weixin.qq.com/document/path/92032) | ⛔ 范围外 | — |
|  | 部门变更事件 | [92052](https://developer.work.weixin.qq.com/document/path/92052) | ⛔ 范围外 | — |

## 家校应用

状态：✅ 13；⚠️ 0；❌ 0；⛔ 0

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 健康上报 | 获取健康上报使用统计 | [93676](https://developer.work.weixin.qq.com/document/path/93676) | ✅ 已对接 | `SchoolClient.getHealthReportStat` / `SchoolService.getHealthReportStat` |
|  | 获取健康上报任务ID列表 | [93677](https://developer.work.weixin.qq.com/document/path/93677) | ✅ 已对接 | `SchoolClient.getReportJobIds` / `SchoolService.getReportJobIds` |
|  | 获取健康上报任务详情 | [93678](https://developer.work.weixin.qq.com/document/path/93678) | ✅ 已对接 | `SchoolClient.getReportJobInfo` / `SchoolService.getReportJobInfo` |
|  | 获取用户填写答案 | [93679](https://developer.work.weixin.qq.com/document/path/93679) | ✅ 已对接 | `SchoolClient.getReportAnswer` / `SchoolService.getReportAnswer` |
| 上课直播 | 获取老师直播ID列表 | [93739](https://developer.work.weixin.qq.com/document/path/93739) | ✅ 已对接 | `SchoolClient.getUserAllLivingId` / `SchoolService.getUserAllLivingId` |
|  | 获取直播详情 | [93740](https://developer.work.weixin.qq.com/document/path/93740) | ✅ 已对接 | `SchoolClient.getLivingInfo` / `SchoolService.getLivingInfo` |
|  | 获取观看直播统计 | [93741](https://developer.work.weixin.qq.com/document/path/93741) | ✅ 已对接 | `SchoolClient.getWatchStat` / `SchoolService.getWatchStat` |
|  | 获取未观看直播统计 | [93742](https://developer.work.weixin.qq.com/document/path/93742) | ✅ 已对接 | `SchoolClient.getUnwatchStat` / `SchoolService.getUnwatchStat` |
|  | 删除直播回放 | [93743](https://developer.work.weixin.qq.com/document/path/93743) | ✅ 已对接 | `SchoolClient.deleteReplayData` / `SchoolService.deleteReplayData` |
|  | 获取观看直播统计V2 | [95793](https://developer.work.weixin.qq.com/document/path/95793) | ✅ 已对接 | `SchoolClient.getWatchStatV2` / `SchoolService.getWatchStatV2` |
|  | 获取未观看直播统计V2 | [95795](https://developer.work.weixin.qq.com/document/path/95795) | ✅ 已对接 | `SchoolClient.getUnwatchStatV2` / `SchoolService.getUnwatchStatV2` |
| 班级收款 | 获取学生付款结果 | [94470](https://developer.work.weixin.qq.com/document/path/94470) | ✅ 已对接 | `SchoolClient.getPaymentResult` / `SchoolService.getPaymentResult` |
|  | 获取订单详情 | [94471](https://developer.work.weixin.qq.com/document/path/94471) | ✅ 已对接 | `SchoolClient.getTrade` / `SchoolService.getTrade` |

## 政民沟通

状态：✅ 5；⚠️ 0；❌ 16；⛔ 3

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 配置网格结构 | 概述 | [94557](https://developer.work.weixin.qq.com/document/path/94557) | ⛔ 范围外 | — |
|  | 添加网格 | [94478](https://developer.work.weixin.qq.com/document/path/94478) | ✅ 已对接 | `GovernmentCommunicationClient.addGrid` / `GovernmentCommunicationService.addGrid` |
|  | 编辑网格 | [94479](https://developer.work.weixin.qq.com/document/path/94479) | ✅ 已对接 | `GovernmentCommunicationClient.updateGrid` / `GovernmentCommunicationService.updateGrid` |
|  | 删除网格 | [94480](https://developer.work.weixin.qq.com/document/path/94480) | ✅ 已对接 | `GovernmentCommunicationClient.deleteGrid` / `GovernmentCommunicationService.deleteGrid` |
|  | 获取网格列表 | [94481](https://developer.work.weixin.qq.com/document/path/94481) | ✅ 已对接 | `GovernmentCommunicationClient.listGrid` / `GovernmentCommunicationService.listGrid` |
|  | 获取用户负责及参与的网格列表 | [94482](https://developer.work.weixin.qq.com/document/path/94482) | ✅ 已对接 | `GovernmentCommunicationClient.getUserGridInfo` / `GovernmentCommunicationService.getUserGridInfo` |
| 配置事件类别 | 添加事件类别 | [94536](https://developer.work.weixin.qq.com/document/path/94536) | ❌ 未对接 | — |
|  | 修改事件类别 | [94537](https://developer.work.weixin.qq.com/document/path/94537) | ❌ 未对接 | — |
|  | 删除事件类别 | [94538](https://developer.work.weixin.qq.com/document/path/94538) | ❌ 未对接 | — |
|  | 获取事件类别列表 | [94540](https://developer.work.weixin.qq.com/document/path/94540) | ❌ 未对接 | — |
| 巡查上报 | 概述 | [93520](https://developer.work.weixin.qq.com/document/path/93520) | ⛔ 范围外 | — |
|  | 获取配置的网格及网格负责人 | [93531](https://developer.work.weixin.qq.com/document/path/93531) | ❌ 未对接 | — |
|  | 获取单位巡查上报数据统计 | [93532](https://developer.work.weixin.qq.com/document/path/93532) | ❌ 未对接 | — |
|  | 获取个人巡查上报数据统计 | [93533](https://developer.work.weixin.qq.com/document/path/93533) | ❌ 未对接 | — |
|  | 获取上报事件分类统计 | [93534](https://developer.work.weixin.qq.com/document/path/93534) | ❌ 未对接 | — |
|  | 获取巡查上报事件列表 | [93536](https://developer.work.weixin.qq.com/document/path/93536) | ❌ 未对接 | — |
|  | 获取巡查上报的事件详情信息 | [93535](https://developer.work.weixin.qq.com/document/path/93535) | ❌ 未对接 | — |
| 居民上报 | 概述 | [93513](https://developer.work.weixin.qq.com/document/path/93513) | ⛔ 范围外 | — |
|  | 获取配置的网格及网格负责人 | [93514](https://developer.work.weixin.qq.com/document/path/93514) | ❌ 未对接 | — |
|  | 获取单位居民上报数据统计 | [93515](https://developer.work.weixin.qq.com/document/path/93515) | ❌ 未对接 | — |
|  | 获取个人居民上报数据统计 | [93516](https://developer.work.weixin.qq.com/document/path/93516) | ❌ 未对接 | — |
|  | 获取上报事件分类统计 | [93517](https://developer.work.weixin.qq.com/document/path/93517) | ❌ 未对接 | — |
|  | 获取居民上报事件列表 | [93518](https://developer.work.weixin.qq.com/document/path/93518) | ❌ 未对接 | — |
|  | 获取居民上报的事件详情信息 | [93519](https://developer.work.weixin.qq.com/document/path/93519) | ❌ 未对接 | — |

## 邮件

状态：✅ 26；⚠️ 0；❌ 0；⛔ 3

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 邮件 | 概述 | [95486](https://developer.work.weixin.qq.com/document/path/95486) | ⛔ 范围外 | — |
| 发送邮件 | 发送普通邮件 | [97445](https://developer.work.weixin.qq.com/document/path/97445) | ✅ 已对接 | EnterpriseEmailService.sendNormalEmail（exmail/app/compose_send） |
|  | 发送日程邮件 | [97854](https://developer.work.weixin.qq.com/document/path/97854) | ✅ 已对接 | EnterpriseEmailService.sendScheduleEmail（exmail/app/compose_send） |
|  | 发送会议邮件 | [97855](https://developer.work.weixin.qq.com/document/path/97855) | ✅ 已对接 | EnterpriseEmailService.sendMeetingEmail（exmail/app/compose_send） |
| 获取接收的邮件 | 获取收件箱邮件列表 | [97369](https://developer.work.weixin.qq.com/document/path/97369) | ✅ 已对接 | EnterpriseEmailService.getMailList（exmail/app/get_mail_list） |
|  | 获取邮件内容 | [97979](https://developer.work.weixin.qq.com/document/path/97979) | ✅ 已对接 | EnterpriseEmailService.readMail（exmail/app/read_mail） |
| 管理应用邮箱账号 | 更新应用邮箱账号 | [97373](https://developer.work.weixin.qq.com/document/path/97373) | ✅ 已对接 | EnterpriseEmailService.updateEmailAlias（exmail/app/update_email_alias） |
|  | 查询应用邮箱账号 | [97991](https://developer.work.weixin.qq.com/document/path/97991) | ✅ 已对接 | EnterpriseEmailService.getEmailAlias（exmail/app/get_email_alias） |
| 管理邮件群组 | 创建邮件群组 | [95510](https://developer.work.weixin.qq.com/document/path/95510) | ✅ 已对接 | EnterpriseEmailClient.createMailGroup |
|  | 更新邮件群组 | [97995](https://developer.work.weixin.qq.com/document/path/97995) | ✅ 已对接 | EnterpriseEmailClient.updateMailGroup |
|  | 删除邮件群组 | [97996](https://developer.work.weixin.qq.com/document/path/97996) | ✅ 已对接 | EnterpriseEmailClient.deleteMailGroup |
|  | 获取邮件群组详情 | [97997](https://developer.work.weixin.qq.com/document/path/97997) | ✅ 已对接 | EnterpriseEmailClient.getMailGroupDetail |
|  | 模糊搜索邮件群组 | [97998](https://developer.work.weixin.qq.com/document/path/97998) | ✅ 已对接 | EnterpriseEmailClient.searchGroup |
| 管理公共邮箱 | 创建公共邮箱 | [95511](https://developer.work.weixin.qq.com/document/path/95511) | ✅ 已对接 | EnterpriseEmailClient.createPublicMail |
|  | 更新公共邮箱 | [98000](https://developer.work.weixin.qq.com/document/path/98000) | ✅ 已对接 | EnterpriseEmailClient.updatePublicMail |
|  | 删除公共邮箱 | [98001](https://developer.work.weixin.qq.com/document/path/98001) | ✅ 已对接 | EnterpriseEmailClient.deletePublicMail |
|  | 获取公共邮箱详情 | [98002](https://developer.work.weixin.qq.com/document/path/98002) | ✅ 已对接 | EnterpriseEmailClient.mailDetail |
|  | 模糊搜索公共邮箱 | [98003](https://developer.work.weixin.qq.com/document/path/98003) | ✅ 已对接 | EnterpriseEmailClient.searchEmail |
|  | 回调通知 | [100180](https://developer.work.weixin.qq.com/document/path/100180) | ⛔ 范围外 | — |
|  | 获取客户端专用密码列表 | [100183](https://developer.work.weixin.qq.com/document/path/100183) | ✅ 已对接 | EnterpriseEmailService.listAuthCodes（exmail/publicmail/get_auth_code_list） |
|  | 删除客户端专用密码 | [100184](https://developer.work.weixin.qq.com/document/path/100184) | ✅ 已对接 | EnterpriseEmailService.deleteAuthCode（exmail/publicmail/delete_auth_code） |
| 高级功能账号管理 | 分配高级功能账号 | [99316](https://developer.work.weixin.qq.com/document/path/99316) | ✅ 已对接 | EnterpriseEmailClient.batchAddVipAccounts（exmail/vip/batch_add） |
|  | 取消高级功能账号 | [99317](https://developer.work.weixin.qq.com/document/path/99317) | ✅ 已对接 | EnterpriseEmailClient.batchDeleteVipAccounts（exmail/vip/batch_del） |
|  | 获取高级功能账号列表 | [99318](https://developer.work.weixin.qq.com/document/path/99318) | ✅ 已对接 | EnterpriseEmailClient.listVipAccounts（exmail/vip/list） |
|  | 禁用/启用邮箱账号 | [95512](https://developer.work.weixin.qq.com/document/path/95512) | ✅ 已对接 | EnterpriseEmailService.actEmail（exmail/account/act_email） |
| 其他邮件客户端登录设置 | 获取用户功能属性 | [95513](https://developer.work.weixin.qq.com/document/path/95513) | ✅ 已对接 | EnterpriseEmailService.getUserOption（exmail/useroption/get） |
|  | 更改用户功能属性 | [98008](https://developer.work.weixin.qq.com/document/path/98008) | ✅ 已对接 | EnterpriseEmailService.updateUserOption（exmail/useroption/update） |
|  | 获取邮件未读数 | [95514](https://developer.work.weixin.qq.com/document/path/95514) | ✅ 已对接 | EnterpriseEmailService.getMailUnreadCount（exmail/mail/get_newcount） |
|  | 回调通知 | [97495](https://developer.work.weixin.qq.com/document/path/97495) | ⛔ 范围外 | — |

## 文档

状态：✅ 56；⚠️ 0；❌ 13；⛔ 2

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 文档 | 概述 | [97392](https://developer.work.weixin.qq.com/document/path/97392) | ⛔ 范围外 | — |
| 管理文档 | 新建文档 | [97460](https://developer.work.weixin.qq.com/document/path/97460) | ✅ 已对接 | WechatDocClient.createDoc |
|  | 重命名文档 | [97736](https://developer.work.weixin.qq.com/document/path/97736) | ✅ 已对接 | WechatDocClient.renameDoc |
|  | 删除文档 | [97735](https://developer.work.weixin.qq.com/document/path/97735) | ✅ 已对接 | WechatDocClient.delDoc |
|  | 获取文档基础信息 | [97734](https://developer.work.weixin.qq.com/document/path/97734) | ✅ 已对接 | WechatDocClient.getDocBaseInfo |
|  | 分享文档 | [97733](https://developer.work.weixin.qq.com/document/path/97733) | ✅ 已对接 | WechatDocClient.shareDoc |
| 管理文档内容 | 编辑文档内容 | [97626](https://developer.work.weixin.qq.com/document/path/97626) | ✅ 已对接 | WechatDocClient.batchUpdate |
|  | 获取文档数据 | [101161](https://developer.work.weixin.qq.com/document/path/101161) | ✅ 已对接 | WechatDocClient.getDocumentContent |
| 管理表格内容 | 编辑表格内容 | [101168](https://developer.work.weixin.qq.com/document/path/101168) | ✅ 已对接 | WechatDocClient.batchUpdateSheet |
|  | 获取表格行列信息 | [97711](https://developer.work.weixin.qq.com/document/path/97711) | ✅ 已对接 | WechatDocClient.getSheetProps |
|  | 获取表格数据 | [97661](https://developer.work.weixin.qq.com/document/path/97661) | ✅ 已对接 | WechatDocClient.getSheetData |
| 管理智能表格内容 | 添加子表 | [99896](https://developer.work.weixin.qq.com/document/path/99896) | ✅ 已对接 | WechatSmartSheetClient.addSheet |
|  | 删除子表 | [99899](https://developer.work.weixin.qq.com/document/path/99899) | ✅ 已对接 | WechatSmartSheetClient.delSheet |
|  | 更新子表 | [99898](https://developer.work.weixin.qq.com/document/path/99898) | ✅ 已对接 | WechatSmartSheetClient.updateSheet |
|  | 查询子表 | [101154](https://developer.work.weixin.qq.com/document/path/101154) | ✅ 已对接 | WechatSmartSheetClient.getSheet |
|  | 添加视图 | [99900](https://developer.work.weixin.qq.com/document/path/99900) | ✅ 已对接 | WechatSmartSheetClient.addView |
|  | 删除视图 | [99901](https://developer.work.weixin.qq.com/document/path/99901) | ✅ 已对接 | WechatSmartSheetClient.delView |
|  | 更新视图 | [99902](https://developer.work.weixin.qq.com/document/path/99902) | ✅ 已对接 | WechatSmartSheetClient.updateView |
|  | 查询视图 | [101155](https://developer.work.weixin.qq.com/document/path/101155) | ✅ 已对接 | WechatSmartSheetClient.getViews |
|  | 添加字段 | [99904](https://developer.work.weixin.qq.com/document/path/99904) | ✅ 已对接 | WechatSmartSheetClient.addField |
|  | 删除字段 | [99905](https://developer.work.weixin.qq.com/document/path/99905) | ✅ 已对接 | WechatSmartSheetClient.delField |
|  | 更新字段 | [99906](https://developer.work.weixin.qq.com/document/path/99906) | ✅ 已对接 | WechatSmartSheetClient.updateField |
|  | 查询字段 | [101157](https://developer.work.weixin.qq.com/document/path/101157) | ✅ 已对接 | WechatSmartSheetClient.getFields |
|  | 添加记录 | [99907](https://developer.work.weixin.qq.com/document/path/99907) | ✅ 已对接 | WechatSmartSheetClient.addRecords |
|  | 删除记录 | [99908](https://developer.work.weixin.qq.com/document/path/99908) | ✅ 已对接 | WechatSmartSheetClient.delRecords |
|  | 更新记录 | [99909](https://developer.work.weixin.qq.com/document/path/99909) | ✅ 已对接 | WechatSmartSheetClient.updateRecords |
|  | 查询记录 | [101158](https://developer.work.weixin.qq.com/document/path/101158) | ✅ 已对接 | WechatSmartSheetClient.getRecords |
|  | 添加编组 | [101100](https://developer.work.weixin.qq.com/document/path/101100) | ✅ 已对接 | WechatSmartSheetClient.addFieldGroup |
|  | 删除编组 | [101102](https://developer.work.weixin.qq.com/document/path/101102) | ✅ 已对接 | WechatSmartSheetClient.delFieldGroups |
|  | 更新编组 | [101101](https://developer.work.weixin.qq.com/document/path/101101) | ✅ 已对接 | WechatSmartSheetClient.updateFieldGroup |
|  | 获取编组 | [101103](https://developer.work.weixin.qq.com/document/path/101103) | ✅ 已对接 | WechatSmartSheetClient.getFieldGroups |
| 管理智能文档内容 | 添加页面 | [101620](https://developer.work.weixin.qq.com/document/path/101620) | ✅ 已对接 | WechatDocClient.addSmartDocPage（wedoc/smartdoc/add_page） |
|  | 更新页面 | [101621](https://developer.work.weixin.qq.com/document/path/101621) | ✅ 已对接 | WechatDocClient.updateSmartDocPage（wedoc/smartdoc/update_page） |
|  | 删除页面 | [101622](https://developer.work.weixin.qq.com/document/path/101622) | ✅ 已对接 | WechatDocClient.delSmartDocPage（wedoc/smartdoc/delete_page） |
|  | 获取页面结构 | [101619](https://developer.work.weixin.qq.com/document/path/101619) | ✅ 已对接 | WechatDocClient.getSmartDocPageHierarchy（wedoc/smartdoc/get_page_hierarchy） |
|  | 添加内容块 | [101623](https://developer.work.weixin.qq.com/document/path/101623) | ✅ 已对接 | WechatDocClient.addSmartDocBlocks（wedoc/smartdoc/add_blocks） |
|  | 更新内容块 | [101624](https://developer.work.weixin.qq.com/document/path/101624) | ✅ 已对接 | WechatDocClient.updateSmartDocBlocks（wedoc/smartdoc/update_blocks） |
|  | 删除内容块 | [101625](https://developer.work.weixin.qq.com/document/path/101625) | ✅ 已对接 | WechatDocClient.delSmartDocBlocks（wedoc/smartdoc/delete_blocks） |
|  | 获取内容块列表 | [101626](https://developer.work.weixin.qq.com/document/path/101626) | ✅ 已对接 | WechatDocClient.getSmartDocBlockList（wedoc/smartdoc/get_block_list） |
|  | 导出内容块 | [101627](https://developer.work.weixin.qq.com/document/path/101627) | ✅ 已对接 | WechatDocClient.exportSmartDoc / getSmartDocExportResult（wedoc/smartdoc/export_task、get_export_result） |
|  | 获取数据源 | [101628](https://developer.work.weixin.qq.com/document/path/101628) | ✅ 已对接 | WechatDocClient.getSmartDocSheetInfo（wedoc/smartdoc/get_smartsheet_info） |
|  | 添加数据表 | [101629](https://developer.work.weixin.qq.com/document/path/101629) | ✅ 已对接 | WechatDocClient.addSmartDocSheet（wedoc/smartdoc/add_smartsheet） |
|  | 删除数据表 | [101630](https://developer.work.weixin.qq.com/document/path/101630) | ✅ 已对接 | WechatDocClient.delSmartDocSheet（wedoc/smartdoc/delete_smartsheet） |
|  | 更新数据表 | [101631](https://developer.work.weixin.qq.com/document/path/101631) | ✅ 已对接 | WechatDocClient.updateSmartDocSheet（wedoc/smartdoc/update_smartsheet） |
|  | 发布智能文档 | [101616](https://developer.work.weixin.qq.com/document/path/101616) | ✅ 已对接 | WechatDocClient.publishSmartDoc（wedoc/smartdoc/publish） |
|  | 取消发布智能文档 | [101617](https://developer.work.weixin.qq.com/document/path/101617) | ✅ 已对接 | WechatDocClient.cancelSmartDocPublish（wedoc/smartdoc/cancel_publish） |
|  | 修改发布页可查看范围 | [101618](https://developer.work.weixin.qq.com/document/path/101618) | ✅ 已对接 | WechatDocClient.setSmartDocPublishRange（wedoc/smartdoc/publish_setting） |
| 设置文档权限 | 获取文档权限信息 | [97461](https://developer.work.weixin.qq.com/document/path/97461) | ✅ 已对接 | WechatDocClient.getDocAuth |
|  | 修改文档加入规则 | [97778](https://developer.work.weixin.qq.com/document/path/97778) | ✅ 已对接 | WechatDocClient.modDocAuth |
|  | 修改文档成员与权限 | [97781](https://developer.work.weixin.qq.com/document/path/97781) | ✅ 已对接 | WechatDocClient.modDocMember |
|  | 修改文档安全设置 | [97782](https://developer.work.weixin.qq.com/document/path/97782) | ✅ 已对接 | WechatDocClient.modDocSafetySetting |
|  | 管理智能表格内容权限 | [99935](https://developer.work.weixin.qq.com/document/path/99935) | ✅ 已对接 | WechatSmartSheetClient.get/update SheetPriv，create/mod/delete Rule |
| 管理收集表 | 创建收集表 | [97462](https://developer.work.weixin.qq.com/document/path/97462) | ✅ 已对接 | WechatDocClient.createForm |
|  | 编辑收集表 | [97816](https://developer.work.weixin.qq.com/document/path/97816) | ✅ 已对接 | WechatDocClient.modifyForm |
|  | 获取收集表信息 | [97817](https://developer.work.weixin.qq.com/document/path/97817) | ✅ 已对接 | WechatDocClient.getFormInfo |
|  | 收集表的统计信息查询 | [97818](https://developer.work.weixin.qq.com/document/path/97818) | ✅ 已对接 | WechatDocClient.getFormStatistic |
|  | 读取收集表答案 | [97819](https://developer.work.weixin.qq.com/document/path/97819) | ✅ 已对接 | WechatDocClient.getFormAnswer |
| 回调通知 | 概述 | [97316](https://developer.work.weixin.qq.com/document/path/97316) | ⛔ 范围外 | — |
|  | 修改文档成员事件 | [97833](https://developer.work.weixin.qq.com/document/path/97833) | ❌ 未对接 | — |
|  | 删除文档事件 | [97834](https://developer.work.weixin.qq.com/document/path/97834) | ❌ 未对接 | — |
|  | 收集表完成事件 | [97835](https://developer.work.weixin.qq.com/document/path/97835) | ❌ 未对接 | — |
|  | 删除收集表事件 | [98095](https://developer.work.weixin.qq.com/document/path/98095) | ❌ 未对接 | — |
|  | 修改收集表设置事件 | [98096](https://developer.work.weixin.qq.com/document/path/98096) | ❌ 未对接 | — |
|  | 字段变更事件 | [100987](https://developer.work.weixin.qq.com/document/path/100987) | ❌ 未对接 | — |
|  | 记录变更事件 | [100986](https://developer.work.weixin.qq.com/document/path/100986) | ❌ 未对接 | — |
| 接收外部数据到智能表格 | 概述 | [101239](https://developer.work.weixin.qq.com/document/path/101239) | ❌ 未对接 | — |
|  | 添加记录 | [101240](https://developer.work.weixin.qq.com/document/path/101240) | ❌ 未对接 | — |
|  | 更新记录 | [101241](https://developer.work.weixin.qq.com/document/path/101241) | ❌ 未对接 | — |
| 高级功能账号管理 | 分配高级功能账号 | [99516](https://developer.work.weixin.qq.com/document/path/99516) | ✅ 已对接 | WechatDocClient.batchAddVipAccounts（/wedoc/vip/batch_add） |
|  | 取消高级功能账号 | [99517](https://developer.work.weixin.qq.com/document/path/99517) | ✅ 已对接 | WechatDocClient.batchDeleteVipAccounts（/wedoc/vip/batch_del） |
|  | 获取高级功能账号列表 | [99518](https://developer.work.weixin.qq.com/document/path/99518) | ✅ 已对接 | WechatDocClient.listVipAccounts（/wedoc/vip/list） |
| 素材管理 | 上传文档图片 | [99933](https://developer.work.weixin.qq.com/document/path/99933) | ❌ 未对接 | — |
|  | 上传文档文件 | [101870](https://developer.work.weixin.qq.com/document/path/101870) | ❌ 未对接 | — |
|  | 分块上传文档文件 | [101871](https://developer.work.weixin.qq.com/document/path/101871) | ❌ 未对接 | — |

## 日程

状态：✅ 11；⚠️ 0；❌ 5；⛔ 3

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 日程 | 概述 | [93624](https://developer.work.weixin.qq.com/document/path/93624) | ⛔ 范围外 | — |
| 管理日历 | 创建日历 | [93647](https://developer.work.weixin.qq.com/document/path/93647) | ✅ 已对接 | WorkOaClient.addCalendar |
|  | 更新日历 | [97716](https://developer.work.weixin.qq.com/document/path/97716) | ✅ 已对接 | WorkOaClient.updateCalendar |
|  | 获取日历详情 | [97717](https://developer.work.weixin.qq.com/document/path/97717) | ✅ 已对接 | WorkOaClient.getCalendar |
|  | 删除日历 | [97718](https://developer.work.weixin.qq.com/document/path/97718) | ✅ 已对接 | WorkOaClient.delCalendar |
| 管理日程 | 创建日程 | [93648](https://developer.work.weixin.qq.com/document/path/93648) | ✅ 已对接 | WorkOaClient.addSchedule |
|  | 更新日程 | [97720](https://developer.work.weixin.qq.com/document/path/97720) | ✅ 已对接 | WorkOaClient.updateSchedule |
|  | 更新重复日程 | [96204](https://developer.work.weixin.qq.com/document/path/96204) | ⛔ 范围外 | 官方说明页，无独立REST路由；重复日程模式由 WorkOaClient.updateSchedule 通过 op_mode/op_start_time 支持 |
|  | 新增日程参与者 | [97721](https://developer.work.weixin.qq.com/document/path/97721) | ✅ 已对接 | WorkOaClient.addScheduleAttendees（oa/schedule/add_attendees） |
|  | 删除日程参与者 | [97722](https://developer.work.weixin.qq.com/document/path/97722) | ✅ 已对接 | WorkOaClient.delScheduleAttendees（oa/schedule/del_attendees） |
|  | 获取日历下的日程列表 | [97723](https://developer.work.weixin.qq.com/document/path/97723) | ✅ 已对接 | WorkOaClient.getScheduleByCalendar |
|  | 获取日程详情 | [97724](https://developer.work.weixin.qq.com/document/path/97724) | ✅ 已对接 | WorkOaClient.getSchedules |
|  | 取消日程 | [97725](https://developer.work.weixin.qq.com/document/path/97725) | ✅ 已对接 | WorkOaClient.delSchedule |
| 回调通知 | 概述 | [93651](https://developer.work.weixin.qq.com/document/path/93651) | ⛔ 范围外 | — |
|  | 删除日历事件 | [97728](https://developer.work.weixin.qq.com/document/path/97728) | ❌ 未对接 | — |
|  | 修改日历事件 | [97730](https://developer.work.weixin.qq.com/document/path/97730) | ❌ 未对接 | — |
|  | 修改日程事件 | [97731](https://developer.work.weixin.qq.com/document/path/97731) | ❌ 未对接 | — |
|  | 删除日程事件 | [97732](https://developer.work.weixin.qq.com/document/path/97732) | ❌ 未对接 | — |
|  | 日程回执事件 | [98111](https://developer.work.weixin.qq.com/document/path/98111) | ❌ 未对接 | — |

## 待办

状态：✅ 2；⚠️ 0；❌ 0；⛔ 0

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 待办 | 获取待办详情 | [101524](https://developer.work.weixin.qq.com/document/path/101524) | ✅ 已对接 | WorkOaClient.getTodoDetail（todo/get） |
|  | 更新待办状态 | [101534](https://developer.work.weixin.qq.com/document/path/101534) | ✅ 已对接 | WorkOaClient.updateTodo（todo/update） |

## 会议

状态：✅ 108；⚠️ 0；❌ 1；⛔ 32

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 会议 | 概述 | [93626](https://developer.work.weixin.qq.com/document/path/93626) | ⛔ 范围外 | — |
| 预约会议基础管理 | 创建预约会议 | [99104](https://developer.work.weixin.qq.com/document/path/99104) | ✅ 已对接 | EfficiencyToolClient.createMeeting |
|  | 修改预约会议 | [99047](https://developer.work.weixin.qq.com/document/path/99047) | ✅ 已对接 | EfficiencyToolClient.updateMeeting |
|  | 取消预约会议 | [99048](https://developer.work.weixin.qq.com/document/path/99048) | ✅ 已对接 | EfficiencyToolClient.cancelMeeting |
|  | 获取会议详情 | [99049](https://developer.work.weixin.qq.com/document/path/99049) | ✅ 已对接 | EfficiencyToolClient.getMeetingInfo |
|  | 获取成员会议ID列表 | [99050](https://developer.work.weixin.qq.com/document/path/99050) | ✅ 已对接 | EfficiencyToolClient.getUserMeetingId |
| 会议统计管理 | 获取会议发起记录 | [99651](https://developer.work.weixin.qq.com/document/path/99651) | ✅ 已对接 | MeetingAdvancedClient.getStartList（meeting/statistics/get_start_list） |
| 预约会议高级管理 | 创建预约会议 | [98148](https://developer.work.weixin.qq.com/document/path/98148) | ✅ 已对接 | EfficiencyToolClient.createMeeting |
|  | 修改预约会议 | [98154](https://developer.work.weixin.qq.com/document/path/98154) | ✅ 已对接 | EfficiencyToolClient.updateMeeting |
|  | 取消预约会议 | [98153](https://developer.work.weixin.qq.com/document/path/98153) | ✅ 已对接 | EfficiencyToolClient.cancelMeeting |
|  | 获取会议详情 | [98149](https://developer.work.weixin.qq.com/document/path/98149) | ✅ 已对接 | EfficiencyToolClient.getMeetingInfo |
|  | 获取会议受邀成员列表 | [98160](https://developer.work.weixin.qq.com/document/path/98160) | ✅ 已对接 | MeetingClient.getInvitees（meeting/get_invitees） |
|  | 更新会议受邀成员列表 | [98162](https://developer.work.weixin.qq.com/document/path/98162) | ✅ 已对接 | MeetingAdvancedClient.setInvitees（meeting/set_invitees） |
|  | 获取成员会议ID列表 | [98714](https://developer.work.weixin.qq.com/document/path/98714) | ✅ 已对接 | EfficiencyToolClient.getUserMeetingId |
|  | 创建用户专属参会链接 | [98818](https://developer.work.weixin.qq.com/document/path/98818) | ✅ 已对接 | MeetingAdvancedClient.createCustomerShortUrl（meeting/create_customer_short_url） |
|  | 获取用户专属参会链接 | [98819](https://developer.work.weixin.qq.com/document/path/98819) | ✅ 已对接 | MeetingAdvancedClient.getCustomerShortUrl（meeting/get_customer_short_url） |
|  | 获取实时会中成员列表 | [98157](https://developer.work.weixin.qq.com/document/path/98157) | ✅ 已对接 | MeetingAdvancedClient.getRealtimeAttendees（meeting/get_realtime_attendee_list） |
|  | 获取已参会成员列表 | [98156](https://developer.work.weixin.qq.com/document/path/98156) | ✅ 已对接 | MeetingAdvancedClient.getAttendees（meeting/get_attendee_list） |
|  | 获取实时等候室成员列表 | [98163](https://developer.work.weixin.qq.com/document/path/98163) | ✅ 已对接 | MeetingAdvancedClient.getCurrentWaitingRoomUsers（meeting/waitingroom/get_current_user_list） |
|  | 获取等候室成员记录 | [98164](https://developer.work.weixin.qq.com/document/path/98164) | ✅ 已对接 | MeetingAdvancedClient.getWaitingRoomUsers（meeting/waitingroom/get_user_list） |
|  | 获取成员设备是否入会 | [98165](https://developer.work.weixin.qq.com/document/path/98165) | ✅ 已对接 | MeetingAdvancedClient.checkDeviceInMeeting（meeting/check_device_in_meeting） |
|  | 获取会议嘉宾列表 | [99039](https://developer.work.weixin.qq.com/document/path/99039) | ✅ 已对接 | MeetingAdvancedClient.getGuests（meeting/get_guests） |
|  | 更新会议嘉宾列表 | [99040](https://developer.work.weixin.qq.com/document/path/99040) | ✅ 已对接 | MeetingAdvancedClient.setGuests（meeting/set_guests） |
|  | 获取会议健康度 | [98821](https://developer.work.weixin.qq.com/document/path/98821) | ✅ 已对接 | MeetingAdvancedClient.getQuality（meeting/get_quality） |
|  | 修改会议报名配置 | [98797](https://developer.work.weixin.qq.com/document/path/98797) | ✅ 已对接 | MeetingAdvancedClient.setEnrollConfig（meeting/enroll/set_config） |
|  | 获取会议报名配置 | [98800](https://developer.work.weixin.qq.com/document/path/98800) | ✅ 已对接 | MeetingAdvancedClient.getEnrollConfig（meeting/enroll/get_config） |
|  | 获取会议成员报名 ID | [98794](https://developer.work.weixin.qq.com/document/path/98794) | ✅ 已对接 | MeetingAdvancedClient.queryEnrollIds（meeting/enroll/query_by_tmp_openid） |
|  | 获取会议报名信息 | [98810](https://developer.work.weixin.qq.com/document/path/98810) | ✅ 已对接 | MeetingAdvancedClient.listEnrolls（meeting/enroll/list） |
|  | 审批会议报名信息 | [98807](https://developer.work.weixin.qq.com/document/path/98807) | ✅ 已对接 | MeetingAdvancedClient.approveEnrolls（meeting/enroll/approve） |
|  | 导入会议报名信息 | [98816](https://developer.work.weixin.qq.com/document/path/98816) | ✅ 已对接 | MeetingAdvancedClient.importEnrolls（meeting/enroll/import） |
|  | 删除会议报名信息 | [98817](https://developer.work.weixin.qq.com/document/path/98817) | ✅ 已对接 | MeetingAdvancedClient.deleteEnrolls（meeting/enroll/delete） |
| 会中控制管理 | 管理会中设置 | [98175](https://developer.work.weixin.qq.com/document/path/98175) | ✅ 已对接 | MeetingAdvancedClient.setRealControl（meeting/realcontrol/set）
|  | 管理联席主持人 | [98180](https://developer.work.weixin.qq.com/document/path/98180) | ✅ 已对接 | MeetingAdvancedClient.setCoHost（meeting/realcontrol/set_cohost）
|  | 静音成员 | [98184](https://developer.work.weixin.qq.com/document/path/98184) | ✅ 已对接 | MeetingAdvancedClient.muteUser（meeting/realcontrol/mute_user）
|  | 关闭或开启成员视频 | [98189](https://developer.work.weixin.qq.com/document/path/98189) | ✅ 已对接 | MeetingAdvancedClient.switchUserVideo（meeting/realcontrol/switch_user_video）
|  | 关闭成员屏幕共享 | [98185](https://developer.work.weixin.qq.com/document/path/98185) | ✅ 已对接 | MeetingAdvancedClient.closeScreenShare（meeting/realcontrol/close_screen_share）
|  | 修改成员在会中显示的昵称 | [98188](https://developer.work.weixin.qq.com/document/path/98188) | ✅ 已对接 | MeetingAdvancedClient.setNicknames（meeting/realcontrol/set_nicknames）
|  | 管理等候室成员 | [98186](https://developer.work.weixin.qq.com/document/path/98186) | ✅ 已对接 | MeetingAdvancedClient.manageWaitingRoomUsers（meeting/realcontrol/manage_waiting_room_users）
|  | 移出成员 | [98181](https://developer.work.weixin.qq.com/document/path/98181) | ✅ 已对接 | MeetingAdvancedClient.kickoutUsers（meeting/realcontrol/kickout_users）
|  | 结束会议 | [98187](https://developer.work.weixin.qq.com/document/path/98187) | ✅ 已对接 | MeetingAdvancedClient.dismissMeeting（meeting/realcontrol/dismiss）
|  | 创建会议投票主题 | [98834](https://developer.work.weixin.qq.com/document/path/98834) | ✅ 已对接 | MeetingAdvancedClient.createPoll（meeting/poll/create_theme）
|  | 修改会议投票主题 | [98835](https://developer.work.weixin.qq.com/document/path/98835) | ✅ 已对接 | MeetingAdvancedClient.updatePoll（meeting/poll/update_theme）
|  | 获取会议投票列表 | [98836](https://developer.work.weixin.qq.com/document/path/98836) | ✅ 已对接 | MeetingAdvancedClient.getPollList（meeting/poll/get_poll_list）
|  | 获取会议投票主题信息 | [98837](https://developer.work.weixin.qq.com/document/path/98837) | ✅ 已对接 | MeetingAdvancedClient.getPollThemeInfo（meeting/poll/get_theme_info）
|  | 获取会议投票详情 | [98838](https://developer.work.weixin.qq.com/document/path/98838) | ✅ 已对接 | MeetingAdvancedClient.getPollDetail（meeting/poll/get_poll_detail）
|  | 删除会议投票 | [98839](https://developer.work.weixin.qq.com/document/path/98839) | ✅ 已对接 | MeetingAdvancedClient.deletePoll（meeting/poll/delete）
|  | 发起会议投票 | [98840](https://developer.work.weixin.qq.com/document/path/98840) | ✅ 已对接 | MeetingAdvancedClient.startPoll（meeting/poll/start）
|  | 结束会议投票 | [98841](https://developer.work.weixin.qq.com/document/path/98841) | ✅ 已对接 | MeetingAdvancedClient.finishPoll（meeting/poll/finish）
| 网络研讨会 (Webinar) 管理 | 创建网络研讨会 | [98842](https://developer.work.weixin.qq.com/document/path/98842) | ✅ 已对接 | MeetingAdvancedClient.createWebinar（meeting/webinar/create）
|  | 修改网络研讨会 | [98843](https://developer.work.weixin.qq.com/document/path/98843) | ✅ 已对接 | MeetingAdvancedClient.updateWebinar（meeting/webinar/update）
|  | 取消网络研讨会 | [98870](https://developer.work.weixin.qq.com/document/path/98870) | ✅ 已对接 | MeetingAdvancedClient.cancelWebinar（meeting/webinar/cancel）
|  | 获取网络研讨会详情 | [98860](https://developer.work.weixin.qq.com/document/path/98860) | ✅ 已对接 | MeetingAdvancedClient.getWebinar（meeting/webinar/get）
|  | 获取网络研讨会嘉宾列表 | [98871](https://developer.work.weixin.qq.com/document/path/98871) | ✅ 已对接 | MeetingAdvancedClient.getWebinarGuests（meeting/webinar/list_guest）
|  | 更新网络研讨会嘉宾列表 | [98872](https://developer.work.weixin.qq.com/document/path/98872) | ✅ 已对接 | MeetingAdvancedClient.updateWebinarGuests（meeting/webinar/update_guest_list）
|  | 管理网络研讨会暖场配置 | [98882](https://developer.work.weixin.qq.com/document/path/98882) | ✅ 已对接 | MeetingAdvancedClient.updateWebinarWarmUp（meeting/webinar/update_warm_up）
|  | 修改网络研讨会报名配置 | [98875](https://developer.work.weixin.qq.com/document/path/98875) | ✅ 已对接 | MeetingAdvancedClient.setWebinarEnrollConfig（meeting/webinar/enroll/set_config）
|  | 获取网络研讨会报名配置 | [98874](https://developer.work.weixin.qq.com/document/path/98874) | ✅ 已对接 | MeetingAdvancedClient.getWebinarEnrollConfig（meeting/webinar/enroll/get_config）
|  | 获取网络研讨会成员报名 ID | [98873](https://developer.work.weixin.qq.com/document/path/98873) | ✅ 已对接 | MeetingAdvancedClient.queryWebinarEnrollIds（meeting/webinar/enroll/query_by_tmp_openid）
|  | 获取网络研讨会报名信息 | [98876](https://developer.work.weixin.qq.com/document/path/98876) | ✅ 已对接 | MeetingAdvancedClient.listWebinarEnrolls（meeting/webinar/enroll/list）
|  | 审批网络研讨会报名信息 | [98877](https://developer.work.weixin.qq.com/document/path/98877) | ✅ 已对接 | MeetingAdvancedClient.approveWebinarEnrolls（meeting/webinar/enroll/approve）
|  | 导入网络研讨会报名信息 | [98880](https://developer.work.weixin.qq.com/document/path/98880) | ✅ 已对接 | MeetingAdvancedClient.importWebinarEnrolls（meeting/webinar/enroll/import）
|  | 删除网络研讨会报名信息 | [98881](https://developer.work.weixin.qq.com/document/path/98881) | ✅ 已对接 | MeetingAdvancedClient.deleteWebinarEnrolls（meeting/webinar/enroll/delete）
| 电话入会（PSTN）管理 | 批量外呼 | [98823](https://developer.work.weixin.qq.com/document/path/98823) | ✅ 已对接 | MeetingAdvancedClient.calloutPhones（meeting/phone/callout）
|  | 获取会议的外呼状态 | [98824](https://developer.work.weixin.qq.com/document/path/98824) | ✅ 已对接 | MeetingAdvancedClient.getPhoneCalloutStatus（meeting/phone/get_callout_status）
|  | 获取电话入会的成员ID | [98825](https://developer.work.weixin.qq.com/document/path/98825) | ✅ 已对接 | MeetingAdvancedClient.getPhoneTmpOpenIds（meeting/phone/get_tmp_openid）
| Rooms会议室管理 | 预定Rooms会议室 | [98791](https://developer.work.weixin.qq.com/document/path/98791) | ✅ 已对接 | MeetingAdvancedClient.bookRooms（meeting/rooms/book） |
|  | 释放Rooms会议室 | [98792](https://developer.work.weixin.qq.com/document/path/98792) | ✅ 已对接 | MeetingAdvancedClient.releaseRooms（meeting/rooms/release） |
|  | 获取Rooms会议室列表 | [98795](https://developer.work.weixin.qq.com/document/path/98795) | ✅ 已对接 | MeetingAdvancedClient.listRooms（meeting/rooms/list） |
|  | 获取Rooms会议室详情 | [98793](https://developer.work.weixin.qq.com/document/path/98793) | ✅ 已对接 | MeetingAdvancedClient.getRoomsInfo（meeting/rooms/get_info） |
|  | 获取Rooms会议室配置项 | [98802](https://developer.work.weixin.qq.com/document/path/98802) | ✅ 已对接 | MeetingAdvancedClient.getRoomsConfig（meeting/rooms/get_config） |
|  | 获取Rooms会议室下的会议列表 | [98796](https://developer.work.weixin.qq.com/document/path/98796) | ✅ 已对接 | MeetingAdvancedClient.listRoomsMeetings（meeting/rooms/list_meetings） |
|  | 获取设备列表 | [98798](https://developer.work.weixin.qq.com/document/path/98798) | ✅ 已对接 | MeetingAdvancedClient.listRoomsDevices（meeting/rooms/list_devices） |
|  | 获取控制器列表 | [98799](https://developer.work.weixin.qq.com/document/path/98799) | ✅ 已对接 | MeetingAdvancedClient.listRoomsControllers（meeting/rooms/list_controllers） |
|  | 获取Rooms会议室资源 | [98809](https://developer.work.weixin.qq.com/document/path/98809) | ✅ 已对接 | MeetingAdvancedClient.getRoomsInventory（meeting/rooms/get_inventory） |
|  | 呼叫Rooms会议室 | [98804](https://developer.work.weixin.qq.com/document/path/98804) | ✅ 已对接 | MeetingAdvancedClient.callRooms（meeting/rooms/call） |
|  | 取消呼叫Rooms会议室 | [98805](https://developer.work.weixin.qq.com/document/path/98805) | ✅ 已对接 | MeetingAdvancedClient.cancelRoomsCall（meeting/rooms/cancel_call） |
|  | 获取Rooms会议室应答状态 | [98806](https://developer.work.weixin.qq.com/document/path/98806) | ✅ 已对接 | MeetingAdvancedClient.getRoomsCallResponseStatus（meeting/rooms/get_response_status） |
| 会议室连接器（MRA）管理 | 获取 MRA 状态信息 | [98786](https://developer.work.weixin.qq.com/document/path/98786) | ❌ 未对接 | — |
|  | 切换 MRA 默认布局 | [98787](https://developer.work.weixin.qq.com/document/path/98787) | ❌ 未对接 | — |
|  | 设置 MRA 举手或手放下 | [98788](https://developer.work.weixin.qq.com/document/path/98788) | ❌ 未对接 | — |
|  | 挂断 MRA 呼叫 | [98789](https://developer.work.weixin.qq.com/document/path/98789) | ✅ 已对接 | MeetingAdvancedClient.hangupMra（meeting/mra/hangup） |
| 会议布局和背景管理 | 获取布局模板列表 | [98844](https://developer.work.weixin.qq.com/document/path/98844) | ✅ 已对接 | MeetingAdvancedClient.listMeetingLayoutTemplates（meeting/layout/list_template） |
|  | 添加会议基础布局 | [98845](https://developer.work.weixin.qq.com/document/path/98845) | ✅ 已对接 | MeetingAdvancedClient.addMeetingLayouts（meeting/layout/add） |
|  | 添加会议高级布局 | [98861](https://developer.work.weixin.qq.com/document/path/98861) | ✅ 已对接 | MeetingAdvancedClient.addMeetingAdvancedLayouts（meeting/advanced_layout/add） |
|  | 修改会议基础布局 | [98846](https://developer.work.weixin.qq.com/document/path/98846) | ✅ 已对接 | MeetingAdvancedClient.updateMeetingLayout（meeting/layout/update） |
|  | 修改会议高级布局 | [98868](https://developer.work.weixin.qq.com/document/path/98868) | ✅ 已对接 | MeetingAdvancedClient.updateMeetingAdvancedLayout（meeting/advanced_layout/update） |
|  | 设置会议默认布局 | [98847](https://developer.work.weixin.qq.com/document/path/98847) | ✅ 已对接 | MeetingAdvancedClient.setDefaultMeetingLayout（meeting/layout/set_default） |
|  | 设置高级布局 | [98869](https://developer.work.weixin.qq.com/document/path/98869) | ✅ 已对接 | MeetingAdvancedClient.applyMeetingAdvancedLayout（meeting/advanced_layout/apply） |
|  | 获取会议布局列表 | [98862](https://developer.work.weixin.qq.com/document/path/98862) | ✅ 已对接 | MeetingAdvancedClient.listMeetingAdvancedLayouts（meeting/advanced_layout/list） |
|  | 获取用户布局 | [98865](https://developer.work.weixin.qq.com/document/path/98865) | ✅ 已对接 | MeetingAdvancedClient.getMeetingUserLayout（meeting/advanced_layout/get_user_layout） |
|  | 批量删除布局 | [98866](https://developer.work.weixin.qq.com/document/path/98866) | ✅ 已对接 | MeetingAdvancedClient.batchDeleteMeetingLayouts（meeting/advanced_layout/batch_delete） |
|  | 添加会议背景 | [98851](https://developer.work.weixin.qq.com/document/path/98851) | ✅ 已对接 | MeetingAdvancedClient.addMeetingBackgrounds（meeting/layout/add_background） |
|  | 设置会议默认背景 | [98852](https://developer.work.weixin.qq.com/document/path/98852) | ✅ 已对接 | MeetingAdvancedClient.setDefaultMeetingBackground（meeting/layout/set_default_background） |
|  | 获取会议背景列表 | [98856](https://developer.work.weixin.qq.com/document/path/98856) | ✅ 已对接 | MeetingAdvancedClient.listMeetingBackgrounds（meeting/layout/list_background） |
|  | 删除会议背景 | [98853](https://developer.work.weixin.qq.com/document/path/98853) | ✅ 已对接 | MeetingAdvancedClient.deleteMeetingBackground（meeting/layout/delete_background） |
|  | 批量删除会议背景 | [98854](https://developer.work.weixin.qq.com/document/path/98854) | ✅ 已对接 | MeetingAdvancedClient.batchDeleteMeetingBackgrounds（meeting/layout/batch_delete_background） |
| 录制管理 | 获取会议录制列表 | [98192](https://developer.work.weixin.qq.com/document/path/98192) | ✅ 已对接 | MeetingAdvancedClient.listMeetingRecords（meeting/record/list） |
|  | 获取录制文件访问统计 | [98209](https://developer.work.weixin.qq.com/document/path/98209) | ✅ 已对接 | MeetingAdvancedClient.getMeetingRecordStatistics（meeting/record/get_statistics） |
|  | 修改会议录制共享设置 | [98208](https://developer.work.weixin.qq.com/document/path/98208) | ✅ 已对接 | MeetingAdvancedClient.updateMeetingRecordSharingConfig（meeting/record/update_sharing_config） |
|  | 删除会议录制 | [98206](https://developer.work.weixin.qq.com/document/path/98206) | ✅ 已对接 | MeetingAdvancedClient.deleteMeetingRecord（meeting/record/delete） |
|  | 删除单个录制文件 | [98207](https://developer.work.weixin.qq.com/document/path/98207) | ✅ 已对接 | MeetingAdvancedClient.deleteMeetingRecordFile（meeting/record/delete_file） |
|  | 获取单个录制文件详情 | [98205](https://developer.work.weixin.qq.com/document/path/98205) | ✅ 已对接 | MeetingAdvancedClient.getMeetingRecordFile（meeting/record/get_file） |
|  | 获取会议录制地址 | [98196](https://developer.work.weixin.qq.com/document/path/98196) | ✅ 已对接 | MeetingAdvancedClient.listMeetingRecordFiles（meeting/record/get_file_list） |
|  | 获取录制转写段落信息 | [98212](https://developer.work.weixin.qq.com/document/path/98212) | ✅ 已对接 | MeetingAdvancedClient.listMeetingTranscriptParagraphs（meeting/record/transcript/get_paragraph_list） |
|  | 获取录制转写详情 | [98211](https://developer.work.weixin.qq.com/document/path/98211) | ✅ 已对接 | MeetingAdvancedClient.getMeetingTranscriptDetail（meeting/record/transcript/get_detail） |
|  | 获取录制转写搜索结果 | [98213](https://developer.work.weixin.qq.com/document/path/98213) | ✅ 已对接 | MeetingAdvancedClient.searchMeetingTranscript（meeting/record/transcript/search） |
| 高级功能账号管理 | 分配高级功能账号 | [99508](https://developer.work.weixin.qq.com/document/path/99508) | ✅ 已对接 | MeetingAdvancedClient.submitMeetingVipAccounts/getMeetingVipAddJobResult（meeting/vip/submit_batch_add_job、meeting/vip/batch_add_job_result） |
|  | 取消高级功能账号 | [99509](https://developer.work.weixin.qq.com/document/path/99509) | ✅ 已对接 | MeetingAdvancedClient.cancelMeetingVipAccounts/getMeetingVipDeleteJobResult（meeting/vip/submit_batch_del_job、meeting/vip/batch_del_job_result） |
|  | 获取高级功能账号列表 | [99510](https://developer.work.weixin.qq.com/document/path/99510) | ✅ 已对接 | MeetingAdvancedClient.listMeetingVips（meeting/vip/list） |
| 回调通知 | 概述 | [99103](https://developer.work.weixin.qq.com/document/path/99103) | ⛔ 范围外 | — |
|  | 修改会议事件 | [99081](https://developer.work.weixin.qq.com/document/path/99081) | ⛔ 范围外 | — |
|  | 取消会议事件 | [99082](https://developer.work.weixin.qq.com/document/path/99082) | ⛔ 范围外 | — |
|  | 会议开始事件 | [98333](https://developer.work.weixin.qq.com/document/path/98333) | ⛔ 范围外 | — |
|  | 会议结束事件 | [98337](https://developer.work.weixin.qq.com/document/path/98337) | ⛔ 范围外 | — |
|  | 会议全体静音事件 | [98341](https://developer.work.weixin.qq.com/document/path/98341) | ⛔ 范围外 | — |
|  | 会议解除全体静音事件 | [98345](https://developer.work.weixin.qq.com/document/path/98345) | ⛔ 范围外 | — |
|  | 成员入会事件 | [98348](https://developer.work.weixin.qq.com/document/path/98348) | ⛔ 范围外 | — |
|  | 成员离会事件 | [98352](https://developer.work.weixin.qq.com/document/path/98352) | ⛔ 范围外 | — |
|  | 成员等待主持人入会事件 | [98353](https://developer.work.weixin.qq.com/document/path/98353) | ⛔ 范围外 | — |
|  | 成员进入等候室事件 | [98354](https://developer.work.weixin.qq.com/document/path/98354) | ⛔ 范围外 | — |
|  | 成员离开等候室事件 | [98355](https://developer.work.weixin.qq.com/document/path/98355) | ⛔ 范围外 | — |
|  | 成员从等候室进入会议事件 | [98393](https://developer.work.weixin.qq.com/document/path/98393) | ⛔ 范围外 | — |
|  | 成员从会议中被移入等候室事件 | [98394](https://developer.work.weixin.qq.com/document/path/98394) | ⛔ 范围外 | — |
|  | 共享屏幕开启事件 | [98395](https://developer.work.weixin.qq.com/document/path/98395) | ⛔ 范围外 | — |
|  | 共享屏幕结束事件 | [98396](https://developer.work.weixin.qq.com/document/path/98396) | ⛔ 范围外 | — |
|  | 会议成员角色变更事件 | [98397](https://developer.work.weixin.qq.com/document/path/98397) | ⛔ 范围外 | — |
|  | 网络研讨会成员角色变更事件 | [98771](https://developer.work.weixin.qq.com/document/path/98771) | ⛔ 范围外 | — |
|  | 网络研讨会暖场上传结果 | [98773](https://developer.work.weixin.qq.com/document/path/98773) | ⛔ 范围外 | — |
|  | PSTN 外呼状态更新事件 | [98774](https://developer.work.weixin.qq.com/document/path/98774) | ⛔ 范围外 | — |
|  | 素材上传结果 | [98775](https://developer.work.weixin.qq.com/document/path/98775) | ⛔ 范围外 | — |
|  | 开始云录制事件 | [98398](https://developer.work.weixin.qq.com/document/path/98398) | ⛔ 范围外 | — |
|  | 暂停云录制事件 | [98399](https://developer.work.weixin.qq.com/document/path/98399) | ⛔ 范围外 | — |
|  | 恢复云录制事件 | [98400](https://developer.work.weixin.qq.com/document/path/98400) | ⛔ 范围外 | — |
|  | 停止云录制事件 | [98401](https://developer.work.weixin.qq.com/document/path/98401) | ⛔ 范围外 | — |
|  | 云录制已完成事件 | [98402](https://developer.work.weixin.qq.com/document/path/98402) | ⛔ 范围外 | — |
|  | 删除云录制事件 | [98404](https://developer.work.weixin.qq.com/document/path/98404) | ⛔ 范围外 | — |
|  | 用户报名事件 | [98781](https://developer.work.weixin.qq.com/document/path/98781) | ⛔ 范围外 | — |
|  | 用户取消报名事件 | [98782](https://developer.work.weixin.qq.com/document/path/98782) | ⛔ 范围外 | — |
|  | 会议室应答事件 | [98783](https://developer.work.weixin.qq.com/document/path/98783) | ⛔ 范围外 | — |
|  | 会议发起事件 | [99648](https://developer.work.weixin.qq.com/document/path/99648) | ⛔ 范围外 | — |

## 微盘

状态：✅ 27；⚠️ 0；❌ 7；⛔ 2

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 微盘 | 概述 | [93654](https://developer.work.weixin.qq.com/document/path/93654) | ⛔ 范围外 | — |
| 管理空间 | 新建空间 | [93655](https://developer.work.weixin.qq.com/document/path/93655) | ✅ 已对接 | EfficiencyToolClient.createSpace |
|  | 重命名空间 | [97856](https://developer.work.weixin.qq.com/document/path/97856) | ✅ 已对接 | EfficiencyToolClient.renameSpace |
|  | 解散空间 | [97857](https://developer.work.weixin.qq.com/document/path/97857) | ✅ 已对接 | EfficiencyToolClient.dismissSpace |
|  | 获取空间信息 | [97858](https://developer.work.weixin.qq.com/document/path/97858) | ✅ 已对接 | EfficiencyToolClient.spaceInfo |
| 管理空间权限 | 添加成员/部门 | [93656](https://developer.work.weixin.qq.com/document/path/93656) | ✅ 已对接 | EfficiencyToolClient.addSpaceAcl |
|  | 移除成员/部门 | [97875](https://developer.work.weixin.qq.com/document/path/97875) | ✅ 已对接 | EfficiencyToolClient.delSpaceAcl |
|  | 安全设置 | [97876](https://developer.work.weixin.qq.com/document/path/97876) | ✅ 已对接 | EfficiencyToolClient.spaceSetting |
|  | 获取邀请链接 | [97877](https://developer.work.weixin.qq.com/document/path/97877) | ✅ 已对接 | EfficiencyToolClient.spaceShare |
|  | 获取空间信息 | [97878](https://developer.work.weixin.qq.com/document/path/97878) | ✅ 已对接 | EfficiencyToolClient.spaceInfo |
| 管理文件 | 获取文件列表 | [93657](https://developer.work.weixin.qq.com/document/path/93657) | ✅ 已对接 | EfficiencyToolClient.listFile（wedrive/file_list） |
|  | 上传文件 | [97880](https://developer.work.weixin.qq.com/document/path/97880) | ✅ 已对接 | EfficiencyToolClient.uploadFile |
|  | 文件分块上传 | [98004](https://developer.work.weixin.qq.com/document/path/98004) | ✅ 已对接 | EfficiencyToolClient.initUploadFile / fileUploadPart / fileUploadFinish |
|  | 下载文件 | [97881](https://developer.work.weixin.qq.com/document/path/97881) | ✅ 已对接 | EfficiencyToolClient.downloadFile |
|  | 新建文件夹/文档 | [97882](https://developer.work.weixin.qq.com/document/path/97882) | ✅ 已对接 | EfficiencyToolClient.createFile（wedrive/file_create） |
|  | 重命名文件 | [97883](https://developer.work.weixin.qq.com/document/path/97883) | ✅ 已对接 | EfficiencyToolClient.renameFile |
|  | 移动文件 | [97884](https://developer.work.weixin.qq.com/document/path/97884) | ✅ 已对接 | EfficiencyToolClient.moveFile |
|  | 删除文件 | [97885](https://developer.work.weixin.qq.com/document/path/97885) | ✅ 已对接 | EfficiencyToolClient.deleteFile |
|  | 获取文件信息 | [97886](https://developer.work.weixin.qq.com/document/path/97886) | ✅ 已对接 | EfficiencyToolClient.fileInfo |
| 管理文件权限 | 新增成员 | [93658](https://developer.work.weixin.qq.com/document/path/93658) | ✅ 已对接 | EfficiencyToolClient.addFileAcl |
|  | 删除成员 | [97888](https://developer.work.weixin.qq.com/document/path/97888) | ✅ 已对接 | EfficiencyToolClient.delFileAcl |
|  | 分享设置 | [97889](https://developer.work.weixin.qq.com/document/path/97889) | ✅ 已对接 | EfficiencyToolClient.fileSetting |
|  | 获取分享链接 | [97890](https://developer.work.weixin.qq.com/document/path/97890) | ✅ 已对接 | EfficiencyToolClient.getFileShare |
|  | 获取文件权限信息 | [97891](https://developer.work.weixin.qq.com/document/path/97891) | ❌ 未对接 | — |
|  | 修改文件安全设置 | [97892](https://developer.work.weixin.qq.com/document/path/97892) | ✅ 已对接 | EfficiencyToolClient.fileSetting |
| 回调通知 | 概述 | [97482](https://developer.work.weixin.qq.com/document/path/97482) | ⛔ 范围外 | — |
|  | 微盘容量不足事件 | [97898](https://developer.work.weixin.qq.com/document/path/97898) | ❌ 未对接 | — |
|  | 空间变更事件 | [97899](https://developer.work.weixin.qq.com/document/path/97899) | ❌ 未对接 | — |
|  | 文件变更事件 | [97900](https://developer.work.weixin.qq.com/document/path/97900) | ❌ 未对接 | — |
|  | 解散空间 | [97901](https://developer.work.weixin.qq.com/document/path/97901) | ❌ 未对接 | — |
|  | 修改空间成员 | [97902](https://developer.work.weixin.qq.com/document/path/97902) | ❌ 未对接 | — |
|  | 修改空间安全设置 | [97903](https://developer.work.weixin.qq.com/document/path/97903) | ❌ 未对接 | — |
| 高级功能账号管理 | 分配高级功能账号 | [99512](https://developer.work.weixin.qq.com/document/path/99512) | ✅ 已对接 | EfficiencyToolClient.batchAddVipAccounts（wedrive/vip/batch_add） |
|  | 取消高级功能账号 | [99513](https://developer.work.weixin.qq.com/document/path/99513) | ✅ 已对接 | EfficiencyToolClient.batchDeleteVipAccounts（wedrive/vip/batch_del） |
|  | 获取高级功能账号列表 | [99514](https://developer.work.weixin.qq.com/document/path/99514) | ✅ 已对接 | EfficiencyToolClient.listVipAccounts（wedrive/vip/list） |
|  | 版本和容量管理 | [95856](https://developer.work.weixin.qq.com/document/path/95856) | ✅ 已对接 | EfficiencyToolClient.proInfo / capacity |

## 直播

状态：✅ 8；⚠️ 0；❌ 0；⛔ 3

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 直播 | 概述 | [93633](https://developer.work.weixin.qq.com/document/path/93633) | ⛔ 范围外 | — |
|  | 创建预约直播 | [93637](https://developer.work.weixin.qq.com/document/path/93637) | ✅ 已对接 | EfficiencyToolClient.createLiving |
|  | 修改预约直播 | [93640](https://developer.work.weixin.qq.com/document/path/93640) | ✅ 已对接 | EfficiencyToolClient.modifyLiving |
|  | 取消预约直播 | [93638](https://developer.work.weixin.qq.com/document/path/93638) | ✅ 已对接 | EfficiencyToolClient.cancelLiving |
|  | 删除直播回放 | [93874](https://developer.work.weixin.qq.com/document/path/93874) | ✅ 已对接 | EfficiencyToolClient.delLiving |
|  | 在微信中观看直播或直播回放 | [93641](https://developer.work.weixin.qq.com/document/path/93641) | ⛔ 范围外 | — |
|  | 获取成员直播ID列表 | [93634](https://developer.work.weixin.qq.com/document/path/93634) | ✅ 已对接 | EfficiencyToolClient.getUserAllLivingId |
|  | 获取直播详情 | [93635](https://developer.work.weixin.qq.com/document/path/93635) | ✅ 已对接 | EfficiencyToolClient.getLivingInfo |
|  | 获取直播观看明细 | [93636](https://developer.work.weixin.qq.com/document/path/93636) | ✅ 已对接 | EfficiencyToolClient.getWatchStat |
|  | 直播回调事件 | [94145](https://developer.work.weixin.qq.com/document/path/94145) | ⛔ 范围外 | — |
|  | 获取跳转小程序商城的直播观众信息 | [94442](https://developer.work.weixin.qq.com/document/path/94442) | ✅ 已对接 | EfficiencyToolClient.getLivingShareInfo |

## 公费电话

状态：✅ 1；⚠️ 0；❌ 0；⛔ 0

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 公费电话 | 获取公费电话拨打记录 | [93662](https://developer.work.weixin.qq.com/document/path/93662) | ✅ 已对接 | EfficiencyToolClient.getDialRecord |

## 打卡

状态：✅ 12；⚠️ 0；❌ 0；⛔ 0

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 打卡 | 获取企业所有打卡规则 | [93384](https://developer.work.weixin.qq.com/document/path/93384) | ✅ 已对接 | WorkOaClient.getCorpCheckInOption |
|  | 获取员工打卡规则 | [90263](https://developer.work.weixin.qq.com/document/path/90263) | ✅ 已对接 | WorkOaClient.getCheckInOption |
|  | 获取打卡记录数据 | [90262](https://developer.work.weixin.qq.com/document/path/90262) | ✅ 已对接 | WorkOaClient.getCheckInData |
|  | 获取打卡日报数据 | [93374](https://developer.work.weixin.qq.com/document/path/93374) | ✅ 已对接 | WorkOaClient.getCheckInDayData |
|  | 获取打卡月报数据 | [93387](https://developer.work.weixin.qq.com/document/path/93387) | ✅ 已对接 | WorkOaClient.getCheckInMonthData |
|  | 获取打卡人员排班信息 | [93380](https://developer.work.weixin.qq.com/document/path/93380) | ✅ 已对接 | WorkOaClient.getCheckInScheduList |
|  | 为打卡人员排班 | [93385](https://developer.work.weixin.qq.com/document/path/93385) | ✅ 已对接 | WorkOaClient.setCheckInScheduleList |
|  | 为打卡人员补卡 | [95803](https://developer.work.weixin.qq.com/document/path/95803) | ✅ 已对接 | WorkOaClient.punchCorrection |
|  | 添加打卡记录 | [99647](https://developer.work.weixin.qq.com/document/path/99647) | ✅ 已对接 | WorkOaClient.addCheckInRecord |
|  | 录入打卡人员人脸信息 | [93378](https://developer.work.weixin.qq.com/document/path/93378) | ✅ 已对接 | WorkOaClient.addCheckInUserFace |
|  | 获取设备打卡数据 | [94126](https://developer.work.weixin.qq.com/document/path/94126) | ✅ 已对接 | WorkOaClient.getHardwareCheckInData |
|  | 管理打卡规则 | [98041](https://developer.work.weixin.qq.com/document/path/98041) | ✅ 已对接 | WorkOaClient.createCheckInRule / updateCheckInRule / clearCheckInRuleArrayField / deleteCheckInRule |

## 审批

状态：✅ 10；⚠️ 0；❌ 0；⛔ 3

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 审批 | 概述 | [91854](https://developer.work.weixin.qq.com/document/path/91854) | ⛔ 范围外 | — |
|  | 获取审批模板详情 | [91982](https://developer.work.weixin.qq.com/document/path/91982) | ✅ 已对接 | WorkOaClient.getTemplateDetail |
|  | 提交审批申请 | [91853](https://developer.work.weixin.qq.com/document/path/91853) | ✅ 已对接 | WorkOaClient.applyEvent |
|  | 审批申请状态变化回调通知 | [91815](https://developer.work.weixin.qq.com/document/path/91815) | ⛔ 范围外 | — |
|  | 批量获取审批单号 | [91816](https://developer.work.weixin.qq.com/document/path/91816) | ✅ 已对接 | WorkOaClient.getApprovalInfo |
|  | 获取审批申请详情 | [91983](https://developer.work.weixin.qq.com/document/path/91983) | ✅ 已对接 | WorkOaClient.getApprovalDetail |
|  | 获取审批数据（旧） | [91530](https://developer.work.weixin.qq.com/document/path/91530) | ✅ 已对接 | WorkOaClient.getApprovalData（corp/getapprovaldata） |
|  | 获取企业假期管理配置 | [93375](https://developer.work.weixin.qq.com/document/path/93375) | ✅ 已对接 | WorkOaClient.getCorpConf |
|  | 获取成员假期余额 | [93376](https://developer.work.weixin.qq.com/document/path/93376) | ✅ 已对接 | WorkOaClient.getUserVacationQuota |
|  | 修改成员假期余额 | [93377](https://developer.work.weixin.qq.com/document/path/93377) | ✅ 已对接 | WorkOaClient.setOneUserQuota |
|  | 审批流程引擎 | [90269](https://developer.work.weixin.qq.com/document/path/90269) | ⛔ 范围外 | — |
|  | 创建审批模板 | [97437](https://developer.work.weixin.qq.com/document/path/97437) | ✅ 已对接 | WorkOaClient.createApprovalTemplate（oa/approval/create_template） |
|  | 更新审批模板 | [97438](https://developer.work.weixin.qq.com/document/path/97438) | ✅ 已对接 | WorkOaClient.updateApprovalTemplate（oa/approval/update_template） |

## 汇报

状态：✅ 4；⚠️ 0；❌ 0；⛔ 1

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 汇报 | 概述 | [93496](https://developer.work.weixin.qq.com/document/path/93496) | ⛔ 范围外 | — |
|  | 批量获取汇报记录单号 | [93393](https://developer.work.weixin.qq.com/document/path/93393) | ✅ 已对接 | WorkOaClient.getJournalRecordList |
|  | 获取汇报记录详情 | [93394](https://developer.work.weixin.qq.com/document/path/93394) | ✅ 已对接 | WorkOaClient.getJournalRecordDetail |
|  | 获取汇报统计数据 | [93395](https://developer.work.weixin.qq.com/document/path/93395) | ✅ 已对接 | WorkOaClient.getJournalStatList |
|  | 下载微盘文件 | [98021](https://developer.work.weixin.qq.com/document/path/98021) | ✅ 已对接 | WorkOaClient.downloadJournalWedriveFile（oa/journal/download_wedrive_file） |

## 人事助手

状态：✅ 3；⚠️ 0；❌ 0；⛔ 1

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 花名册 | 概述 | [99130](https://developer.work.weixin.qq.com/document/path/99130) | ⛔ 范围外 | — |
|  | 获取员工字段配置 | [99131](https://developer.work.weixin.qq.com/document/path/99131) | ✅ 已对接 | HrClient.getStaffFields（hr/get_fields） |
|  | 获取员工花名册信息 | [99132](https://developer.work.weixin.qq.com/document/path/99132) | ✅ 已对接 | HrClient.getStaffInfo（hr/get_staff_info） |
|  | 更新员工花名册信息 | [99133](https://developer.work.weixin.qq.com/document/path/99133) | ✅ 已对接 | HrClient.updateStaffInfo（hr/update_staff_info） |

## 会议室

状态：✅ 2；⚠️ 0；❌ 0；⛔ 2

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 会议室 | 概述 | [93618](https://developer.work.weixin.qq.com/document/path/93618) | ⛔ 范围外 | — |
|  | 会议室管理 | [93619](https://developer.work.weixin.qq.com/document/path/93619) | ✅ 已对接 | WorkOaClient.add/search/update/delete MeetingRoom |
|  | 会议室预定管理 | [93620](https://developer.work.weixin.qq.com/document/path/93620) | ✅ 已对接 | WorkOaClient.getBookingInfo / bookMeetingRoom / cancelBookMeetingRoom |
|  | 回调事件 | [95333](https://developer.work.weixin.qq.com/document/path/95333) | ⛔ 范围外 | — |

## 高级功能

状态：✅ 3；⚠️ 0；❌ 0；⛔ 3

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 高级功能 | 概述 | [99860](https://developer.work.weixin.qq.com/document/path/99860) | ⛔ 范围外 | — |
|  | 成员申请的提交回调 | [99876](https://developer.work.weixin.qq.com/document/path/99876) | ⛔ 范围外 | — |
|  | 成员申请的终止回调 | [99877](https://developer.work.weixin.qq.com/document/path/99877) | ⛔ 范围外 | — |
|  | 设置审批单审批信息 | [99880](https://developer.work.weixin.qq.com/document/path/99880) | ✅ 已对接 | AdvancedFeatureClient.setApprovalDetail（advanced_feature/set_approval_detail） |
|  | 批量获取申请单ID | [99883](https://developer.work.weixin.qq.com/document/path/99883) | ✅ 已对接 | AdvancedFeatureClient.getApplyIdList（advanced_feature/get_apply_id_list） |
|  | 获取申请单详细信息 | [99885](https://developer.work.weixin.qq.com/document/path/99885) | ✅ 已对接 | AdvancedFeatureClient.getApprovalInfo（advanced_feature/get_approval_info） |

## 紧急通知应用

状态：✅ 2；⚠️ 0；❌ 0；⛔ 1

| 分组 | 文档页 | 官方链接 | 状态 | 代码证据 / 说明 |
| --- | --- | --- | --- | --- |
| 紧急通知应用 | 概述 | [91623](https://developer.work.weixin.qq.com/document/path/91623) | ⛔ 范围外 | — |
|  | 发起语音电话 | [91627](https://developer.work.weixin.qq.com/document/path/91627) | ✅ 已对接 | WorkOaClient.pstnccCall |
|  | 获取接听状态 | [91628](https://developer.work.weixin.qq.com/document/path/91628) | ✅ 已对接 | WorkOaClient.pstnccCallState |

## 仓库代码中的额外实现

当前目录核对还发现 `LinkedCorpClient` 已实现 5 个企业互联相关 Feign 路由：`linkedcorp/agent/get_perm_list`、`linkedcorp/user/get`、`linkedcorp/user/simplelist`、`linkedcorp/user/list`、`linkedcorp/department/list`。这些路由未在当前官方侧边栏的“企业互联”四页中单独列出，保留在此备注，避免误判为代码遗漏。
