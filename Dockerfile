# Use a base image with Java 21
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Java source code into the container
COPY ./src /app/src

# Set the classpath and compile the Java files
RUN javac -d out /app/src/main/java/org/systemeReparti/fibonacci/FibonaciVirtualExecutor.java

# Set the working directory to the compiled output directory
WORKDIR /app/out

# Run the Java program
CMD ["java", "org.systemeReparti.fibonacci.FibonaciVirtualExecutor"]
