package com.my.study.entity;

import lombok.Data;

@Data
//@TableName("consult_content")
public class ConsultContent {

 //指定主键
   // @TableId
    public Integer id;
    //指定列名
   // @TableField("item_index")
    public Integer itemIndex;
    public String content;
    public String type;
    public Integer state;

    //表示不与列进行映射
  //  @TableField(exist = false)
  //  private  String remark;

}
