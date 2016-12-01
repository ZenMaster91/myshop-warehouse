package com.myshop2.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.myshop.warehouse.controllers.WorkingPlanController;

public class WorkingPlanControllerDeserializar implements JsonDeserializer<WorkingPlanController> {

	@Override
	public WorkingPlanController deserialize(JsonElement json, Type arg1, JsonDeserializationContext context)
			throws JsonParseException {
		
		JsonObject jobject = (JsonObject) json;
		
		return new WorkingPlanController(jobject.get("wp_id").getAsInt());
	}

}
