package com.prasad.roostack.dto;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lgunti on 007, Dec 07.
 */

/*@Entity
@Table(name="todoFiles")*/
public class TodoFileDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fileId")
    private int fileId;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "fileType")
    private String fileType;

    @Column(name = "fileUploadedDate")
    private Date fileUploadDate;

    @Column(name = "secure")
    private boolean secure;


    @Lob
    @Column(name="fileContent", nullable=true,columnDefinition="BLOB")
    private byte[] fileContent;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
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
