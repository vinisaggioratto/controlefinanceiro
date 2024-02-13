package com.vs.cf.repository;

import com.vs.cf.entity.Installments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentsRepository extends JpaRepository<Installments, Long> {
}
