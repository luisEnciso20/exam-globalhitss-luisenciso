package com.prac.examglobalhitss.service;

import com.prac.examglobalhitss.model.Producto;
import com.prac.examglobalhitss.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> getProductosList(Producto producto){

        List<Producto> productosList = productoRepository.getProductoList(producto);
        return productosList;

    }
}
