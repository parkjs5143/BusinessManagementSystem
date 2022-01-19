package com.wish.test.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountApiResponse {
    private String busiNum;
    private String factory;
    private String tradeBank;
    private String accountNum;
}
