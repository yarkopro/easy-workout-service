package org.yarkopro.tics;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import org.yarkopro.Controller;

public enum TickController implements Controller {
	INSTANCE(DefaultTicksDao.INSTANCE);

	private final TicksDao dao;

	TickController(TicksDao dao) {
		this.dao = dao;
	}


	@Override
	public APIGatewayProxyResponseEvent getResult(APIGatewayProxyRequestEvent request,
							APIGatewayProxyResponseEvent response,
							Context context) {
		response.setBody(new Gson().toJson(dao.findAll()));
		return response;
	}
}
