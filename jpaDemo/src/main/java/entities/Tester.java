package entities;

import dto.PersonStyleDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tha-Y
 */
public class Tester {
    
    public static void main(String[] args) {
        
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            
            
            Person p1 = new Person("Bent Hansen", 1955);
            Person p2 = new Person("Johnny Madsen", 1965);
            
            Address a1 = new Address("Smedeløkken 11", 3770, "Allinge");
            Address a2 = new Address("Tejnvej 24", 3770, "Allinge");
            
            p1.setAdress(a1);
            p2.setAdress(a2);
            
            Fee f1 = new Fee(100);
            Fee f2 = new Fee(500);
            Fee f3 = new Fee(150);
            
            
            p1.addFee(f1);
            p2.addFee(f2);
            p2.addFee(f3);
            
            
            SwimStyle s1 = new SwimStyle("Crawl");
            SwimStyle s2 = new SwimStyle("ButterFly");
            SwimStyle s3 = new SwimStyle("Breat Stroke");
            
            p1.addSwimStyle(s1);
            p1.addSwimStyle(s3);
            p2.addSwimStyle(s2);
            
            
            
            em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
             em.getTransaction().commit();
                        
              em.getTransaction().begin();
              p1.removeSwimStyle(s3);
              em.getTransaction().commit();
             
             
             System.out.println("p1: " + p1.getP_id());
             System.out.println("p2: " + p2.getP_id());
             System.out.println("p1 bor her " + p1.getAdress().getStreet());
             
             System.out.println("Lad os se om to-vejs virker: " + a1.getPerson().getName());
               
             
             System.out.println("Hvem har betalt f2? Det har:" +  f2.getPerson().getName());
             
             
             TypedQuery<Fee> q1 = em.createQuery("SELECT f from Fee f ", Fee.class);
             List<Fee> feelist = q1.getResultList();
             
             System.out.println("Hvad er betalt?");
             for (Fee fee : feelist) {
                 System.out.println(fee.getPerson().getName()+ ":" +fee.getAmount());
            
        }
             
             Query q2 = em.createQuery("SELECT new dto.PersonStyleDTO(p.name, p.year, s.styleName) FROM Person p JOIN p.swimStyles s");
             List<PersonStyleDTO> plist = q2.getResultList();
             
             for (PersonStyleDTO personStyleDTO : plist) {
                 System.out.println(personStyleDTO.getName() + " " + personStyleDTO.getYear() + " " + personStyleDTO.getStyleName());
            
        }
             
             
            
        }
             
                   
        
    }

