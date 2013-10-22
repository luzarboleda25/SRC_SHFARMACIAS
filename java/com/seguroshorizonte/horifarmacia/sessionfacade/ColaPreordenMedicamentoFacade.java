/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.ColaPreordenMedicamento;
import java.util.Date;
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
    
    public Object listaColaHoy(Date fecha){
        
        Object listaFechaHoy;
        Query listaHoy=em.createNamedQuery("ColaPreordenMedicamento.findByFechaHoy", ColaPreordenMedicamento.class);
        listaHoy.setParameter("fecha", fecha);
        listaFechaHoy = listaHoy.getSingleResult();
        return listaFechaHoy;
    }
    
    
    public int listaColaNoHoy(Date fecha){
        
        int listaFechaNoHoy;
        Query listaNoHoy=em.createNamedQuery("ColaPreordenMedicamento.findByFechaNoHoy", ColaPreordenMedicamento.class);
        listaNoHoy.setParameter("fecha", fecha);
        listaFechaNoHoy = listaNoHoy.getFirstResult();
        return listaFechaNoHoy;
    }
}
