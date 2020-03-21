package com.my.study.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TJobHistory implements Serializable {
    private Integer id;

    private Integer userId;

    private String compName;

    private Integer years;

    private String title;

}