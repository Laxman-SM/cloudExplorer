FROM fedora
RUN dnf -y install fvwm tar xterm tigervnc-* java firefox

#Configure Startup Script
RUN echo "#!/bin/bash" > /opt/start.sh
RUN echo "rm -rf /tmp/.X*" >> /opt/start.sh
RUN echo "vncserver -fg" >> /opt/start.sh
RUN chmod +x /opt/start.sh

#Add main package and VNC information
ADD cloudExplorer /
RUN mkdir /root/.vnc
RUN echo 123456 | vncpasswd -f > /root/.vnc/passwd
RUN echo "fvwm &" > /root/.vnc/xstartup
RUN echo "exec java -jar /CloudExplorer.jar" >> /root/.vnc/xstartup

RUN chmod 600 /root/.vnc/passwd
RUN chmod 777 /root/.vnc/xstartup
CMD /opt/start.sh
