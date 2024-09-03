package com.RESTAPI.demo.domain;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Calculation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String formula;
    private boolean isCorrect;

    @Builder
    public Calculation(String username, String formula, boolean isCorrect) {
        this.username = username;
        this.formula = formula;
        this.isCorrect = isCorrect;
    }
}

