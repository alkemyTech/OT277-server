package com.alkemy.ong.utils;

import com.alkemy.ong.dto.PageableResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageableUtils {

    public PageableResponse pageableUtils(Page<?> member, List<?> memberDTO, PageableResponse response, int page, int pageSize) {
        if(member.isLast()){
            response.setNextPage(null);
        }else {
            response.setNextPage("?page="+(page+1)+"&size="+(pageSize));
        }
        if (member.isFirst()){
            response.setPreviousPage(null);
        }else {
            response.setPreviousPage("?page="+(page == 0 ? page : page-1)+"&size="+(pageSize));
        }
        response.setContent(memberDTO);
        response.setPageNumber(member.getNumber());
        response.setPageSize(member.getSize());
        response.setTotalElements(member.getTotalElements());
        response.setTotalPages(member.getTotalPages());
        response.setLastPage(member.isLast());
        return response;
    }
}
