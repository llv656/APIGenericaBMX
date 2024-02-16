package com.sssolutions.bmx.APIGenericaBMX.BD.dao.operations;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.APIGenericaBMX.BD.entity.SpSimpleResponseEntity;
import com.sssolutions.bmx.APIGenericaBMX.BD.dao.IUserExampleRepository;
import com.sssolutions.bmx.APIGenericaBMX.BD.entity.FnUsuarioEntity;
import com.sssolutions.bmx.APIGenericaBMX.BD.entity.mapper.SpSimpleResponseRowMapper;
import com.sssolutions.bmx.APIGenericaBMX.dto.ResponseServiceDTO;
import com.sssolutions.bmx.APIGenericaBMX.BD.entity.mapper.FnUsuarioMapper;
import com.sssolutions.bmx.RepositoryBMX.DaoBMX;
import com.sssolutions.bmx.RepositoryBMX.enums.DAOStatus;
import com.sssolutions.bmx.RepositoryBMX.pojo.ResponseDaoDTO;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserExampleOperations implements IUserExampleRepository {
	private static final Logger LOGGER = LogManager.getLogger(UserExampleOperations.class);
	
	private DaoBMX bdService;

	@Override
	public ResponseDaoDTO addClient (RequestAddUserExampleModel userModel, CompletableFuture<ResponseServiceDTO> credentiaslAsyncResponseDAO){
		LOGGER.info("\t\tMethod: ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
		
		ResponseDaoDTO responseDaoDto = new ResponseDaoDTO();
		
		try {
			Function<NamedParameterJdbcTemplate, ResponseDaoDTO> callback  = jt-> {
				ResponseDaoDTO r = new ResponseDaoDTO();

				SqlParameterSource parameters = new MapSqlParameterSource()
						.addValue("nombre", userModel.getNombre())
						.addValue("aPaterno", userModel.getApellidoPaterno())
						.addValue("aMaterno", userModel.getApellidoMaterno())
						.addValue("fNacimiento", userModel.getFechaNacimiento())
						.addValue("tipoUsuarioId", userModel.getIdTipoUsuario().shortValue())
						.addValue("exitoSalida",  null)
						.addValue("mensajeSalida", null);
				
				SpSimpleResponseEntity entity = jt.queryForObject(
						"call test_sss.sp_registrar_usuario(:nombre, :aPaterno, :aMaterno, :fNacimiento, :tipoUsuarioId, :exitoSalida, :mensajeSalida)",
						parameters,
						new SpSimpleResponseRowMapper());
	            
	            r.setValid(entity.getValid());
				r.setGenericDao(entity.getValid() ? DAOStatus.successful : DAOStatus.err_execution);
				r.setMessage(new String[] { entity.getMessage() });
				return r;
			};
			
			CompletableFuture<ResponseDaoDTO> responseFuture = credentiaslAsyncResponseDAO.thenComposeAsync(c -> {
				
				Map<String, String> credentiaslResponse = (Map<String, String>) c.getResult();
				NamedParameterJdbcTemplate jdbcTemplate = bdService.transformDataSourceAtJdbcTemplate.apply(credentiaslResponse);
				
				ResponseDaoDTO response = bdService.executeAndTransformResponse.apply(callback, jdbcTemplate);		
				
				return CompletableFuture.completedFuture(response);
				
			});
			responseDaoDto = responseFuture.get();
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		LOGGER.info("\t\tTermina ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
		return responseDaoDto;
		
	}

	@Override
	public ResponseDaoDTO getClient(Integer userId, Map<String, String> dbConnectionDetails) {
		LOGGER.info("\t\tMethod: ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
		
		NamedParameterJdbcTemplate jdbcTemplate = bdService.transformDataSourceAtJdbcTemplate.apply(dbConnectionDetails);
		
		Function<NamedParameterJdbcTemplate, ResponseDaoDTO> callback  = jt-> {
			ResponseDaoDTO responseDAO = new ResponseDaoDTO(); 
			SqlParameterSource parameters = new MapSqlParameterSource().addValue("userId", userId);
			
			FnUsuarioEntity entity = jt.queryForObject(
					"select * from test_sss.fn_obtener_user(:userId)",
					parameters,
					new FnUsuarioMapper());
			
			responseDAO.setValid(true);
            responseDAO.setGenericDao(DAOStatus.successful);
			responseDAO.setResult(entity);
			return responseDAO; 
		};
		
		ResponseDaoDTO responseDAO = bdService.executeAndTransformResponse.apply(callback, jdbcTemplate);
		
		LOGGER.info("\t\tTermina ".concat(new Object(){}.getClass().getEnclosingMethod().getName()));
	
		return responseDAO;
	}
	
}
