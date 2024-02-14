package com.sssolutions.bmx.APIGenericaBMX.BD.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sssolutions.bmx.APIGenericaBMX.BD.entity.FnUsuarioEntity;

public class FnUsuarioMapper implements RowMapper<FnUsuarioEntity> {

	@Override
	public FnUsuarioEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		FnUsuarioEntity entity = new FnUsuarioEntity();
		
		entity.setUsuarioId(resultSet.getInt("ci_usuario_id"));
        entity.setNombreUsuario(resultSet.getString("cs_nombre"));
        entity.setApellidoPaterno(resultSet.getString("cs_apellido_paterno"));
        entity.setApellidoMaterno(resultSet.getString("cs_apellido_materno"));
        entity.setFechaNacimiento(resultSet.getDate("cd_fecha_nacimiento").toLocalDate());
        entity.setTipoUsuarioId(resultSet.getInt("ci_tipo_usuario_id"));
        entity.setEstatusId(resultSet.getInt("ci_estatus_id"));
        entity.setFechaCreacion(resultSet.getTimestamp("cts_fecha_creacion").toLocalDateTime());
        
		return entity;
	}

}
