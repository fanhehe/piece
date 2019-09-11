package com.fanhehe.codepiece.service.impl;


import com.fanhehe.codepiece.entity.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fanhehe.codepiece.entity.Piece;
import com.fanhehe.codepiece.constant.LabelEnum;
import com.fanhehe.codepiece.service.PieceService;
import com.fanhehe.codepiece.util.result.IResult;
import com.fanhehe.codepiece.util.result.InvokeResult;

import com.fanhehe.codepiece.repository.LabelRepository;
import com.fanhehe.codepiece.repository.PieceRepository;
import com.fanhehe.codepiece.repository.PieceLabelRepository;

@Service
public class PieceServiceImpl implements PieceService {

    private Logger logger = LoggerFactory.getLogger(PieceService.class);

    /**
     * 根据代码片段ID获取片段信息
     * @param pieceId 片段ID
     * @return 获取信息
     */
    @Override
    public IResult queryPieceById(Long pieceId) {
        return pieceRepository.findById(pieceId)
                .map(InvokeResult::success)
                .orElse(InvokeResult.failure("未找到指定文章"));
    }

    /**
     * 分页查询piece列表
     * @param labelEnum 选择哪个label
     * @param pageable 分页选项
     * @return 分页查询结果
     */
    public Page<Piece> queryPiecePage(LabelEnum labelEnum, Pageable pageable) {
        return pieceRepository
                .findAll((root, criteriaQuery, criteriaBuilder) -> {

            if (labelEnum.equals(LabelEnum.ALL)) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder
                .and(criteriaBuilder.equal(root.get("title"), labelEnum.getName()));
//
//            CompoundSelection<Piece> compoundSelection = criteriaBuilder.construct(Piece.class, root.get("name"), root.get("content"), root.get("createdAt"));
//
//            criteriaQuery.multiselect(compoundSelection).where(piecePredicate);
//
//            return null;
                    }, pageable);
    }

    private LabelRepository labelRepository;
    private PieceRepository pieceRepository;
    private PieceLabelRepository pieceLabelRepository;

    public PieceRepository getPieceRepository() {
        return pieceRepository;
    }

    public LabelRepository getLabelRepository() {
        return labelRepository;
    }

    public PieceLabelRepository getPieceLabelRepository() {
        return pieceLabelRepository;
    }

    @Autowired
    public void setLabelRepository( LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Autowired
    public void setPieceRepository(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    @Autowired
    public void setPieceLabelRepository(PieceLabelRepository pieceLabelRepository) {
        this.pieceLabelRepository = pieceLabelRepository;
    }
}
