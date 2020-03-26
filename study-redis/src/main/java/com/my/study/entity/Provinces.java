package com.my.study.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "yq_gw_province")
@Data
@Entity
public class Provinces implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", insertable = false, nullable = false)
  private String id;

  /**
   * 确诊人数
   */
  @Column(name = "confirmed_count")
  private String confirmedCount;

  /**
   * 治愈人数
   */
  @Column(name = "cured_count")
  private String curedCount;

  /**
   * 采集时间
   */
  @Column(name = "date_time")
  private Timestamp dateTime;

  /**
   * 死亡人数
   */
  @Column(name = "dead_count")
  private String deadCount;

  /**
   * 国外名称
   */
  @Column(name = "province_name")
  private String provinceName;

  
}