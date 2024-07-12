# Use the official Oracle Database 21c Enterprise Edition base image
FROM container-registry.oracle.com/database/enterprise:latest

# Switch to root user
USER root

# Install OpenJDK 17 (Java 17) from Adoptium
RUN yum install -y wget && \
    wget https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.7%2B7/OpenJDK17U-jdk_x64_linux_hotspot_17.0.7_7.tar.gz && \
    tar -xzf OpenJDK17U-jdk_x64_linux_hotspot_17.0.7_7.tar.gz -C /opt && \
    rm OpenJDK17U-jdk_x64_linux_hotspot_17.0.7_7.tar.gz && \
    ln -s /opt/jdk-17.0.7+7 /opt/jdk-17

# Set JAVA_HOME environment variable
ENV JAVA_HOME=/opt/jdk-17
ENV PATH=$JAVA_HOME/bin:$PATH

# Change permissions for the Oracle log directory
RUN mkdir -p /opt/oracle/product/21c/dbhome_1/network/log && \
    touch /opt/oracle/product/21c/dbhome_1/network/log/listener.log && \
    chmod -R 777 /opt/oracle/product/21c/dbhome_1/network/log

# Switch to oracle user
USER oracle

# Copy the response file and the script to enable Data Vault
COPY dbca.rsp /opt/oracle/scripts/setup/
COPY enable_dv.sql /opt/oracle/scripts/setup/

# Set the working directory
WORKDIR /opt/oracle/scripts/setup/

# Run DBCA with the response file and enable Data Vault
RUN dbca -silent -createDatabase -responseFile /opt/oracle/scripts/setup/dbca.rsp && \
    sqlplus / as sysdba @enable_dv.sql

# Expose necessary ports
EXPOSE 1521 5500