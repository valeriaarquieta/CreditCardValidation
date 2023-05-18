package com.example.levelup.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.levelup.controller.CreditCardController.CreditCardData;

import java.util.Optional;



@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardData, Long>{
	Optional<CreditCardData>findByNombre(String pan);
	}

