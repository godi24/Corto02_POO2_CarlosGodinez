/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.UsuariosFacadeLocal;
import com.sv.udb.modelo.Usuarios;
import com.sv.udb.utils.LOG4J;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Carlos
 */
@Named
@ViewScoped
public class UsuariosBean implements Serializable{

    @EJB
    private UsuariosFacadeLocal FCDEUsuarios;
    
    private List<Usuarios> listUsua;
    private Usuarios objeUsua;
    private boolean guardar;
    private LOG4J log;
    /**
     * Creates a new instance of UsuariosBean
     */
    public UsuariosBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.listUsua = FCDEUsuarios.findAll();
        log = new LOG4J();
        log.debug("Se ha inicializado el modelo de Usuarios");
    }

    public UsuariosFacadeLocal getFCDEUsuarios() {
        return FCDEUsuarios;
    }

    public void setFCDEUsuarios(UsuariosFacadeLocal FCDEUsuarios) {
        this.FCDEUsuarios = FCDEUsuarios;
    }

    public List<Usuarios> getListUsua() {
        return listUsua;
    }

    public void setListUsua(List<Usuarios> listUsua) {
        this.listUsua = listUsua;
    }

    public Usuarios getObjeUsua() {
        return objeUsua;
    }

    public void setObjeUsua(Usuarios objeUsua) {
        this.objeUsua = objeUsua;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    public LOG4J getLog() {
        return log;
    }

    public void setLog(LOG4J log) {
        this.log = log;
    }

    
    
}
