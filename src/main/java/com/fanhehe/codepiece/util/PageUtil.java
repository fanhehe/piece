package com.fanhehe.codepiece.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageUtil {

    private static final int PAGE_SIZE_MIN = 5;
    private static final int PAGE_SIZE_MAX = 20;

    private static final int PAGE_NUM_DEFAULT = 0;
    private static final int PAGE_SIZE_DEFAULT = 20;

    public static Pageable createPageable() {
        return createPageable(PAGE_NUM_DEFAULT
            , PAGE_SIZE_DEFAULT
            , Sort.by(Sort.Direction.DESC, "id"));
    }

    public static Pageable createPageable(Integer pageNum, Integer pageSize) {
        return createPageable(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "id"));
    }

    public static Pageable createPageable(Integer pageNum, Integer pageSize, Sort sort) {
        return createPageable(pageNum, pageSize, sort, PAGE_NUM_DEFAULT, PAGE_SIZE_DEFAULT);
    }

    private static Pageable createPageable(Integer pageNum, Integer pageSize, Sort sort, int pageNumDefault, int pageSizeDefault) {

        pageNum = pageNum != null ? pageNum : pageNumDefault;
        pageSize = pageSize != null ? pageSize : pageSizeDefault;

        if (!(pageSize > PAGE_SIZE_MIN && pageSize <= PAGE_SIZE_MAX)) {
            pageSize = pageSizeDefault;
        }

        return PageRequest.of(pageNum, pageSize, sort);
    }
}
