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

import java.util.List;

import org.gradle.api.GradleException;
import org.gradle.api.specs.Specs;
import org.gradle.api.tasks.TaskAction;


/**
 * Runs the JodaBeans validator against the source directory.
 *
 * @author Andreas Schilling
 *
 */
public class Validate extends AbstractJodaBeansTask
{
  public static final String ID = "jodaValidate";


  public Validate()
  {
    super();
    getOutputs().upToDateWhen( Specs.satisfyNone() );
  }


  @TaskAction
  public void jodaValidate()
  {
    runBeanGenerator();
  }


  @Override
  protected List<String> buildGeneratorArguments()
  {
    final List<String> argsList = super.buildGeneratorArguments();
    argsList.add( "-nowrite" );
    return argsList;
  }

  
  @Override
  protected String getExecutionType()
  {
    return "validator";
  }
  
  @Override
  public String getDescription() {
    return "Validates JodaBeans";
  }
  
  @Override
  protected int runTool(Class<?> toolClass, List<String> argsList) {
    //intercepts the call to runTool, failing if in strict mode and files were updated by the tool
    int filesUpdated = super.runTool(toolClass, argsList);
    if (isStrict() && filesUpdated != 0) {
      throw new GradleException(filesUpdated + "  beans need regenerating. See log."); 
    } else {
      return filesUpdated;
    }
  }
  
}
