package com.initvingadores.gamebook.model

import javax.persistence.*


@Entity
@Table
data class Tag(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column
        val id: Long,

        @Column(nullable = false, length = 100)
        val name: String
)