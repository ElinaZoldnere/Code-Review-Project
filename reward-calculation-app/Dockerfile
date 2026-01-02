FROM eclipse-temurin:21

# Install bash
# RUN apk add --no-cache bash

# Set the working directory inside the container
#WORKDIR /app

COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x /wait-for-it.sh

COPY reward-calculation-app-1.0.1.jar reward-calculation-app.jar

CMD ["./wait-for-it.sh", "reward-calculation-db:3306", "--timeout=30", "--" , "java", "-Dspring.profiles.active=mysql-container", "-jar", "reward-calculation-app.jar"]

EXPOSE 8080
