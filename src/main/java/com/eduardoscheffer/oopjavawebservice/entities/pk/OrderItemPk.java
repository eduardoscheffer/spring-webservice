package com.eduardoscheffer.oopjavawebservice.entities.pk;

import com.eduardoscheffer.oopjavawebservice.entities.Order;
import com.eduardoscheffer.oopjavawebservice.entities.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
/* Esta classe é destinada a ser incorporada em outra classe. Em outras palavras, os atributos order e product desta classe podem ser incorporados em uma classe maior,
possivelmente como parte de uma chave primária composta ou como parte de uma entidade maior que representa um item de pedido em algum sistema. */
public class OrderItemPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPk that = (OrderItemPk) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
