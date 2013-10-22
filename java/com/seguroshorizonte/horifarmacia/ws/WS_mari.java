/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;

import com.seguroshorizonte.horifarmacia.entidades.Preorden;
import com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamento;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author pc
 */
@WebService(serviceName = "WS_mari")
public class WS_mari {

    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.PreordenFacade preordenServices;
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.PreordenMedicamentoFacade preordenMedicamentoServices;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }



    @WebMethod(operationName = "obtenerPreordenMedicamentoXIdPreorden")
    public List< PreordenMedicamento> obtenerPreordenMedicamentoXIdPreorden(@WebParam(name = "idPreorden") String idPreorden) {
        if (idPreorden == null) {
            return null;
        }
        Preorden Preorden = new Preorden();
        Preorden.setIdpreorden(new BigDecimal(idPreorden));
        List<PreordenMedicamento> Resultado = preordenMedicamentoServices.obtenerListadoMedicamentosXIdpreorden(Preorden);
        if (Resultado != null && !Resultado.isEmpty()) {
            System.out.println("__________________"+Resultado.get(0).getIdmedicamento().getDescripcion());    ;
            return Resultado;
        }
        return null;
    }
}
