package com.soulcode.sistemachamadosdois.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@DiscriminatorValue("technician")
public class Technician extends UserModel {

    private String department;
    private String phone;

}
