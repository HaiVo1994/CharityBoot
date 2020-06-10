package org.haivo_charity.charity.model.support;

import org.haivo_charity.charity.model.Vote;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;

public class VoteFileUpload extends Vote implements Serializable {
    private static final long serialVersionUID = 1L;
    private MultipartFile[] fileDatas;
    public VoteFileUpload(){
    }
    public MultipartFile[] getFileDatas() {
        return fileDatas;
    }
    public void setFileDatas(MultipartFile[] fileDatas) {
        this.fileDatas = fileDatas;
    }
}
