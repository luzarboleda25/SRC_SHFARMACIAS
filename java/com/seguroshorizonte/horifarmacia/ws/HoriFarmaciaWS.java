/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.ws;


import com.seguroshorizonte.horifarmacia.entidades.Clinica;
import com.seguroshorizonte.horifarmacia.entidades.ClinicaTemporal;
import com.seguroshorizonte.horifarmacia.entidades.Dosis;
import com.seguroshorizonte.horifarmacia.entidades.Medicamento;
import com.seguroshorizonte.horifarmacia.entidades.Medico;
import com.seguroshorizonte.horifarmacia.entidades.MedicoTemporal;
import com.seguroshorizonte.horifarmacia.entidades.Tercero;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Gedica
 */

@WebService(serviceName = "HoriFarmaciaWS")
public class HoriFarmaciaWS {

    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.DosisFacade dosisServices;
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.MedicoFacade medicoServices;
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.ClinicaFacade clinicaServices;
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.ClinicaTemporalFacade clinicaTempServices;
    
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.MedicoTemporalFacade medicoTemporalServices;
    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.MedicamentoFacade medicamentosServices;
//    
    @EJB
    private com.seguroshorizonte.horifarmacia.sessionfacade.TerceroFacade terceroServices;
    
    
    @WebMethod(operationName = "listarDosis")
    public List<Dosis> listarDosis() {
        return dosisServices.findAll();
    }
    
    @WebMethod(operationName = "obtenerMedicoXCILike")
    public List<Medico> obtenerMedicoXCILike(@WebParam(name = "tipoid") String tipoId,@WebParam(name = "numid") String numId) {
        
        if(numId==null){
            numId="";
        }
        if(tipoId==null){
            tipoId="";
        }
        
        return medicoServices.obtenerMedicoXCILike(tipoId, numId+"%");
    }
    
    @WebMethod(operationName = "obtenerMedicoTemporalXCILike")
    public List<MedicoTemporal> obtenerMedicoTemporalXCILike(@WebParam(name = "tipoid") String tipoId,@WebParam(name = "numid") String numId) {
        
        if(numId==null){
            numId="";
        }
        if(tipoId==null){
            tipoId="";
        }
        
        return medicoTemporalServices.obtenerMedicoTemporalXCILike(tipoId, numId+"%");
    }
    
    @WebMethod(operationName = "obtenerTodosMedicos")
    public List<Medico> obtenerTodosMedicos() {
        
        return medicoServices.findAll();
    }
    
    @WebMethod(operationName = "registrarMedicoTemporal")
    public boolean registrarMedicoTemporal(@WebParam(name = "medicox") MedicoTemporal medico) {
        
        if( medico != null ){
            medicoTemporalServices.create(medico);
            return true;
        }
        return false;
    }
    
    @WebMethod(operationName = "buscarClinicaXNombreLike")
    public List<Tercero> buscarClinicaXNombreLike(@WebParam(name = "patron") String patron) {
        if(patron==null){
            patron="";
        }
        List<Tercero> clinicas = clinicaServices.obtenerClinicaXPatronLike(patron);
        return clinicas;
    }
    
    @WebMethod(operationName = "buscarClinicaTemporalXNombreLike")
    public List<ClinicaTemporal> buscarClinicaTemporalXNombreLike(@WebParam(name = "patron") String patron) {
        if(patron==null){
            patron="";
        }
        List<ClinicaTemporal> clinicasTemporales = clinicaTempServices.obtenerClinicaTemporalXNombreLike(patron);
        return clinicasTemporales;
    }
    
    
    
    @WebMethod(operationName = "registrarClinicaTemporal")
    public boolean registrarClinicaTemporal(@WebParam(name = "clinicax") ClinicaTemporal clinicaTemp) {
        
        if( clinicaTemp != null ){
            clinicaTempServices.create(clinicaTemp);
            return true;
        }
        return false;
    }
    
    @WebMethod(operationName = "buscarMedicoXCedulaLike")
    public List<Tercero> buscarMedicoXCedulaLike(@WebParam(name = "tipoid") String tipoid,@WebParam(name = "numid") String numid) {
        if(tipoid==null){
            tipoid="";
        }
        if(numid==null){
            numid="";
        }
        List<Tercero> medicos = medicoServices.obtenerMedicoXNumidLike(tipoid, numid);
        return medicos;
    }
    
    
    
    
//    
//    @WebMethod(operationName = "obtenerCentroAsistencialLIKE")
//    public List<CentroAsistencial> obtenerCentroAsistencialLIKE(@WebParam(name = "nombre") String nombre) {
//        List<CentroAsistencial> listaCentros = centroAsistServices.findByNombreLIKE(nombre.toUpperCase(),"T");
//        return listaCentros;
//    }
//    
//    @WebMethod(operationName = "validarCentroAXNombre")
//    public List<CentroAsistencial> validarCentroAXNombre(@WebParam(name = "nombre") String nombre) {
//        List<CentroAsistencial> listaCentros = centroAsistServices.findByNombreAndTemporal(nombre.toUpperCase(),"T");
//        return listaCentros;
//    }
    
    @WebMethod(operationName = "obtenerMedicamentosXNombre")
    public List<Medicamento> obtenerMedicamentosXNombre(@WebParam(name = "nombre") String nombre) {
        List<Medicamento> listaMedicamentos = medicamentosServices.findMedicamentoJoinPAXNombre( "%" + nombre.toUpperCase() + "%" );
        return listaMedicamentos;
    }
//    
//    
    
}
