package com.initvingadores.gamebook.model

import com.initvingadores.gamebook.dto.flow.DetailFlowDTO
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

        @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn
        val document: Document,

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        @JoinColumn
        val decision1: Flow?,

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        @JoinColumn
        val decision2: Flow?,

        @Column(nullable = false)
        @Enumerated
        val situation: Situation = Situation.ACTIVE
)

fun Flow.toDetailFlowDTO() : DetailFlowDTO =
        DetailFlowDTO(
                id,
                story,
                document,
                decision1,
                decision2)