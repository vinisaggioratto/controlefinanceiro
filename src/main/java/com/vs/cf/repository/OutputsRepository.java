package com.vs.cf.repository;

import com.vs.cf.entity.Outputs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputsRepository extends JpaRepository<Outputs, Long> {
}
