/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
import com.seguroshorizonte.horifarmacia.entidades.ColaPreordenMedicamento;
import com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamentoAnalista;
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
        
        Date fecha = new Date();
        System.out.println("Fechaaaa:  "+fecha);
        
        try{
            int colaFecha=0;
            String result;
            Object resultado;
            
            resultado=colaServices.obtenerTotalXFechaHoy(fecha);
            result=resultado.toString();
            colaFecha=Integer.parseInt(result);
            return colaFecha;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Solicitudes Ingresadas en la Cola Hoy");
            return 0;
        }
    }
    
    
    @WebMethod(operationName = "obtenerTotalXFecha")
    public int obtenerTotalXFecha(@WebParam(name = "fecha") String fecha) {
    
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/mm/yy");
        String strFecha = fecha;
        Date fechadate = null;
        
        try{
            int colaFecha;
            String result;
            Object resultado;
            
            fechadate = formatoDelTexto.parse(strFecha);
            resultado=colaServices.obtenerTotalXFecha(fechadate);
            result=resultado.toString();
            colaFecha=Integer.parseInt(result);
            return colaFecha;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Solicitudes Pendientes por Procesar");
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

    /*@WebMethod(operationName = "listaPreordenMedicamentoAnalistaXidAnalista")
    public List<PreordenMedicamentoAnalista> listaPreordenMedicamentoAnalistaXidAnalista(@WebParam(name = "idAnalista") String idAnalista) {

        try {
            List<PreordenMedicamentoAnalista> listaPMA;
            listaPMA = poMedicamentoAnalistaServices.listarPreOrdenProcesadasXidAnalista(idAnalista);
            return listaPMA;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de PreOrden");
            return null;
        }
    }*/
}
