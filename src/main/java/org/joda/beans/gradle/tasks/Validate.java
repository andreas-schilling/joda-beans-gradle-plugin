package org.joda.beans.gradle.tasks;

import java.util.List;

import org.gradle.api.specs.Specs;
import org.gradle.api.tasks.TaskAction;

import com.google.common.base.Strings;

public class Validate extends AbstractJodaBeansTask {

	public Validate() {
		super();
		getOutputs().upToDateWhen(Specs.satisfyAll());
	}

	@TaskAction
	public void validate() {
		System.out.println("Joda-Bean validator started, directory: "
				+ getSourceDir()
				+ (Strings.isNullOrEmpty(getTestSourceDir()) ? ""
						: ", test directory:" + getTestSourceDir())
				+ ", target directory:" + getTargetDir());

		ClassLoader classLoader = obtainClassLoader();
		Class<?> toolClass = null;
		try {
			toolClass = classLoader.loadClass("org.joda.beans.gen.BeanCodeGen");
		} catch (Exception ex) {
			System.out
					.println("Skipping as joda-beans is not in the project compile classpath");
			return;
		}
		List<String> argsList = buildArgs();
		System.out.println("Running Joda-Bean validator using arguments "
				+ argsList);
		runTool(toolClass, argsList);
	}
}
