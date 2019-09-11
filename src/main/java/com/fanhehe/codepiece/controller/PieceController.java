package com.fanhehe.codepiece.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.fanhehe.codepiece.util.result.IResult;
import com.fanhehe.codepiece.service.PieceService;

@Controller
public class PieceController {

    @RequestMapping(method = RequestMethod.GET, value = "/piece/{pieceId}")
    public String piece(Model model,
                        @PathVariable(required = false) Long pieceId) {

        if (pieceId == null) {
            return "redirect:/timeline";
        }

        IResult result = pieceService.queryPieceById(pieceId);

        if (result.isFailure()) {
            return "redirect:/timeline";
        }

        model.addAttribute("data", result);

        return "piece";
    }

    private PieceService pieceService;

    private static Logger logger = LoggerFactory.getLogger(PieceController.class);

    public PieceService getPieceService() {
        return pieceService;
    }

    @Autowired
    public void setPieceService(PieceService pieceService) {
        this.pieceService = pieceService;
    }
}
