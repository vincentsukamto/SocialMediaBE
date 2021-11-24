package com.instagramgaul.demo.common;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination<T> {
    private Integer currentPage = 1;
    private Integer total = 0;
    private Integer perPage = 0;
    private Integer lastPage = 0;
    private Integer from = 0;
    private Integer to = 0;
    private String nextPageUrl = null;
    private String prevPageUrl = null;
    private List<T> data = new ArrayList<>();

    @JsonIgnore
    @Autowired
    private PropertiesUtil env;

    public Pagination<T> convertPageable(Page<T> pageable, String endpoint){
        this.data = pageable.getContent();
        this.total = Integer.parseInt(Long.toString(pageable.getTotalElements()));
        this.currentPage = pageable.getNumber() + 1;
        this.perPage = pageable.getNumberOfElements();
        this.from = (pageable.getNumber() * 9) + 1;
        this.to = (pageable.getNumber() * 9) + 10;

        this.prevPageUrl =
                pageable.getNumber() == 0 ?
                        null :
                        PropertiesUtil.load("base.url") + ":" + PropertiesUtil.load("server.port") + endpoint + "?page=" + this.getCurrentPage();

        this.nextPageUrl =
                this.total <= this.to  ?
                        null :
                        PropertiesUtil.load("base.url") + ":" + PropertiesUtil.load("server.port") + endpoint + "?page=" + this.getCurrentPage() + 1;

        return this;
    }
}