package com.initvingadores.gamebook.model

import javax.persistence.*

@Entity
@Table
data class Customer (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column
        val id: Long,

        @Column(nullable = false)
        @Enumerated
        val situation: Situation = Situation.ACTIVE,

        @Column(length = 300)
        val name: String,

        @Column
        val email: String,

        @Column
        val password: String,

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        @JoinColumn
        val image: File?
)