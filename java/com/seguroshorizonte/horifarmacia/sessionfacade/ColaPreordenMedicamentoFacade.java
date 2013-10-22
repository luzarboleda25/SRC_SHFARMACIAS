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
    
    public int listaColaHoy(Date fecha){
        
        int listaFechaHoy;
        Query listaHoy=em.createNamedQuery("ColaPreordenMedicamento.findByFechaHoy", ColaPreordenMedicamento.class);
        listaHoy.setParameter("fecha", fecha);
        listaFechaHoy = listaHoy.getMaxResults();
        return listaFechaHoy;
    }
    
    
    public int listaColaNoHoy(Date fecha){
        
        int listaFechaNoHoy;
        Query listaNoHoy=em.createNamedQuery("ColaPreordenMedicamento.findByFechaNoHoy", ColaPreordenMedicamento.class);
        listaNoHoy.setParameter("fecha", fecha);
        listaFechaNoHoy = listaNoHoy.getMaxResults();
        return listaFechaNoHoy;
    }
}
