/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.Clinica;
import com.seguroshorizonte.horifarmacia.entidades.MedicoTemporal;
import com.seguroshorizonte.horifarmacia.entidades.Tercero;
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
public class ClinicaFacade extends AbstractFacade<Clinica> {

    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClinicaFacade() {
        super(Clinica.class);
    }

    public List<Tercero> obtenerClinicaXPatronLike(String patron) {

        StringBuilder sql = new StringBuilder();
        
        sql.append("select ter.* ");
        sql.append("from clinica cli ");
        sql.append("inner join tercero ter ");
        sql.append("on cli.tipoid = ter.tipoid ");
        sql.append("and cli.numid = ter.numid ");
        sql.append("and cli.dvid = ter.dvid ");
        sql.append("and (ter.nomter LIKE ").append("'%").append(patron.toUpperCase()).append("%' ");
        sql.append("or ter.apeter LIKE ").append("'%").append(patron.toUpperCase()).append("%' )");
        
        Query query = em.createNativeQuery(sql.toString(), Tercero.class);

        List<Tercero> resultList = query.getResultList();

        return resultList;
    }
}
