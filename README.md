# anagram
Spring boot rest service with Gradle and Jersey client with Maven

<h6>Pre-Requisites</h6>
1) JAVA 1.8
2) GRADLE 5.5
3) DOCKER 18.x
4) MAVEN 3.3.9

1) git clone https://github.com/stahani86/anagram.git

<h4> Rest Service</h4>

1) cd anagram/restservice
2) gradle build
3) sudo docker build --file Dockerfile -t anagram:latest .
4) sudo docker run -p 3000:3000 anagram

<h4> Rest Client</h4>

1) open another terminal
2) cd anagram/restclient
3) mvn clean install
4) java -jar target/restclient-0.0.1-SNAPSHOT.jar
