/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;

import com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamentoAnalista;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Pangea
 */
@WebService(serviceName = "Joseweb")
public class Joseweb {
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.PreordenMedicamentoAnalistaFacade poMedicamentoAnalistaServices;
       

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "listaPreordenMedicamentoAnalistaXidAnalista")
    public List<PreordenMedicamentoAnalista> listaPreordenMedicamentoAnalistaXidAnalista(@WebParam(name = "idAnalista") String idAnalista) {

        try {
            List<PreordenMedicamentoAnalista> listaPMA;
            listaPMA = poMedicamentoAnalistaServices.listarPreOrdenProcesadasXidAnalista(idAnalista);
            return listaPMA;
        } catch (Exception ex) {
            System.out.println("ERROR de la busqueda de PreOrden");
            return null;
        }
    }
}
