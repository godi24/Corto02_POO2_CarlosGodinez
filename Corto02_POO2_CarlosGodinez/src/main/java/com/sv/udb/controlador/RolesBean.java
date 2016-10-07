/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.RolesFacadeLocal;
import com.sv.udb.modelo.Roles;
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
@Named(value = "rolesBean")
@ViewScoped
public class RolesBean implements Serializable{
    @EJB
    private RolesFacadeLocal FCDERoles;
    
    private List<Roles> listRole;
    private Roles objeRole;
    private boolean guardar;
    private LOG4J log;
    
    /**
     * Creates a new instance of RolesBean
     */
    public RolesBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.listRole = FCDERoles.findAll();
        log = new LOG4J();
        log.debug("Se ha inicializado el modelo de Roles");
    }

    public RolesFacadeLocal getFCDERoles() {
        return FCDERoles;
    }

    public void setFCDERoles(RolesFacadeLocal FCDERoles) {
        this.FCDERoles = FCDERoles;
    }

    public List<Roles> getListRole() {
        return listRole;
    }

    public void setListRole(List<Roles> listRole) {
        this.listRole = listRole;
    }

    public Roles getObjeRole() {
        return objeRole;
    }

    public void setObjeRole(Roles objeRole) {
        this.objeRole = objeRole;
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
