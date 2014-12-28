package org.joda.beans.gradle;

/**
 * Holds all the configuration properties for the gradle plugin.
 * 
 * @author Andreas Schilling
 * 
 */
public class JodaBeansExtension {
	public static final String ID = "jodabeans";

	private String sourceDir;

	private String testSourceDir;

	private String prefix;

	private Integer verbose;

	private String indent;

	private Boolean recursive;

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

	public Integer getVerbose() {
		return verbose;
	}

	public void setVerbose(Integer verbose) {
		this.verbose = verbose;
	}

	public String getIndent() {
		return indent;
	}

	public void setIndent(String indent) {
		this.indent = indent;
	}

	public Boolean getRecursive() {
		return recursive;
	}

	public void setRecursive(Boolean recursive) {
		this.recursive = recursive;
	}
}
