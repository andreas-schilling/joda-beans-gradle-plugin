/**
 * Copyright 2014-2016 Andreas Schilling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kiirun.joda.beans.gradle;

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

	private String config;

	private Boolean recursive;

	private Boolean strict;

	public String getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(final String sourceDir) {
		this.sourceDir = sourceDir;
	}

	public String getTestSourceDir() {
		return testSourceDir;
	}

	public void setTestSourceDir(final String testSourceDir) {
		this.testSourceDir = testSourceDir;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(final String prefix) {
		this.prefix = prefix;
	}

	public Integer getVerbose() {
		return verbose;
	}

	public void setVerbose(final Integer verbose) {
		this.verbose = verbose;
	}

	public String getIndent() {
		return indent;
	}

	public void setIndent(final String indent) {
		this.indent = indent;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(final String config) {
		this.config = config;
	}

	public Boolean getRecursive() {
		return recursive;
	}

	public void setRecursive(final Boolean recursive) {
		this.recursive = recursive;
	}

	public Boolean isStrict() {
		return strict;
	}

	public void setStrict(final Boolean strict) {
		this.strict = strict;
	}
}
