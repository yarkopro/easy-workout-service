package org.yarkopro.facilities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import org.yarkopro.Controller;

import java.util.Map;

public enum FacilityController implements Controller {

	INSTANCE(DefaultFacilityDao.INSTANCE);

	FacilityDao dao;

	FacilityController(FacilityDao dao) {
		this.dao = dao;
	}

	public APIGatewayProxyResponseEvent getResult(APIGatewayProxyRequestEvent event,
							APIGatewayProxyResponseEvent response,
							Context context) {
		Map<String, String> params = event.getQueryStringParameters();
		String id = null;
		if (params != null) id = params.get("id");
		if (id != null) {
			response.setBody(new Gson().toJson(dao.findOne(Integer.parseInt(id))));
		} else {
			response.setBody(new Gson().toJson(dao.findAll()));
		}
		return response;
	}
}
