package com.wish.test.controller;

import com.wish.test.entity.Custom;
import com.wish.test.entity.Header;
import com.wish.test.repository.CustomApiRequest;
import com.wish.test.repository.CustomApiResponse;
import com.wish.test.repository.CustomListApiResponse;
import com.wish.test.service.CustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomRestController extends CrudController<CustomApiRequest, CustomApiResponse, Custom> {
    private final CustomService customService;

    // 거래처 리스트
    @GetMapping("/api/list_custom")
    public List<CustomListApiResponse> list(){
        return customService.list();
    }

    // 조건검색
    @PostMapping("/api/search_list")
    public List<CustomListApiResponse> searchList(@RequestBody Header<CustomApiRequest> request) {
        return customService.searchList(request);
    }

    // 상세(사업자번호)
    @GetMapping("/api/read_custom/{busiNum}")
    public Header<CustomApiResponse> read(@PathVariable String busiNum){
        return customService.read(busiNum);
    }

    //등록
    @PostMapping("/api/regist_custom")
    public Header<CustomApiResponse> create(@RequestBody Header<CustomApiRequest> request) {
        return customService.create(request);
    }

    // 삭제
    @DeleteMapping("/api/delete_custom/{busiNum}")
    public Header<CustomApiResponse> delete(@PathVariable String busiNum){
        return customService.delete(busiNum);
    }

    // 정보 수정
    @PutMapping("/api/update_custom")
    public Header<CustomApiResponse> update(@RequestBody Header<CustomApiRequest> request) {
        return customService.update(request);
    }
}
