package com.springboot.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.project.model.Pdboard;

public interface PdRepository extends CrudRepository<Pdboard, Long> {
}
