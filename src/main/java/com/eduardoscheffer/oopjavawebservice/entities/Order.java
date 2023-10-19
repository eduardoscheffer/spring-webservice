package com.eduardoscheffer.oopjavawebservice.entities;

import com.eduardoscheffer.oopjavawebservice.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @ManyToOne // relacao n pra 1
    @JoinColumn(name = "client_id")
    private User client; // associacao

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "id.order") // assosiacao 1 pra n com OrderItem
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // mapeando as duas entidades para ter o mesmo id
    private Payment payment;

    public Order(Long id, Instant moment, User client, OrderStatus orderStatus) {
        this.id = id;
        this.moment = moment;
        this.client = client;
        this.orderStatus = orderStatus;
    }
}
