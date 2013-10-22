/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
import com.seguroshorizonte.horifarmacia.entidades.Preorden;
import com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamentoAnalista;
import java.math.BigDecimal;
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
public class PreordenMedicamentoAnalistaFacade extends AbstractFacade<PreordenMedicamentoAnalista> {
    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreordenMedicamentoAnalistaFacade() {
        super(PreordenMedicamentoAnalista.class);
    }
    
    public List<PreordenMedicamentoAnalista> listarPreOrdenProcesadasXidAnalista(String IdAnalista) {
         Analista data= new Analista();
         data.setIdanalista(new BigDecimal(IdAnalista));
        Query query;
        query = em.createNamedQuery("PreordenMedicamentoAnalista.findByIdAnalista", Preorden.class);
        query.setParameter("analistaIdanalista",data);

        List<PreordenMedicamentoAnalista> resultList = query.getResultList();

        return resultList;

    }
    
}
