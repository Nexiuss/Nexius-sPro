/**
 * FileName: ClientStatus
 * Author:   Administrator
 * Date:     2018/10/9 19:06
 * Description:
 */
package system;

import java.util.Date;

public class ClientStatus {

    String projectName;
    int version;
    String group;
    String[] remark;
    long freeMemory;
    long totalMemory;
    long maxMemory;
    String osName;
    String ipAddress;
    String id;
    String host;
    Date startTime;
    String classPath;
    long runtime;
    int threadCount;
    String projectPath;
    Date commitDate;
    long pid;

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setRemark(String[] remark) {
        this.remark = remark;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }
}
