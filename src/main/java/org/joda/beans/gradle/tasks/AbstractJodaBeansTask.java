package org.joda.beans.gradle.tasks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.joda.beans.gradle.JodaBeansExtension;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * Abstract base class for both {@code validate} and {@code generate} tasks.
 * 
 * @author Andreas Schilling
 *
 */
public abstract class AbstractJodaBeansTask extends DefaultTask {
	private static final String JODA_BEANS_CODE_GEN_CLASS = "org.joda.beans.gen.BeanCodeGen";

	private static final String DEFAULT_INDENT = "4";

	private static final String DEFAULT_STRING_VALUE = "";

	protected String getSourceDir() {
		return ((JodaBeansExtension) getProject().getExtensions().getByName(
				JodaBeansExtension.ID)).getSourceDir();
	}

	protected String getTestSourceDir() {
		return ((JodaBeansExtension) getProject().getExtensions().getByName(
				JodaBeansExtension.ID)).getTestSourceDir();
	}

	protected String getIndent() {
		String indent = ((JodaBeansExtension) getProject().getExtensions()
				.getByName(JodaBeansExtension.ID)).getIndent();
		return indent != null ? indent : DEFAULT_INDENT;
	}

	protected String getPrefix() {
		String prefix = ((JodaBeansExtension) getProject().getExtensions()
				.getByName(JodaBeansExtension.ID)).getPrefix();
		return prefix != null ? prefix : DEFAULT_STRING_VALUE;
	}

	protected Integer getVerbose() {
		return ((JodaBeansExtension) getProject().getExtensions().getByName(
				JodaBeansExtension.ID)).getVerbose();
	}

	protected boolean operateRecursive() {
		Boolean recursive = ((JodaBeansExtension) getProject().getExtensions()
				.getByName(JodaBeansExtension.ID)).getRecursive();
		return recursive != null ? recursive : true;
	}

	protected abstract String getExecutionType();

	protected void runBeanGenerator() {
		getLogger().debug(
				"Running JodaBeans "
						+ getExecutionType()
						+ " in directory: "
						+ getSourceDir()
						+ (Strings.isNullOrEmpty(getTestSourceDir()) ? ""
								: ", test directory:" + getTestSourceDir()));

		ClassLoader classLoader = obtainClassLoader();
		Class<?> toolClass = null;
		try {
			toolClass = classLoader.loadClass(JODA_BEANS_CODE_GEN_CLASS);
		} catch (Exception ex) {
			getLogger()
					.error("Skipping as joda-beans is not in the project compile classpath");
			return;
		}
		List<String> arguments = buildGeneratorArguments();
		getLogger().debug("Using arguments " + arguments);
		runTool(toolClass, arguments);
		getLogger().debug(
				"JodaBeans " + getExecutionType() + " successfully completed.");
	}

	/**
	 * Builds the arguments to the tool.
	 * 
	 * @return the arguments, not null
	 */
	protected List<String> buildGeneratorArguments() {
		List<String> arguments = Lists.newArrayList();
		if (operateRecursive()) {
			arguments.add("-R");
		}
		if (getIndent() != null) {
			arguments.add("-indent=" + getIndent());
		}
		if (getPrefix() != null) {
			arguments.add("-prefix=" + getPrefix());
		}
		if (getVerbose() != null) {
			arguments.add("-v=" + getVerbose());
		}
		return arguments;
	}

	/**
	 * Obtains the classloader from a set of file paths.
	 * 
	 * @return the classloader, not null
	 */
	protected ClassLoader obtainClassLoader() {
		return AbstractJodaBeansTask.class.getClassLoader();
	}

	protected int runTool(Class<?> toolClass, List<String> argsList) {
		String sourceDir = getSourceDir();
		if (Strings.isNullOrEmpty(sourceDir)) {
			throw new GradleException("Source directory must be given!");
		}
		argsList.add(sourceDir);
		int count = invoke(toolClass, argsList);
		// optionally invoke test source
		if (!Strings.isNullOrEmpty(getTestSourceDir())) {
			argsList.set(argsList.size() - 1, getTestSourceDir());
			count += invoke(toolClass, argsList);
		}
		return count;
	}

	private int invoke(Class<?> toolClass, List<String> argsList) {
		Method createFromArgsMethod = findCreateFromArgsMethod(toolClass);
		Method processMethod = findProcessMethod(toolClass);
		Object beanCodeGen = createBuilder(argsList, createFromArgsMethod);
		return invokeBuilder(processMethod, beanCodeGen);
	}

	private Object createBuilder(List<String> argsList,
			Method createFromArgsMethod) throws GradleException {
		String[] args = argsList.toArray(new String[argsList.size()]);
		try {
			return createFromArgsMethod.invoke(null, new Object[] { args });
		} catch (IllegalArgumentException ex) {
			throw new GradleException(
					"Error invoking BeanCodeGen.createFromArgs()");
		} catch (IllegalAccessException ex) {
			throw new GradleException(
					"Error invoking BeanCodeGen.createFromArgs()");
		} catch (InvocationTargetException ex) {
			throw new GradleException("Invalid Joda-Beans Mojo configuration: "
					+ ex.getCause().getMessage(), ex.getCause());
		}
	}

	private int invokeBuilder(Method processMethod, Object beanCodeGen)
			throws GradleException {
		try {
			return (Integer) processMethod.invoke(beanCodeGen);
		} catch (IllegalArgumentException ex) {
			throw new GradleException("Error invoking BeanCodeGen.process()");
		} catch (IllegalAccessException ex) {
			throw new GradleException("Error invoking BeanCodeGen.process()");
		} catch (InvocationTargetException ex) {
			throw new GradleException("Error while running Joda-Beans tool: "
					+ ex.getCause().getMessage(), ex.getCause());
		}
	}

	private Method findCreateFromArgsMethod(Class<?> toolClass) {
		Method createFromArgsMethod = null;
		try {
			createFromArgsMethod = toolClass.getMethod("createFromArgs",
					String[].class);
		} catch (Exception ex) {
			throw new GradleException(
					"Unable to find method BeanCodeGen.createFromArgs()");
		}
		return createFromArgsMethod;
	}

	private Method findProcessMethod(Class<?> toolClass) throws GradleException {
		Method processMethod = null;
		try {
			processMethod = toolClass.getMethod("process");
		} catch (Exception ex) {
			throw new GradleException(
					"Unable to find method BeanCodeGen.process()");
		}
		return processMethod;
	}
}
