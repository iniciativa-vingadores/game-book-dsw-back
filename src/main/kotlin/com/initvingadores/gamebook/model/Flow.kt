package com.initvingadores.gamebook.model

import javax.persistence.*

@Entity
@Table
data class Flow (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column
        val id: Long,

        @Column(nullable = false)
        val story: String,

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        @JoinColumn
        val decision1: Flow,

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        @JoinColumn
        val decision2: Flow
)