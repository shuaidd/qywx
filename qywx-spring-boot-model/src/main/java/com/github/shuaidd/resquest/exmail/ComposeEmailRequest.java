package com.github.shuaidd.resquest.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 企业邮箱发送邮件请求，普通邮件、日程邮件和会议邮件共用该请求结构。
 */
public class ComposeEmailRequest {

    /**
     * 收件人；emails 和 userids 至少填写一种
     */
    @JsonProperty("to")
    private EmailRecipients to;

    /**
     * 抄送人
     */
    @JsonProperty("cc")
    private EmailRecipients cc;

    /**
     * 密送人
     */
    @JsonProperty("bcc")
    private EmailRecipients bcc;

    /**
     * 邮件标题
     */
    @JsonProperty("subject")
    private String subject;

    /**
     * 邮件正文
     */
    @JsonProperty("content")
    private String content;

    /**
     * 附件列表
     */
    @JsonProperty("attachment_list")
    private List<EmailAttachment> attachmentList;

    /**
     * 正文类型：html 或 text，默认 html
     */
    @JsonProperty("content_type")
    private String contentType;

    /**
     * 日程数据；发送日程邮件必填，发送会议邮件时也必须填写
     */
    @JsonProperty("schedule")
    private EmailSchedule schedule;

    /**
     * 会议数据；发送会议邮件必填
     */
    @JsonProperty("meeting")
    private EmailMeeting meeting;

    /**
     * 是否开启 ID 转译：0关闭，1开启，默认0
     */
    @JsonProperty("enable_id_trans")
    private Integer enableIdTrans;

    public EmailRecipients getTo() {
        return to;
    }

    public void setTo(EmailRecipients to) {
        this.to = to;
    }

    public EmailRecipients getCc() {
        return cc;
    }

    public void setCc(EmailRecipients cc) {
        this.cc = cc;
    }

    public EmailRecipients getBcc() {
        return bcc;
    }

