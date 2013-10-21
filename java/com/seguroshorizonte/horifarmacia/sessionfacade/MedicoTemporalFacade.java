/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.Medico;
import com.seguroshorizonte.horifarmacia.entidades.MedicoTemporal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Gedica
 */
@Stateless
public class MedicoTemporalFacade extends AbstractFacade<MedicoTemporal> {
    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicoTemporalFacade() {
        super(MedicoTemporal.class);
    }
    
    /**
     * busca medico temporal por numid y tipoid estrictamente iguales.
     * @param tipoid
     * @param numid
     * @return 
     */
    public MedicoTemporal buscarMedicoTemporal(String tipoid , String numid){
        
        Query query = em.createNamedQuery("MedicoTemporal.findByNumidYTipoid",MedicoTemporal.class);
        query.setParameter("numid",numid );
        query.setParameter("tipoid",tipoid.toUpperCase() );
        
        List<MedicoTemporal> resultList = query.getResultList();
       
        return super.getFirst(resultList);
    }
    
    /**
     * Trae listado de medicos los cuales la cedula comience en el valor que tenga numid.
     * @param tipoid
     * @param numid
     * @return 
     */
    public List<MedicoTemporal> obtenerMedicoTemporalXCILike(String tipoid , String numid){
    
        Query query = em.createNamedQuery("MedicoTemporal.findByNumidYTipoid",MedicoTemporal.class);
        query.setParameter("numid",numid.toUpperCase());//cualquiera que comience con el valor de numid
        query.setParameter("tipoid",tipoid.toUpperCase());
        
        List<MedicoTemporal> resultList = query.getResultList();
        
        return resultList;
    }
}
