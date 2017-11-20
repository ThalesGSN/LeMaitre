/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.domain;

/**
 *
 * @author thales
 */
public class Image {   
    private Long codImage;
    
    private String location;

    public Image() {
    }
    
    public Image(Long codImage, String location) {
        this.codImage = codImage;
        this.location = location;
    }
    
    /**
     * Get the value of location
     *
     * @return the value of location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the value of location
     *
     * @param location new value of location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the value of codImage
     *
     * @return the value of codImage
     */
    public Long getCodImage() {
        return codImage;
    }

    /**
     * Set the value of codImage
     *
     * @param codImage new value of codImage
     */
    public void setCodImage(Long codImage) {
        this.codImage = codImage;
    }

    
}
