package com.eduardoscheffer.oopjavawebservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    @JsonIgnore // usado pra fazer o lazy loading, pois é uma associacao para muitos (n);
    @OneToMany(mappedBy = "client") // associacao 1 pra n. mapppedBy = nome da associação do outro lado (outra tabela);
    private List<Order> orders = new ArrayList<>();

    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
