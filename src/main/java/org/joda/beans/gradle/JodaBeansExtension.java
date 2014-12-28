package org.joda.beans.gradle;

public class JodaBeansExtension {
	public static final String ID = "jodabeans";

	private String sourceDir;

	private String testSourceDir;

	private String targetDir;

	private String prefix;

	private String verbose;

	private String indent;

	public String getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(String targetDir) {
		this.targetDir = targetDir;
	}

	public String getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(String sourceDir) {
		this.sourceDir = sourceDir;
	}

	public String getTestSourceDir() {
		return testSourceDir;
	}

	public void setTestSourceDir(String testSourceDir) {
		this.testSourceDir = testSourceDir;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getVerbose() {
		return verbose;
	}

	public void setVerbose(String verbose) {
		this.verbose = verbose;
	}

	public String getIndent() {
		return indent;
	}

	public void setIndent(String indent) {
		this.indent = indent;
	}
}
