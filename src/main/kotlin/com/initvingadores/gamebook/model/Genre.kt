package com.initvingadores.gamebook.model

import com.initvingadores.gamebook.dto.genre.DetailGenreDTO
import javax.persistence.*

@Entity
@Table
data class Genre (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column
        val id: Long,

        @Column(nullable = false, length = 100)
        val name: String
)

fun Genre.toDetailGenreDTO () : DetailGenreDTO =
        DetailGenreDTO(id, name)