package com.wish.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@DynamicUpdate
@DynamicInsert
public class Account {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)   // 자동증가
    @Column(name = "BUSI_NUM")
    private String busiNum;

    @Column(name = "FACTORY")
    private String factory;

    @Column(name = "TRADE_BANK")
    private String tradeBank;

    @Column(name = "ACCOUNT_NUM")
    private String accountNum;
}

