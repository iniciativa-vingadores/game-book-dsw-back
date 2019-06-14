package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.document.CreateDocumentDTO
import com.initvingadores.gamebook.dto.document.DetailDocumentDTO
import com.initvingadores.gamebook.dto.document.UpdateDocumentDTO

interface DocumentService {

    fun save(documentDTO: CreateDocumentDTO): DetailDocumentDTO

    fun list(size: Int, page: Long, query: String?): List<DetailDocumentDTO>

    fun detail(idDocument: Long): DetailDocumentDTO

    fun update(documentDTO: UpdateDocumentDTO): DetailDocumentDTO

    fun delete(idDocument: Long)
}