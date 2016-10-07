/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.UsuariosRolesFacadeLocal;
import com.sv.udb.modelo.UsuariosRoles;
import com.sv.udb.utils.LOG4J;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Carlos
 */
@Named(value = "usuariosRolesBean")
@ViewScoped
public class UsuariosRolesBean implements Serializable{
    @EJB
    private UsuariosRolesFacadeLocal FCDEUsuariosRoles;    
    private UsuariosRoles objeUsuaRole;
    private List<UsuariosRoles> listUsuaRole;
    private boolean guardar;
    private LOG4J log;
    /**
     * Creates a new instance of UsuariosRolesBean
     */
    public UsuariosRolesBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
        log = new LOG4J();
        log.debug("Se ha inicializado el modelo de Usuarios-Roles");
    }

    public UsuariosRolesFacadeLocal getFCDEUsuariosRoles() {
        return FCDEUsuariosRoles;
    }

    public void setFCDEUsuariosRoles(UsuariosRolesFacadeLocal FCDEUsuariosRoles) {
        this.FCDEUsuariosRoles = FCDEUsuariosRoles;
    }

    public UsuariosRoles getObjeUsuaRole() {
        return objeUsuaRole;
    }

    public void setObjeUsuaRole(UsuariosRoles objeUsuaRole) {
        this.objeUsuaRole = objeUsuaRole;
    }

    public List<UsuariosRoles> getListUsuaRole() {
        return listUsuaRole;
    }

    public void setListUsuaRole(List<UsuariosRoles> listUsuaRole) {
        this.listUsuaRole = listUsuaRole;
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
    
    
    
    public void limpForm()
    {
        this.objeUsuaRole = new UsuariosRoles();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeUsuaRole.setFechAltaRole(new Date());
            FCDEUsuariosRoles.create(this.objeUsuaRole);
            this.listUsuaRole.add(this.objeUsuaRole);
            log.info("Asignación de usuarios-roles agregada: " +objeUsuaRole.getCodiUsuaRole());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            this.limpForm();
        }
        catch(Exception ex)
        {
            log.error("Error al agregar asignación de usuarios-roles: " +  String.valueOf(ex.fillInStackTrace()));
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
        finally
        {
            
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            if(this.objeUsuaRole.getEstaUsuaRole() == 0)
            {
                this.objeUsuaRole.setFechBajaRole(new Date());
            }
            this.listUsuaRole.remove(this.objeUsuaRole); //Limpia el objeto viejo
            FCDEUsuariosRoles.edit(this.objeUsuaRole);
            this.listUsuaRole.add(this.objeUsuaRole); //Agrega el objeto modificado
            log.info("Asignación de usuarios-roles modificada: " +objeUsuaRole.getCodiUsuaRole());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            log.error("Error al modificar asignación de usuarios-roles: " +  String.valueOf(ex.fillInStackTrace()));
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
        finally
        {
            
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEUsuariosRoles.remove(this.objeUsuaRole);
            this.listUsuaRole.remove(this.objeUsuaRole);
            log.info("Asignación de usuarios-roles eliminada: " +objeUsuaRole.getCodiUsuaRole());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            this.limpForm();
        }
        catch(Exception ex)
        {
            log.error("Error al eliminar asignación de usuarios-roles: " +  String.valueOf(ex.fillInStackTrace()));
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
        finally
        {
            
        }
    }
    
    public void consTodo()
    {
        try
        {
            this.listUsuaRole = FCDEUsuariosRoles.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiUsuaRolePara"));
        try
        {
            this.objeUsuaRole = FCDEUsuariosRoles.find(codi);
            this.guardar = false;
            log.info("Asignación de usuarios-roles consultada: " +objeUsuaRole.getCodiUsuaRole());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado')");
        }
        catch(Exception ex)
        {
            log.error("Error al consultar asignación de usuarios-roles: " +  String.valueOf(ex.fillInStackTrace()));
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
        finally
        {
            
        }
    }
}
