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
    public Object obtenerTotaOperadoresConectadosXEstado(String estado) {

        Object resultCont;
        Query query = em.createNamedQuery("Analista.findByContadorStatusConectado", Analista.class);
        query.setParameter("status", estado);
        resultCont = query.getSingleResult();
        return resultCont;
    }

    
}
