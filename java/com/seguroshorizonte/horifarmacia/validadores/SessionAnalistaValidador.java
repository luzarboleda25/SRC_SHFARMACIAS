/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.validadores;

import com.seguroshorizonte.horifarmacia.entidades.Analista;
import com.seguroshorizonte.horifarmacia.envoltorios.WR_resultado;


/**
 * Esta clase posee los metodos necesarios para validar los parametros
 * introducidos a las operaciones web del servicio
 * Gestion_De_Control_De_Usuarios. el objetivo de esta clase solo es verificar
 * que los atributos necesarios de los objetos ingresados existen y tienen un
 * formato correcto, no se verificara la coerencia de los datos con el sistema
 * de base de datos ni la congruencia entre ellos
 *
 * <p>Las operaciones de esta clase retornan objetos del tipo wrapper que
 * encapsulan el resultado de las operaciones e informacion sobre errores
 *
 * @author pangea technologies c.a.
 */
public class SessionAnalistaValidador {

    /**
     * Valida que los atributos id y clave del usuario introducido tengan un
     * formato valido. El objetivo de este metodo es validar que los atributos
     * del usuario introducido como parametro a la operacion LogIn existan y
     * tengan un foormato valido.
     *
     * @param usuarioActual objeto de la clase usuario cuyos atributos id y
     * clave deben ser un numero natural y una cadena no vacia de caracteres
     * respectivamente
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la validacion
     * @see WR_resultado
     */
    public WR_resultado validarLogIn(Analista sesionActual) {
        WR_resultado Resultado = new WR_resultado();
        return Resultado;
    }

    /**
     * Valida que el atributo id del usuario introducido sea un numero natural.
     * El objetivo de este metodo es validar que el usuario ingresado a la
     * operacion LogOut tenga un formato valido.
     *
     * @param usuarioActual objeto de la clase usuario cuyo id debe ser un
     * numero natural
     * @return objeto de la clase WR_resultado que informa sobre el resultado de
     * la validacion
     * @see WR_resultado
     */
    public WR_resultado validarLogOut(Analista sesionActual) {
        WR_resultado Resultado = new WR_resultado();
        
        return Resultado;
    }
}
