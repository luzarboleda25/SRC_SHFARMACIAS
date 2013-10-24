/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.ColaPreordenMedicamento;
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
    
    
      public int contarSHXidAnalista(String IdAnalista) {
       
        
         Query query = em.createNamedQuery("ColaPreordenMedicamento.findAll", ColaPreordenMedicamento.class);
        

        List<ColaPreordenMedicamento> resultList = query.getResultList();
        
         String contador= resultList.toString();
         int con=Integer.parseInt(contador);
        return con;

    }
     
     public BigDecimal primeroCola(){
        
         
           Query query = em.createNamedQuery("ColaPreordenMedicamento.findPrimeroCola", ColaPreordenMedicamento.class);
          Object resultList = query.getSingleResult();
         String Auxi= resultList.toString();
        BigDecimal aux = new BigDecimal(Auxi);
         return aux;
         
     }
     
     
     public ColaPreordenMedicamento buscarColaXcodCli(String codCli){
        
         
           Query query = em.createNamedQuery("ColaPreordenMedicamento.findByCodCli", ColaPreordenMedicamento.class);
           query.setParameter("codCli",codCli);

       ColaPreordenMedicamento resultList = (ColaPreordenMedicamento) query.getSingleResult();
         return resultList;
         
     }
  
      public ColaPreordenMedicamento buscarColaXidPreOrden(String idPreOrden){
        
         
           Query query = em.createNamedQuery("ColaPreordenMedicamento.findByColaIdPreOrden", ColaPreordenMedicamento.class);
           query.setParameter(":idpreorden",new BigDecimal(idPreOrden));

       ColaPreordenMedicamento resultList = (ColaPreordenMedicamento) query.getSingleResult();
         return resultList;
         
     }
    
}
