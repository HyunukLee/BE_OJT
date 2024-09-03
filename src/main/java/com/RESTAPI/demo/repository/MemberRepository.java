package com.RESTAPI.demo.repository;

import com.RESTAPI.demo.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    private final EntityManager em;

    @Autowired
    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Member member) {
        em.persist(member);
    }

    public Optional<Member> findByName(String username) {
        List<Member> result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
        return result.stream().findAny();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m where", Member.class).getResultList();
    }
}