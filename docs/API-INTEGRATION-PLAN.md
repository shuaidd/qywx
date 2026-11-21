# 企微服务端 API 补齐计划

## 目标与口径

- 目标：按官方服务端 REST API 目录逐步补齐 `API-COVERAGE.md` 中标记为 `❌ 未对接` 的接口。
- 范围：只处理服务端 REST API；不处理回调事件、前端 JSSDK、接入说明和非 REST 页面。
- 官方接口目录：<https://developer.work.weixin.qq.com/document/path/90664>
- 官方更新日志：<https://developer.work.weixin.qq.com/document/path/93221>
- 每完成一个批次，必须编译验证、更新 `API-COVERAGE.md`，并在本文件同步记录状态。
- 涉及更新日志的变更处理完成后，必须在 `CHANGELOG-PROCESSED.md` 记录日期、变更内容、影响模块和处理结果，避免重复处理。

## 执行方式

1. 按低风险基础接口 -> 高风险交易/安全接口 -> 业务专区 -> 大规模会议/文档类接口推进。
2. 一个批次只改动同一类模块，保持 Feign Client、Service、Model 和文档更新在同一提交或相邻提交内可追溯。
3. 官方文档无法访问时，不猜测路由；先用可验证的公开资料确认接口，不能确认的接口继续保留在未对接清单。
4. Java 8、4 空格缩进，沿用现有 `resquest` 包名、Lombok/手写 getter 并存的风格，不做无关重构。
5. 编译通过是最小门槛；真实接口调用需要凭据，不在无授权情况下执行生产请求。

## 批次划分

| 批次 | 范围 | 初步数量 | 状态 | 说明 |
| --- | --- | ---: | --- | --- |
| 1 | 账号 ID 转换、手机号/邮箱获取 userid、单个部门详情 | 4 | 已完成 | 新增转换/查询 Feign、Service、请求响应模型；`API-COVERAGE.md` 已同步，模块编译通过。 |
| 2 | 通讯录查看权限与身份验证二次验证 | 5 | 已完成 | 身份验证二次验证 2 页、通讯录查看权限 4 页已完成；路由 `auth/get_tfa_info`、`user/tfa_succ`、`contactrule/create`、`contactrule/list`、`contactrule/update`、`contactrule/delete` 已接入。 |
| 3 | 消息接收与发送、应用管理、机器人消息 | 6 | 已完成 | 覆盖模板卡片更新、消息撤回、学校通知、机器人主动回复、智能表格自动化群聊列表和工作台自定义展示；共新增 10 个 Feign 方法。 |
| 4 | 客户联系缺口 | 17 | 进行中 | 已完成成员对外信息、客户联系规则组管理、规则组客户标签、客户群 opengid 转换、客户群「加入群聊」管理和企业朋友圈发表与停止页；其余按朋友圈、获客助手和群发细分推进。 |
| 5 | 企业支付与小程序收款 | 21 | 待开始 | 涉及资金能力，先确认路由和参数，不修改支付配置。 |
| 6 | 效率工具：文档、日程、待办、收集表 | 53 | 待开始 | 文档页较多，按智能文档/智能表格/日程待办继续拆分。 |
| 7 | 会议全家桶 | 128 | 待开始 | 最大批次，按基础会议、统计、会控、录制、Webinar、Rooms、MRA 拆成子批。 |
| 8 | 家校沟通与家校应用 | 42 | 待开始 | 按学生/家长通讯录、班级、关注接口分组。 |
| 9 | 政民沟通 | 21 | 待开始 | 按人员、咨询、消息和转接接口分组。 |
| 10 | 邮件 | 16 | 待开始 | 覆盖发送、收件箱、账号和高级功能账号。 |
| 11 | 上下游、安全、微盘、智能表格编组 | 43 | 待开始 | 上下游已部分覆盖，优先补齐可独立验证的 REST 路由。 |
| 12 | 打卡、审批、汇报、人事助手、高级功能等剩余业务 | 27 | 待开始 | 收尾时再次全量核对官方目录，防止目录调整导致遗漏。 |

> 数量按 `API-COVERAGE.md` 当前文档页统计，不是接口路由数；实现时一个文档页可能产生多个 Feign 方法。

## 批次完成标准

- [ ] Feign 路由、请求/响应模型和 Service 入口完成，命名与邻近接口一致。
- [ ] 必要参数有 Service 层校验，敏感值不出现在日志或示例配置中。
- [ ] `API-COVERAGE.md` 状态和证据已更新。
- [ ] 目标模块或全量 `mvn -B package` 编译通过。
- [ ] 若属于更新日志，`CHANGELOG-PROCESSED.md` 已记录处理结果。
- [ ] 本计划中的批次状态已更新，未完成的路由保留在未对接清单中。

