package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.flow.CreateFlowDTO
import com.initvingadores.gamebook.dto.flow.DetailFlowDTO
import com.initvingadores.gamebook.dto.flow.UpdateFlowDTO

interface FlowService {

    fun save(idDocument: Long, flowDTO: CreateFlowDTO): DetailFlowDTO

    fun list(idDocument: Long, size: Int, page: Int): List<DetailFlowDTO>

    fun detail(idDocument: Long, idFlow: Long): DetailFlowDTO

    fun update(idDocument: Long, flowDTO: UpdateFlowDTO): DetailFlowDTO

    fun delete(idDocument: Long, idFlow: Long)
}