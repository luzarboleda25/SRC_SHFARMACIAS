/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
import com.seguroshorizonte.horifarmacia.entidades.ColaPreorden;
import com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamento;
import com.seguroshorizonte.horifarmacia.entidades.PreordenAnalista;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pangea
 */
@WebService(serviceName = "Joseweb")
public class Joseweb {
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.PreordenAnalistaFacade poMedicamentoAnalistaServices;
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.ColaPreordenFacade ColaPoMedicamentoServices;
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.AnalistaFacade AnalistaServices;
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.PreordenMedicamentoFacade PreordenMedicamentoServices;
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.RegistroIngresoFacade RegistroIngresoServices;
    
       

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "listaPreordenAnalistaXidAnalista")
    public List<PreordenAnalista> listaPreordenAnalistaXidAnalista(@WebParam(name = "idAnalista") String idAnalista) {

        try {
            List<PreordenAnalista> listaPMA;
            listaPMA = poMedicamentoAnalistaServices.listarPreOrdenProcesadasXidAnalista(idAnalista);
           int j=0;
            while(listaPMA.size()>j){
                listaPMA.get(j).getPreorden().setPreordenAnalista(null);
                listaPMA.get(j).getPreorden().setPreordenMedicamentoList(null);
                j++;
            }
            
            
            return listaPMA;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de PreOrden");
            return null;
        }
    }
    
     @WebMethod(operationName = "contarSHXidAnalista")
    public int contarSHXidAnalista(@WebParam(name = "idAnalista") String idAnalista) {

        try {
            int Cont = poMedicamentoAnalistaServices.contarSHXidAnalista(idAnalista);
            return Cont;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de PreOrden");
            return 0;
        }
    }
     
       @WebMethod(operationName = "extraerDeLaColaXidAnalista")
    public int extraerDeLaColaXidAnalista(@WebParam(name = "idAnalista") String idAnalista) {
        
           try{
               
           BigDecimal primeroC = ColaPoMedicamentoServices.primeroCola();
           ColaPreorden primero = ColaPoMedicamentoServices.find(primeroC);
           Analista analista = AnalistaServices.find(new BigDecimal(idAnalista));
           PreordenAnalista PMAnalista=new PreordenAnalista();
           
           PMAnalista.setFecha(new Date());
           //
           PMAnalista.setIdanalista(analista);
           PMAnalista.setStatus("0");
           ColaPoMedicamentoServices.remove(primero);
           poMedicamentoAnalistaServices.create(PMAnalista);
           
            }catch(Exception ex){
                   return 0;
            }
           
        return 1;
        }
       @WebMethod(operationName = "extraerDeLaColaXcodCli")
       public int extraerDeLaColaXcodCli(@WebParam(name = "codCli")String codCli) {
        
           ColaPreorden buscar = ColaPoMedicamentoServices.buscarColaXcodCli(codCli);
           ColaPoMedicamentoServices.remove(buscar);
         
           
           
          return 0;


        }
       @WebMethod(operationName = "extraerDeLaColaXidPreOrden")
        public int extraerDeLaColaXidPreOrden(@WebParam(name = "idPreOrden")String idPreOrden) {
        
           ColaPreorden buscar= ColaPoMedicamentoServices.buscarColaXidPreOrden(idPreOrden);
           ColaPoMedicamentoServices.remove(buscar);
           
           
          return 0;


        }
        
        @WebMethod(operationName = "promedioSolicitudesXidAnalista")
        public int promedioSolicitudesXidAnalista(@WebParam(name = "idAnalista")String idAnalista){
       
        
        GregorianCalendar cal = new GregorianCalendar();
        Analista data = new Analista();
        data.setIdanalista(new BigDecimal(idAnalista));
        
	int  diaSemana=cal.get(Calendar.DAY_OF_WEEK);     
        diaSemana=diaSemana-2;
        Date fecha = new Date();
        System.out.print(fecha);
        Date fecha2;
        cal.setTime(fecha);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        System.out.print(dia);
        int dia2=dia+1;
        cal.set(Calendar.DAY_OF_MONTH, dia2);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fecha2 = cal.getTime();
        int lunes=dia-diaSemana;
        cal.set(Calendar.DAY_OF_MONTH, lunes);
        fecha = cal.getTime();
            
        int buscarDTS = RegistroIngresoServices.diasTrajadosXSemana(idAnalista,fecha,fecha2);
        int buscarSS = poMedicamentoAnalistaServices.contarSHXidAnalistaXS(idAnalista, fecha, fecha2);
        int promedio;
        
        if(buscarDTS==0 || buscarSS==0){
            promedio=0;
        }else {
            promedio= buscarSS/buscarDTS;
        }
            
            return promedio;
        }
    }

  