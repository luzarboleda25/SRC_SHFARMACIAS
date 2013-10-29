/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
import com.seguroshorizonte.horifarmacia.entidades.Preorden;
import com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamento;
import com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamentoAnalista;
import com.seguroshorizonte.horifarmacia.entidades.RegistroIngreso;
import java.math.BigDecimal;
import java.util.Date;
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
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.RegistroIngresoFacade registroIngresoServices;


    /**
     * Método que ingresa la auditoria hecha a una preorden ya procesada
     *
     * @param Preordenn
     * @param Auditor
     * @param Observacion
     * @return
     */
    @WebMethod(operationName = "ingresarAuditoria")
    public int ingresarAuditoria(@WebParam(name = "PreMedAna") Preorden Preordenn, Analista Auditor, String Observacion) {
        int Resultado = 0;
        try {
            PreordenMedicamentoAnalista PreordMedAna = PreMedAnaServices.obtenerPreordenMedicamentoAnalistaXidPreorden(Preordenn);
            PreordMedAna.setIdauditor(Auditor);
            PreordMedAna.setObservacionauditor(Observacion);
            PreMedAnaServices.edit(PreordMedAna);
            Resultado = 1;
        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }
}
