package org.lauchcode.matchpoint.models.data;

import org.lauchcode.matchpoint.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
