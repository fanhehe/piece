package com.fanhehe.codepiece.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fanhehe.codepiece.util.PageUtil;
import com.fanhehe.codepiece.util.result.IResult;
import com.fanhehe.codepiece.util.result.InvokeResult;
import com.fanhehe.codepiece.constant.LabelEnum;
import com.fanhehe.codepiece.service.PieceService;

import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Controller
public class TimelineController {

    @RequestMapping(method = RequestMethod.GET, value = "/timeline")
    public String timeline(Model model) {

        Page page = pieceService
            .queryPiecePage(LabelEnum.ALL, PageUtil.createPageable());

        model.addAttribute("page", page); //aaaaaaaaaaaaaaaaaaa

        return "timeline";
    }

    /**
     * timeline页面获取列表接口
     * @param label 类型A
     * @param pageNum 页数
     * @param pageSize 页大小
     * @return 分页查询结果
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = {"/api/timeline/list/{label}", "/api/timeline/list"})
    public IResult queryListByLabel(
        @PathVariable(required = false) String label
        , @RequestParam(required = false) Integer pageNum
        , @RequestParam(required = false) Integer pageSize) {

        Pageable pageable = PageUtil.createPageable(pageNum, pageSize);

        LabelEnum labelEnum = Optional
            .ofNullable(LabelEnum.getLabelByName(label))
            .orElse(LabelEnum.JAVA);

        Page page = pieceService.queryPiecePage(labelEnum, pageable);

        return InvokeResult.success(page);
    }

    private PieceService pieceService;

    private Logger logger = LoggerFactory.getLogger(TimelineController.class);

    public PieceService getPieceService() {
        return pieceService;
    }

    @Autowired
    public void setPieceService(PieceService pieceService) {
        this.pieceService = pieceService;
    }
}
