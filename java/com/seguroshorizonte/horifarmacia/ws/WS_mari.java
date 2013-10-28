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
     * This is a sample web service operation
     *
     * @param txt
     * @return
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Método encargado de verificar si el usuario y la contraseña coinciden
     * para el inicio de sesión
     *
     * @param Analistaa
     * @return
     */
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

    /**
     * Método encargado de obtener la información del analista por medio del
     * nombre de usuario y realizar el registro de ingreso en la base de datos
     *
     * @param Usuario String que contiene el nombre de usuario
     * @return un objeto de la entidad Analista con la información del analista
     */
    @WebMethod(operationName = "obtenerAnalistaXUsuario")
    public Analista obtenerAnalistaXUsuario(@WebParam(name = "Usuario") String Usuario) {
        Analista Resultado = new Analista();
        RegistroIngreso Registro = new RegistroIngreso();
        if (Usuario == null) {
            Usuario = "";
        }
        try {
            Resultado = analistaServices.obtenerAnalistaXUsuario(Usuario);
            Resultado.setEstado(Short.parseShort("1"));
            analistaServices.edit(Resultado);
            Registro.setFecha(new Date());
            Registro.setAnalistaIdanalista(Resultado);
            registroIngresoServices.create(Registro);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    /**
     * Método en el cual se obtiene una lista de la entidad PreordenMedicamento
     * por medio del id de Preorden
     *
     * @param idPreorden
     * @return
     */
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
