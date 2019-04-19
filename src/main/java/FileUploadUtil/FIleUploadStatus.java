package FileUploadUtil;

import java.util.Objects;

public class FIleUploadStatus {
    private String username;
    private String sessionid;
    private String fileName;
    private long transfered;
    private long fileSize;
    private long bytePerSecond;

    public long getBytePerSecond() {
        return bytePerSecond;
    }

    public void setBytePerSecond(long bytePerSecond) {
        this.bytePerSecond = bytePerSecond;
    }

    public FIleUploadStatus(String username, String sessionid, String fileName, Long fileSize) {
        this.username = username;
        this.sessionid = sessionid;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FIleUploadStatus that = (FIleUploadStatus) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(sessionid, that.sessionid) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(fileSize, that.fileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, sessionid, fileName, fileSize);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getTransfered() {
        return transfered;
    }

    public void setTransfered(long transfered) {
        this.transfered = transfered;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
