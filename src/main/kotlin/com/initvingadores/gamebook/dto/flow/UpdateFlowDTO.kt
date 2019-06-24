package com.initvingadores.gamebook.dto.flow

import com.initvingadores.gamebook.model.Document
import com.initvingadores.gamebook.model.Flow
import javax.validation.constraints.NotNull

data class UpdateFlowDTO (
        @NotNull(message = "Id não pode ser nulo")
        val id: Long,

        val story: String?,

        val decision1: Flow?,

        val decision2: Flow?
)

fun UpdateFlowDTO.toFlow(story: String, document: Document) : Flow =
        Flow(
                id,
                story,
                document,
                decision1,
                decision2)