package com.fanhehe.codepiece.service;

import com.fanhehe.codepiece.constant.LabelEnum;
import com.fanhehe.codepiece.entity.Piece;
import com.fanhehe.codepiece.util.result.IResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PieceService {
    IResult queryPieceById(Long pieceId);
    Page<Piece> queryPiecePage(LabelEnum labelEnum, Pageable pageable);
}
