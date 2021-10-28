package model.dao;

import model.entity.Cachorro;
import model.entity.Gato;

import javax.persistence.EntityManager;
import java.util.List;

public class GatoDao {

    private EntityManager entityManager;

    public GatoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Gato gato){
        this.entityManager.persist(gato);
    }

    public void update(Gato gato){
        this.entityManager.merge(gato);
    }

    public void delete(Gato gato){
        gato = entityManager.merge(gato);
        this.entityManager.remove(gato);
    }

    public Gato findById(int id){
        return entityManager.find(Gato.class, id);
    }

    public List<Gato> findAll(){
        String jpql = "SELECT g from Gato g";
        return entityManager.createQuery(jpql, Gato.class).getResultList();
    }





}
