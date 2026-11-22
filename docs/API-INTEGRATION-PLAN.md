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
| 4 | 客户联系缺口 | 17 | 已完成 | 客户联系目录 48 个文档页全部核对完毕：42 个已对接、6 个确认为范围外。覆盖成员对外信息、规则组、企业标签、继承、客户群、朋友圈、获客助手、群发、统计、商品图册、敏感词、附件资源与已服务外部联系人。 |
| 5 | 企业支付与小程序收款 | 21 | 已完成 | 14 个 REST 文档页接入，15 个 Feign 方法；JS-SDK 与回调页、微信支付商户 XML/证书 API 确认为范围外，未修改支付配置。 |
| 6 | 效率工具：文档、日程、待办、收集表 | 53 | 已完成 | 已完成待办详情与状态更新、收集表管理、日程参与者、智能表格编组和智能文档页面、内容块、数据表与发布能力；更新重复日程核对为官方说明页。 |
| 7 | 会议全家桶 | 128 | 已完成 | 第一子批完成基础成员/嘉宾、等候室、健康度、报名和发起统计 19 页；第二子批完成会控和投票 17 页；第三子批完成 Webinar 14 页；第四子批完成 PSTN 3 页；第五子批完成 Rooms 12 页，第六子批完成 MRA 4 页，第七子批完成布局和背景 15 页，第八子批完成录制 10 页，第九子批完成高级功能账号 3 页；31 个会议回调文档页已调整为范围外。 |
| 8 | 家校沟通与家校应用 | 42 | 已完成 | 2026-11-22 完成 8A-8D 共 42 页；Feign 方法数 526；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过，真实接口调用未执行。 |
| 9 | 政民沟通 | 21 | 进行中 | 已完成配置网格结构 5 页；事件类别和巡查/居民上报因官方文档返回验证码暂缓，不猜测路由。 |
| 10 | 邮件 | 16 | 待开始 | 覆盖发送、收件箱、账号和高级功能账号。 |
| 11 | 上下游、安全、微盘、智能表格编组 | 43 | 待开始 | 上下游已部分覆盖，优先补齐可独立验证的 REST 路由。 |
| 12 | 打卡、审批、汇报、人事助手、高级功能等剩余业务 | 27 | 进行中 | 已完成打卡 3 个文档页、人事助手花名册 3 个文档页和高级功能审批 3 个文档页；其余按汇报和高级功能账号继续拆分推进。 |

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
- 2026-11-21：完成获取由获客链接添加的客户信息文档页，覆盖 `externalcontact/customer_acquisition/customer`；覆盖度更新为已对接 254 页、未对接 340 页，Feign 方法数达到 325；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-21：完成获客助手额度管理与使用统计文档页，覆盖 `externalcontact/customer_acquisition_quota` 和 `externalcontact/customer_acquisition/statistic`；统计请求已校验并归一化到自然日，覆盖度更新为已对接 255 页、未对接 339 页，Feign 方法数达到 327；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-21：核对提升广告有效率文档页，确认为广告运营指引页；`API-COVERAGE.md` 改记为范围外，不计入 REST 接口补齐数量。
- 2026-11-21：完成获取成员多次收消息详情文档页，覆盖 `externalcontact/customer_acquisition/get_chat_info`；新增模型字段均带中文含义注释，覆盖度更新为已对接 256 页、未对接 337 页，Feign 方法数达到 328；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。

- 2026-11-21：完成提醒成员群发文档页，覆盖 `externalcontact/remind_groupmsg_send`；新增请求模型字段带中文含义注释，Service 校验 `msgId`；覆盖度更新为已对接 257 页、未对接 336 页，Feign 方法数达到 329。

- 2026-11-21：完成停止企业群发文档页，覆盖 `externalcontact/cancel_groupmsg_send`；新增请求模型字段带中文含义注释，Service 校验 `msgId`；覆盖度更新为已对接 258 页、未对接 335 页，Feign 方法数达到 330。

- 2026-11-21：完成获取已服务的外部联系人文档页，覆盖 `externalcontact/contact_list`；新增请求/响应模型字段均带中文含义注释，Service 校验 `limit` 范围；覆盖度更新为已对接 259 页、未对接 334 页，Feign 方法数达到 331；`mvn -B -pl qywx-spring-boot-model,qywx-spring-boot-api -am -DskipTests package` 编译通过。

- 2026-11-21：完成审批模板创建与更新 2 个文档页，覆盖 `oa/approval/create_template` 和 `oa/approval/update_template`；新增请求/响应字段均带中文含义注释，Service 校验模板名称、控件列表和更新时的 `templateId`；覆盖度更新为已对接 261 页、未对接 332 页，Feign 方法数达到 333；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。

