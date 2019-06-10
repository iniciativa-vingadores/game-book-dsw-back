package com.initvingadores.gamebook.model

import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList


@Entity
@Table
data class Document (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column
        val id: Long,

        @Temporal(TemporalType.DATE)
        @Column
        val date: Date = Date(),

        @Column(nullable = false)
        @Enumerated
        val situation: Situation = Situation.ACTIVE,

        @Column(nullable = false, length = 300)
        val title: String,

        @Column(length = 3000)
        val overview: String,

        @Column
        val rate: Double,

        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn
        val genre: MutableList<Genre> = ArrayList(),

        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn
        val keyWords: MutableList<Tag> = ArrayList(),

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        @JoinColumn
        val start: Flow,

        @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn
        val owner: Customer,

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        @JoinColumn
        val image: File
)