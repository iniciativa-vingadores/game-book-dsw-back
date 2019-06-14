package com.initvingadores.gamebook.controller

import com.initvingadores.gamebook.dto.flow.CreateFlowDTO
import com.initvingadores.gamebook.dto.flow.DetailFlowDTO
import com.initvingadores.gamebook.dto.flow.UpdateFlowDTO
import com.initvingadores.gamebook.service.FlowService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("documents/{idDocument}/flows")
class FlowController {

    @Autowired
    lateinit var flowService: FlowService

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @PostMapping
    fun saveFlow(
            @PathVariable idDocument: Long,
            @Valid
            @RequestBody
            flowDTO: CreateFlowDTO) : ResponseEntity<DetailFlowDTO> {

        return ResponseEntity(flowService.save(idDocument, flowDTO), HttpStatus.CREATED)
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @GetMapping
    fun listFlow(
            @PathVariable idDocument: Long,
            @RequestParam(required = false, defaultValue = "20") size: Int,
            @RequestParam(required = false, defaultValue = "0") page: Long,
            @RequestParam(required = false) query: String?
    ) : ResponseEntity<List<DetailFlowDTO>> {

        return ResponseEntity(flowService.list(idDocument, size, page, query),
                HttpStatus.OK)
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @GetMapping("/{idFlow}")
    fun detail(
            @PathVariable idDocument: Long,
            @PathVariable idFlow: Long) : ResponseEntity<DetailFlowDTO> {

        return ResponseEntity(flowService.detail(idDocument, idFlow), HttpStatus.OK)
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @PutMapping
    fun updateFlow(
            @PathVariable idDocument: Long,
            @Valid
            @RequestBody
            flowDTO: UpdateFlowDTO) : ResponseEntity<DetailFlowDTO> {

        return ResponseEntity(flowService.update(idDocument, flowDTO), HttpStatus.OK)
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @DeleteMapping("/{idFlow}")
    fun deleteFlow(
            @PathVariable idDocument: Long,
            @PathVariable idFlow: Long) : ResponseEntity<String> {

        flowService.delete(idDocument, idFlow)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}