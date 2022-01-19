package com.wish.test.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomApiRequest{
    private String busiNum;
    private String custom;
    private String shortName;
    private String ceo;
    private String chargePerson;
    private String busiCondition;
    private String item;
    private String postNum;
    private String addr1;
    private String addr2;
    private String tel;
    private String fax;
    private String homepage;
    private Long coYn;
    private Long foreignYn;
    private Long taxYn;
    private String countryEng;
    private String countryKor;
    private Long specialRelation;
    private Long tradeStop;
    private LocalDate contractPeriodS;
    private LocalDate contractPeriodE;
    private String regiInfoMan;
    private LocalDateTime regiInfoDate;
    private String modiInfoMan;
    private LocalDateTime modiInfoDate;

    private String factory;
    private String tradeBank;
    private String accountNum;
}
