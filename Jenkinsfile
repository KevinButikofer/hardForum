pipeline{
	agent any
	stages {
		stage('Build'){			
			echo 'Build'
			docker
			{
				image 'maven:3-alpine'
			}
			steps{
				sh '(mvn clean package)'
			}	
		}
		stage('Test'){
			steps{
				echo 'Testing'
			}
		}
		stage('Deploy'){
			steps{
				echo 'Deploying'
			}
		}
	}
}
