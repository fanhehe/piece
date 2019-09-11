package com.fanhehe.codepiece.repository;

import com.fanhehe.codepiece.entity.PieceLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceLabelRepository extends JpaRepository<PieceLabel, Long> {
}
