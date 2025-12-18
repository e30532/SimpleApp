
# Prerequisite
アプリケーションを動かすためにはEMPというテーブルが必要になります。
以下の例はSAMPLEというデータベースのDB2INST1というスキーマのEMPというテーブルのDDLです。

```
CREATE TABLE "DB2INST1"."EMP" ("ID" INTEGER NOT NULL, "NAME" CHAR(5 OCTETS)) IN "USERSPACE1" ORGANIZE BY ROW; 
ALTER TABLE "DB2INST1"."EMP" ADD PRIMARY KEY("ID");
INSERT INTO EMP(ID, NAME) VALUES(1,'IBM')
```

```
[db2inst1@fyre1 ~]$ db2look -d SAMPLE -e -x -z DB2INST1 -t EMP
:
CONNECT TO SAMPLE;
------------------------------------------------
-- DDL Statements for Table "DB2INST1"."EMP"
------------------------------------------------
CREATE TABLE "DB2INST1"."EMP"  (
		  "ID" INTEGER NOT NULL , 
		  "NAME" CHAR(5 OCTETS) )   
		 IN "USERSPACE1"  
		 ORGANIZE BY ROW; 
     
-- DDL Statements for Primary Key on Table "DB2INST1"."EMP"
ALTER TABLE "DB2INST1"."EMP" 
	ADD PRIMARY KEY
		("ID");
COMMIT WORK;
CONNECT RESET;
TERMINATE;
```

# How to access
http://host:9080/SimpleJDBCWeb/


# How to work
index.jspのフォームデータから、select/insert/update/deleteの４タイプのSQLをサーブレットに送ることができます。サーブレットはタイプに応じて、DBへの接続を取得し、SQLを実行します。   
テーブル全体をSELECTしたり、更新したりできます。必要に応じてtext boxのSQLを変更してください。   


# Libertyで動かすための設定例

serverNameやuser, passwordは適宜変更する必要があります。  

Oracleの場合  

```
<server description="new server">

    <!-- Enable features -->
    <featureManager>
        <feature>jsp-2.2</feature>
        <feature>jdbc-4.0</feature>
        <feature>localConnector-1.0</feature>
    </featureManager>

    <!-- To access this server from a remote client add a host attribute to the following element, e.g. host="*" -->
    <httpEndpoint host="*" httpPort="9080" httpsPort="9443" id="defaultHttpEndpoint"/>

        <!-- Automatically expand WAR files and EAR files -->
        <applicationManager autoExpand="true"/>
        <dataSource jndiName="jdbc/SimpleDS">
                <jdbcDriver libraryRef="OracleLib"/>
                <properties.oracle URL="jdbc:oracle:thin:@//fyre1:1521/PDB1" password="*****" user="e30532"/>
        </dataSource>
        <library id="OracleLib">
        <fileset dir="/opt/IBM/WebSphere/Liberty/ND/usr/shared/resources/"/>
    </library>

    <applicationMonitor updateTrigger="mbean"/>
</server>
```


DB2の場合  

```
<server description="new server">
    <!-- Enable features -->
    <featureManager>
        <feature>jsp-2.3</feature>
        <feature>jdbc-4.0</feature>
        <feature>localConnector-1.0</feature>
    </featureManager>
    <!-- To access this server from a remote client add a host attribute to the following element, e.g. host="*" -->
    <httpEndpoint host="*" httpPort="9080" httpsPort="9443" id="defaultHttpEndpoint"/>
    <!-- Automatically expand WAR files and EAR files -->
    <applicationManager autoExpand="true"/>
<library id="DB2JCC4Lib">
    <fileset dir="C:/tools" includes="db2jcc4.jar"/>
</library>
<dataSource id="DefaultDataSource" jndiName="jdbc/SAMPLE">
    <connectionManager agedTimeout="0" connectionTimeout="10s" maxPoolSize="20" minPoolSize="5"/>
    <jdbcDriver libraryRef="DB2JCC4Lib"/>
    <properties.db2.jcc databaseName="SAMPLE" password="*****" portNumber="50000" serverName="fyre1" user="db2inst1"/>
</dataSource>

    <applicationMonitor updateTrigger="mbean"/>
</server>
```

MS SQL Server
```
<server description="new server">

    <!-- Enable features -->
    <featureManager>
        <feature>localConnector-1.0</feature>
		<feature>javaee-7.0</feature>
	</featureManager>

    <!-- To access this server from a remote client add a host attribute to the following element, e.g. host="*" -->
    <httpEndpoint host="*" httpPort="9080" httpsPort="9443" id="defaultHttpEndpoint"/>
                  
    <!-- Automatically expand WAR files and EAR files -->
    <applicationManager autoExpand="true"/>

    <keyStore id="defaultKeyStore" password="Liberty"/>
    <basicRegistry id="basic" realm="BasicRealm"> 
        <user name="admin" password="admin"/>
    </basicRegistry>
    <administrator-role>
         <user>admin</user>
    </administrator-role>	

    <library id="MSSQLJCC4Lib">
        <fileset dir="/home/ibmwasl2/Downloads/sqljdbc_13.2/enu/jars" includes="mssql-jdbc-13.2.1.jre8.jar"/>
    </library>
    <dataSource id="DefaultDataSource" jndiName="jdbc/SimpleDS">
	<connectionManager connectionTimeout="15s" maxPoolSize="50" minPoolSize="0" maxIdleTime="30s" reapTime="10s" purgePolicy="EntirePool"/>
        <jdbcDriver libraryRef="MSSQLJCC4Lib"/>
	<properties.microsoft.sqlserver serverName="c55664v1.fyre.ibm.com" portNumber="1433" databaseName="WASDB" user="dbadmin" password="*****" encrypt="false" integratedSecurity="false"/>
    </dataSource>
</server>

```


# WASで動かすための設定例
data sourceの設定が必要です。

 アプリケーションに含まれるweb.xmlのres-ref(リソース参照)の構成は、アプリケーションが間接参照でリソース（データソース）をlookupするのに必要ですが、アノテーションを使うことで省略も可能です。   
 res-refの中のres-authがApplicationとなっているのは、getConnection要求の際に、uid, passwordが引数で指定されればそれが使われ、指定されてなければ、データソースのコンポーネント管理認証別名を設定する必要があります。res-authがContainerの場合には、コンテナー管理認証別名を設定する必要があります。　　　
