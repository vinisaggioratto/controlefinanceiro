package com.vs.cf.repository;

import com.vs.cf.entity.Inputs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputsRepository extends JpaRepository<Inputs, Long> {
}
