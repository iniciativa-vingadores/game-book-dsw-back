package com.initvingadores.gamebook.repository

import com.initvingadores.gamebook.model.Flow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FlowRepository : JpaRepository<Flow, Long>