package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.document.CreateDocumentDTO
import com.initvingadores.gamebook.dto.document.DetailDocumentDTO
import com.initvingadores.gamebook.dto.document.UpdateDocumentDTO
import com.initvingadores.gamebook.model.Customer
import com.initvingadores.gamebook.model.Document

interface DocumentService {

    fun save(documentDTO: CreateDocumentDTO): DetailDocumentDTO

    fun list(size: Int, page: Int, title: String?, rate: Double?,
             genre: List<String>?, keywords: List<String>?, owner: Customer?): List<DetailDocumentDTO>

    fun detail(idDocument: Long): DetailDocumentDTO

    fun update(documentDTO: UpdateDocumentDTO): DetailDocumentDTO

    fun delete(idDocument: Long)

    fun getDocumentById(idDocument: Long) : Document
}