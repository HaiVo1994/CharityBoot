package org.haivo_charity.charity.model.support;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;

public class FileUpload implements Serializable {
    private static final long serialVersionUID = 1L;
    private CommonsMultipartFile[] fileSData;
    public FileUpload(){
    }
    public CommonsMultipartFile[] getFileDatas() {
        return fileSData;
    }
    public void setFileDatas(CommonsMultipartFile[] fileSData) {
        this.fileSData = fileSData;
    }
}
