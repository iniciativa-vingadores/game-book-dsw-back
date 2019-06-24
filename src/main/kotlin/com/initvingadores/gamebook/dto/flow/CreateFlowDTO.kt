package com.initvingadores.gamebook.dto.flow

import com.initvingadores.gamebook.model.Document
import com.initvingadores.gamebook.model.Flow
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateFlowDTO (
        val id: Long = 0L,

        @NotNull(message = "Trecho da história não pode ser nula")
        @NotBlank(message = "Campo obrigatório")
        val story: String,

        val document: Document,

        val decision1: Flow?,

        val decision2: Flow?
)

fun CreateFlowDTO.toFlow() : Flow =
        Flow(
                id,
                story,
                document,
                decision1,
                decision2)