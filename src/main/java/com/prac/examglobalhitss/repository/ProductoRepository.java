package com.prac.examglobalhitss.repository;

import com.prac.examglobalhitss.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;


@Repository
public class ProductoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Producto> getProductoList(Producto producto){

        SimpleJdbcCall simpleJdbcCall= new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("DEVDB")
                .withProcedureName("productoprocedure")
                .declareParameters(new SqlParameter[]{
                        new SqlParameter("V_ID_PROD", Types.INTEGER),
                        new SqlParameter("V_NOMBRE", Types.VARCHAR),
                        new SqlParameter("V_FEC_REGISTRO", Types.DATE),
                        new SqlParameter("c_cursor", Types.REF_CURSOR),
                        new SqlParameter("c_sqlcode", Types.INTEGER),
                        new SqlParameter("c_sqlmsg", Types.VARCHAR)
                        }).returningResultSet("c_cursor", new RowMapper<Producto>() {
                    @Override
                    public Producto mapRow(ResultSet rs, int rowNum) throws SQLException{
                        Producto product = new Producto();
                        product.setIdProd(rs.getInt("ID_PROD"));
                        product.setNombre(rs.getString("NOMBRE"));
                        product.setFecRegistro(rs.getDate("FEC_REGISTRO"));
                        return product;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("V_ID_PROD", producto.getIdProd());
        mapSqlParameterSource.addValue("V_NOMBRE", producto.getNombre());
        mapSqlParameterSource.addValue("V_FEC_REGISTRO", producto.getFecRegistro());

        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Producto> productoList = (List<Producto>) results.get("c_cursor");

        return productoList;
    }

}
