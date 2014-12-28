package org.joda.beans.gradle.tasks;

import java.util.List;

import org.gradle.api.specs.Specs;
import org.gradle.api.tasks.TaskAction;

/**
 * Runs the JodaBeans validator against the source directory.
 * 
 * @author Andreas Schilling
 *
 */
public class Validate extends AbstractJodaBeansTask {
	public static final String ID = "jodaValidate";

	public Validate() {
		super();
		getOutputs().upToDateWhen(Specs.satisfyNone());
	}

	@TaskAction
	public void jodaValidate() {
		runBeanGenerator();
	}

	@Override
	protected List<String> buildGeneratorArguments() {
		List<String> argsList = super.buildGeneratorArguments();
		argsList.add("-nowrite");
		return argsList;
	}

	@Override
	protected String getExecutionType() {
		return "validator";
	}
}
