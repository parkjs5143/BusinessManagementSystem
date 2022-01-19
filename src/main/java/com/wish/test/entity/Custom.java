package com.wish.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Address;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@DynamicUpdate
@DynamicInsert
public class Custom {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)   // 자동증가
    @Column(name = "BUSI_NUM")
    private String busiNum;

    @Column(name = "CUSTOM")
    private String custom;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "CEO")
    private String ceo;

    @Column(name = "CHARGE_PERSON")
    private String chargePerson;

    @Column(name = "BUSI_CONDITION")
    private String busiCondition;

    @Column(name = "ITEM")
    private String item;

    @Column(name = "POST_NUM")
    private String postNum;

    @Column(name = "ADDR1")
    private String addr1;

    @Column(name = "ADDR2")
    private String addr2;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "HOMEPAGE")
    private String homepage;

    @Column(name = "CO_YN")
    private Long coYn;

    @Column(name = "FOREIGN_YN")
    private Long foreignYn;

    @Column(name = "TAX_YN")
    private Long taxYn;

    @Column(name = "COUNTRY_ENG")
    private String countryEng;

    @Column(name = "COUNTRY_KOR")
    private String countryKor;

    @Column(name = "SPECIAL_RELATION")
    private Long specialRelation;

    @Column(name = "TRADE_STOP")
    private Long tradeStop;

    @Column(name = "CONTRACT_PERIOD_S")
    private LocalDate contractPeriodS;

    @Column(name = "CONTRACT_PERIOD_E")
    private LocalDate contractPeriodE;

    @Column(name = "REGI_INFO_MAN")
    private String regiInfoMan;

    @Column(name = "REGI_INFO_DATE")
    private LocalDateTime regiInfoDate;

    @Column(name = "MODI_INFO_MAN")
    private String modiInfoMan;

    @Column(name = "MODI_INFO_DATE")
    private LocalDateTime modiInfoDate;
}

