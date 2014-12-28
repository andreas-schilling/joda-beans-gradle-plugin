package org.joda.beans.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.joda.beans.gradle.tasks.Validate;

public class JodaBeansPlugin implements Plugin<Project> {

	@Override
	public void apply(Project target) {
		target.getExtensions().add("jodabeans", new JodaBeansExtension());
		target.getTasks().create("validate", Validate.class);
	}

}
