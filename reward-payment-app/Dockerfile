FROM eclipse-temurin:21

# Install bash
# RUN apk add --no-cache bash

# Set the working directory inside the container
#WORKDIR /app

COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x /wait-for-it.sh

COPY reward-payment-app-1.0.1.jar reward-payment-app.jar

CMD ["./wait-for-it.sh", "reward-payment-db:3306", "--timeout=30", "--", "java", "-Dspring.profiles.active=mysql-container", "-jar", "reward-payment-app.jar"]

EXPOSE 8080
