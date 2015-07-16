# License
This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.

A copy of the GNU GPL is located in `LICENSE.txt`.

# Screenshots 

![Graph](https://www.linux-toys.com/7.png)
![Objects](https://www.linux-toys.com/8.png)

# Running Cloud Explorer in Docker
<br>
The following steps will explain how to run CloudExplorer from a Docker container. The template will install a CentOS 7 container with TWM and Xterm.
```
git clone https://github.com/rusher81572/cloudExplorer.git -b docker
cd cloudExplorer
docker build -t cloudexplorer .
docker run -d --net=host cloudexplorer 
```
If you want to use your existing S3 configuration file, copy s3.config from your home directory into the cloudExplorer directory. Modify DockerFile by adding the following before the CMD line:
```
ADD s3.config /root/
```
Save the file and repeat the "docker build" and "docker run" commands from earlier.

To connect to the container, you can use a VNC client such as vncviewer to connect to display #1.
```
	vncviewer localhost:1
```
After you login VNC with the default password of 123456, you should see the GUI.

If you want to use the Cloud Explorer CLI, start a terminal in the VNC session by right clicking on the desktop and choose "xterm". Finally, run the following command:
```
	java -jar /cloudExplorer/CloudExplorer.jar help
```	
** Please note that you will need to use the GUI to create the s3.config file before using the CLI if you did not import your existing s3.config file **

<br>
