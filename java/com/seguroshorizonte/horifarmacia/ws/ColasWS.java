/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;

import com.seguroshorizonte.horifarmacia.entidades.PreordenAnalista;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author pc
 */
@WebService(serviceName = "ColasWS")
public class ColasWS {
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.ColaPreordenFacade colaServices;
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.AnalistaFacade analistaServices;
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.PreordenAnalistaFacade poAnalistaServices;

    /**
     * Método que obtiene el total de la cola
     * @return 
     */
    @WebMethod(operationName = "obtenerColaPreOrden")
    public int obtenerColaPreOrden() {
        try{
            return colaServices.count();
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Total de Solicitudes por Procesar");
            return 0;
        }
    }
    
    
    /**
     * Método que obtiene el total de la cola el día de hoy
     * @return
     */
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
    
    
    /**
     * Método que obtiene el total de los operadores conectados con estado "1"
     * @param estado
     * @return
     */
    @WebMethod(operationName = "obtenerTotalOperadoresConectadosXEstado")
    public int obtenerTotalOperadoresConectadosXEstado(@WebParam(name = "estado") String estado) {
    
        try{
            int operadoresConectados;
            String result;
            Object resultado;

            resultado=analistaServices.obtenerTotaOperadoresConectadosXEstado(estado);
            result=resultado.toString();
            operadoresConectados= Integer.parseInt(result);
            return operadoresConectados;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Operadores Conectados");
            return 0;
        }
    }
    
    /**
     * Método que obtiene la lista de las solicitudes procesadas el día de hoy
     * @param estado
     * @return
     */
    @WebMethod(operationName = "listaSolicitudesProcesadasXFecha")
    public List<PreordenAnalista> listaSolicitudesProcesadasXFecha(@WebParam(name = "estado") String estado) {

        try {
            List<PreordenAnalista> listaPMA;
            
            listaPMA=poAnalistaServices.listaSolicitudesProcesadasXFecha(estado);
            return listaPMA;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Solicitudes Procesadas");
            return null;
        }
    }
    
    /**
     * Método que obtiene el total de solicitudes procesadas el día de hoy por analista
     * @param estado
     * @return
     */
    @WebMethod(operationName = "obtenerSolicitudesProcesadasXFecha")
    public int obtenerSolicitudesProcesadasXFecha(@WebParam(name = "estado") String estado) {

        try {
            int contPMA;
            String result;
            Object resultado;
            
            resultado=poAnalistaServices.obtenerSolicitudesProcesadasXFecha(estado);
            result=resultado.toString();
            contPMA=Integer.parseInt(result);
            return contPMA;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Solicitudes Procesadas");
            return 0;
        }
    }
    
    
    /**
     * Método que obtiene el total de las solicitudes procesadas por el analista
     * @param idAnalista
     * @return
     */
    @WebMethod(operationName = "contarSHXidAnalista")
    public int contarSHXidAnalista(@WebParam(name = "idAnalista") String idAnalista) {

        try {
            int Cont = poAnalistaServices.contarSHXidAnalista(idAnalista);
            return Cont;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de PreOrden");
            return 0;
        }
    }
}
