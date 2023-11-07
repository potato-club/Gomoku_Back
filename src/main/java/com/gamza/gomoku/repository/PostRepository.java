package com.gamza.gomoku.repository;

import com.gamza.gomoku.entity.PostEntity;
import com.gamza.gomoku.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Page<PostEntity> findByAllOrderByIdDesc(Pageable pageable);
}
