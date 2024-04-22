package com.soulcode.sistemachamadosdois.repositories;

import com.soulcode.sistemachamadosdois.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
