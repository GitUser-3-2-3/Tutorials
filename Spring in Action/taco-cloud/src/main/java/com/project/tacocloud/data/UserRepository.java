package com.project.tacocloud.data;

import com.project.tacocloud.model.TacoUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<TacoUser, Long> {
    TacoUser findUserByUsername(String username);
}
