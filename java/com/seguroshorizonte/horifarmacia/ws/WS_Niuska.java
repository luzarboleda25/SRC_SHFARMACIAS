/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
import com.seguroshorizonte.horifarmacia.entidades.ColaPreordenMedicamento;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author USER
 */
@WebService(serviceName = "WS_Niuska")
public class WS_Niuska {
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.ColaPreordenMedicamentoFacade colaServices;
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.AnalistaFacade analistaServices;
    
    
    
    @WebMethod(operationName = "contarColaPreOrden")
    public int contarColaPreOrden() {
        try{
            return colaServices.count();
        } catch (Exception ex) {
            System.out.println("Hubo error");
            return 0;
        }
    }
    
    
    @WebMethod(operationName = "contarColaFechaHoy")
    public int contarColaFechaHoy() {
        
        Date fecha = new Date();
        System.out.println("Fechaaaa:  "+fecha);
        
        try{
            int colaFecha=0;
            String result;
            Object resultado;
            
            resultado=colaServices.listaColaNoHoy(fecha);
            result=resultado.toString();
            colaFecha=Integer.parseInt(result);
            return colaFecha;
        } catch (Exception ex) {
            System.out.println("Hubo error");
            return 0;
        }
    }
    
    
    @WebMethod(operationName = "contarColaFechaNoHoy")
    public int contarColaFechaNoHoy(@WebParam(name = "fecha") String fecha) {
    
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/mm/yy");
        String strFecha = fecha;
        Date fechadate = null;
        
        try{
            int colaFecha;
            String result;
            Object resultado;
            
            fechadate = formatoDelTexto.parse(strFecha);
            resultado=colaServices.listaColaNoHoy(fechadate);
            result=resultado.toString();
            colaFecha=Integer.parseInt(result);
            return colaFecha;
        } catch (Exception ex) {
            System.out.println("Hubo error");
            return 0;
        }
    }
    
    
    @WebMethod(operationName = "contarOperadoresConectados")
    public int contarOperadoresConectados(@WebParam(name = "estado") String estado) {
    
        try{
            int operadoresConectados, estadoint;
            String result;
            Object resultado;
            
            estadoint = Integer.parseInt(estado);

            resultado=analistaServices.operadoresConectados(estadoint);
            result=resultado.toString();
            operadoresConectados= Integer.parseInt(result);
            return operadoresConectados;
        } catch (Exception ex) {
            System.out.println("Hubo error");
            return 0;
        }
    }

}
