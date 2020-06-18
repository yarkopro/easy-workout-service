package org.yarkopro;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public interface Controller {
	APIGatewayProxyResponseEvent getResult(APIGatewayProxyRequestEvent request, APIGatewayProxyResponseEvent response, Context context);
}
