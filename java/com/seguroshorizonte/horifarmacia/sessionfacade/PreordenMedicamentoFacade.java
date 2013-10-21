/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.ClinicaTemporal;
import com.seguroshorizonte.horifarmacia.entidades.Preorden;
import com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamento;
import java.math.BigDecimal;
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
public class PreordenMedicamentoFacade extends AbstractFacade<PreordenMedicamento> {
    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreordenMedicamentoFacade() {
        super(PreordenMedicamento.class);
    }
    
    
    public List<PreordenMedicamento> obtenerListadoMedicamentosXIdpreorden(Preorden idpreorden){
    
        Query query = em.createNamedQuery("PreordenMedicamento.findByIdpreorden",PreordenMedicamento.class);
        query.setParameter("idpreorden", idpreorden );
      
        List<PreordenMedicamento> resultList = query.getResultList();
        
        return resultList;
    }
    
}
