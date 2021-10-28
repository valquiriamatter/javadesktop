package model.dao;

import model.entity.Animal;
import model.entity.Cachorro;
import model.entity.Dono;
import model.entity.Gato;

import javax.persistence.EntityManager;
import java.util.List;

public class DonoDao {

    private EntityManager entityManager;

    public DonoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Dono dono){
        this.entityManager.persist(dono);
    }

    public void update(Dono dono){
        this.entityManager.merge(dono);
    }

    public void delete(Dono dono){
        dono = entityManager.merge(dono);
        this.entityManager.remove(dono);
    }

    public Dono findById(int id){
        return entityManager.find(Dono.class, id);
    }




//    public List<Animal> findByName(String nome){
//        String jpql = "select c.nome =:nome from cachorro c inner join dono d on c.dono_id = d.id";
//        return entityManager.createQuery(jpql, Animal.class).setParameter("nome", nome).getResultList();
//    }

    public List<Dono> findAll(){
        String jpql = "select d from Dono d";
        return entityManager.createQuery(jpql, Dono.class).getResultList();
    }



}
