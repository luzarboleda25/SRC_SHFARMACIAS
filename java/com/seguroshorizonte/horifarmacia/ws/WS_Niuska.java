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
        try{
            return colaServices.count();
        } catch (Exception ex) {
            System.out.println("Hubo error");
            return 0;
        }
    }
    
    
    @WebMethod(operationName = "contarColaFechaHoy")
    public int contarColaFechaHoy(@WebParam(name = "listaHoy") Date fecha) {
    
        try{
            int colaHoy = ((Integer) colaServices.listaColaHoy(fecha)).intValue();
            return colaHoy;
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
            int operadoresConecatdos = ((Integer) analistaServices.operadoresConectados(estado)).intValue();
            return operadoresConecatdos;
        } catch (Exception ex) {
            System.out.println("Hubo error");
            return 0;
        }
    }

}
