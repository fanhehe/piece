package com.fanhehe.codepiece.repository;

import com.fanhehe.codepiece.entity.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Long>, JpaSpecificationExecutor<Piece> {
}
