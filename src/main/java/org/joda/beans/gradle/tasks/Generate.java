package org.joda.beans.gradle.tasks;

import java.util.List;

import org.gradle.api.tasks.TaskAction;

import com.google.common.base.Strings;

public class Generate extends AbstractJodaBeansTask {
	public static final String ID = "jodaGenerate";

	@TaskAction
	public void jodaGenerateTask() {
		System.out.println("Joda-Bean generator started, directory: "
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
		System.out.println("Running Joda-Bean generator using arguments "
				+ argsList);
		runTool(toolClass, argsList);

		System.out.println("Joda-Bean generator completed");
	}

}
