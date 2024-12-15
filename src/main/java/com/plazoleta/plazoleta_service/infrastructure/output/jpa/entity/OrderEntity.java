package com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Long idClient;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private RestaurantAndEmployeeEntity chef;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;
}