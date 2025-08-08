# Start by building the jar
FROM eclipse-temurin:17-jdk AS deps

WORKDIR /build

# The official docker documentation uses a wrapper for this, so so can we
COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/

# Download deps as their own step, this leverages docker caching
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline

##########

FROM deps AS package

WORKDIR /build

COPY ./src src/
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw package -DskipTests && \
    mv target/$(./mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout)-$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout).jar target/app.jar

##########
# This is the runtime for the application
FROM eclipse-temurin:17-jre AS final

# Create a non-privileged user that the app will run under.
# See https://docs.docker.com/go/dockerfile-user-best-practices/
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser

COPY --from=package build/target/app.jar ./
COPY --from=package build/target/final/ ./

ENTRYPOINT ["java", "-jar", "app.jar"]