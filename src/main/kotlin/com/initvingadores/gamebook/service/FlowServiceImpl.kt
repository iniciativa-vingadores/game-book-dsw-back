package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.repository.FlowRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FlowServiceImpl : FlowService {

    @Autowired
    lateinit var flowRepository: FlowRepository
}