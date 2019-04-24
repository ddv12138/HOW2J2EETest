package FileUploadUtil;

import java.text.DecimalFormat;
import java.util.Objects;

public class FIleUploadStatus {
    private String username;
    private String sessionid;
    private String fileName;
    private long transfered;
    private long fileSize;
    private String percent;

    public FIleUploadStatus(String username, String sessionid, String fileName, Long fileSize) {
        this.username = username;
        this.sessionid = sessionid;
        this.fileName = fileName;
        this.fileSize = fileSize;
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
        DecimalFormat df = new DecimalFormat("#.00");
        this.percent = df.format((double) transfered / (double) fileSize);
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
