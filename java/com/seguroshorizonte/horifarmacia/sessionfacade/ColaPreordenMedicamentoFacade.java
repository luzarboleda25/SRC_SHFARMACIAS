/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.ColaPreordenMedicamento;
import java.util.Calendar;
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
    
    public Object obtenerTotalXFechaHoy(){
        
        Date fecha=new Date();
        Date fecha2;
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
      
        cal.setTime(fecha);
        System.out.print(cal);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
       
        fecha=cal.getTime();
        cal2.setTime(fecha);
        int dia= cal.get(Calendar.DAY_OF_MONTH);
        dia++;
        cal2.set(Calendar.DAY_OF_MONTH, dia);
       
        fecha2=cal2.getTime();
        
        Object resultCont;
        Query query = em.createNamedQuery("ColaPreordenMedicamento.findByFechaHoy", ColaPreordenMedicamento.class);
        query.setParameter("fecha1",fecha);
        query.setParameter("fecha2",fecha2);
        resultCont = query.getSingleResult();
        return resultCont;
    }
    
}
