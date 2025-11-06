# task123-demo
# Clients API: Java + MongoDB Microservice  
Repository: [vikasshinde206/task123-demo](https://github.com/vikasshinde206/task123-demo.git)

This project is a demonstration microservice named **Clients API**. It uses Java 17 + Spring Boot, persists data in MongoDB, and is built & deployed using Docker, Kubernetes, and CI/CD (via Jenkins).  
Ideal for showcasing DevOps skills – from code commit to cluster deployment.

---

## 🚀 Tech Stack  
- **Language / Framework**: Java 17 + Spring Boot 3.x  
- **Data Store**: MongoDB  
- **Build Tool**: Maven  
- **Containerization**: Docker (multi-stage)  
- **Orchestration**: Kubernetes (with LoadBalancer, Ingress, TLS via cert-manager)  
- **CI/CD**: Jenkins (single pipeline approach)  
- **Infrastructure**: Cloud-agnostic (EKS/GKE/AKS or on-prem)  

---

## 📁 Project Structure  
task123-demo/
├── clients-api/
│ ├── src/
│ ├── pom.xml
│ ├── Dockerfile
│ └── Jenkinsfile
└── kubernetes/
├── mongo/
│ ├── 00-namespace.yaml
│ ├── 01-secrets-mongo.yaml
│ ├── 02-pvc-mongo.yaml
│ ├── 03-deployment-mongo.yaml
│ └── 04-service-mongo.yaml
├── clients-api/
│ ├── 05-deployment-clients-api.yaml
│ ├── 06-service-clients-api.yaml
│ └── 11-ingress-clients-api.yaml
├── ingress-controller/
│ ├── 07-ingress-nginx-controller-deployment.yaml
│ └── 08-ingress-nginx-controller-service.yaml
└── cert-manager/
├── 09-clusterissuer-letsencrypt-staging.yaml
└── 10-certificate-clients-api.yaml

---

## 🧠 Local Development Setup  
### Prerequisites  
- Java 17 SDK  
- Maven  
- Docker  
- MongoDB (local or remote)  

### Steps  
1. Clone the repo:  
   ```bash
   git clone https://github.com/vikasshinde206/task123-demo.git
   cd task123-demo/clients-api

2. Run MongoDB (if local):

docker run -d --name mongo -p 27017:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=admin \
  -e MONGO_INITDB_ROOT_PASSWORD=change-me-please \
  mongo:6.0

3.  Build the project:

mvn clean package


4. Launch the app:

java -jar target/clients-api-1.0.0.jar


5. Verify via browser/curl:

http://localhost:8080/api/clients

# Docker Usage

1. Build image
docker build -t clients-api:latest .

2. Run container
docker run -p 8080:8080 \
  -e MONGO_URI="mongodb://admin:change-me-please@localhost:27017/clientsdb?authSource=admin" \
  clients-api:latest

# Kubernetes Deployment
# Prerequisites

Kubernetes cluster with dynamic storage provisioning

kubectl configured

Install cert-manager in cluster:

kubectl apply -f https://github.com/cert-manager/cert-manager/releases/latest/download/cert-manager.yaml

# Apply manifests
# In repo root
kubectl apply -f kubernetes/mongo
kubectl apply -f kubernetes/clients-api
kubectl apply -f kubernetes/ingress-controller
kubectl apply -f kubernetes/cert-manager

# After deployment

Get external IP of LoadBalancer service:

kubectl get svc -n clients-demo


Create DNS record:
clients.api.deltacapita.com → <EXTERNAL-IP>

Access the service:

https://clients.api.deltacapita.com/api/clients

CI/CD Pipeline (via Jenkins)

This project uses a single Jenkinsfile to manage both CI and CD flows:

Pipeline Highlights

Checkout code from GitHub

Build & Test using Maven

Build Docker image tagged with Git commit hash

Scan image with Trivy for vulnerabilities

Push image to AWS ECR (or other registry)

Deploy to Kubernetes when branch is main

Required Jenkins Credentials
ID                  Type	                 Purpose
ecr-creds	      Username & Password	     Authenticate with ECR registry
kubeconfig-cred	  Secret File	           Provide Kubeconfig to access cluster
