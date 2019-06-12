package com.initvingadores.gamebook.controller

import com.initvingadores.gamebook.service.FlowService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("documents/{idDocument}/flows")
class FlowController {

    @Autowired
    lateinit var flowService: FlowService

    //TODO(): rotas de fluxo da história
}