/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;

import com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamentoAnalista;
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
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.PreordenMedicamentoAnalistaFacade poMedicamentoAnalistaServices;
   
    
    @WebMethod(operationName = "obtenerColaPreOrden")
    public int obtenerColaPreOrden() {
        try{
            return colaServices.count();
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Total de Solicitudes por Procesar");
            return 0;
        }
    }
    
    
    @WebMethod(operationName = "obtenerTotalXFechaHoy")
    public int obtenerTotalXFechaHoy() {
        
        try{
            int colaFecha=0;
            String result;
            Object resultado;
            
            resultado=colaServices.obtenerTotalXFechaHoy();
            result=resultado.toString();
            colaFecha=Integer.parseInt(result);
            return colaFecha;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Solicitudes Ingresadas en la Cola Hoy");
            return 0;
        }
    }
    
    
    @WebMethod(operationName = "obtenerTotaOperadoresConectadosXEstado")
    public int obtenerTotaOperadoresConectadosXEstado(@WebParam(name = "estado") String estado) {
    
        try{
            int operadoresConectados, estadoint;
            String result;
            Object resultado;
            
            estadoint = Integer.parseInt(estado);

            resultado=analistaServices.obtenerTotaOperadoresConectadosXEstado(estadoint);
            result=resultado.toString();
            operadoresConectados= Integer.parseInt(result);
            return operadoresConectados;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Operadores Conectados");
            return 0;
        }
    }
    
    @WebMethod(operationName = "obtenerSolicitudesProcesadasXFecha")
    public List<PreordenMedicamentoAnalista> obtenerSolicitudesProcesadasXFecha(@WebParam(name = "estado") String estado) {

        try {
            List<PreordenMedicamentoAnalista> listaPMA;
            int estadoint;
            
            estadoint = Integer.parseInt(estado);
            
            listaPMA=poMedicamentoAnalistaServices.obtenerSolicitudesProcesadasXFecha(estadoint);
            return listaPMA;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Solicitudes Procesadas");
            return null;
        }
    }
}
