/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.RegistroIngreso;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
    public int diasTrajadosXSemana(String idAnalista){
        
        
        GregorianCalendar cal = new GregorianCalendar();
	int  diaSemana=cal.get(Calendar.DAY_OF_WEEK);     
        diaSemana=diaSemana-2;
        Date fecha = new Date();
        Date fecha2;
        cal.setTime(fecha);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int dia2=dia++;
        cal.set(Calendar.DAY_OF_MONTH, dia2);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fecha2 = cal.getTime();
        int lunes=dia-diaSemana;
        cal.set(Calendar.DAY_OF_MONTH, lunes);
        fecha = cal.getTime();
        
        System.out.print(fecha);
        System.out.print(fecha2);
        
        

      
        return 0;
        
    }
    
    
    
}
