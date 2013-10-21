/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.Medico;
import com.seguroshorizonte.horifarmacia.entidades.Tercero;
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
public class TerceroFacade extends AbstractFacade<Tercero> {
    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TerceroFacade() {
        super(Tercero.class);
    }
    
    
    public Tercero findXTipoIdAndNumid(BigDecimal numid,String tipoid){
    
        Query query = em.createNamedQuery("Tercero.findByNumidTipoid",Tercero.class);
        query.setParameter("numid",numid );
        query.setParameter("tipoid",tipoid );
        
        List<Tercero> resultList = query.getResultList();
        
        return super.getFirst(resultList);
    
    }
}
