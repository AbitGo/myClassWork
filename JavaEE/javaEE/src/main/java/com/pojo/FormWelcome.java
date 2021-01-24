package com.pojo;

/***
 * @function 邮件欢迎语的pojo类
 * @startTime 表单提交开始时间
 * @endTime 表单提交截至时间
 */
public class FormWelcome {
    private String welcome;
    private String startTime;
    private String endTime;

    public FormWelcome(String welcome, String startTime, String endTime) {
        this.welcome = welcome;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
