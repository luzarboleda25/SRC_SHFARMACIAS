/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
import com.seguroshorizonte.horifarmacia.entidades.ColaPreorden;
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

/**
 *
 * @author pc
 */
@WebService(serviceName = "ColasWS")
public class ColasWS {
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.ColaPreordenFacade colaPoServices;
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.AnalistaFacade analistaServices;
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.PreordenAnalistaFacade poAnalistaServices;

    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.RegistroIngresoFacade RegistroIngresoServices;
    
    
     /**
     * Enlista las PreOrdenes por el Id del analista dado
     * @param idAnalista
     * @return una lista de objetos de PreordenAnalista
     */
    @WebMethod(operationName = "listaPreordenAnalistaXidAnalista")
    public List<PreordenAnalista> listaPreordenAnalistaXidAnalista(@WebParam(name = "idAnalista") String idAnalista) {

        try {
            List<PreordenAnalista> listaPMA;
            listaPMA = poAnalistaServices.listarPreOrdenProcesadasXidAnalista(idAnalista);
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
    
     
       /**
     *
     * @param idAnalista
     * @return
     */
    @WebMethod(operationName = "extraerDeLaColaXidAnalista")
    public int extraerDeLaColaXidAnalista(@WebParam(name = "idAnalista") String idAnalista) {
        
           try{
               
           BigDecimal primeroC = colaPoServices.primeroCola();
           ColaPreorden primero = colaPoServices.find(primeroC);
           Analista analista = analistaServices.find(new BigDecimal(idAnalista));
           PreordenAnalista PMAnalista=new PreordenAnalista();
           
           PMAnalista.setFecha(new Date());
           //
           PMAnalista.setIdanalista(analista);
           PMAnalista.setStatus("0");
           colaPoServices.remove(primero);
           poAnalistaServices.create(PMAnalista);
           
            }catch(Exception ex){
                   return 0;
            }
           
        return 1;
        }
       /**
     *
     * @param codCli
     * @return
     */
    
    
    @WebMethod(operationName = "extraerDeLaColaXcodCli")
       public int extraerDeLaColaXcodCli(@WebParam(name = "codCli")String codCli) {
        
           ColaPreorden buscar = colaPoServices.buscarColaXcodCli(codCli);
           colaPoServices.remove(buscar);
         
           
           
          return 0;


        }
       /**
     *
     * @param idPreOrden
     * @return
     */
    @WebMethod(operationName = "extraerDeLaColaXidPreOrden")
        public int extraerDeLaColaXidPreOrden(@WebParam(name = "idPreOrden")String idPreOrden) {
        
           ColaPreorden buscar= colaPoServices.buscarColaXidPreOrden(idPreOrden);
           colaPoServices.remove(buscar);
           
           
          return 0;


        }
        
        /**
     *
     * @param idAnalista
     * @return
     */
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
        int buscarSS = poAnalistaServices.contarSHXidAnalistaXS(idAnalista, fecha, fecha2);
        int promedio;
        
        if(buscarDTS==0 || buscarSS==0){
            promedio=0;
        }else {
            promedio= buscarSS/buscarDTS;
        }
            
            return promedio;
        }
    
    
    /**
     * Método que obtiene el total de la cola
     * @return 
     */
    @WebMethod(operationName = "obtenerColaPreOrden")
    public int obtenerColaPreOrden() {
        try{
            return colaPoServices.count();
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
            
            resultado=colaPoServices.obtenerTotalXFechaHoy();
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
   
     @WebMethod(operationName = "buscarAnalista")
    public Analista buscarAnalista(@WebParam(name = "idAnalista") String idAnalista) {
        Analista find = analistaServices.find(new BigDecimal(idAnalista));
         return find;
     }
    
}