- 2026-11-21：完成待办详情与状态更新 2 个文档页，覆盖 `todo/get` 和 `todo/update`；新增请求/响应、参与人和提醒模型字段均带中文含义注释，Service 校验 `todoId`、状态枚举、参与人数量（最多20）及参与人字段；覆盖度更新为已对接 263 页、未对接 330 页，Feign 方法数达到 335；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。

- 2026-11-21：完成微信客服客户数据统计 2 个文档页，覆盖 `kf/get_corp_statistic` 和 `kf/get_servicer_statistic`；新增请求/响应模型字段均带中文含义注释，Service 校验客服账号、接待人员可选 userid、自然日区间（最近180天、闭区间最大31天）并归一化到0点；覆盖度更新为已对接 265 页、未对接 328 页，Feign 方法数达到 337；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。

- 2026-11-22：完成打卡 3 个文档页，覆盖 checkin/add_checkin_record、hardware/get_hardware_checkin_data，以及 checkin/add_checkin_option、update_checkin_option、clear_checkin_option_array_field、del_checkin_option；新增模型字段均带中文含义注释，Service 校验打卡记录、设备数据时间跨度/人员数量和规则字段约束；覆盖度更新为已对接 268 页、未对接 325 页，Feign 方法数达到 343；mvn -B -pl qywx-spring-boot-api -am -DskipTests package 编译通过。

- 2026-11-22：批次 6 完成收集表管理 5 个文档页，覆盖 `wedoc/create_form`、`wedoc/modify_form`、`wedoc/get_form_info`、`wedoc/get_form_statistic` 和 `wedoc/get_form_answer`；新增模型字段均带中文含义注释，Service 校验表单/问题必填项、问题数量（最多200）、oper=2 时的 `fillOutAuth`、统计 `req_type`/时间范围/`limit`（最大10000）和答案批次（最多100）；覆盖度更新为已对接 273 页、未对接 320 页，Feign 方法数达到 348；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。

- 2026-11-22：批次 6 完成日程参与者 2 个文档页，覆盖 `oa/schedule/add_attendees` 和 `oa/schedule/del_attendees`；更新重复日程页核对为官方说明页，无独立REST路由，已在更新日程模型中补充 `op_mode`/`op_start_time` 并校验模式1/2时的周期开始时间；覆盖度更新为已对接 275 页、未对接 317 页、范围外 144 页，Feign 方法数达到 350；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-22：批次 6 完成智能表格编组 4 个文档页，覆盖 `wedoc/smartsheet/add_field_group`、`update_field_group`、`delete_field_groups` 和 `get_field_groups`；新增编组模型字段均带中文含义注释，Service 校验文档/子表/编组必填项、编组字段数量（最多150）和分页参数；覆盖度更新为已对接 279 页、未对接 313 页，Feign 方法数达到 354；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-22：批次 6 完成智能文档页面管理 4 个文档页，覆盖 `wedoc/smartdoc/get_page_hierarchy`、`add_page`、`update_page` 和 `delete_page`；新增页面模型字段均带中文含义注释，Service 校验文档、页面、更新时的页面 ID 和布局模式（1/2/3）；覆盖度更新为已对接 283 页、未对接 309 页，Feign 方法数达到 358；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-22：批次 6 完成智能文档内容块 5 个文档页，覆盖 `wedoc/smartdoc/add_blocks`、`update_blocks`、`delete_blocks`、`get_block_list`、`export_task` 和 `get_export_result`；新增模型字段均带中文业务含义注释，Service 校验文档、页面、内容块、更新时的内容块 ID、删除 ID 列表、导出任务 ID、`content_type` 只支持 Markdown（1）以及内容块分页 `start`/`limit`；覆盖度更新为已对接 288 页、未对接 304 页，Feign 方法数达到 364；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-22：批次 6 完成智能文档数据表与发布 7 个文档页，覆盖 `wedoc/smartdoc/get_smartsheet_info`、`add_smartsheet`、`update_smartsheet`、`delete_smartsheet`、`publish`、`cancel_publish` 和 `publish_setting`；新增模型字段均带中文业务含义注释，Service 校验 `docid`、`block_id`、`info`、发布范围以及指定成员可见时的成员/部门字段；覆盖度更新为已对接 295 页、未对接 299 页，Feign 方法数达到 371；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-22：批次 5 完成 14 个企业支付与小程序对外收款 REST 文档页，覆盖 `externalpay/getmerchant`、`set_mch_use_scope`、`get_bill_list`、`get_payment_info`、`get_fund_flow`、`miniapppay/apply_mch`、`get_applyment_status`、`upload_image`、`create_order`、`get_order`、`close_order`、`get_sign`、`refund`、`get_refund_detail`、`get_bill`；新增模型字段均带中文业务含义注释，Service 校验商户、日期、分页、金额、退款、申请资料和上传图片约束；JS-SDK/回调页及微信支付商户 XML/证书 API 标记为范围外。覆盖度更新为已对接 309 页、部分已对接 2 页、未对接 280 页、范围外 147 页，Feign 方法数达到 386；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-22：批次 7 第一子批完成会议基础与统计 19 个文档页，覆盖 `meeting/statistics/get_start_list`、`meeting/set_invitees`、`meeting/create_customer_short_url`、`meeting/get_customer_short_url`、`meeting/get_realtime_attendee_list`、`meeting/get_attendee_list`、`meeting/waitingroom/get_current_user_list`、`meeting/waitingroom/get_user_list`、`meeting/check_device_in_meeting`、`meeting/get_guests`、`meeting/set_guests`、`meeting/get_quality`、`meeting/enroll/set_config`、`meeting/enroll/get_config`、`meeting/enroll/query_by_tmp_openid`、`meeting/enroll/list`、`meeting/enroll/approve`、`meeting/enroll/import` 和 `meeting/enroll/delete`；新增模型字段均带中文业务含义注释，Service 校验会议/子会议 ID、专属链接字段、嘉宾/报名列表、审批操作、设备入会查询和时间范围；覆盖度更新为已对接 328 页、未对接 261 页，Feign 方法数达到 405；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。

