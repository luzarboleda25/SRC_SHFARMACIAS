/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
import com.seguroshorizonte.horifarmacia.entidades.Preorden;
import com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamentoAnalista;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
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
        Query query;
        query = em.createNamedQuery("PreordenMedicamentoAnalista.findByIdAnalista", Preorden.class);
        query.setParameter("analistaIdanalista",data);
        query.setParameter("fecha1",fecha);
        query.setParameter("fecha2",fecha2);

        List<PreordenMedicamentoAnalista> resultList = query.getResultList();

        return resultList;

    }
    
     public int ContarSHXidAnalista(String IdAnalista) {
         Analista data= new Analista();
         data.setIdanalista(new BigDecimal(IdAnalista));
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
         Query query;
         query = em.createNamedQuery("PreordenMedicamentoAnalista.ContarSHXidAnalista", Preorden.class);
         query.setParameter("analistaIdanalista",data);
         query.setParameter("fecha1",fecha);
         query.setParameter("fecha2",fecha2);
        
        

        Object resultList = query.getSingleResult();
        
         String contador= resultList.toString();
         int con=Integer.parseInt(contador);
        return con;

    }
     
     
     public List<PreordenMedicamentoAnalista> obtenerSolicitudesProcesadasXFecha(int estado) {
        
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
        Query query;
        List<PreordenMedicamentoAnalista> resultList;
        query = em.createNamedQuery("PreordenMedicamentoAnalista.findByProcesadaFechaHoy", Preorden.class);
        query.setParameter("estado",estado);
        query.setParameter("fecha1",fecha);
        query.setParameter("fecha2",fecha2);
        resultList=query.getResultList();
        return resultList;

    }
    
}
