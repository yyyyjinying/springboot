package com.changgou.user.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_permission")
public class Permission implements Serializable {
    @Id
    @Column(name = "id")
    private String id; // id

    @Column(name = "code")
    private String code; // 权限标示

    @Column(name="description")
    private String description; // 描述

    @Column(name = "url")
    private String url; // 路径


    @Column(name = "p_tag")
    private String pTag;

    public String getpTag() {
        return pTag;
    }

    public void setpTag(String pTag) {
        this.pTag = pTag;
    }
//    public String getpTag() {
//        return pTag;
//    }
//
//    public void setpTag(String pTag) {
//        this.pTag = pTag;
//    }

//    private String p_tag;
//
//    public String getp_tag() {
//        return p_tag;
//    }
//
//    public void setp_tag(String p_tag) {
//        this.p_tag = p_tag;
//    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", pTag='" + pTag + '\'' +
                '}';
    }
}
