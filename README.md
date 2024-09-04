Jenkins Pipeline for Java based application using Maven, SonarQube, Argo CD and Kubernetes

![228301952-abc02ca2-9942-4a67-8293-f76647b6f9d8](https://github.com/user-attachments/assets/35aa8a5d-e9f8-4470-8d1a-620f2bc74598)



Pipeline stages:

1.Checkout out from GitHub: Cloned the repository from GitHub.
2.Build and Test: Navigated to the project directory and ran Maven commands to clean,compile,build and test.
3.Static Code Analysis: Integrated with SonarQube for continuous inspection of code quality check.
4.Build and Push Docker Image: Built a Docker image for our Spring Boot application, tagged it with the Jenkins build number,
and pushed it to Docker Hub for consistent and reliable and deployment across environments.
5.Update Deployment File: Automated the update of deployment files to reflect the latest build, ensuring our deployments are always up-to-date. 
6.Argo CD pull the latest changes from Manifest repository and deployed them to Kubernetes cluster.
7.Argo CD: Integrated Argo CD with Kubernetes to automate the deployment process, ensuring that any changes pushed to the GitHub repository are seamlessly reflected in our Kubernetes environment.

**Build DOCKER Image**

Using DOCEKER as Agent

docker build -t product-application-pipeline:v1 .

docker run -d -p 8081:8081 -t product-application-pipeline:v1

 Access the application on http://<ip-address>:8010

Steps:

**Step 1 : Create an EC2 instance**

Go to AWS Console
Launch EC2 instances(Ubuntu)

Login to terminal

**Step 2: Install Java **

sudo apt update

sudo apt install openjdk-17-jre

**Step 3 :  Install Jenkins**

curl -fsSL https://pkg.jenkins.io/debian/jenkins.io-2023.key | sudo tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
	
sudo apt-get update

sudo apt-get install jenkins

**Note: ** By default, Jenkins will not be accessible to the external world due to the inbound traffic restriction by AWS. 
Open port 8080 in the inbound traffic rules.

Login to Jenkins using the below URL:
http://ipaddress:8080 -->  (IP address from EC2 instance)

username : admin

password : used this command to get the password - (sudo cat /var/lib/jenkins/secrets/initialAdminPassword)

**Step 4 : Create Pipeline in Jenkins**

Add the repo details for the github

**Step 5: Install the Docker and Sonar Pipeline plugin in Jenkins:**

Go to Manage Jenkins > Manage Plugins.
In the Available tab, search for "Docker Pipeline".

Select the plugin and click the Install button.

Restart Jenkins after the plugin is installed.

**Step 6: Install SonarQube**

apt install unzip

adduser sonarqube

wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-9.4.0.54424.zip

unzip *

chmod -R 755 /home/sonarqube/sonarqube-9.4.0.54424

chown -R sonarqube:sonarqube /home/sonarqube/sonarqube-9.4.0.54424

cd sonarqube-9.4.0.54424/bin/linux-x86-64/

./sonar.sh start

Login Sonarqube : http://ip-address :9000

**Step 7: Install Docker**

sudo apt update

sudo apt install docker.io

Grant Jenkins user and Ubuntu user permission to docker deamon.

sudo su - 

usermod -aG docker jenkins

usermod -aG docker ubuntu

systemctl restart docker

**Step 8: Configure the token and password in jenkins** 

Add the secret text for Sonarqqube 

Add the password for Docker

Add the secret text for github

**Step 9: Install Minikube in system**

Setup minikube in the system

minikube start

minikube status

https://operatorhub.io/operator/argocd-operator

**Step 10: Update the jenkinfile**

Update thr jenkin file with sonar ip address

**Step 11: Build the pipeline**

Build thr pipeline in Jenkins

**Step 12: Setup ARGOCD (CD Pipeline)**

Using the step from the url

https://argocd-operator.readthedocs.io/en/latest/usage/basics/



Reference : https://www.youtube.com/watch?v=zZfhAXfBvVA&list=RDCMUCnnQ3ybuyFdzvgv2Ky5jnAA&index=1
















