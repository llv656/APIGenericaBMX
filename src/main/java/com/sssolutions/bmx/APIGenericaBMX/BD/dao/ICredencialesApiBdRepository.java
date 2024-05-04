package com.sssolutions.bmx.APIGenericaBMX.BD.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sssolutions.bmx.APIGenericaBMX.BD.entity.CredencialesApiBdEntity;

/**
 * This interface represents a repository for managing API credentials in the database.
 * 
 * @author Lenin Leines Vite
 * @version 1.0.0
 */
public interface ICredencialesApiBdRepository extends JpaRepository<CredencialesApiBdEntity, Integer>{

	/**
	 * Retrieves the API credentials from the database based on the provided parameters.
	 *
	 * @param webId       The UUID of the web application.
	 * @param apiId       The ID of the API.
	 * @param endpointId  The ID of the API endpoint.
	 * @return The {@link CredencialesApiBdEntity} object representing the API credentials.
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM sss.fn_obtener_credenciales_api_bd(:ais_webapp_id, :aii_api_id, :ai_endpoint_id)")
	CredencialesApiBdEntity getBDAPICredentials(@Param("ais_webapp_id") UUID webId, @Param("aii_api_id") short apiId, @Param("ai_endpoint_id") short endpointId);

}
