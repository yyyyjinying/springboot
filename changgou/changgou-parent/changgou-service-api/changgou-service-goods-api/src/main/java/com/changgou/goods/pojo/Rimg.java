package com.changgou.goods.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="t_rimg")
public class Rimg implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//品牌id

    @Column(name = "path")
    private String path;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
