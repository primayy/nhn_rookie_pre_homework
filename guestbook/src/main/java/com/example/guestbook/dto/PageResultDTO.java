package com.example.guestbook.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO,EN> {
    //DTO리스트
    private List<DTO> dtoList;

    //총 페이지 번호
    private int totalPage;

    //현재 페이지 번호
    private  int page;

    //목록 사이즈
    private int size;

    private int start,end;

    private boolean prev,next;

    private List<Integer> pageList;

    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn){
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() +1;
        this.size = pageable.getPageSize();

        int tmpEnd = (int)(Math.ceil(page/10.0))*10;

        start = tmpEnd - 9;
        prev = start>1;
        end = totalPage > tmpEnd ? tmpEnd : totalPage;

        next = totalPage > tmpEnd;

        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
    }
}
