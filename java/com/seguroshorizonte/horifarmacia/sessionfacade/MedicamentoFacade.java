/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.Medicamento;
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
public class MedicamentoFacade extends AbstractFacade<Medicamento> {
    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicamentoFacade() {
        super(Medicamento.class);
    }
    
    public List<Medicamento> findMedicamentoJoinPAXNombre(String nombre){
    
        Query query = em.createNamedQuery("Medicamento.findMedicamentoJoinPAXNombre", Medicamento.class);
        query.setParameter("nombre","" + nombre );
        return query.getResultList();
    }
}
