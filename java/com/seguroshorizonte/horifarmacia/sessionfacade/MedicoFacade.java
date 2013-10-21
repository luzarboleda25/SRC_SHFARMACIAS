/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.Medico;
import com.seguroshorizonte.horifarmacia.entidades.Tercero;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Gedica
 */
@Stateless
public class MedicoFacade extends AbstractFacade<Medico> {
    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicoFacade() {
        super(Medico.class);
    }
    /**
     * Trae listado de medicos los cuales la cedula comience en el valor que tenga numid.
     * @param tipoid
     * @param numid
     * @return 
     */
    public List<Medico> obtenerMedicoXCILike(String tipoid , String numid){
    
        Query query = em.createNamedQuery("Medico.findByNumidYTipoid",Medico.class);
        query.setParameter("numid",numid.toUpperCase());//cualquiera que comience con el valor de numid
        query.setParameter("tipoid",tipoid.toUpperCase());
        
        List<Medico> resultList = query.getResultList();
        
        return resultList;
    }
    
    /**
     * Valida si el medico existe.
     * @param tipoid
     * @param numid
     * @return 
     */
    public boolean validarMedico(String tipoid , String numid){
    
        Query query = em.createNamedQuery("Medico.validarMedico",Medico.class);
        query.setParameter("numid",numid );
        query.setParameter("tipoid",tipoid );
        
        List<Medico> resultList = query.getResultList();
        System.out.println("size" + resultList.size() );
        
        if(resultList != null && !resultList.isEmpty()){
           return true;
        }
        System.out.println("no se consiguio registro");
        return false;//no se consiguio medico con la ci enviada
    }
    
    /**
     * metodo que busca un medico por tipoid y numid estrictamente iguales.
     * @param tipoid
     * @param numid
     * @return medico si existe
     */
    public Medico buscarMedico(String tipoid , String numid){
        
        Query query = em.createNamedQuery("Medico.findByNumidYTipoid",Medico.class);
        query.setParameter("numid",numid );
        query.setParameter("tipoid",tipoid );
        
        List<Medico> resultList = query.getResultList();
       
        return super.getFirst(resultList);
    }
    
     public List<Tercero> obtenerMedicoXNombreLike(String nombre) {

        StringBuilder sql = new StringBuilder();
        
        sql.append("select ter.* ");
        sql.append("from medico me ");
        sql.append("inner join tercero ter ");
        sql.append("on me.tipoid = ter.tipoid ");
        sql.append("and me.numid = ter.numid ");
        sql.append("and me.dvid = ter.dvid ");
        sql.append("and (ter.nomter LIKE ").append("'%").append(nombre.toUpperCase()).append("%' ");
        sql.append("or ter.apeter LIKE ").append("'%").append(nombre.toUpperCase()).append("%' )");
        
        Query query = em.createNativeQuery(sql.toString(), Tercero.class);

        List<Tercero> resultList = query.getResultList();

        return resultList;
    }
    
     public List<Tercero> obtenerMedicoXNumidLike(String tipoid ,String numid) {

        StringBuilder sql = new StringBuilder();
        
        sql.append("select ter.* ");
        sql.append("from medico me ");
        sql.append("inner join tercero ter ");
        sql.append("on me.tipoid = ter.tipoid ");
        sql.append("and me.dvid = ter.dvid ");
        sql.append("and me.numid = ter.numid ");
        sql.append("where me.tipoid = '").append(tipoid.toUpperCase()).append("' ");
        sql.append("and  to_char(me.numid) LIKE ").append("'%").append(numid.toUpperCase()).append("%' ");
        
        Query query = em.createNativeQuery(sql.toString(), Tercero.class);

        List<Tercero> resultList = query.getResultList();

        return resultList;
    }
    
}
