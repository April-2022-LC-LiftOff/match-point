package org.lauchcode.matchpoint.models.data;

import org.lauchcode.matchpoint.models.ERole;
import org.lauchcode.matchpoint.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);
}
