package com.initvingadores.gamebook.dto.document

import com.initvingadores.gamebook.model.File
import com.initvingadores.gamebook.model.Flow
import com.initvingadores.gamebook.model.Genre
import com.initvingadores.gamebook.model.Tag
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateDocumentDTO(
        val id: Long = 0L,

        @NotNull
        @NotBlank(message = "Campo obrigatório")
        val title: String,

        @NotNull
        @NotBlank(message = "Campo obrigatório")
        val overview: String,

        @NotNull
        @NotBlank(message = "Campo obrigatório")
        val genres: MutableList<Genre>,

        val keyWords: Tag,

        val image: File?,

        @NotNull(message = "É necessário informar o fluxo inicial da história.")
        val flow: Flow
)