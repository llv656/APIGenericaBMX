package com.sssolutions.bmx.APIGenericaBMX.BD.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sssolutions.bmx.APIGenericaBMX.BD.entity.SpSimpleResponseEntity;

public class SpSimpleResponseRowMapper implements RowMapper<SpSimpleResponseEntity> {

	@Override
	public SpSimpleResponseEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		SpSimpleResponseEntity entity = new SpSimpleResponseEntity();
		entity.setValid(rs.getBoolean("aob_exito"));
		entity.setMessage(rs.getString("aos_mensaje"));
		
		return entity;
	}

}