- 2026-11-22：批次 7 第二子批完成会控和投票 17 个文档页，覆盖 `meeting/realcontrol/set`、`set_cohost`、`mute_user`、`switch_user_video`、`close_screen_share`、`set_nicknames`、`manage_waiting_room_users`、`kickout_users`、`dismiss`，以及 `meeting/poll/create_theme`、`update_theme`、`get_poll_list`、`get_theme_info`、`get_poll_detail`、`delete`、`start`、`finish`；新增模型字段均带中文业务含义注释，Service 校验会控成员设备、等候室操作、结束会议参数、投票操作者、主题/问题/选项长度和投票 ID 组合；覆盖度更新为已对接 345 页、未对接 244 页，Feign 方法数达到 422；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。

- 2026-11-22：批次 7 第三子批完成网络研讨会 14 个文档页，覆盖 `meeting/webinar/create`、`update`、`cancel`、`get`、`list_guest`、`update_guest_list`、`update_warm_up`，以及 `meeting/webinar/enroll/set_config`、`get_config`、`query_by_tmp_openid`、`list`、`approve`、`import`、`delete`；新增模型字段均带中文业务含义注释，Service 校验会议 ID、会议 Code、设置、嘉宾、暖场和报名记录；覆盖度更新为已对接 359 页、未对接 230 页，Feign 方法数达到 436；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-22：批次 7 第四子批完成电话入会 3 个文档页，覆盖 `meeting/phone/callout`、`get_callout_status` 和 `get_tmp_openid`；新增模型字段均带中文业务含义注释，Service 校验会议 ID、电话区号/号码、外呼 50 路、查询号码 20 个和分页 100 条上限；覆盖度更新为已对接 362 页、未对接 227 页，Feign 方法数达到 439；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-22：批次 7 第五子批完成 Rooms会议室管理 12 个文档页，覆盖 `meeting/rooms/book`、`release`、`list`、`get_info`、`get_config`、`list_meetings`、`list_devices`、`list_controllers`、`get_inventory`、`call`、`cancel_call` 和 `get_response_status`；新增模型字段均带中文业务含义注释，Service 校验会议 ID、Rooms ID 列表、分页上限、会议室/设备 ID 二选一、90 天时间区间以及 MRA 协议和信令地址；覆盖度更新为已对接 374 页、未对接 215 页，Feign 方法数达到 451；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。

