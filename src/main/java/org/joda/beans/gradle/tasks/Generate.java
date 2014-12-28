package org.joda.beans.gradle.tasks;

import org.gradle.api.tasks.TaskAction;

/**
 * Runs the JodaBeans generator against the source directory.
 * 
 * @author Andreas Schilling
 *
 */
public class Generate extends AbstractJodaBeansTask {
	public static final String ID = "jodaGenerate";

	@TaskAction
	public void jodaGenerateTask() {
		runBeanGenerator();
	}

	@Override
	protected String getExecutionType() {
		return "generator";
	}
}
