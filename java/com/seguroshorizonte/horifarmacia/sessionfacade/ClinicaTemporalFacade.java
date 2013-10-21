/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.ClinicaTemporal;
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
public class ClinicaTemporalFacade extends AbstractFacade<ClinicaTemporal> {
    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClinicaTemporalFacade() {
        super(ClinicaTemporal.class);
    }
    
    public List<ClinicaTemporal> obtenerClinicaTemporalXNombreLike(String patron){
    
        Query query = em.createNamedQuery("ClinicaTemporal.findByNombreLike",ClinicaTemporal.class);
        query.setParameter("nombre","%" + patron.toUpperCase() + "%" );
      
        List<ClinicaTemporal> resultList = query.getResultList();
        
        return resultList;
    }
}
