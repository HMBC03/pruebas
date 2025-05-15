package com.trivalApi.CRUD.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.trivalApi.CRUD.models.Usuario.*;


public interface RolRepository extends JpaRepository<RolModel, Long> {
    Optional<RolModel> findByRolEnum(RolEnum rolEnum);
}
