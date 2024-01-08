package com.mifel.apirest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mifel.apirest.model.Poke;


@Repository
public interface PokeRepository extends JpaRepository<Poke, Integer> {

}