    public void setBcc(EmailRecipients bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<EmailAttachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<EmailAttachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public EmailSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(EmailSchedule schedule) {
        this.schedule = schedule;
    }

    public EmailMeeting getMeeting() {
        return meeting;
    }

    public void setMeeting(EmailMeeting meeting) {
        this.meeting = meeting;
    }

    public Integer getEnableIdTrans() {
        return enableIdTrans;
    }

    public void setEnableIdTrans(Integer enableIdTrans) {
        this.enableIdTrans = enableIdTrans;
    }

    /**
     * 邮件人员信息，emails 为外部邮箱地址，userids 为企业成员账号
     */
    public static class EmailRecipients {

        /**
         * 邮箱地址列表
         */
        @JsonProperty("emails")
        private List<String> emails;

        /**
         * 企业成员 userid 列表
         */
        @JsonProperty("userids")
        private List<String> userids;

        public List<String> getEmails() {
            return emails;
        }

        public void setEmails(List<String> emails) {
            this.emails = emails;
        }

        public List<String> getUserids() {
            return userids;
        }

        public void setUserids(List<String> userids) {
            this.userids = userids;
        }
    }

    /**
     * 邮件附件
     */
    public static class EmailAttachment {

        /**
         * 附件文件名
         */
        @JsonProperty("file_name")
        private String fileName;

        /**
         * 附件内容，使用 Base64 编码
         */
        @JsonProperty("content")
        private String content;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    /**
     * 日程或会议时间数据
     */
    public static class EmailSchedule {

        /**
         * 日程或会议 ID；修改和取消时必填
         */
        @JsonProperty("schedule_id")
        private String scheduleId;

        /**
         * 操作方法：request 创建或修改，cancel 取消
         */
        @JsonProperty("method")
        private String method;

        /**
         * 地点
         */
        @JsonProperty("location")
        private String location;

        /**
         * 开始时间，Unix 时间戳
         */
        @JsonProperty("start_time")
        private Integer startTime;

        /**
         * 结束时间，Unix 时间戳
         */
        @JsonProperty("end_time")
        private Integer endTime;

        /**
         * 提醒和重复设置
         */
        @JsonProperty("reminders")
        private EmailReminder reminders;

        /**
         * 日程管理员列表，最多 3 个且只支持 userid
         */
        @JsonProperty("schedule_admins")
        private EmailRecipients scheduleAdmins;

        public EmailRecipients getScheduleAdmins() {
            return scheduleAdmins;
        }

        public void setScheduleAdmins(EmailRecipients scheduleAdmins) {
            this.scheduleAdmins = scheduleAdmins;
        }

        public String getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(String scheduleId) {
            this.scheduleId = scheduleId;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Integer getStartTime() {
            return startTime;
        }

        public void setStartTime(Integer startTime) {
            this.startTime = startTime;
        }

        public Integer getEndTime() {
            return endTime;
        }

        public void setEndTime(Integer endTime) {
            this.endTime = endTime;
        }

        public EmailReminder getReminders() {
            return reminders;
        }

        public void setReminders(EmailReminder reminders) {
            this.reminders = reminders;
        }
    }

    /**
     * 日程或会议提醒与重复规则
     */
    public static class EmailReminder {

        /**
         * 是否提醒：0否，1是
         */
        @JsonProperty("is_remind")
        private Integer isRemind;

        /**
         * 开始前提醒分钟数；负数表示开始后提醒
         */
        @JsonProperty("remind_before_event_mins")
        private Integer remindBeforeEventMins;

        /**
         * UTC 偏移小时数，取值范围 -12~12
         */
        @JsonProperty("timezone")
        private Integer timezone;

        /**
         * 是否重复：0否，1是
         */
        @JsonProperty("is_repeat")
        private Integer isRepeat;

        /**
         * 是否自定义重复：0否，1是
         */
        @JsonProperty("is_custom_repeat")
        private Integer isCustomRepeat;

        /**
         * 重复类型：0每日，1每周，2每月，5每年
         */
        @JsonProperty("repeat_type")
        private Integer repeatType;

        /**
         * 自定义重复间隔
         */
        @JsonProperty("repeat_interval")
        private Integer repeatInterval;

        /**
         * 每周重复的周一至周日，取值1~7
         */
        @JsonProperty("repeat_day_of_week")
        private List<Integer> repeatDayOfWeek;

        /**
         * 每月重复的日期，取值1~31
         */
        @JsonProperty("repeat_day_of_month")
        private List<Integer> repeatDayOfMonth;

        /**
         * 每年或每月重复的每周序号
         */
        @JsonProperty("repeat_week_of_month")
        private List<Integer> repeatWeekOfMonth;

        /**
         * 每年重复的月份，取值1~12
         */
        @JsonProperty("repeat_month_of_year")
        private List<Integer> repeatMonthOfYear;

        /**
         * 重复结束时间，Unix 时间戳；0表示一直重复
         */
        @JsonProperty("repeat_until")
        private Integer repeatUntil;

        public Integer getIsRemind() {
            return isRemind;
        }

        public void setIsRemind(Integer isRemind) {
            this.isRemind = isRemind;
        }

        public Integer getRemindBeforeEventMins() {
            return remindBeforeEventMins;
        }

        public void setRemindBeforeEventMins(Integer remindBeforeEventMins) {
            this.remindBeforeEventMins = remindBeforeEventMins;
        }

        public Integer getTimezone() {
            return timezone;
        }

        public void setTimezone(Integer timezone) {
            this.timezone = timezone;
        }

        public Integer getIsRepeat() {
            return isRepeat;
        }

        public void setIsRepeat(Integer isRepeat) {
            this.isRepeat = isRepeat;
        }

        public Integer getIsCustomRepeat() {
            return isCustomRepeat;
        }

        public void setIsCustomRepeat(Integer isCustomRepeat) {
            this.isCustomRepeat = isCustomRepeat;
        }

        public Integer getRepeatType() {
            return repeatType;
        }

        public void setRepeatType(Integer repeatType) {
            this.repeatType = repeatType;
        }

        public Integer getRepeatInterval() {
            return repeatInterval;
        }

        public void setRepeatInterval(Integer repeatInterval) {
            this.repeatInterval = repeatInterval;
        }

        public List<Integer> getRepeatDayOfWeek() {
            return repeatDayOfWeek;
        }

        public void setRepeatDayOfWeek(List<Integer> repeatDayOfWeek) {
            this.repeatDayOfWeek = repeatDayOfWeek;
        }

        public List<Integer> getRepeatDayOfMonth() {
            return repeatDayOfMonth;
        }

        public void setRepeatDayOfMonth(List<Integer> repeatDayOfMonth) {
            this.repeatDayOfMonth = repeatDayOfMonth;
        }

        public List<Integer> getRepeatWeekOfMonth() {
            return repeatWeekOfMonth;
        }

        public void setRepeatWeekOfMonth(List<Integer> repeatWeekOfMonth) {
            this.repeatWeekOfMonth = repeatWeekOfMonth;
        }

        public List<Integer> getRepeatMonthOfYear() {
            return repeatMonthOfYear;
        }

        public void setRepeatMonthOfYear(List<Integer> repeatMonthOfYear) {
            this.repeatMonthOfYear = repeatMonthOfYear;
        }

        public Integer getRepeatUntil() {
            return repeatUntil;
        }

        public void setRepeatUntil(Integer repeatUntil) {
            this.repeatUntil = repeatUntil;
        }
    }

    /**
     * 会议邮件专属数据
     */
    public static class EmailMeeting {

        /**
         * 会议主持人列表，最多10个且只支持 userid
         */
        @JsonProperty("hosts")
        private EmailRecipients hosts;

        /**
         * 会议管理员，仅可指定1个企业内成员且必须在参与人中
         */
        @JsonProperty("meeting_admins")
        private EmailRecipients meetingAdmins;

        /**
         * 会议基础设置
         */
        @JsonProperty("option")
        private EmailMeetingOption option;

        public EmailRecipients getHosts() {
            return hosts;
        }

        public void setHosts(EmailRecipients hosts) {
            this.hosts = hosts;
        }

        public EmailRecipients getMeetingAdmins() {
            return meetingAdmins;
        }

        public void setMeetingAdmins(EmailRecipients meetingAdmins) {
            this.meetingAdmins = meetingAdmins;
        }

        public EmailMeetingOption getOption() {
            return option;
        }

        public void setOption(EmailMeetingOption option) {
            this.option = option;
        }
    }

    /**
     * 会议邮件基础设置
     */
    public static class EmailMeetingOption {

        /**
         * 入会密码，4~6位纯数字
         */
        @JsonProperty("password")
        private String password;

        /**
         * 自动录制：0关闭，1本地录制，2云录制
         */
        @JsonProperty("auto_record")
        private Integer autoRecord;

        /**
         * 是否开启等候室
         */
        @JsonProperty("enable_waiting_room")
        private Boolean enableWaitingRoom;

        /**
         * 是否允许成员在主持人进会前加入
         */
        @JsonProperty("allow_enter_before_host")
        private Boolean allowEnterBeforeHost;

        /**
         * 入会限制：0所有人，2仅企业内部用户
         */
        @JsonProperty("enter_restraint")
        private Integer enterRestraint;

        /**
         * 是否开启屏幕水印
         */
        @JsonProperty("enable_screen_watermark")
        private Boolean enableScreenWatermark;

        /**
         * 入会静音：0关闭，1开启，2超过6人自动开启
         */
        @JsonProperty("enable_enter_mute")
        private Integer enableEnterMute;

        /**
         * 会议开始提醒范围：1不提醒，2仅主持人，3所有成员
         */
        @JsonProperty("remind_scope")
        private Integer remindScope;

        /**
         * 水印类型：0单排，1多排
         */
        @JsonProperty("water_mark_type")
        private Integer waterMarkType;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getAutoRecord() {
            return autoRecord;
        }

        public void setAutoRecord(Integer autoRecord) {
            this.autoRecord = autoRecord;
        }

        public Boolean getEnableWaitingRoom() {
            return enableWaitingRoom;
        }

        public void setEnableWaitingRoom(Boolean enableWaitingRoom) {
            this.enableWaitingRoom = enableWaitingRoom;
        }

        public Boolean getAllowEnterBeforeHost() {
            return allowEnterBeforeHost;
        }

        public void setAllowEnterBeforeHost(Boolean allowEnterBeforeHost) {
            this.allowEnterBeforeHost = allowEnterBeforeHost;
        }

        public Integer getEnterRestraint() {
            return enterRestraint;
        }

        public void setEnterRestraint(Integer enterRestraint) {
            this.enterRestraint = enterRestraint;
        }

        public Boolean getEnableScreenWatermark() {
            return enableScreenWatermark;
        }

        public void setEnableScreenWatermark(Boolean enableScreenWatermark) {
            this.enableScreenWatermark = enableScreenWatermark;
        }

        public Integer getEnableEnterMute() {
            return enableEnterMute;
        }

        public void setEnableEnterMute(Integer enableEnterMute) {
            this.enableEnterMute = enableEnterMute;
        }

        public Integer getRemindScope() {
            return remindScope;
        }

        public void setRemindScope(Integer remindScope) {
            this.remindScope = remindScope;
        }

        public Integer getWaterMarkType() {
            return waterMarkType;
        }

        public void setWaterMarkType(Integer waterMarkType) {
            this.waterMarkType = waterMarkType;
        }
    }
}
