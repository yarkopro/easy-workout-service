package org.yarkopro;

import org.yarkopro.activities.ActivityController;
import org.yarkopro.facilities.FacilityController;

public enum ResourceType {
	FACILITY("facilities", FacilityController.INSTANCE),
	STANDALONE_ACTIVITY("standalone-activities", ActivityController.INSTANCE),
	TICK("ticks", null);

	private final String resourceUrlName;
	private final Object controllerInstance;

	ResourceType(String resourceUrlName, Object controllerInstance) {
		this.resourceUrlName = resourceUrlName;
		this.controllerInstance = controllerInstance;
	}

	public String getResourceUrlName() {
		return resourceUrlName;
	}

	public Object getControllerInstance() {
		return controllerInstance;
	}
}
