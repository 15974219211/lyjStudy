package com.my.study.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@Table(name = "consult_content")
public class ConsultContent {

    @Id
    @Column(name = "id")
    public Integer id;
    @Column(name = "item_index")
    public Integer itemIndex;
    @Column(name = "content")
    public String content;
    @Column(name = "type")
    public String type;
    @Column(name = "state")
    public Integer state;

}
