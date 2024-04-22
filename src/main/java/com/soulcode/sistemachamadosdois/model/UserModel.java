package com.soulcode.sistemachamadosdois.model;

import com.soulcode.sistemachamadosdois.dtos.RequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type")
public class UserModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id") // Nome da coluna na tabela 'TB_USERS' que armazena o id do role
    private Role role;

    private boolean isActive;

    @CreationTimestamp
    private Instant creationTimestamp;

    public boolean isLoginCorrect(RequestDTO requestDto, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(requestDto.password(), this.password);
    }

}
