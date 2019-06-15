package com.initvingadores.gamebook.model

import com.initvingadores.gamebook.dto.customer.DetailCustomerDTO
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

        @Column(unique = true)
        val email: String,

        @Column
        val password: String,

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        @JoinColumn
        val image: File?
)

fun Customer.toDetailCustomerDTO() : DetailCustomerDTO =
        DetailCustomerDTO(id, name, email, situation, image)