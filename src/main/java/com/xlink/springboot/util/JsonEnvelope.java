/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xlink.springboot.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonEnvelope {

    private static final Logger LOG = Logger.getLogger(JsonEnvelope.class.getName());

    public static Map<String, Object> encloseErrorData(int httpCode, String exceptionName, String message) {

        Map<String, Object> envelope = new LinkedHashMap<>();
        Map<String, Object> errors = new LinkedHashMap<>();

        errors.put("exception", exceptionName);
        errors.put("message", message);

        envelope.put("succeeded", false);
        envelope.put("code", httpCode);
        envelope.put("errors", errors);

        return envelope;

    }

    public static Map<String, Object> encloseErrorData(int httpCode, String exceptionName, Map<String, String> errors) {

        Map<String, Object> envelope = new LinkedHashMap<>();

        errors.put("exception", exceptionName);

        envelope.put("succeeded", false);
        envelope.put("code", httpCode);
        envelope.put("errors", errors);

        return envelope;

    }

    public static Map<String, Object> encloseSingleData(int httpCode, Object data) {

        Map<String, Object> envelope = new LinkedHashMap<>();

        envelope.put("succeeded", true);
        envelope.put("code", httpCode);
        envelope.put("data", data);

        LOG.log(Level.INFO, "envelope = {0}", envelope.toString());
        return envelope;

    }

    public static Map<String, Object> encloseMessage(boolean succeeded, int httpCode, String message) {

        Map<String, Object> envelope = new LinkedHashMap();

        envelope.put("succeeded", succeeded);
        envelope.put("code", httpCode);
        envelope.put("message", message);

        return envelope;

    }

    public static Map<String, Object> encloseMessage(boolean succeeded, int httpCode, Map<String, Object> payload) {

        Map<String, Object> envelope = new LinkedHashMap<>();

        envelope.put("succeeded", succeeded);
        envelope.put("code", httpCode);
        envelope.put("payload", payload);

        return envelope;

    }

    public static Map<String, Object> encloseData(int httpCode, List data) {

        Map<String, Object> envelope = new LinkedHashMap();

        envelope.put("succeeded", true);
        envelope.put("code", httpCode);
        envelope.put("count", data.size());
        envelope.put("data", data);

        return envelope;

    }

}