- 2026-11-22：批次 7 第七子批完成会议布局和背景 15 个文档页，覆盖 `meeting/layout/list_template`、`meeting/layout/add`、`meeting/advanced_layout/add`、`meeting/layout/update`、`meeting/advanced_layout/update`、`meeting/layout/set_default`、`meeting/advanced_layout/apply`、`meeting/advanced_layout/list`、`meeting/advanced_layout/get_user_layout`、`meeting/advanced_layout/batch_delete`、`meeting/layout/add_background`、`meeting/layout/set_default_background`、`meeting/layout/list_background`、`meeting/layout/delete_background` 和 `meeting/layout/batch_delete_background`；新增模型字段均带中文业务含义注释，Service 校验布局/背景数量、模板与宫格、座次身份、轮询参数、设备类型、图片MD5/URL和空字符串默认ID语义；覆盖度更新为已对接 393 页、未对接 196 页，Feign 方法数达到 470；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-22：批次 7 第八子批完成会议录制 10 个文档页，覆盖 `meeting/record/list`、`get_statistics`、`update_sharing_config`、`delete`、`delete_file`、`get_file`、`get_file_list`，以及 `meeting/record/transcript/get_paragraph_list`、`get_detail`、`search`；新增 27 个模型文件均带中文业务含义注释，Service 校验查询维度二选一、31天时间区间、分页上限、录制/文件/会议ID、转写段落数、搜索文本以及共享开关、权限类型、密码和有效期组合；覆盖度更新为已对接403页、未对接186页，会议模块已对接105页、未对接35页，Feign方法数达到480；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。真实企微调用未执行。
- 2026-11-22：批次 7 第六子批完成 MRA管理 4 个文档页，覆盖 `meeting/mra/query_status`、`set_default_layout`、`set_raise_hand` 和 `hangup`；新增模型字段均带中文业务含义注释，Service 校验会议 ID、MRA临时ID、举手状态、默认分屏和默认非视频成员显示方式；覆盖度更新为已对接 378 页、未对接 211 页，Feign 方法数达到 455；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。
- 2026-11-22：批次 7 第九子批完成高级功能账号 3 个文档页，覆盖 `meeting/vip/submit_batch_add_job`、`batch_add_job_result`、`submit_batch_del_job`、`batch_del_job_result` 和 `list`；一个批次包含 3 个文档页 / 5 个 REST 路由，新增 7 个模型文件均带中文业务含义注释，Service 校验成员列表非空且最多100个、成员ID、任务ID以及分页上限1~200；覆盖度更新为已对接406页、未对接183页，会议模块已对接108页、未对接32页，Feign方法数达到485；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。真实企微调用未执行。
- 2026-11-22：批次 7 收尾完成，将 31 个会议回调文档 ID 调整为范围外，不实现回调代码；会议模块覆盖状态更新为已对接108页、未对接1页、范围外32页，总体状态更新为已对接406页、部分对接2页、未对接152页、范围外178页，Feign方法数保持485；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。真实企微调用未执行。
- 2026-11-22：批次 8 完成 8D 家校应用 13 个文档页，覆盖 `health/get_health_report_stat`、`health/get_report_jobids`、`health/get_report_job_info`、`health/get_report_answer`、`living/get_user_all_livingid`、`school/living/get_living_info`、`school/living/get_watch_stat`、`school/living/get_unwatch_stat`、`living/delete_replay_data`、`school/living/get_watch_stat_v2`、`school/living/get_unwatch_stat_v2`、`school/get_payment_result` 和 `school/get_trade`；新增模型字段均带中文业务含义注释，Service 校验日期、任务/直播/收款/订单/成员 ID、分页范围和游标；覆盖度更新为已对接 445 页、部分对接 2 页、未对接 113 页、范围外 178 页，Feign 方法数达到 526；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。真实企微调用未执行。

- 2026-11-22：批次 9 首子批完成配置网格结构 5 个文档页，覆盖 `report/grid/add`、`report/grid/update`、`report/grid/delete`、`report/grid/list` 和 `report/grid/get_user_grid_info`；新增模型字段均带中文业务含义注释，Service 校验应用名、网格名称长度、父/目标网格 ID、负责人数量、成员上限和成员 ID；事件类别及巡查/居民上报页当前被官方站验证码拦截，已保留未对接且不猜测路由。覆盖度更新为已对接 450 页、部分对接 2 页、未对接 108 页、范围外 178 页，Feign 方法数达到 531；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。真实企微调用未执行。

- 2026-11-22：批次 12 完成人事助手花名册 3 个文档页，覆盖 `hr/get_fields`、`hr/get_staff_info` 和 `hr/update_staff_info`；新增模型字段均带中文业务含义注释，Service 校验应用名、员工 userid、字段列表以及更新时新增/修改/删除操作至少一项非空，更新结果按官方 JSON 示例映射 `insert_result` 单数字段；覆盖度更新为已对接 453 页、部分对接 2 页、未对接 105 页、范围外 178 页，Feign 方法数达到 534；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。真实企微调用未执行。
- 2026-11-22：批次 12 完成高级功能审批 3 个文档页，覆盖 `advanced_feature/set_approval_detail`、`advanced_feature/get_apply_id_list` 和 `advanced_feature/get_approval_info`；新增模型字段均带中文业务含义注释，Service 校验申请/审批ID、审批状态、审批链接 http(s) 前缀、审批节点状态与多人审批方式必填、待处理/已处理审批人各最多100个且不可同时为空、账号类型 1~4、分页上限 1~200 和申请单类型 0~2；覆盖度更新为已对接 456 页、部分对接 2 页、未对接 102 页、范围外 178 页，Feign 方法数达到 537；`mvn -B -pl qywx-spring-boot-api -am -DskipTests package` 编译通过。真实企微调用未执行。