/**
 * Copyright 2014-2015 Andreas Schilling
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
package org.joda.beans.gradle.tasks;

import org.gradle.api.tasks.TaskAction;


/**
 * Runs the JodaBeans generator against the source directory.
 *
 * @author Andreas Schilling
 *
 */
public class Generate extends AbstractJodaBeansTask
{
  public static final String ID = "jodaGenerate";


  @TaskAction
  public void jodaGenerateTask()
  {
    runBeanGenerator();
  }


  @Override
  protected String getExecutionType()
  {
    return "generator";
  }
  
  @Override
  public String getDescription() {
    return "Generates JodaBeans";
  }
}
