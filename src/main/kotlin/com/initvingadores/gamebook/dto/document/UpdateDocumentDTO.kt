package com.initvingadores.gamebook.dto.document

import com.initvingadores.gamebook.model.*
import java.awt.datatransfer.ClipboardOwner
import javax.validation.constraints.NotNull

data class UpdateDocumentDTO (
        @NotNull(message = "Id não pode ser nulo")
        val id: Long,
        val title: String?,
        val overview: String?,
        val genres: List<Genre>?,
        val image: File?,
        val flow: Flow?
)

fun UpdateDocumentDTO.toDocument(
        id: Long,
        title: String,
        overview: String,
        genres: List<Genre>,
        rate: Double,
        image: File?,
        owner: Customer,
        flow: Flow?) : Document =
        Document(
                id = id,
                title = title,
                overview = overview,
                genre = genres,
                image = image,
                rate = rate,
                start = flow,
                owner = owner)