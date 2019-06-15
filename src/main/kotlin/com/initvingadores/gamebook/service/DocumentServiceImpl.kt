package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.document.CreateDocumentDTO
import com.initvingadores.gamebook.dto.document.DetailDocumentDTO
import com.initvingadores.gamebook.dto.document.UpdateDocumentDTO
import com.initvingadores.gamebook.dto.document.toDocument
import com.initvingadores.gamebook.model.Document
import com.initvingadores.gamebook.model.Genre
import com.initvingadores.gamebook.model.toDetailDocumentDTO
import com.initvingadores.gamebook.repository.DocumentRepository
import com.initvingadores.gamebook.repository.TagRepository
import com.initvingadores.gamebook.system.exception.NotFoundException
import com.initvingadores.gamebook.system.getIdUserLogged
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DocumentServiceImpl : DocumentService {

    @Autowired
    lateinit var documentRepository: DocumentRepository

    @Autowired
    lateinit var customerService: CustomerServiceImpl

    @Autowired
    lateinit var tagRepository: TagRepository

    @Autowired
    lateinit var genreService: GenreService

    override fun save(documentDTO: CreateDocumentDTO): DetailDocumentDTO {
        if (documentDTO.keyWords.isNotEmpty()) {
            tagRepository.saveAll(documentDTO.keyWords)
        }

        checkGenres(documentDTO.genres)

        return documentRepository.save(
                documentDTO.toDocument(
                        customerService.getCustomerById(
                                        getIdUserLogged()!!)
                )).toDetailDocumentDTO()
    }

    override fun list(size: Int, page: Long, query: String?): List<DetailDocumentDTO> {
        TODO("not implemented")
    }

    override fun detail(idDocument: Long): DetailDocumentDTO =
            getDocumentById(idDocument).toDetailDocumentDTO()

    override fun update(documentDTO: UpdateDocumentDTO): DetailDocumentDTO {
        TODO("not implemented")
    }

    override fun delete(idDocument: Long) {
        TODO("not implemented")
    }

    private fun checkGenres(genres: List<Genre>) {
        genres.forEach {
            genreService.detail(it.id)
        }
    }

    private fun getDocumentById (idDocument: Long) : Document {
        return documentRepository.findById(idDocument)
                .orElseThrow { throw NotFoundException("História não encontrada.") }
    }
}