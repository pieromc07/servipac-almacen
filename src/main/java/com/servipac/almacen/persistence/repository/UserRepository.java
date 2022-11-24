package com.servipac.almacen.persistence.repository;

import com.servipac.almacen.persistence.model.Role;
import com.servipac.almacen.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    List<User> findAllByStatusIsTrue();
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<User> findByRole(Role role);
    List<User> findAllByStatusIsFalse();


}
