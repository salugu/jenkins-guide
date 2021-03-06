#!/usr/bin/groovy

def jenkins_bkp(dirName, jenUrl)
{

  sh "mkdir -p ${dirName}-${BUILD_ID}/jobs"
   // Copy job definitions into the workspace
  sh "rsync -am --include='config.xml' --include='*/' --prune-empty-dirs --exclude='*' ${JENKINS_HOME}/jobs/${jenUrl}/ ${dirName}-${BUILD_ID}/jobs"
   // Create an archive from all copied files
  sh "tar czf ${dirName}-configuration-${BUILD_ID}.tar.gz ${dirName}-${BUILD_ID}/"

}

return this;
