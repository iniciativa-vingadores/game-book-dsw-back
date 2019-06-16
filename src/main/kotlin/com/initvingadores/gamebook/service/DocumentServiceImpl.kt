package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.document.CreateDocumentDTO
import com.initvingadores.gamebook.dto.document.DetailDocumentDTO
import com.initvingadores.gamebook.dto.document.UpdateDocumentDTO
import com.initvingadores.gamebook.dto.document.toDocument
import com.initvingadores.gamebook.model.Document
import com.initvingadores.gamebook.model.Genre
import com.initvingadores.gamebook.model.Situation
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
            documentDTO.keyWords.forEach {
                tagRepository.findByName(it.name) ?: tagRepository.save(it)
            }
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
        val documentDB = getDocumentById(documentDTO.id)

        val document = documentDTO.toDocument(
                documentDTO.id,
                documentDTO.title ?: documentDB.title,
                documentDTO.overview ?: documentDB.overview,
                documentDTO.genres ?: documentDB.genre,
                documentDB.rate,
                documentDTO.image ?: documentDB.image,
                documentDB.owner,
                documentDTO.flow ?: documentDB.start)

        return documentRepository.save(document).toDetailDocumentDTO()
    }

    override fun delete(idDocument: Long) {
        val documentDB = getDocumentById(idDocument)

        val deletedDocument = Document(
                documentDB.id,
                documentDB.date,
                Situation.INACTIVE,
                documentDB.title,
                documentDB.overview,
                documentDB.rate,
                documentDB.genre,
                documentDB.keyWords,
                documentDB.start,
                documentDB.owner,
                documentDB.image)

        documentRepository.save(deletedDocument)
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