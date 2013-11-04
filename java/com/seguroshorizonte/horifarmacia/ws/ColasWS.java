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
     *
     * @param idAnalista
     * @return una lista de objetos de PreordenAnalista
     */
    @WebMethod(operationName = "listaPreordenAnalistaXidAnalista")
    public List<PreordenAnalista> listaPreordenAnalistaXidAnalista(@WebParam(name = "idAnalista") String idAnalista) {

        try {
            List<PreordenAnalista> listaPMA;
            listaPMA = poAnalistaServices.listarPreOrdenProcesadasXidAnalista(idAnalista);
            int j = 0;
            while (listaPMA.size() > j) {
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
     * Extrae el primero de la cola y lo asigna al analista asiganado
     *
     * @param idAnalista
     * @return 1 cuando se hizo la extracción y se asigno y 0 cuando hubo un
     * error
     */
    @WebMethod(operationName = "extraerDeLaColaXidAnalista")
    public int extraerDeLaColaXidAnalista(@WebParam(name = "idAnalista") String idAnalista) {

        try {

            BigDecimal primeroC = colaPoServices.primeroCola();
            ColaPreorden primero = colaPoServices.find(primeroC);
            Analista analista = analistaServices.find(new BigDecimal(idAnalista));
            PreordenAnalista PMAnalista = new PreordenAnalista();

            PMAnalista.setFecha(new Date());
            //
            PMAnalista.setIdanalista(analista);
            PMAnalista.setStatus("0");
            colaPoServices.remove(primero);
            poAnalistaServices.create(PMAnalista);

        } catch (Exception ex) {
            return 0;
        }

        return 1;
    }

    /**
     * coloca de primero en la cola todas las preordenes que esten asociadas al
     * codigo del cliente
     *
     * @param codCli
     * @return 1 cuando se hizo la operacion y 0 cuando hubo un error
     */
    @WebMethod(operationName = "priorizarColaXcodCli")
    public List<ColaPreorden> priorizarDeLaColaXcodCli(@WebParam(name = "codCli") String codCli) {


        try {
            List<ColaPreorden> buscar = colaPoServices.buscarColaXcodCli(codCli);
           
            return buscar;
        } catch (Exception ex) {
            return null;
        }

       



    }

    /**
     * coloca de primero en la cola todas las preorden que esten asociadas al
     * idPreOrden
     *
     * @param idPreOrden
     * @return
     */
    @WebMethod(operationName = "priorizarColaXidPreOrden")
    public int priorizarDeLaColaXidPreOrden(@WebParam(name = "idPreOrden") String idPreOrden) {
        try {
            ColaPreorden buscar = colaPoServices.buscarColaXidPreOrden(idPreOrden);
            BigDecimal primeroC = colaPoServices.primeroCola();
            int idmin = primeroC.intValue() - 1;
            if (idmin != 0) {
                colaPoServices.remove(buscar);
                buscar.setIdcolapreorden(new BigDecimal(idmin));
                colaPoServices.edit(buscar);
            } else {
                return 0;
            }


        } catch (Exception ex) {
            return 0;
        }

        return 1;


    }

    /**
     * Obtiene el promedio de las solicitudes procesadas de la semana del
     * analista dado
     *
     * @param idAnalista
     * @return un numero entero del promedio de solicitudes procesadas
     */
    @WebMethod(operationName = "promedioSolicitudesXidAnalista")
    public int promedioSolicitudesXidAnalista(@WebParam(name = "idAnalista") String idAnalista) {

        try {
            GregorianCalendar cal = new GregorianCalendar();
            Analista data = new Analista();
            data.setIdanalista(new BigDecimal(idAnalista));

            int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
            diaSemana = diaSemana - 2;
            Date fecha = new Date();
            System.out.print(fecha);
            Date fecha2;
            cal.setTime(fecha);
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            System.out.print(dia);
            int dia2 = dia + 1;
            cal.set(Calendar.DAY_OF_MONTH, dia2);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            fecha2 = cal.getTime();
            int lunes = dia - diaSemana;
            cal.set(Calendar.DAY_OF_MONTH, lunes);
            fecha = cal.getTime();

            int buscarDTS = RegistroIngresoServices.diasTrajadosXSemana(idAnalista, fecha, fecha2);
            int buscarSS = poAnalistaServices.contarSHXidAnalistaXS(idAnalista, fecha, fecha2);
            int promedio;

            if (buscarDTS == 0 || buscarSS == 0) {
                promedio = 0;
            } else {
                promedio = buscarSS / buscarDTS;
            }

            return promedio;

        } catch (Exception ex) {
            return 0;
        }


    }

    /**
     * Método que obtiene el total de la cola
     *
     * @return total de la cola preorden
     */
    @WebMethod(operationName = "obtenerColaPreOrden")
    public int obtenerColaPreOrden() {
        try {
            return colaPoServices.count();
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Total de Solicitudes por Procesar");
            return 0;
        }
    }

    /**
     * Método que obtiene el total de la cola el día de hoy
     *
     * @return total de la cola preorden asignada el día de hoy
     */
    @WebMethod(operationName = "obtenerTotalXFechaHoy")
    public int obtenerTotalXFechaHoy() {

        try {
            int colaFecha = 0;
            String result;
            Object resultado;

            resultado = colaPoServices.obtenerTotalXFechaHoy();
            result = resultado.toString();
            colaFecha = Integer.parseInt(result);
            return colaFecha;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Solicitudes Ingresadas en la Cola Hoy");
            return 0;
        }
    }

    /**
     * Método que obtiene el total de los operadores conectados con estado "1"
     *
     * @param estado
     * @return total de operadores conectados
     */
    @WebMethod(operationName = "obtenerTotalOperadoresConectadosXEstado")
    public int obtenerTotalOperadoresConectadosXEstado(@WebParam(name = "estado") String estado) {

        try {
            int operadoresConectados;
            String result;
            Object resultado;

            resultado = analistaServices.obtenerTotaOperadoresConectadosXEstado(estado);
            result = resultado.toString();
            operadoresConectados = Integer.parseInt(result);
            return operadoresConectados;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Operadores Conectados");
            return 0;
        }
    }

    /**
     * Método que obtiene la lista de las solicitudes procesadas el día de hoy
     *
     * @param estado
     * @return lista de solicitudes procesadas el día de hoy
     */
    @WebMethod(operationName = "listaSolicitudesProcesadasXFecha")
    public List<PreordenAnalista> listaSolicitudesProcesadasXFecha(@WebParam(name = "estado") String estado) {

        try {
            List<PreordenAnalista> listaPMA;

            listaPMA = poAnalistaServices.listaSolicitudesProcesadasXFecha(estado);
            return listaPMA;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Solicitudes Procesadas");
            return null;
        }
    }

    /**
     * Método que obtiene el total de solicitudes procesadas el día de hoy por
     * analista
     *
     * @param estado
     * @return total de solicitudes procesadas el día de hoy
     */
    @WebMethod(operationName = "obtenerSolicitudesProcesadasXFecha")
    public int obtenerSolicitudesProcesadasXFecha(@WebParam(name = "estado") String estado) {

        try {
            int contPMA;
            String result;
            Object resultado;

            resultado = poAnalistaServices.obtenerSolicitudesProcesadasXFecha(estado);
            result = resultado.toString();
            contPMA = Integer.parseInt(result);
            return contPMA;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Solicitudes Procesadas");
            return 0;
        }
    }

    /**
     * Método que obtiene el total de las solicitudes procesadas por el analista
     *
     * @param idAnalista
     * @return total de solicitudes procesdas por el analista
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
    
     /**
     * Busca el analista por el Id
     *
     * @param idAnalista
     * @return todos los datos del analista
     */

    @WebMethod(operationName = "buscarAnalista")
    public Analista buscarAnalista(@WebParam(name = "idAnalista") String idAnalista) {
        try {
            Analista find = analistaServices.find(new BigDecimal(idAnalista));
            return find;
        } catch (Exception ex) {
            return null;
        }

    }
    
     /**
     * Enlista toda la cola
     * @return la cola de preOrdenes
     */
    @WebMethod(operationName = "imprimirCola")
    public List<ColaPreorden> imprimirCola() {
        try {
           List<ColaPreorden> Cola=colaPoServices.Cola();
           int j=0;
           while(j<Cola.size()){
           Cola.get(j).getIdpreorden().setPreordenAnalista(null);
           Cola.get(j).getIdpreorden().setColaPreordenCollection(null);
           Cola.get(j).getIdpreorden().setPreordenMedicamentoList(null);
           j++;
           }
           
           return Cola;   
        } catch (Exception ex) {
            return null;
        }
         
    }
    
    /**
     * Método que lista los analista conectados
     * @param estado
     * @return lista de los analistas conectados
     */
    @WebMethod(operationName = "analistasConectados")
    public List<Analista> analistasConectados(@WebParam(name = "estado") String estado) {
        try {
           List<Analista> listaAnalista;
           listaAnalista=analistaServices.analistasConectados(estado);
           return listaAnalista;   
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Analista");
            return null;
        }
         
    }
    
    /**
     * Método que lista las solicitudes pendientes para cada analista
     * @param estado
     * @return lista de analistas con solicitudes pendientes
     */
    @WebMethod(operationName = "listaSolicitudesPendientes")
    public List<PreordenAnalista> listaSolicitudesPendientes(@WebParam(name = "estado") String estado) {

        try {
            List<PreordenAnalista> listaPMA;

            listaPMA = poAnalistaServices.listaSolicitudesPendientes(estado);
            return listaPMA;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de Solicitudes Pendientes");
            return null;
        }
    }
    
    /**
     * Método que obtiene el total de las solicitudes procesadas por el analista
     * en el día de hoy
     *
     * @param idAnalista
     * @return contador para cada analista de solicitudes procesadas el día de hoy
     */
    @WebMethod(operationName = "contarAnalistaStatusYFecha")
    public int contarAnalistaStatusYFecha(@WebParam(name = "idAnalista") String idAnalista) {

        try {
            int Cont = poAnalistaServices.contarAnalistaStatusYFecha(idAnalista);
            return Cont;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de PreOrden");
            return 0;
        }
    }
    
    /**
     * Método que obtiene el total de las solicitudes pendientes por analista
     *
     * @param idAnalista
     * @return contador para cada analista de solicitudes pendientes
     */
    @WebMethod(operationName = "contarAnalistaPendientes")
    public int contarAnalistaPendientes(@WebParam(name = "idAnalista") String idAnalista) {

        try {
            int Cont = poAnalistaServices.contarAnalistaStatusPendiente(idAnalista);
            return Cont;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de PreOrden");
            return 0;
        }
    }
}
