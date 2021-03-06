package com.example.guestbook.service;

import com.example.guestbook.dto.GuestbookDTO;
import com.example.guestbook.dto.PageRequestDTO;
import com.example.guestbook.dto.PageResultDTO;
import com.example.guestbook.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTests {
    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister(){
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("title test")
                .content("sample content")
                .writer("user0")
                .build();

        System.out.println(service.register(guestbookDTO));
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(2).size(10).build();
        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        System.out.println("prev:"+resultDTO.isPrev());
        System.out.println("next:"+resultDTO.isNext());
        System.out.println("total:"+resultDTO.getTotalPage());
        System.out.println("------------------------------------");
        for(GuestbookDTO guestbookDTO:resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
        System.out.println("====================================");
        resultDTO.getPageList().forEach(i-> System.out.println(i));
    }

    @Test
    public void testSearch(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).type("tc").keyword("한글").build();

        PageResultDTO<GuestbookDTO,Guestbook> resultDTO = service.getList(pageRequestDTO);

        System.out.println("Prev:"+resultDTO.isPrev());
        System.out.println("Next:"+resultDTO.isNext());
        System.out.println("Total:"+resultDTO.getTotalPage());
        System.out.println("------------------------------------");
        for(GuestbookDTO guestbookDTO:resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
        System.out.println("====================================");
        resultDTO.getPageList().forEach(i-> System.out.println(i));
    }
}
