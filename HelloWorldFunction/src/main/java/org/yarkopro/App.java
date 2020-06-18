package org.yarkopro;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.*;
import com.google.gson.Gson;
import org.yarkopro.activities.ActivityController;
import org.yarkopro.facilities.FacilityController;
import org.yarkopro.tics.DefaultTicksDao;
import org.yarkopro.tics.Tick;
import org.yarkopro.tics.TickController;
import org.yarkopro.tics.TicksDao;

import java.util.List;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private final TicksDao ticksDao = DefaultTicksDao.INSTANCE;

	public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent request, final Context context) {
		LambdaLogger logger = context.getLogger();
		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		try {
			List<Tick> ticks = ticksDao.findAll();

			logger.log(request.toString());
			this.getControllerResponse(request, response, context);
			response.setBody(new Gson().toJson(ticks));
			return response;
		} catch (Exception e) {
			String errorMsg = "{\"error\":\"" + e.getMessage() + "\"}";
			response.setBody(errorMsg);
			if (e.getMessage().startsWith("Communications link failure")) {
				response.setStatusCode(504);
				return response;
			}
			logger.log(e.getMessage());
			e.printStackTrace();
			return response;
		}
	}

	private APIGatewayProxyResponseEvent getControllerResponse(APIGatewayProxyRequestEvent request,
															   APIGatewayProxyResponseEvent response,
															   Context context) {
		String path = request.getPath();
		String resourceName = path.split("/")[1];

		context.getLogger().log("\n\n" + resourceName + "\n\n");
		if (ResourceType.FACILITY.getResourceUrlName().equals(resourceName)) {
			return FacilityController.INSTANCE.getResult(request, response, context);
		} else if (ResourceType.STANDALONE_ACTIVITY.getResourceUrlName().equals(resourceName)) {
			return ActivityController.INSTANCE.getResult(request, response, context);
		} else if (ResourceType.TICK.getResourceUrlName().equals(resourceName)) {
			return TickController.INSTANCE.getResult(request, response, context);
		}
		return response;
	}
}
