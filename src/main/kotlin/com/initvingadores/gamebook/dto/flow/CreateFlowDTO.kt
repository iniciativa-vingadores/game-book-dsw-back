package com.initvingadores.gamebook.dto.flow

import com.initvingadores.gamebook.model.Flow
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateFlowDTO (
        val id: Long = 0L,

        @NotNull
        @NotBlank(message = "Campo obrigatório")
        val story: String,

        val decision1: Flow?,

        val decision2: Flow?
)