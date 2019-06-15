package com.initvingadores.gamebook.dto.document

import com.initvingadores.gamebook.model.*
import java.util.*

data class DetailDocumentDTO (
        val id: Long,
        val date: Date,
        val situation: Situation,
        val title: String,
        val overview: String,
        val rate: Double,
        val genres: List<Genre>,
        val keywords: List<Tag>,
        val owner: Customer,
        val start: Flow?,
        val image: File?
)