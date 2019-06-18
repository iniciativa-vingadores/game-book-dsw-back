package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.flow.CreateFlowDTO
import com.initvingadores.gamebook.dto.flow.DetailFlowDTO
import com.initvingadores.gamebook.dto.flow.UpdateFlowDTO
import com.initvingadores.gamebook.repository.FlowRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FlowServiceImpl : FlowService {

    @Autowired
    lateinit var flowRepository: FlowRepository

    override fun save(idDocument: Long, flowDTO: CreateFlowDTO): DetailFlowDTO {
        TODO("not implemented")
    }

    override fun list(idDocument: Long, size: Int, page: Int): List<DetailFlowDTO> {
        TODO("not implemented")
    }

    override fun detail(idDocument: Long, idFlow: Long): DetailFlowDTO {
        TODO("not implemented")
    }

    override fun update(idDocument: Long, flowDTO: UpdateFlowDTO): DetailFlowDTO {
        TODO("not implemented")
    }

    override fun delete(idDocument: Long, idFlow: Long) {
        TODO("not implemented")
    }
}