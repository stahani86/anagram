# anagram
Spring boot rest service with Gradle and Jersey client with Maven

<h6>Pre-Requisites</h6>
<li>JAVA 1.8</li>
<li>GRADLE 5.5</li>
<li>DOCKER 18.x</li>
<li>MAVEN 3.3.9</li>

1) git clone https://github.com/stahani86/anagram.git

<h4> Rest Service</h4>

1) cd anagram/restservice
2) gradle build
3) sudo docker build --file Dockerfile -t anagram:latest .
4) sudo docker run -p 3000:3000 anagram

<h4> Rest Client</h4>

1) open terminal
2) cd anagram/restclient
3) mvn clean install
4) java -jar target/restclient-0.0.1-SNAPSHOT.jar
