package com.initvingadores.gamebook.controller

import com.initvingadores.gamebook.dto.document.CreateDocumentDTO
import com.initvingadores.gamebook.dto.document.DetailDocumentDTO
import com.initvingadores.gamebook.dto.document.UpdateDocumentDTO
import com.initvingadores.gamebook.service.DocumentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/documents")
class DocumentController {

    @Autowired
    lateinit var documentService: DocumentService

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @PostMapping
    fun saveDocument(
            @Valid
            @RequestBody
            documentDTO: CreateDocumentDTO) : ResponseEntity<DetailDocumentDTO> {

        return ResponseEntity(documentService.save(documentDTO), HttpStatus.CREATED)
    }

    @GetMapping
    fun listDocument(
            @RequestParam(required = false, defaultValue = "20") size: Int,
            @RequestParam(required = false, defaultValue = "0") page: Long,
            @RequestParam(required = false) query: String?
    ) : ResponseEntity<List<DetailDocumentDTO>> {

        return ResponseEntity(documentService.list(size, page, query), HttpStatus.OK)
    }

    @GetMapping("/{idDocument}")
    fun detailDocument (@PathVariable idDocument: Long) : ResponseEntity<DetailDocumentDTO> {

        return ResponseEntity(documentService.detail(idDocument), HttpStatus.OK)
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @PutMapping
    fun updateDocument(
            @Valid
            @RequestBody
            documentDTO: UpdateDocumentDTO) : ResponseEntity<DetailDocumentDTO> {

        return ResponseEntity(documentService.update(documentDTO), HttpStatus.OK)
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @DeleteMapping("/{idDocument}")
    fun deleteDocument(@PathVariable idDocument: Long) : ResponseEntity<String> {

        documentService.delete(idDocument)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}