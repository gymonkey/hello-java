# 多阶段构建：第一阶段 - 使用Maven编译项目
FROM maven:3.8.4-openjdk-17 AS builder

# 设置工作目录
WORKDIR /app

# 复制pom.xml文件并下载依赖（利用Docker缓存）
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 复制源代码并编译打包
COPY src ./src
RUN mvn clean package -DskipTests

# 多阶段构建：第二阶段 - 使用Java运行应用
FROM openjdk:17-jre-slim

# 设置工作目录
WORKDIR /app

# 从第一阶段复制生成的fat jar
COPY --from=builder /app/target/*.jar app.jar

# 暴露应用端口（根据实际应用端口修改）
EXPOSE 8080

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]