package com.prac.examglobalhitss.controller;

import com.prac.examglobalhitss.model.Producto;
import com.prac.examglobalhitss.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("v1/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping()
    public List<Producto> getProductosList(Producto producto){

        List<Producto> productoList = productoService.getProductosList(producto);
        return productoList;
    }

}
