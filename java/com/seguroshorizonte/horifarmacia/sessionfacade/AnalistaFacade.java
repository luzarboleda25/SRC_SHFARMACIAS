/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pangea
 */
@Stateless
public class AnalistaFacade extends AbstractFacade<Analista> {
    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnalistaFacade() {
        super(Analista.class);
    }
    
    public int operadoresConectados(int estado){
        
        int contOperadores;
        Query cont=em.createNamedQuery("Analista.findByEstado",Analista.class);
        cont.setParameter("estado", estado);
        contOperadores =cont.getMaxResults();
        return contOperadores;
    }
}