## 记录

- 2026-11-21：创建计划。当前基线为已对接 228 页、部分已对接 2 页、未对接 367 页、范围外 141 页；开始执行批次 1。
- 2026-11-21：批次 1 完成 4 个文档页，覆盖 `convert_tmp_external_userid`、`user/getuserid`、`user/get_userid_by_email` 和 `department/get`。覆盖度更新为已对接 232 页、未对接 363 页；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。

- 2026-11-21：批次 2 部分完成身份验证二次验证 2 个文档页，覆盖 `auth/get_tfa_info` 和 `user/tfa_succ`；目标模块编译通过。通讯录查看权限 4 个文档页因官方文档验证码频控暂缓，未猜测路由。

- 2026-11-21：批次 2 完成 4 个通讯录查看权限文档页，覆盖 `contactrule/create`、`contactrule/list`、`contactrule/update` 和 `contactrule/delete`。覆盖度更新为已对接 238 页、未对接 357 页；Feign 方法数达到 285；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。

- 2026-11-21：批次 3 完成 6 个文档页，覆盖 `message/update_template_card`、`message/recall`、`externalcontact/message/send`、`aibot/response`、`wedoc/smartsheet/groupchat/list`，以及 `agent/set_workbench_template`、`agent/get_workbench_template`、`agent/set_workbench_data`、`agent/batch_set_workbench_data`、`agent/get_workbench_data`。覆盖度更新为已对接 244 页、未对接 351 页；Feign 方法数达到 295；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。

- 2026-11-21：批次 4 完成成员对外信息文档页，在通讯录成员创建、读取和更新模型中补齐 `external_profile`；`external_position` 和 `extattr` 已有模型支持。目标模块干净编译通过。

- 2026-11-21：批次 4 完成客户联系规则组管理文档页，覆盖 `externalcontact/customer_strategy/list`、`customer_strategy/get`、`customer_strategy/get_range`、`customer_strategy/create`、`customer_strategy/edit` 和 `customer_strategy/del` 六条路由；覆盖度更新为已对接 246 页、未对接 349 页，Feign 方法数达到 301；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-21：批次 4 完成管理企业规则组下的客户标签文档页，覆盖 `externalcontact/get_strategy_tag_list`、`externalcontact/add_strategy_tag`、`externalcontact/edit_strategy_tag` 和 `externalcontact/del_strategy_tag` 四条路由；覆盖度更新为已对接 247 页、未对接 348 页，Feign 方法数达到 305；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-21：批次 4 完成客户群 opengid 转换文档页，覆盖 `externalcontact/opengid_to_chatid`；覆盖度更新为已对接 248 页、未对接 347 页，Feign 方法数达到 306；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-21：批次 4 完成客户群「加入群聊」管理文档页，覆盖 `externalcontact/groupchat/add_join_way`、`get_join_way`、`update_join_way` 和 `del_join_way` 四条路由；覆盖度更新为已对接 249 页、未对接 346 页，Feign 方法数达到 310；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-21：核对客户朋友圈概述页，确认为概念说明页；`API-COVERAGE.md` 改记为范围外，不计入 REST 接口补齐数量。
- 2026-11-21：批次 4 完成企业发表内容到客户的朋友圈文档页，覆盖 `externalcontact/add_moment_task` 和 `externalcontact/get_moment_task_result`；创建、查询 Service 入口与请求参数校验已补齐，覆盖度更新为已对接 250 页、未对接 344 页，Feign 方法数达到 312；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-21：批次 4 完成停止发表企业朋友圈文档页，覆盖 `externalcontact/cancel_moment_task`；覆盖度更新为已对接 251 页、未对接 343 页，Feign 方法数达到 313；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-21：批次 4 完成客户朋友圈规则组管理文档页，覆盖 `externalcontact/moment_strategy/list`、`moment_strategy/get`、`moment_strategy/get_range`、`moment_strategy/create`、`moment_strategy/edit` 和 `moment_strategy/del` 六条路由；覆盖度更新为已对接 252 页、未对接 342 页，Feign 方法数达到 319；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-21：完成获客链接管理文档页，覆盖 `externalcontact/customer_acquisition/list_link`、`get`、`create_link`、`update_link`、`delete_link` 五条路由；覆盖度更新为已对接 253 页、未对接 341 页，Feign 方法数达到 324；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。
