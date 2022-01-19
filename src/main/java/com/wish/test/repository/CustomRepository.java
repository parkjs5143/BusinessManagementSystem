package com.wish.test.repository;

import com.wish.test.entity.Custom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CustomRepository extends JpaRepository<Custom, String>, JpaSpecificationExecutor<Custom> {

    @Override
    List<Custom> findAll(Specification<Custom> spec);

    // 사업자번호
    Custom findByBusiNum(String busiNum);

    Optional<Custom> getByBusiNum(String busiNum);

    // 거래처명
    Optional<Custom> getByCustom(String custom);

    // 사업자번호 like
    @Query("SELECT m FROM Custom AS m WHERE m.busiNum like %?1%")
    List<Object[]> findAllByBusiNumLike(String busiNum);

    // 사업자번호 또는 거래처명
    List<Custom> findByBusiNumContainingOrCustomContaining(String busiNum, String Custom);
}
