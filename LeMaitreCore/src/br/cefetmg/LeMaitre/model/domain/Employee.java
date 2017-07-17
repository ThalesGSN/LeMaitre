/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.domain;

/**
 *
 * @author Thalesgsn
 */
public class Employee {
    private Integer codID;
    
    private String nomName;
    
    private char idtProfile;

    private String nomUsername;

    private String txtPassword;

    public Employee() { }

    public Employee(Integer codID, String nomName, char idtProfile, String nomUsername, String txtPassword) {
        this.codID = codID;
        this.nomName = nomName;
        this.idtProfile = idtProfile;
        this.nomUsername = nomUsername;
        this.txtPassword = txtPassword;
    }

    /**
     * Get the value of codID
     *
     * @return the value of codID
     */
    public Integer getCod_ID() {
        return codID;
    }

    /**
     * Set the value of codID
     *
     * @param codID new value of codID
     */
    public void setCod_ID(Integer codID) {
        this.codID = codID;
    }

    /**
     * Get the value of nomName
     *
     * @return the value of nomName
     */
    public String getNom_name() {
        return nomName;
    }

    /**
     * Set the value of nomName
     *
     * @param nomName new value of nomName
     */
    public void setNom_name(String nomName) {
        this.nomName = nomName;
    }
    
    /**
     * Get the value of idtProfile
     *
     * @return the value of idtProfile
     */
    public char getidtProfile() {
        return idtProfile;
    }

    /**
     * Set the value of idtProfile
     *
     * @param idtProfile new value of idtProfile
     */
    public void setidtProfile(char idtProfile) {
        this.idtProfile = idtProfile;
    }
    
    /**
     * Get the value of nomUsername
     *
     * @return the value of nomUsername
     */
    public String getNom_username() {
        return nomUsername;
    }

    /**
     * Set the value of nomUsername
     *
     * @param nomUsername new value of nomUsername
     */
    public void setNom_username(String nomUsername) {
        this.nomUsername = nomUsername;
    }

    /**
     * Get the value of txtPassword
     *
     * @return the value of txtPassword
     */
    public String getTxt_password() {
        return txtPassword;
    }

    /**
     * Set the value of txtPassword
     *
     * @param txtPassword new value of txtPassword
     */
    public void setTxt_password(String txtPassword) {
        this.txtPassword = txtPassword;
    }
}
