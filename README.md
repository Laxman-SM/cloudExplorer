![Graph](https://www.linux-toys.com/7.png)

# Running Cloud Explorer in Docker
<br>
The following steps will explain how to run Cloud Explorer from a Docker container. The template will install an Ubuntu container using the FVWM window manager.
```
git clone https://github.com/rusher81572/cloudExplorer.git -b docker
cd cloudExplorer
docker build -t cloudexplorer .
docker run -d --net=host cloudexplorer 
```

# Using your exisiting s3.config file
If you want to use your existing S3 configuration file, copy s3.config from your home directory into the cloudExplorer directory. Finally, modify DockerFile by adding the following before the CMD line:
```
ADD s3.config /root/
```

Save the file and repeat the "docker build" and "docker run" commands from earlier.

# Using Enviornment Variables with the CLI

To use enviornment variables to store S3 account information when using the  Cloud Explorer CLI instead of uploading an s3.config file:
```
docker run -it --net=host -e ACCESS_KEY='****' -e SECRET_KEY='***' -e ENDPOINT='https://s3.amazonaws.com:443' -e REGION='default' cloudexplorer 
java -jar /CloudExplorer.jar help
```

# Accessing the GUI via VNC

To connect to the container, you can use a VNC client such as vncviewer to connect to display #1.
```
	vncviewer localhost:1
```
After you login VNC with the default password of 123456, you should see the GUI.

# Using the CLI in VNC 

If you want to use the Cloud Explorer CLI, start a terminal in the VNC session by right clicking on the desktop and choose "xterm". Finally, run the following command:
```
	java -jar /cloudExplorer/CloudExplorer.jar help
```	
** Please note that you will need to use the GUI to create the s3.config file before using the CLI if you did not import your existing s3.config file **

<br>
