package org.yarkopro.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import org.yarkopro.Controller;

import java.util.Map;

public enum ActivityController implements Controller {

	INSTANCE(DefaultActivityDao.INSTANCE);

	ActivityDao dao;

	ActivityController(ActivityDao dao) {
		this.dao = dao;
	}

	public APIGatewayProxyResponseEvent getResult(APIGatewayProxyRequestEvent event,
												  APIGatewayProxyResponseEvent response,
												  Context context) {
		Map<String, String> params = event.getQueryStringParameters();
		String id = params.get("id");
		if (id != null) {
			response.setBody(new Gson().toJson(dao.findStandaloneActivityById(Integer.valueOf(id))));
		} else {
			response.setStatusCode(400);
			response.setBody("ERROR: Bad request. Query parameter 'id' is not specified.");
		}
		return response;
	}
}
