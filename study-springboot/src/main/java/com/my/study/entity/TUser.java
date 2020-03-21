package com.my.study.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TUser implements Serializable{
	
    private Integer id;

    private String userName;

    private String realName;

    private Byte sex;

    private String mobile;

    private String email;

    private String note;
    
    private List<TJobHistory> jobs ;
    private TPosition position;



}