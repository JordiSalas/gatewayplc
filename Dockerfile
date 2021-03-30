FROM java:8
COPY . /java/www/java
WORKDIR /java/www/java
RUN javac Gatewayplc.java
