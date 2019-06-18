package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.flow.CreateFlowDTO
import com.initvingadores.gamebook.dto.flow.DetailFlowDTO
import com.initvingadores.gamebook.dto.flow.UpdateFlowDTO
import com.initvingadores.gamebook.dto.flow.toFlow
import com.initvingadores.gamebook.model.Flow
import com.initvingadores.gamebook.model.Situation
import com.initvingadores.gamebook.model.toDetailFlowDTO
import com.initvingadores.gamebook.repository.FlowRepository
import com.initvingadores.gamebook.system.exception.DataException
import com.initvingadores.gamebook.system.exception.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FlowServiceImpl : FlowService {

    @Autowired
    lateinit var flowRepository: FlowRepository
    @Autowired
    lateinit var documentService: DocumentService

    override fun save(idDocument: Long, flowDTO: CreateFlowDTO): DetailFlowDTO {
        if (idDocument != flowDTO.document.id) {
            throw DataException("Id da história informado diferente da id da rota")
        }

        documentService.getDocumentById(idDocument)

        return flowRepository.save(flowDTO.toFlow()).toDetailFlowDTO()
    }

    override fun list(idDocument: Long, size: Int, page: Int): List<DetailFlowDTO> {
        val current = flowRepository.findAll()

        var filteredList = current.filter {
            it.situation == Situation.ACTIVE
                    && it.document.id == idDocument
        }

        val totalPages = (filteredList.size / size)
        filteredList = when (page) {
            totalPages -> filteredList.subList(page * size, (page * size) + filteredList.size % size)
            else -> filteredList.subList(page * size, ((page * size) + (size)))
        }

        return filteredList.map { it.toDetailFlowDTO() }
    }

    override fun detail(idDocument: Long, idFlow: Long): DetailFlowDTO =
            getFlowById(idFlow).toDetailFlowDTO()

    override fun update(idDocument: Long, flowDTO: UpdateFlowDTO): DetailFlowDTO {
        val flowDB = getFlowById(flowDTO.id)

        val updatedFlow = Flow(
                flowDB.id,
                flowDTO.story ?: flowDB.story,
                flowDB.document,
                flowDTO.decision1 ?: flowDB.decision1,
                flowDTO.decision2 ?: flowDB.decision2)

        return flowRepository.save(updatedFlow).toDetailFlowDTO()
    }

    override fun delete(idDocument: Long, idFlow: Long) {
        val flowDB = getFlowById(idFlow)

        val deletedFlow = Flow(
                flowDB.id,
                flowDB.story,
                flowDB.document,
                flowDB.decision1,
                flowDB.decision2,
                Situation.INACTIVE)

        flowRepository.save(deletedFlow)
    }

    private fun getFlowById(idFlow: Long): Flow =
            flowRepository.findById(idFlow)
                    .orElseThrow { throw NotFoundException("Fluxo de história não encontrada.") }
}