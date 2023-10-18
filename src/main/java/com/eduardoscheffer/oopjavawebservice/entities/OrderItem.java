package com.eduardoscheffer.oopjavawebservice.entities;

import com.eduardoscheffer.oopjavawebservice.entities.pk.OrderItemPk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

    @EmbeddedId
    private OrderItemPk id = new OrderItemPk(); // id composto que for uma classe auxiliar, deve-se instanciar
    private Integer quantity;
    private Double price;

    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        this.quantity = quantity;
        this.price = price;
        id.setOrder(order);
        id.setProduct(product);
    }
    @JsonIgnore
    /*A anotação @JsonIgnore é usada para evitar que o atributo order seja serializado quando um objeto OrderItem é convertido em formato JSON. Isso é feito para evitar uma associação em loop na serialização,
    pois a classe OrderItem tem uma referência para Order por meio do atributo order na classe OrderItemPk, e Order também pode ter uma lista de OrderItem.
    Sem a anotação @JsonIgnore, a serialização JSON resultaria em uma representação recursiva infinita, o que pode causar problemas de desempenho e estouro de pilha.*/
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

}
