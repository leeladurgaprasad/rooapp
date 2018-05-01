package com.prasad.roostack.bean;

import java.util.Date;

/**
 * Created by lgunti on 007, Dec 07.
 */
public class TodoFile {

    private int fileId;

    private String fileName;

    private String fileType;

    private byte[] fileContent;

    private Date fileUploadDate;

    private boolean secure;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public Date getFileUploadDate() {
        return fileUploadDate;
    }

    public void setFileUploadDate(Date fileUploadDate) {
        this.fileUploadDate = fileUploadDate;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }
}
