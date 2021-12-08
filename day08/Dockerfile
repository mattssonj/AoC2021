FROM openjdk:17-alpine AS BUILD
COPY code/ code/
ADD https://github.com/JetBrains/kotlin/releases/download/v1.5.32/kotlin-compiler-1.5.32.zip .
RUN apk add -U unzip && rm -rf /var/cache/apk/*
RUN apk add --no-cache bash
RUN unzip kotlin-compiler-1.5.32.zip && rm -rf kotlin-compiler-1.5.32.zip
RUN kotlinc/bin/kotlinc code -include-runtime -d aoc.jar

FROM openjdk:17-alpine
COPY --from=BUILD aoc.jar /app.jar
COPY input.txt ./day08/input.txt
CMD java -jar /app.jar