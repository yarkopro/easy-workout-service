package org.yarkopro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import org.yarkopro.dao.DefaultTicksDao;
import org.yarkopro.dao.TicksDao;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<Object, Object> {

    private final TicksDao ticksDao = DefaultTicksDao.INSTANCE;

    public Object handleRequest(final Object input, final Context context) {

        LambdaLogger logger = context.getLogger();
        try {
            String output = new Gson().toJson(ticksDao.findAll());
            return output;
        } catch (Exception e) {
            logger.log(e.getMessage());
            e.printStackTrace();
            return "{\"error\":\""+e.getMessage()+"\"}";
        }
    }

}
