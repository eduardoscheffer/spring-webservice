package com.eduardoscheffer.oopjavawebservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    @Setter(value = AccessLevel.NONE)
    @ManyToMany
    // O atributo joinColumns indica a chave estrangeira da entidade Product, e inverseJoinColumns indica a chave estrangeira da entidade Category:
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id")) // assossiacao N pra N
    private Set<Category> categories = new HashSet<>(); // Set para o Produto não ser de mais de uma categoria diferente

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }
}
