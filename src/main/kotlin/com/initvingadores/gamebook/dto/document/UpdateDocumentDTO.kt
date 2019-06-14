package com.initvingadores.gamebook.dto.document

import com.initvingadores.gamebook.model.File
import com.initvingadores.gamebook.model.Flow
import com.initvingadores.gamebook.model.Genre
import javax.validation.constraints.NotNull

data class UpdateDocumentDTO (
        @NotNull(message = "Id não pode ser nulo")
        val id: Long,
        val title: String?,
        val overview: String?,
        val genres: MutableList<Genre>?,
        val image: File?,
        val flow: Flow?
)