/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.sessionfacade;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pangea
 */
@Stateless
public class AnalistaFacade extends AbstractFacade<Analista> {

    @PersistenceContext(unitName = "HoriFarmaciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnalistaFacade() {
        super(Analista.class);
    }

    public Object obtenerTotaOperadoresConectadosXEstado(int estado) {

        Object resultCont;
        Query query = em.createNamedQuery("Analista.findByEsta", Analista.class);
        query.setParameter("estado", estado);
        resultCont = query.getSingleResult();
        return resultCont;
    }

    public int verificarLogIn(Analista Analistaa) {
        Query query = em.createNamedQuery("Analista.findByUsuarioYContrasena", Analista.class);
        query.setParameter("usuario", Analistaa.getUsuario());
        query.setParameter("contrasena", Analistaa.getContrasena());
        Analista Resultado = (Analista) query.getSingleResult();
        if (Resultado == null) {
            return 0;
        }
        return 1;
    }

    public Analista obtenerAnalistaXUsuario(String Usuario) {
        Query query = em.createNamedQuery("Analista.findByUsuario", Analista.class);
        query.setParameter("usuario",Usuario);
        Analista Resultado = (Analista) query.getSingleResult();
        return Resultado;
    }
}
