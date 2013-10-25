/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
import com.seguroshorizonte.horifarmacia.entidades.Preorden;
import com.seguroshorizonte.horifarmacia.entidades.RegistroIngreso;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pangea
 */
@Stateless
public class RegistroIngresoFacade extends AbstractFacade<RegistroIngreso> {
    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroIngresoFacade() {
        super(RegistroIngreso.class);
        
    }
    
    public int diasTrajadosXSemana(String idAnalista, Date fecha, Date fecha2){
        
        Analista data = new Analista();
        data.setIdanalista(new BigDecimal(idAnalista));
       
        
        Query query = em.createNamedQuery("RegistroIngreso.diasTrabajadosXS", Preorden.class);
        query.setParameter("analistaIdanalista", data);
        query.setParameter("fecha1", fecha);
        query.setParameter("fecha2", fecha2);

      
        Object resultList = query.getSingleResult();

        String contador = resultList.toString();
        int con = Integer.parseInt(contador);
        return con;
        
    }
    
    
    
}
