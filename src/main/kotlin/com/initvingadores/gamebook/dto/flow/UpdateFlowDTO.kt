package com.initvingadores.gamebook.dto.flow

import com.initvingadores.gamebook.model.Flow
import javax.validation.constraints.NotNull

data class UpdateFlowDTO (
        @NotNull
        val id: Long,

        val story: String?,

        val decision1: Flow?,

        val decision2: Flow?
)