package com.dexingworld.hanfu.middleware.mail.bean;

import java.io.File;

/**
 * 邮件附件
 * Created by wangpeng on 2016/11/28.
 */
public class EmailAttachment {

    private String attachmentName;
    private File attachmentFile;

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public File getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(File attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public EmailAttachment(String attachmentName, File attachmentFile) {
        this.attachmentName = attachmentName;
        this.attachmentFile = attachmentFile;
    }

    public EmailAttachment(File attachmentFile) {
        this.attachmentFile = attachmentFile;
        String fileName = attachmentFile.getName();
        this.attachmentName = fileName;
    }
}
