FROM centos
RUN yum -y install tar xterm tigervnc-* java 
RUN rpm -Uvh http://mirror.centos.org/centos/6/os/x86_64/Packages/xorg-x11-twm-1.0.3-5.1.el6.x86_64.rpm

#Configure Startup Script
RUN echo "#!/bin/bash" > /opt/start.sh
RUN echo "rm -rf /tmp/.X*" >> /opt/start.sh
RUN echo "vncserver -fg" >> /opt/start.sh
RUN chmod +x /opt/start.sh

#Add main package and VNC information
ADD cloudExplorer /
RUN mkdir /root/.vnc
RUN echo 123456 | vncpasswd -f > /root/.vnc/passwd
RUN echo "twm &" > /root/.vnc/xstartup
RUN echo "exec java -jar /CloudExplorer.jar" >> /root/.vnc/xstartup

RUN chmod 600 /root/.vnc/passwd
RUN chmod 777 /root/.vnc/xstartup
CMD /opt/start.sh
