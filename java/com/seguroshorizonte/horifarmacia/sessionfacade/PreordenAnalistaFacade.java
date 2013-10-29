/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
import com.seguroshorizonte.horifarmacia.entidades.Preorden;
import com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamento;
import com.seguroshorizonte.horifarmacia.entidades.PreordenAnalista;
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
public class PreordenAnalistaFacade extends AbstractFacade<PreordenAnalista> {

    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreordenAnalistaFacade() {
        super(PreordenAnalista.class);
    }

    public List<PreordenAnalista> listarPreOrdenProcesadasXidAnalista(String IdAnalista) {

        Analista data = new Analista();
        data.setIdanalista(new BigDecimal(IdAnalista));
        
        Date fecha = new Date();
        Date fecha2;
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal.setTime(fecha);
        System.out.print(cal);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        fecha = cal.getTime();
        cal2.setTime(fecha);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        dia++;
        cal2.set(Calendar.DAY_OF_MONTH, dia);

        fecha2 = cal2.getTime();
        Query query;
        query = em.createNamedQuery("PreordenAnalista.findByIdAnalista", Preorden.class);
        query.setParameter("idanalista", data);
        query.setParameter("fecha1", fecha);
        query.setParameter("fecha2", fecha2);

        List<PreordenAnalista> resultList = query.getResultList();

        return resultList;

    }

    public int contarSHXidAnalista(String IdAnalista) {

        Analista data = new Analista();
        data.setIdanalista(new BigDecimal(IdAnalista));
        Date fecha = new Date();
        Date fecha2;
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal.setTime(fecha);
        System.out.print(cal);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        fecha = cal.getTime();
        cal2.setTime(fecha);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        dia++;
        cal2.set(Calendar.DAY_OF_MONTH, dia);

        fecha2 = cal2.getTime();
        Query query;
        query = em.createNamedQuery("PreordenAnalista.ContarSHXidAnalista", Preorden.class);
        query.setParameter("idanalista", data);
        query.setParameter("fecha1", fecha);
        query.setParameter("fecha2", fecha2);

        Object resultList = query.getSingleResult();

        String contador = resultList.toString();
        int con = Integer.parseInt(contador);
        return con;

    }

    
     public int contarSHXidAnalistaXS(String IdAnalista,Date fecha, Date fecha2) {

        Analista data = new Analista();
        data.setIdanalista(new BigDecimal(IdAnalista));
        
        Query query;
        query = em.createNamedQuery("PreordenAnalista.ContarSHXidAnalista", Preorden.class);
        query.setParameter("idanalista", data);
        query.setParameter("fecha1", fecha);
        query.setParameter("fecha2", fecha2);

        Object resultList = query.getSingleResult();

        String contador = resultList.toString();
        int con = Integer.parseInt(contador);
        
        if(contador==null){
            
            con=0;
        }
        return con;

    }

    
    
    
    public List<PreordenAnalista> listaSolicitudesProcesadasXFecha(String status) {

        Date fecha = new Date();
        Date fecha2;
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal.setTime(fecha);
        System.out.print(cal);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        fecha = cal.getTime();
        cal2.setTime(fecha);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        dia++;
        cal2.set(Calendar.DAY_OF_MONTH, dia);

        fecha2 = cal2.getTime();
        Query query;
        List<PreordenAnalista> resultList;
        query = em.createNamedQuery("PreordenAnalista.findByProcesadaFechaHoy", Preorden.class);
        query.setParameter("status", status);
        query.setParameter("fecha1", fecha);
        query.setParameter("fecha2", fecha2);
        resultList = query.getResultList();
        return resultList;

    }

    public Object obtenerSolicitudesProcesadasXFecha(String status) {

        Date fecha = new Date();
        Date fecha2;
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal.setTime(fecha);
        System.out.print(cal);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        fecha = cal.getTime();
        cal2.setTime(fecha);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        dia++;
        cal2.set(Calendar.DAY_OF_MONTH, dia);

        fecha2 = cal2.getTime();
        Query query;
        Object resultCont;
        query = em.createNamedQuery("PreordenAnalista.findByContadorProcesadas", Preorden.class);
        query.setParameter("status", status);
        query.setParameter("fecha1", fecha);
        query.setParameter("fecha2", fecha2);
        resultCont = query.getSingleResult();
        return resultCont;

    }

    public PreordenAnalista obtenerPreordenAnalistaXidPreorden(Preorden idPreorden) {
        Query query = em.createNamedQuery("PreordenAnalista.findByIdpreMedAna", PreordenAnalista.class);
        query.setParameter("idpreMedAna", idPreorden);
        PreordenAnalista Resultado = (PreordenAnalista) query.getSingleResult();
        return Resultado;
    }
}