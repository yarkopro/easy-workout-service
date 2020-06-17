package org.yarkopro.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import org.yarkopro.Controller;
import org.yarkopro.facilities.DefaultFacilityDao;
import org.yarkopro.facilities.FacilityDao;

import java.util.Map;

public enum FacilityController implements Controller {

	INSTANCE(DefaultFacilityDao.INSTANCE);

	FacilityDao dao;

	FacilityController(FacilityDao dao) {
		this.dao = dao;
	}

	public Object getResult(APIGatewayProxyRequestEvent event, Context context) {
		Map<String, String> pathParams = event.getPathParameters();
		if (pathParams.size() > 0) {
			return dao.findOne(Integer.valueOf(pathParams.get("facilityId")));
		} else {
			return dao.findAll();
		}
	}

}
