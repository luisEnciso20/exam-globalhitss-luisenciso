package com.prac.examglobalhitss.model;

import lombok.Data;

import java.util.Date;

@Data
public class Producto {
    private Integer idProd ;
    private String nombre;
    private Date fecRegistro;
}
