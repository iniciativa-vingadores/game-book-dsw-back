package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.document.CreateDocumentDTO
import com.initvingadores.gamebook.dto.document.DetailDocumentDTO
import com.initvingadores.gamebook.dto.document.UpdateDocumentDTO
import com.initvingadores.gamebook.dto.document.toDocument
import com.initvingadores.gamebook.model.*
import com.initvingadores.gamebook.repository.DocumentRepository
import com.initvingadores.gamebook.repository.TagRepository
import com.initvingadores.gamebook.system.exception.NotFoundException
import com.initvingadores.gamebook.system.getIdUserLogged
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
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

    override fun list(size: Int, page: Int, title: String?,
                      rate: Double?, genre: List<String>?, keywords: List<String>?, owner: Customer?)
            : List<DetailDocumentDTO> {
        val currentPage = documentRepository.findAll(PageRequest.of(page, size))

        var filteredList : List<Document> = currentPage.content
        genre?.let { genres ->
            genres.forEach { value ->
                filteredList = filteredList.filter { document ->
                    document.genre.any { it.name == value}
                }
            }
        }

        keywords?.let { keys ->
            keys.forEach { key ->
                filteredList = filteredList.filter { document ->
                    document.keyWords.any { it.name == key}
                }
            }
        }


        return filteredList
                .filter {document ->
                    document.situation == Situation.ACTIVE
                            && title?.let{ document.title == it } ?: true
                            && rate?.let{ document.rate == it } ?: true
                            && owner?.let{ document.owner.id == it.id } ?: true
                }
                .map { it.toDetailDocumentDTO() }
                .sortedBy { it.date }
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