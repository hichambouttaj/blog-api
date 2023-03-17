package com.bojji.blogapi.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public class SortUtil {
    public static PageRequest buildPageRequest(Integer pageNumber,
                                         Integer pageSize,
                                         String sortField,
                                         String sortDirection) {
        int queryPageNumber;
        int queryPageSize;

        if(pageNumber != null && pageNumber > 0){
            queryPageNumber = pageNumber - 1;
        }else{
            queryPageNumber = Constants.DEFAULT_PAGE_NUMBER;
        }

        if(pageSize == null){
            queryPageSize = Constants.DEFAULT_PAGE_SIZE;
        }else{
            if(pageSize > 1000) {
                queryPageSize = 1000;
            }else{
                queryPageSize = pageSize;
            }
        }

        if(!StringUtils.hasText(sortField)) {
            sortField = Constants.DEFAULT_SORT_FIELD;
        }

        Sort sort = Sort.by(sortField);

        if(StringUtils.hasText(sortDirection)) {
            if(sortDirection.equalsIgnoreCase(Sort.Direction.DESC.name())) {
                sort = Sort.by(sortField).descending();
            }
        }

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }
}
