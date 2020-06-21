package org.yarkopro;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.yarkopro.activities.ActivityController;
import org.yarkopro.facilities.FacilityController;
import org.yarkopro.tics.TickController;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent event, final Context context) {
		LambdaLogger logger = context.getLogger();
		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		response.setHeaders(getHeaders());
		try {
			logger.log(event.toString());
			return this.getControllerResponse(event, response, context);
		} catch (Exception e) {
			String errorMsg = "{\"error\":\"" + e.getMessage() + "\"}";
			response.setBody(errorMsg);
			if (e.getMessage() != null && e.getMessage().startsWith("Communications link failure")) {
				response.setStatusCode(504);
				return response;
			}
			response.setStatusCode(500);
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

		Map<String, String> params = request.getQueryStringParameters();
		context.getLogger().log("\n\n" + resourceName + "\n\n");
		context.getLogger().log("[QUERY PARAMS]" + params);

		if (ResourceType.FACILITY.getResourceUrlName().equals(resourceName)) {
			return FacilityController.INSTANCE.getResult(request, response, context);
		} else if (ResourceType.STANDALONE_ACTIVITY.getResourceUrlName().equals(resourceName)) {
			return ActivityController.INSTANCE.getResult(request, response, context);
		} else if (ResourceType.TICK.getResourceUrlName().equals(resourceName)) {
			return TickController.INSTANCE.getResult(request, response, context);
		}
		return response;
	}

	private Map<String, String> getHeaders() {
		Map<String, String> headers = Stream.of(new String[][] {
			{ "X-Requested-With", "*" },
			{ "Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,x-requested-with" },
			{ "Access-Control-Allow-Origin", "*" },
			{ "Access-Control-Allow-Methods", "POST,GET,OPTIONS" },
		}).collect(Collectors.toMap(data -> data[0], data -> data[1]));
		return headers;
	}
}
