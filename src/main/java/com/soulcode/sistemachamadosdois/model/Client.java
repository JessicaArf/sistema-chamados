package com.soulcode.sistemachamadosdois.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@DiscriminatorValue("client") // Definindo o valor discriminador para esta classe
public class Client extends UserModel {

    private String endereco;

}