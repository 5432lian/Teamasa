package com.example.xlianxi_2.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class DbBean {
    @Id(autoincrement = true)
    private Long id;
    private String url;
    private String name;
    @Generated(hash = 1826658466)
    public DbBean(Long id, String url, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
    }
    @Generated(hash = 1953169116)
    public DbBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
