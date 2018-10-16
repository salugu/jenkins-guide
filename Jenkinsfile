#!/usr/bin/groovy


def delivery
pipeline{
  agent any
  stages {
   stage('Load deploy grrovy code')
	{
steps {
	checkout scm
     script {
     	delivery = load 'deploy.groovy'
	sh "ls ."
	}	
}
}
//   stage('Create Archive  -- Web-App')
//	{
//steps {
//     script {
//	delivery.jenkins_bkp("", "jobs/Java-Web-App")
//	}
//	}
//}

   stage('Create Archive  -- Devops-2')
        {
steps {
     script {
        delivery.jenkins_bkp("Devops-2", "jobs/Devops-2")
        }
        }
}
   stage('Backup to S3 Bucket')
	{
steps {
        sh "cd $WORKSPACE; mkdir Outputs; mv *.tar.gz Outputs/"
        withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'mobile-s3-user', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
        //sh "aws s3 cp *.tar.gz s3://mybucket-ssp --region ap-south-1"
        sh "aws s3 cp Outputs/ s3://mybucket-ssp/SSP-Jenkins-Bkps --recursive"
          }	
   	} 
     }
}
post {
  always {
   	cleanWs()
        }
    } 
}
