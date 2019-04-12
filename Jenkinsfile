pipeline{
	agent any
	stages {
		stage('Build'){		
			agent
			{	
				docker
				{
					image 'maven:3-alpine'
				}
			}
			steps{
				sh '(mvn clean package)'
				stash name: "app", includes: "**"
			}	
		}
		stage('Quality Test'){
			agent
			{	
				docker
				{
					image 'maven:3-alpine'
				}
			}
			steps{
				unstash "app"
				sh 'cd HolygramSpring; mvn clean test'
				sh 'cd HolygramSpring; mvn sonar:sonar \
					-Dsonar.projectKey=KevinButikofer_hardForum \
					-Dsonar.organization=kevinbutikofer-github \
					-Dsonar.host.url=https://sonarcloud.io \
					-Dsonar.login=b992d342e64ce7a123ec996eb153c298deab1051'
			}
		}
		stage('Deploy'){
			steps{
				echo 'Deploying'
			}
		}
	}
}
