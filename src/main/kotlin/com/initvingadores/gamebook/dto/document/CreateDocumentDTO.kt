package com.initvingadores.gamebook.dto.document

import com.initvingadores.gamebook.model.*
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
        val genres: List<Genre>,

        val keyWords: List<Tag> = ArrayList(0),

        val image: File?
)

fun CreateDocumentDTO.toDocument(owner: Customer, flow: Flow? = null) : Document =
        Document(
                id,
                title = title,
                overview = overview,
                genre = genres,
                keyWords = keyWords,
                image = image,
                owner = owner,
                start = flow,
                rate = 0.0)