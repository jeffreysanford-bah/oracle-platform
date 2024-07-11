This currently pulls Oracle Database 23c into a docker container.

`docker login`
`docker pull container-registry.oracle.com/database enterprise:latest`

Run your docker container

`docker run container-registry.oracle.com/database/enterprise:latest`

This gets the IP address of your docker container

`docker inspect --format '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' hardcore_lichterman`

This get's you shell into your docker container
`docker exec -it hardcore_lichterman bash`

Once your XE reference below is setup, you should be able to log into the oracle instance without sending a username password.

bash-4.4$ sqlplus sys/password@xe as sysdba

whats the username and password for that particular oracle docker?

`docker run container-registry.oracle.com/database/enterprise:latest`

When running the Oracle Database Docker container, the default username and password are as follows:
Username: sys
Password: 12345678

`docker run container-registry.oracle.com/database/enterprise:latest`

`docker run -d --name oracle-db -p 1521:1521 -p 5500:5500 -e ORACLE_SID=ORCLCDB -e ORACLE_PDB=ORCLPDB1 -e ORACLE_PWD=12345678 container-registry.oracle.com/database/enterprise:latest`

This command does the following:
-d: Runs the container in detached mode.
--name oracle-db: Assigns a name to the container (you can choose a different name).
-p 1521:1521 -p 5500:5500: Maps host ports to container ports for Oracle Database and Enterprise Manager.
-e ORACLE_SID=ORCLCDB -e ORACLE_PDB=ORCLPDB1 -e ORACLE_PWD=12345678: Sets environment variables for the Oracle instance.

To monitor the build process of a Docker container, you can use the following approaches:

Check Container Status:

Use docker ps to see the status of running containers. If your container is still building, it won’t appear in the list of running containers.

Alternatively, you can inspect a specific container using `docker inspect -f '{{.State.Status}}' CONTAINER_NAME_OR_ID`. Replace CONTAINER_NAME_OR_ID with the actual name or ID of your container.

Live Resource Usage Stats:

To monitor resource usage during the build process, use `docker stats CONTAINER_NAME_OR_ID`. This command provides live data on CPU, memory, network I/O, and other metrics for running containers2.

Systemctl (Linux):
On Linux distributions using Systemd (e.g., Debian, Ubuntu, CentOS, Red Hat), you can check Docker’s status with:
sudo systemctl status docker
Look for the status displayed under “Active” to determine if Docker is running3.

Remember that the container won’t appear in docker ps until it’s fully built and running. If you need more detailed information, consider using the health check scripts mentioned earlier.

Log past:
[2024:06:27 17:11:43]: Acquiring lock .ORCLCDB.create_lck with heartbeat 30 secs
[2024:06:27 17:11:43]: Lock acquired
[2024:06:27 17:11:43]: Starting heartbeat
[2024:06:27 17:11:43]: Lock held .ORCLCDB.create_lck
ORACLE EDITION: ENTERPRISE
LSNRCTL for Linux: Version 21.0.0.0.0 - Production on 27-JUN-2024 17:11:44
Copyright (c) 1991, 2021, Oracle. All rights reserved.
Starting /opt/oracle/product/21c/dbhome_1/bin/tnslsnr: please wait...
TNSLSNR for Linux: Version 21.0.0.0.0 - Production
System parameter file is /opt/oracle/homes/OraDB21Home1/network/admin/listener.o
ra
Log messages written to /opt/oracle/diag/tnslsnr/01b7fc4c85ec/listener/alert/log
.xml
Listening on: (DESCRIPTION=(ADDRESS=(PROTOCOL=ipc)(KEY=EXTPROC1)))
Listening on: (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=0.0.0.0)(PORT=1521)))
Connecting to (DESCRIPTION=(ADDRESS=(PROTOCOL=IPC)(KEY=EXTPROC1)))
STATUS of the LISTENER

---

Alias LISTENER
Version TNSLSNR for Linux: Version 21.0.0.0.0 - Production
Start Date 27-JUN-2024 17:11:44
Uptime 0 days 0 hr. 0 min. 0 sec
Trace Level off
Security ON: Local OS Authentication
SNMP OFF
Listener Parameter File /opt/oracle/homes/OraDB21Home1/network/admin/listener.
ora
Listener Log File /opt/oracle/diag/tnslsnr/01b7fc4c85ec/listener/alert/l
og.xml

Listening Endpoints Summary...
(DESCRIPTION=(ADDRESS=(PROTOCOL=ipc)(KEY=EXTPROC1)))
(DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=0.0.0.0)(PORT=1521)))
The listener supports no services
The command completed successfully
