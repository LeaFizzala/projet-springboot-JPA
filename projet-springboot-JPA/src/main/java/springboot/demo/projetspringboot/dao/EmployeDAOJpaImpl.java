package springboot.demo.projetspringboot.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springboot.demo.projetspringboot.entity.Employe;

@Repository
public class EmployeDAOJpaImpl implements EmployeDAO {
	
	private EntityManager em;
	
	
	@Autowired
	public EmployeDAOJpaImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional
	public List<Employe> attrapezLesTous() {

		//creer la requete avec les methodes JPA
	Query requete =
			em.createQuery("from Employe");
		
		// ranger la requete effectuee dans une liste
		
		List<Employe> employes = requete.getResultList();
		
		return employes;
	}

	@Override
	public Employe trouverParId(int id) {

    //récupérer l'employe
		Employe e = em.find(Employe.class, id);
		
		return e;
	}

	@Override
	@Transactional
	public void sauvegarder(Employe employe) {

		Employe e = em.merge(employe);
		
		employe.setId(e.getId());

	}

	@Override
	@Transactional
	public void supprimerParId(int id) {


		Query requete = em.createQuery("delete from Employe where id=:EmployeId");
		
		requete.setParameter("EmployeId", id);
		
		requete.executeUpdate();

	}

}
