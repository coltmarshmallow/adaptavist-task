# Stage 1: test the application
FROM maven:amazoncorretto as test
WORKDIR /app
COPY . /app
RUN mvn test -f ./Wordcount/pom.xml

# Stage 2: build the application
FROM maven:amazoncorretto as builder
WORKDIR /app
COPY . /app
RUN mvn package -f ./Wordcount/pom.xml

# Stage 3: package application
FROM amazoncorretto:22-alpine
WORKDIR /app
COPY --from=builder /app/Wordcount/target/wordcount-1.0-SNAPSHOT.jar /app/wordcount-1.0-SNAPSHOT.jar
CMD ["java", "-jar", "/app/wordcount-1.0-SNAPSHOT.jar"]