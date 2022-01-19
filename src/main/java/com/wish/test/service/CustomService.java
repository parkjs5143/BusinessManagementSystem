package com.wish.test.service;

import com.sun.tools.jconsole.JConsoleContext;
import com.wish.test.entity.Account;
import com.wish.test.entity.Custom;
import com.wish.test.entity.Header;
import com.wish.test.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomService extends BaseService<CustomApiRequest, CustomApiResponse, Custom> {
    @Autowired
    private final CustomRepository customRepository;
    private final AccountRepository accountRepository;
    private final CustomSpecification customSpecification;

    // 등록
    @Transactional
    public Header<CustomApiResponse> create(Header<CustomApiRequest> request) {
        CustomApiRequest customApiRequest = request.getData();

        Custom custom = Custom.builder()
                .busiNum(customApiRequest.getBusiNum())
                .custom(customApiRequest.getCustom())
                .shortName(customApiRequest.getShortName())
                .ceo(customApiRequest.getCeo())
                .chargePerson(customApiRequest.getChargePerson())
                .busiCondition(customApiRequest.getBusiCondition())
                .item(customApiRequest.getItem())
                .postNum(customApiRequest.getPostNum())
                .addr1(customApiRequest.getAddr1())
                .addr2(customApiRequest.getAddr2())
                .tel(customApiRequest.getTel())
                .fax(customApiRequest.getFax())
                .homepage(customApiRequest.getHomepage())
                .coYn(customApiRequest.getCoYn())
                .foreignYn(customApiRequest.getForeignYn())
                .taxYn(customApiRequest.getTaxYn())
                .countryEng(customApiRequest.getCountryEng())
                .countryKor(customApiRequest.getCountryKor())
                .specialRelation(customApiRequest.getSpecialRelation())
                .tradeStop(customApiRequest.getTradeStop())
                .contractPeriodS(customApiRequest.getContractPeriodS())
                .contractPeriodE(customApiRequest.getContractPeriodE())
                .regiInfoMan(customApiRequest.getRegiInfoMan())
                .regiInfoDate(customApiRequest.getRegiInfoDate())
                .build();
        Custom custom1 = baseRepository.save(custom);

        Account account = Account.builder()
                .busiNum(customApiRequest.getBusiNum())
                .factory(customApiRequest.getFactory())
                .tradeBank(customApiRequest.getTradeBank())
                .accountNum(customApiRequest.getAccountNum())
                .build();
        Account account1 = accountRepository.save(account);

        return Header.OK(response(custom1, account1));
    }

    // 수정
    @Transactional
    public Header<CustomApiResponse> update(Header<CustomApiRequest> request) {
        CustomApiRequest customApiRequest = request.getData();

        // char타입 공백 맞추기
        String busiNum = customApiRequest.getBusiNum();
        int busiNumLenght = customApiRequest.getBusiNum().length();
        for (int i = 0; i < 20-busiNumLenght; i++) {
            busiNum += " ";
        }

        Custom custom = Custom.builder()
                .busiNum(busiNum)
                .custom(customApiRequest.getCustom())
                .shortName(customApiRequest.getShortName())
                .ceo(customApiRequest.getCeo())
                .chargePerson(customApiRequest.getChargePerson())
                .busiCondition(customApiRequest.getBusiCondition())
                .item(customApiRequest.getItem())
                .postNum(customApiRequest.getPostNum())
                .addr1(customApiRequest.getAddr1())
                .addr2(customApiRequest.getAddr2())
                .tel(customApiRequest.getTel())
                .fax(customApiRequest.getFax())
                .homepage(customApiRequest.getHomepage())
                .coYn(customApiRequest.getCoYn())
                .foreignYn(customApiRequest.getForeignYn())
                .taxYn(customApiRequest.getTaxYn())
                .countryEng(customApiRequest.getCountryEng())
                .countryKor(customApiRequest.getCountryKor())
                .specialRelation(customApiRequest.getSpecialRelation())
                .tradeStop(customApiRequest.getTradeStop())
                .contractPeriodS(customApiRequest.getContractPeriodS())
                .contractPeriodE(customApiRequest.getContractPeriodE())
                .regiInfoMan(customApiRequest.getRegiInfoMan())
                .regiInfoDate(customApiRequest.getRegiInfoDate())
                .contractPeriodE(customApiRequest.getContractPeriodE())
                .modiInfoMan(customApiRequest.getModiInfoMan())
                .modiInfoDate(customApiRequest.getModiInfoDate())
                .build();
        Custom custom1 = baseRepository.save(custom);

        Account account = Account.builder()
                .busiNum(busiNum)
                .factory(customApiRequest.getFactory())
                .tradeBank(customApiRequest.getTradeBank())
                .accountNum(customApiRequest.getAccountNum())
                .build();
        Account account1 = accountRepository.save(account);

        return Header.OK(response(custom1, account1));
    }

    public CustomApiResponse response(Custom custom, Account account){
        CustomApiResponse customApiResponse = CustomApiResponse.builder()
                .busiNum(custom.getBusiNum())
                .custom(custom.getCustom()==null?custom.getCustom():custom.getCustom().trim())
                .shortName(custom.getShortName()==null?custom.getShortName():custom.getShortName().trim())
                .ceo(custom.getCeo()==null?custom.getCeo():custom.getCeo().trim())
                .chargePerson(custom.getChargePerson()==null?custom.getChargePerson():custom.getChargePerson().trim())
                .busiCondition(custom.getBusiCondition()==null?custom.getBusiCondition():custom.getBusiCondition().trim())
                .item(custom.getItem()==null?custom.getItem():custom.getItem().trim())
                .postNum(custom.getPostNum()==null?custom.getPostNum():custom.getPostNum().trim())
                .addr1(custom.getAddr1()==null?custom.getAddr1():custom.getAddr1().trim())
                .addr2(custom.getAddr2()==null?custom.getAddr2():custom.getAddr2().trim())
                .tel(custom.getTel()==null?custom.getTel():custom.getTel().trim())
                .fax(custom.getFax()==null?custom.getFax():custom.getFax().trim())
                .homepage(custom.getHomepage()==null?custom.getHomepage():custom.getHomepage().trim())
                .coYn(custom.getCoYn())
                .foreignYn(custom.getForeignYn())
                .taxYn(custom.getTaxYn())
                .countryEng(custom.getCountryEng()==null?custom.getCountryEng():custom.getCountryEng().trim())
                .countryKor(custom.getCountryKor()==null?custom.getCountryKor():custom.getCountryKor().trim())
                .specialRelation(custom.getSpecialRelation())
                .tradeStop(custom.getTradeStop())
                .contractPeriodS(custom.getContractPeriodS())
                .contractPeriodE(custom.getContractPeriodE())
                .regiInfoMan(custom.getRegiInfoMan()==null?custom.getRegiInfoMan():custom.getRegiInfoMan().trim())
                .regiInfoDate(custom.getRegiInfoDate())
                .modiInfoMan(custom.getModiInfoMan()==null?custom.getModiInfoMan():custom.getModiInfoMan().trim())
                .modiInfoDate(custom.getModiInfoDate())
                .factory(account.getFactory()==null?account.getFactory():account.getFactory().trim())
                .tradeBank(account.getTradeBank()==null?account.getTradeBank():account.getTradeBank().trim())
                .accountNum(account.getAccountNum()==null?account.getAccountNum():account.getAccountNum().trim())
                .build();
        return customApiResponse;
    }

    // 디테일
    @Transactional
    public Header<CustomApiResponse> read(String busiNum){
        // char타입 공백 맞춰주기
        if(busiNum.length() != 20){
            int busiNumLength = 20-busiNum.length();
            for (int i = 0; i < busiNumLength; i++) {
                busiNum += " ";
            }
        }
        Custom custom = customRepository.findByBusiNum(busiNum);
        Account account = accountRepository.findByBusiNum(busiNum);

        CustomApiResponse customApiResponse = response(custom, account);
        return Header.OK(customApiResponse);
    }

    // 조건검색
    public List<CustomListApiResponse> searchList (Header<CustomApiRequest> request){
        List<Custom> customerList = customSpecification.searchCustomList(request);

        List<CustomListApiResponse> customListApiResponseList = customerList.stream()
                .map(custom -> {
                    CustomListApiResponse customListApiResponse = CustomListApiResponse.builder()
                            .busiNum(custom.getBusiNum())
                            .custom(custom.getCustom())
                            .build();
                    return customListApiResponse;
                }).collect(Collectors.toList());
        return customListApiResponseList;
    }

    // like 검색
    public List<CustomApiResponse> searchLike (Header<CustomApiRequest> request){
        return null;
    }

    // 삭제
    public Header delete(String busiNum){
        // char타입 공백 맞춰주기
        if(busiNum.length() != 20){
            int busiNumLength = 20-busiNum.length();
            for (int i = 0; i < busiNumLength; i++) {
                busiNum += " ";
            }
        }
        Optional<Custom> optionalCustom = customRepository.getByBusiNum(busiNum);
        Optional<Account> optionalAccount = accountRepository.getByBusiNum(busiNum);

        return optionalCustom.map(custom -> {
            baseRepository.delete(custom);
            return optionalAccount.map(account -> {
                accountRepository.delete(account);
                return Header.OK();
            }).orElseGet(()->Header.ERROR("계좌정보 없음"));
        }).orElseGet(() -> Header.ERROR("고객정보 없음"));
    }

    // 거래처 리스트
    public List<CustomListApiResponse> list (){
        List<Custom> customList = customRepository.findAll();
        List<CustomListApiResponse> customListApiResponseList = customList.stream()
                .map(custom -> {
                    CustomListApiResponse customListApiResponse = CustomListApiResponse.builder()
                            .busiNum(custom.getBusiNum())
                            .custom(custom.getCustom())
                            .build();
                    return customListApiResponse;
                }).collect(Collectors.toList());
        return customListApiResponseList;
    }
}
