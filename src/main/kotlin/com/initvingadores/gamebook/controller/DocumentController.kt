package com.initvingadores.gamebook.controller

import com.initvingadores.gamebook.service.DocumentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/documents")
class DocumentController {

    @Autowired
    lateinit var documentService: DocumentService

    //TODO(): rotas da história
}