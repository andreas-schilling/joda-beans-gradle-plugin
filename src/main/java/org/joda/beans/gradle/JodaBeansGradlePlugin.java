package org.joda.beans.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class JodaBeansGradlePlugin implements Plugin<Project> {

	public void apply(Project target) {
		target.task("validateTask");
	}

}
