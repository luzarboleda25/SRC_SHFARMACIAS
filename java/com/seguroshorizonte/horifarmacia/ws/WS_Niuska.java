/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;

import java.util.Date;
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
        
            return colaServices.count();
       
    }
    
    
    @WebMethod(operationName = "contarColaFechaHoy")
    public int contarColaFechaHoy(@WebParam(name = "listaHoy") Date fecha) {
    
        try{
            return colaServices.listaColaHoy(fecha);
        } catch (Exception ex) {
            System.out.println("Hubo error");
            return 0;
        }
    }
    
    
    @WebMethod(operationName = "contarColaFechaNoHoy")
    public int contarColaFechaNoHoy(@WebParam(name = "listaNoHoy") Date fecha) {
    
        try{
            return colaServices.listaColaNoHoy(fecha);
        } catch (Exception ex) {
            System.out.println("Hubo error");
            return 0;
        }
    }
    
    
    
    @WebMethod(operationName = "contarOperadoresConectados")
    public int contarOperadoresConectados(@WebParam(name = "operadoresConectados") int estado) {
    
        try{
            return analistaServices.operadoresConectados(estado);
        } catch (Exception ex) {
            System.out.println("Hubo error");
            return 0;
        }
    }

}
