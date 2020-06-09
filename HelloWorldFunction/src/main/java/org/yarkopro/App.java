package org.yarkopro;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.google.gson.Gson;
import org.yarkopro.tics.DefaultTicksDao;
import org.yarkopro.tics.Tick;
import org.yarkopro.tics.TicksDao;

import java.util.List;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayV2HTTPEvent, Object> {

    private final TicksDao ticksDao = DefaultTicksDao.INSTANCE;

    public Object handleRequest(final APIGatewayV2HTTPEvent input, final Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(input.getRouteKey());
        try {
            logger.log(input.toString());
            List<Tick> ticks = ticksDao.findAll();
//            String output = new Gson().toJson(ticksDao.findAll());
            return ticks;
        } catch (Exception e) {
            logger.log(e.getMessage());
            e.printStackTrace();
            return "{\"error\":\""+e.getMessage()+"\"}";
        }
    }
}
