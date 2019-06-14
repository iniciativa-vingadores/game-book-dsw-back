package com.initvingadores.gamebook.dto.flow

import com.initvingadores.gamebook.model.Document
import com.initvingadores.gamebook.model.Flow

data class DetailFlowDTO (
        val id: Long,
        val story: String,
        val document: Document,
        val decision1: Flow?,
        val decision2: Flow?
)