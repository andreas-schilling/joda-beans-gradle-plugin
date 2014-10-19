package org.joda.beans.gradle.tasks;

import java.util.List;

import org.gradle.api.tasks.TaskAction;

import com.google.common.base.Strings;

public class ValidateTask extends AbstractJodaBeansTask {
	public String sourceDir;

	@TaskAction
	public void validateTask() {
		System.out.println("Joda-Bean validator started, directory: "
				+ getSourceDir()
				+ (Strings.isNullOrEmpty(getTestSourceDir()) ? ""
						: ", test directory:" + getTestSourceDir()));

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
		runTool(toolClass, argsList);

		System.out.println("Joda-Bean validator completed");
	}

}
