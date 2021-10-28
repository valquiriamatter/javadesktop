package model.dao;

import model.entity.Cachorro;
import javax.persistence.EntityManager;
import java.util.List;

public class CachorroDao {

    private EntityManager entityManager;

    public CachorroDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Cachorro cachorro){
        this.entityManager.persist(cachorro);
    }

    public void update(Cachorro cachorro){
        this.entityManager.merge(cachorro);
    }

    public void delete(Cachorro cachorro){
        cachorro = entityManager.merge(cachorro);
        this.entityManager.remove(cachorro);
    }

    public Cachorro findById(int id){
        return entityManager.find(Cachorro.class, id);
    }

    public List<Cachorro> findAll(){
        String jpql = "SELECT c from Cachorro c";
        return entityManager.createQuery(jpql, Cachorro.class).getResultList();
    }





}
