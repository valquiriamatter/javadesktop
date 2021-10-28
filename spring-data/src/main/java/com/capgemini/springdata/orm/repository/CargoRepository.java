package com.capgemini.springdata.orm.repository;


import com.capgemini.springdata.orm.entity.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository                                           //tipo classe que vai manipular e tipo do id da classe
public interface CargoRepository extends CrudRepository<Cargo, Integer> {




}
