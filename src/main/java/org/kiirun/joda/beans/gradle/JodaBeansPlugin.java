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

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.kiirun.joda.beans.gradle.tasks.Generate;
import org.kiirun.joda.beans.gradle.tasks.Validate;

/**
 * Main class for the plugin implementation.
 *
 * @author Andreas Schilling
 *
 */
public class JodaBeansPlugin implements Plugin<Project> {

	@Override
	public void apply(final Project target) {
		target.getExtensions().add(JodaBeansExtension.ID, new JodaBeansExtension());
		target.getTasks().create(Validate.ID, Validate.class);
		target.getTasks().create(Generate.ID, Generate.class);
	}
}
