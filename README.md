# microservices-quarkus

### Introduction
These are the set of commands used in this lecture of the course available on [Udemy](https://www.udemy.com/course/k8s-native-microservices-on-quarkus/learn/lecture/33082874#content)

- [ ] Running a postgres db in docker   
`docker run --name postgresql -e POSTGRES_USER=user -e POSTGRES_PASSWORD=pass -p 5432:5432 -v ~/Work/decoded-bytes/db-data:/var/lib/postgresql/data -d postgres`

- [ ] Creating a secret in minikube using kubectl command   
`kubectl create secret generic pg-credentials --from-literal=username=user --from-literal=password=pass`

- [ ] Getting a secret and its values from minikube in yaml  
`kubectl get secrets`   

`kubectl get secret <secret_name> -o yaml`

- [ ] Create a database from postgres.yml file in minikube  
`kubectl create -f postgres-db.yml`

- [ ] Find a service URL in minikube  
`minikube service <service_name> --url`

- [ ] Create a config map using kubectl command  
`kubectl create -f config-map.yml`

- [ ] Check the running pods in kubectl  
`kubectl get pods`

- [ ] Get logs of a pod using kubectl  
`kubectl logs <pod_name>`

- [ ] Find URL of a service in minikube  
`minikube service <service_name> --url`


