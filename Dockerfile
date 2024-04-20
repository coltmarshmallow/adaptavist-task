# Stage 1: build the application
FROM amazoncorretto:22-jdk as builder
WORKDIR /app
COPY . /app
RUN javac WordCount.java

# Stage 2: package application
FROM amazoncorretto:22-alpine
WORKDIR /app
COPY --from=builder /app/WordCount.class /app/
CMD ["java", "WordCount"]