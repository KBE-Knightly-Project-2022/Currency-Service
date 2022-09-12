FROM maven:3.8.4-openjdk-17

WORKDIR /CurrencyService
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run