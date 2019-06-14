package com.initvingadores.gamebook.dto.document

import com.initvingadores.gamebook.model.File
import com.initvingadores.gamebook.model.Flow
import com.initvingadores.gamebook.model.Genre
import com.initvingadores.gamebook.model.Tag
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateDocumentDTO(
        val id: Long = 0L,

        @NotNull(message = "Título não pode ser nulo")
        @NotBlank(message = "Campo obrigatório")
        val title: String,

        @NotNull(message = "Resumo não pode ser nulo")
        @NotBlank(message = "Campo obrigatório")
        val overview: String,

        @NotNull(message = "Informe pelo menos um gênero")
        @NotBlank(message = "Campo obrigatório")
        val genres: MutableList<Genre>,

        val keyWords: Tag,

        val image: File?
)