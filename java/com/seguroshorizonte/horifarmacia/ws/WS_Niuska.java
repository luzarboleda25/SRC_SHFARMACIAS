/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
import com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamentoAnalista;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    
    
    @WebMethod(operationName = "obtenerTotalOperadoresConectadosXEstado")
    public int obtenerTotalOperadoresConectadosXEstado(@WebParam(name = "estado") String estado) {
    
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
    
    @WebMethod(operationName = "listaSolicitudesProcesadasXFecha")
    public List<PreordenMedicamentoAnalista> listaSolicitudesProcesadasXFecha(@WebParam(name = "estado") String estado) {

        try {
            List<PreordenMedicamentoAnalista> listaPMA;
            
            listaPMA=poMedicamentoAnalistaServices.listaSolicitudesProcesadasXFecha(estado);
            return listaPMA;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Solicitudes Procesadas");
            return null;
        }
    }
    
    @WebMethod(operationName = "obtenerSolicitudesProcesadasXFecha")
    public int obtenerSolicitudesProcesadasXFecha(@WebParam(name = "estado") String estado) {

        try {
            int contPMA;
            String result;
            Object resultado;
            
            resultado=poMedicamentoAnalistaServices.obtenerSolicitudesProcesadasXFecha(estado);
            result=resultado.toString();
            contPMA=Integer.parseInt(result);
            return contPMA;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Solicitudes Procesadas");
            return 0;
        }
    }
    
    @WebMethod(operationName = "obtenerProcesadasXAnalista")
    public int[] obtenerProcesadasXAnalista(@WebParam(name = "estado") String estado) {

        try {
            List<PreordenMedicamentoAnalista> listaPMA;
            String idAnalista, a, b;
            int contAnalistas[];
            int conPA, tamaño, j=0;
            
            listaPMA=poMedicamentoAnalistaServices.listaSolicitudesProcesadasXFecha(estado);
            tamaño=listaPMA.size();
            contAnalistas=new int[tamaño];
            
            while(listaPMA.size()>j){
                a=listaPMA.get(0).getAnalistaIdanalista().getIdanalista().toString();
                System.out.println("Analistaaaaaaaa: "+a);
                b=listaPMA.get(1).getAnalistaIdanalista().getIdanalista().toString();
                System.out.println("Analistaaaaaaaa: "+b);
                idAnalista=listaPMA.get(j).getAnalistaIdanalista().getIdanalista().toString();
                System.out.println("Analistaaaaaaaa: "+idAnalista);
                conPA=poMedicamentoAnalistaServices.ContarSHXidAnalista(idAnalista);
                contAnalistas[j]=conPA;
                System.out.println("Contadores Analistas: "+idAnalista+ "y" +contAnalistas[j]);
                j++;
            }
             
            return contAnalistas;
            
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Solicitudes Procesadas por Analista");
            return null;
        }
    }
}
