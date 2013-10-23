/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.ColaPreordenMedicamento;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pangea
 */
@Stateless
public class ColaPreordenMedicamentoFacade extends AbstractFacade<ColaPreordenMedicamento> {
    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ColaPreordenMedicamentoFacade() {
        super(ColaPreordenMedicamento.class);
    }
    
    public Object obtenerTotalXFechaHoy(Date fecha){
        
        Object resultCont;
        Query query = em.createNamedQuery("ColaPreordenMedicamento.findByFechaHoy", ColaPreordenMedicamento.class);
        query.setParameter("fecha", fecha);
        query.setParameter("fechaa", fecha);
        resultCont = query.getSingleResult();
        return resultCont;
    }
    
    
    public Object obtenerTotalXFecha(Date fecha){
        
        Object resultCont;
        Query query = em.createNamedQuery("ColaPreordenMedicamento.findByFechaNoHoy", ColaPreordenMedicamento.class);
        query.setParameter("fecha", fecha);
        resultCont = query.getSingleResult();
        return resultCont;
    }
}
