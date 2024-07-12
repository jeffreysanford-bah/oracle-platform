docker login container-registry.oracle.com

Pulll these docker files:
Oracle17c:
docker pull container-registry.oracle.com/database/enterprise:latest

Redhat:
docker pull registry.access.redhat.com/ubi8/ubi:latest

Start docker

Run these commands to create and run the environment:

docker build -t raptor-oracle-java:21c .

docker run -d raptor-oracle-java:21c

<return id>

docker exec -it <return id> bash

Example:

$ docker run -d raptor-oracle-java:21c
ec0be457404b779759ed13d93caeefd40324ab041f816016c3541d2220cfb9cb

633517@BAHCND3161L74 MINGW64 ~/repos/oracle-platform/kubernetes (master)
$ docker exec -it ec0be457404b77975 bash
bash-4.2$ java -version
openjdk version "17.0.7" 2023-04-18
OpenJDK Runtime Environment Temurin-17.0.7+7 (build 17.0.7+7)
OpenJDK 64-Bit Server VM Temurin-17.0.7+7 (build 17.0.7+7, mixed mode, sharing)
bash-4.2$

Login into your oracle database

`sqlplus / as sysdba`

true means Oracle Data Vault is installed.

SQL> `SELECT * FROM V$OPTION WHERE PARAMETER = 'Oracle Database Vault';`
`

Oracle Database Vault
FALSE      

`ALTER SESSION SET CONTAINER = CDB$ROOT;`
`ALTER SYSTEM SET enable_DV = TRUE SCOPE=SPFILE;`
`SHUTDOWN IMMEDIATE;`
`STARTUP;`

ALTER the SYSTEM with dv doesnt work, this command does:

`@?/rdbms/admin/catmac.sql USERS TEMP`

Verify the Existence of the DBMS_DV Package: Check if the DBMS_DV package exists in the database:
SQL

SELECT object_name, object_type
FROM dba_objects
WHERE object_name = 'DBMS_DV';

Fail:  no rows selected


SELECT comp_name, version, status
FROM dba_registry
WHERE comp_id = 'DV';

Pass: 
COMP_NAME
--------------------------------------------------------------------------------
VERSION                        STATUS
------------------------------ --------------------------------------------
Oracle Database Vault
21.0.0.0.0                     VALID


1 row selected.


