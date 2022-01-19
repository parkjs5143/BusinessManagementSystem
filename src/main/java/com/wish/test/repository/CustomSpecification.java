package com.wish.test.repository;

import com.wish.test.entity.Custom;
import com.wish.test.entity.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomSpecification {
    private final CustomRepository customRepository;

    public static Specification<Custom> equalBusiNum(String busiNum) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("busiNum"), busiNum);
    }
    public static Specification<Custom> equalCustom(String custom) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("custom"), custom);
    }

    public List<Custom> searchCustomList(Header<CustomApiRequest> custom){
        Specification<Custom> spec = null;

        System.out.println(custom);

        if(custom.getData().getBusiNum() != null){

            // char타입 공백 맞추기
            String busiNum = custom.getData().getBusiNum();
            int busiNumLength = busiNum.length();
            for (int i = 0; i < 20-busiNumLength; i++) {
                busiNum += " ";
            }

            if(spec == null){
                spec = Specification.where(CustomSpecification.equalBusiNum(busiNum));
            } else {
                spec = spec.or(CustomSpecification.equalBusiNum(busiNum));
            }
        }

        if(custom.getData().getCustom() != null){
            if(spec == null){
                spec = Specification.where(CustomSpecification.equalCustom(custom.getData().getCustom().trim()));
            } else {
                spec = spec.or(CustomSpecification.equalCustom(custom.getData().getCustom().trim()));
            }
        }

        return customRepository.findAll(spec);
    }
}
