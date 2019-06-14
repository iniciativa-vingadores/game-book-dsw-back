package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.document.CreateDocumentDTO
import com.initvingadores.gamebook.dto.document.DetailDocumentDTO
import com.initvingadores.gamebook.dto.document.UpdateDocumentDTO
import com.initvingadores.gamebook.repository.DocumentRepository
import com.initvingadores.gamebook.repository.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DocumentServiceImpl : DocumentService {

    @Autowired
    lateinit var documentRepository: DocumentRepository

    @Autowired
    lateinit var tagRepository: TagRepository

    override fun save(documentDTO: CreateDocumentDTO): DetailDocumentDTO {
        TODO("not implemented")
    }

    override fun list(size: Int, page: Long, query: String?): List<DetailDocumentDTO> {
        TODO("not implemented")
    }

    override fun detail(idDocument: Long): DetailDocumentDTO {
        TODO("not implemented")
    }

    override fun update(documentDTO: UpdateDocumentDTO): DetailDocumentDTO {
        TODO("not implemented")
    }

    override fun delete(idDocument: Long) {
        TODO("not implemented")
    }
}