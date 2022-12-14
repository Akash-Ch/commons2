package io.mosip.kernel.uingenerator.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.mosip.kernel.core.authmanager.authadapter.spi.VertxAuthenticationProvider;
import io.mosip.kernel.uingenerator.constant.UinGeneratorConstant;
import io.mosip.kernel.uingenerator.entity.BaseEntity;
import io.vertx.ext.web.RoutingContext;

/**
 * Utility class for uingenerator
 * 
 * @author Dharmesh Khandelwal
 * @author Megha Tanga
 * @since 1.0.0
 *
 */
@Component
public class UINMetaDataUtil {
	
	@Autowired
	private VertxAuthenticationProvider authHandler;

	/**
	 * Function to set metadata
	 * 
	 * @param entity entity
	 * @return <T> Entity with metadata
	 */
	public <T extends BaseEntity> T setCreateMetaData(T entity) {
		String contextUser = UinGeneratorConstant.DEFAULTADMIN_MOSIP_IO;
		LocalDateTime time = LocalDateTime.now(ZoneId.of(UinGeneratorConstant.UTC));
		entity.setCreatedBy(contextUser);
		entity.setCreatedtimes(time);
		entity.setIsDeleted(false);
		return entity;
	}

	/**
	 * Function to set metadata for update operation
	 * 
	 * @param entity entity
	 * @return <T> Entity with metadata
	 */
	public <T extends BaseEntity> T setUpdateMetaData(T entity, RoutingContext routingContext) {
		String contextUser = authHandler.getContextUser(routingContext);
		LocalDateTime time = LocalDateTime.now(ZoneId.of(UinGeneratorConstant.UTC));
		entity.setUpdatedBy(contextUser);
		entity.setUpdatedtimes(time);
		entity.setIsDeleted(false);
		return entity;
	}
}
