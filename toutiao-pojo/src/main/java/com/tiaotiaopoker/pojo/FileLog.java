package com.tiaotiaopoker.pojo;

import com.tiaotiaopoker.StringUtils;

import java.util.Date;

public class FileLog {
    private String id;

    private String userId;

    private Date addTime;

    private String filePath;

    private String fileName;

    private String fileUrl;

    private Integer isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 自定义方法
     */
    public static final FileLog genFileLog(String fileName,
                                           String savePath,
                                           String fileUrl,
                                           String userId) {
        FileLog fileLog = new FileLog();
        fileLog.setFileName( fileName );
        fileLog.setFilePath( savePath );
        fileLog.setFileUrl( fileUrl );
        fileLog.setUserId( userId );
        fileLog.setId( StringUtils.generateShortUUID() );
        return fileLog;
    }
}