/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;

import com.seguroshorizonte.horifarmacia.entidades.Dosis;
import com.seguroshorizonte.horifarmacia.entidades.Medicamento;
import com.seguroshorizonte.horifarmacia.entidades.Preorden;
import com.seguroshorizonte.horifarmacia.entidades.PreordenArchivo;
import com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamento;
import com.seguroshorizonte.horifarmacia.entidades.TipoDoc;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Gedica
 */
@WebService(serviceName = "PreOrdenWS")
public class PreOrdenWS {

    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.PreordenFacade poServices;
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.PreordenMedicamentoFacade poMedicamentoServices;
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.DosisFacade dosisServices;
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.MedicamentoFacade medicamentoServices;
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.TipoDocFacade tipodocServices;  
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.PreordenArchivoFacade preordenArchivoServices; 

    @WebMethod(operationName = "ingresarPreOrden")
    public Preorden ingresarPreOrden(@WebParam(name = "po") Preorden preOrden) {

        try {
            System.out.println("Inicio ingresarPreOrden");
            preOrden.setFecha(new Date());
            preOrden.setFechainformemedico(new Date());
            poServices.create(preOrden);
            poServices.actualizarCotexto();
            //Preorden find = poServices.find(preOrden);

            preOrden.setCodigo("FARM-" + Calendar.getInstance().get(Calendar.YEAR) + "-" + preOrden.getIdpreorden());

            poServices.edit(preOrden);
            poServices.actualizarCotexto();
            return preOrden;
        } catch (Exception ex) {
            return null;
        }
    }

    @WebMethod(operationName = "ingresarPreOrdenMedicamento")
    public boolean ingresarPreOrdenMedicamento(@WebParam(name = "pm") PreordenMedicamento pom, @WebParam(name = "iddosis") String iddosis, @WebParam(name = "idmedicamento") String idmedicamento, @WebParam(name = "idpreorden") String idpreorden) {

        try {
            System.out.println("Inicio ingresarPreOrdenMedicamento");
            Dosis dosisFind = dosisServices.find(new BigDecimal(iddosis));
            Medicamento medicFind = medicamentoServices.find(new BigDecimal(idmedicamento));
            Preorden poFind = poServices.find(new BigDecimal(idpreorden));

            pom.setIddosis(dosisFind);
            pom.setIdmedicamento(medicFind);
            pom.setIdpreorden(poFind);

            poMedicamentoServices.create(pom);
            return true;
        } catch (Exception ex) {
            System.out.println("No se guardo PreOrdenMedicamento");
            return false;
        }
    }

    @WebMethod(operationName = "ingresarPreOrdenArchivo")
    public boolean ingresarPreOrdenArchivo(@WebParam(name = "nombre") String nombre, @WebParam(name = "idtipodoc") String idtipodoc, @WebParam(name = "idpreorden") String idpreorden) {

        try {
        
            PreordenArchivo archivo = new PreordenArchivo();
            
            archivo.setNombre(nombre);
            
            TipoDoc tipodocFind = tipodocServices.find(new BigDecimal(idtipodoc));
            Preorden poFind = poServices.find(new BigDecimal(idpreorden));

            archivo.setIdpreorden(poFind);
            archivo.setIdtipodoc(tipodocFind);

            preordenArchivoServices.create(archivo);
            return true;
        } catch (Exception ex) {
            System.out.println("No se guardo PreOrdenArchivo");
            return false;
        }
    }
    
    
    @WebMethod(operationName = "listarPreOrdenXCodcli")
    public List<Preorden> listarPreOrdenXCodcli(@WebParam(name = "codcli") String codcli) {

        try {
            List<Preorden> listaPreOrden = poServices.listarPreOrdenXCodcli(codcli);

            return listaPreOrden;
        } catch (Exception ex) {
            return null;
        }
    }
    
    
    @WebMethod(operationName = "buscarPreOrdenXIdepreorden")
    public Preorden buscarPreOrdenXIdepreorden(@WebParam(name = "idpreorden") String idepreorden) {

        try {
            Preorden listaPreOrden = poServices.buscarPreOrdenXIdepreorden(idepreorden);
            return listaPreOrden;
        } catch (Exception ex) {
            return null;
        }
    }
    
    
    @WebMethod(operationName = "buscarPreOrdenMedicamentosXIdepreorden")
    public List<PreordenMedicamento> buscarPreOrdenMedicamentosXIdepreorden(@WebParam(name = "idpreorden") String idepreorden) {

        try {
            Preorden preOrden = poServices.findByIdpreorden(new BigInteger(idepreorden));
            List<PreordenMedicamento> preordenMedicamentoList = poMedicamentoServices.obtenerListadoMedicamentosXIdpreorden(preOrden);
            System.out.println("size: " + preordenMedicamentoList);
            return preordenMedicamentoList;
        } catch (Exception ex) {
            System.out.println("Hubo error");
            return null;
        }
    }
}
