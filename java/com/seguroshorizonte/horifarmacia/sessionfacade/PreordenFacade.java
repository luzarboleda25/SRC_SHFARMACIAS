/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.Preorden;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class PreordenFacade extends AbstractFacade<Preorden> {

    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreordenFacade() {
        super(Preorden.class);
    }

    public Preorden findByIdpreorden(BigInteger id) {
        return super.find(new BigDecimal(id));
    }

    public void actualizarCotexto() {
        em.flush();
    }

    public List<Preorden> listarPreOrdenXCodcli(String codcli) {

        Query query = em.createNamedQuery("Preorden.findByCodcli", Preorden.class);
        query.setParameter("codcli", codcli);

        List<Preorden> resultList = query.getResultList();

        return resultList;

    }
    
    public Preorden buscarPreOrdenXIdepreorden(String idepreorden) {

        Query query = em.createNamedQuery("Preorden.findByIdpreorden", Preorden.class);
        query.setParameter("idpreorden", new BigDecimal( idepreorden ) );

        List<Preorden> resultList = query.getResultList();

        return super.getFirst(resultList);

    }
//    
//    public Preorden buscarPreOrdenXCodcli(String codcli) {
//
//        Query query = em.createNamedQuery("Preorden.findByCodcli", Preorden.class);
//        query.setParameter("codcli", codcli );
//
//        List<Preorden> resultList = query.getResultList();
//
//        return super.getFirst(resultList);
//
//    }
    
    
    
    
}
