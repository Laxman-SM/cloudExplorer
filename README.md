![Graph](https://www.linux-toys.com/7.png)

# Running Cloud Explorer in Docker
First
The following steps will explain how to run Cloud Explorer from a Docker container. The template will install an Ubuntu container using the FVWM window manager. 

# Creating the container

First, clone this repo:
```
git clone https://github.com/rusher81572/cloudExplorer.git -b docker
cd cloudExplorer
```

If you want to use your existing S3 configuration file, copy s3.config from your home directory into the cloudExplorer directory. Modify DockerFile by adding the following before the CMD line:
```
ADD s3.config /root/
```

Build the container
```
docker build -t cloudexplorer .
```

# Running the container - CLI


To use enviornment variables to store S3 account information when using the Cloud Explorer CLI instead of uploading an s3.config file:
```
docker run -it --net=host -e ACCESS_KEY='****' -e SECRET_KEY='***' -e ENDPOINT='https://s3.amazonaws.com:443' -e REGION='default' cloudexplorer 
java -jar /CloudExplorer.jar help
```


# Running the container - GUI

Run the container:
```
docker run -d --net=host cloudexplorer 
````

To connect to the container, you can use a VNC client such as vncviewer to connect to display #1.
```
vncviewer docker_host_ip:1
```
After you login VNC with the default password of 123456, you should see the GUI.

# Using the CLI in VNC 

If you want to use the Cloud Explorer CLI, start a terminal in the VNC session by right clicking on the desktop and choose "xterm". Finally, run the following command:
```
java -jar /cloudExplorer/CloudExplorer.jar help
```	




