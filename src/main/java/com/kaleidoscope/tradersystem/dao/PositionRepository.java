package com.kaleidoscope.tradersystem.dao;

import com.kaleidoscope.tradersystem.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity,String> { }

