/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
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
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.AnalistaFacade analistaServices;
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.PreordenMedicamentoAnalistaFacade PreMedAnaServices;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "logIn")
    public int logIn(@WebParam(name = "Analistaa") Analista Analistaa) {
        int Respuesta = 0;
        if (Analistaa == null) {
            return Respuesta;
        }
        try {
            Respuesta = analistaServices.verificarLogIn(Analistaa);
        } catch (Exception e) {
            return 0;
        } 
        return Respuesta;
    }

    @WebMethod(operationName = "obtenerAnalistaXUsuario")
    public Analista obtenerAnalistaXUsuario(@WebParam(name = "Usuario") String Usuario) {
        Analista Resultado = new Analista();
        if (Usuario == null) {
            Usuario = "";
        }
        try {
            Resultado = analistaServices.obtenerAnalistaXUsuario(Usuario);
            Resultado.setEstado(BigDecimal.ONE);
            analistaServices.edit(Resultado);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "obtenerPreordenMedicamentoXIdPreorden")
    public List<PreordenMedicamento> obtenerPreordenMedicamentoXIdPreorden(@WebParam(name = "idPreorden") String idPreorden) {
        if (idPreorden == null) {
            return null;
        }
        Preorden Preorden = new Preorden();
        Preorden.setIdpreorden(new BigDecimal(idPreorden));
        List<PreordenMedicamento> Resultado = preordenMedicamentoServices.obtenerListadoMedicamentosXIdpreorden(Preorden);
        if (Resultado != null && !Resultado.isEmpty()) {
            return Resultado;
        }
        return null;
    }
    /* falta agregar facade
     @WebMethod(operationName = "ingresarAuditoria")
     public int ingresarAuditoria(@WebParam(name = "PreMedAna") PreordenMedicamentoAnalista PreMedAna) {
     int Resultado = 0;
     try {
     PreordenMedicamentoAnalista PreordMedAna = PreMedAnaServices.obtenerPreordenMedicamentoAnalistaXidPreordenMedicamento(PreMedAna.getPreordMedId());
     PreMedAna.setIdpreMedAna(PreordMedAna.getIdpreMedAna());
     PreMedAnaServices.edit(PreMedAna);
     Resultado = 1;
     } catch (Exception e) {
     Resultado = 0;
     }
     return Resultado;
     }
     */
}
