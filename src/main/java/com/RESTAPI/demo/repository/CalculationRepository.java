package com.RESTAPI.demo.repository;

import com.RESTAPI.demo.domain.Calculation;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CalculationRepository {

    private final EntityManager em;

    @Autowired
    public CalculationRepository(EntityManager em) {
        this.em = em;
    }

    public void save (Calculation calculation) {
        em.persist(calculation);
    }

    public List<Calculation> findbyName(String username) {
        return em.createQuery("select c from Calculation c where c.username = :username", Calculation.class)
                .setParameter("username", username)
                .getResultList();
    }
}
