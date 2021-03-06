# License
This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.

A copy of the GNU GPL is located in `LICENSE.txt`.

# Important Advisory
Version 7.1 contains a windows syncing bug. Please upgrade to v7.2 from inside Cloud Explorer or download the full release.

# Description 
Cloud Explorer is a open-source S3 client. It works on Windows, Linux, and Mac.  It has a graphical and command line interface for each supported operating system. If you have a feature suggestion or find a bug, please open an issue.

# Features

* Search
* Performance testing
* Music player
* Transition buckets to Amazon Glacier  
* Amazon RRS and Infrequently Accessed storage tiers
* Migrate buckets between S3 accounts
* Compress files prior to upload
* Take screen shots to S3
* Simple text editor
* IRC client
* Share buckets with users
* Access shared buckets
* View images
* Sync folders
* Graph CSV files and save them to a bucket
* Record audio messages and save them to a bucket
* Create snapshots of buckets

# Screenshots 

![Graph](http://i.imgur.com/aw5iKZf.png)

# How to run the program

To run the application, most users should be able to run the program by double clicking on the CloudExplorer.jar file. 

If not, you can start the program with the Java command:
```
java -jar CloudExplorer.jar
```
# Video demonstraton

https://www.youtube.com/watch?v=O1HVDYywZRY

# 7.x Upgrade Information

The latest release is v7.2. There is no upgrade path to this release prior to v7.1 due to a change in the AWS Java API's. Please download the full v7.2 release under releases. 


# How to compile from source

Video demonstration for NetBeans: http://youtu.be/54v3rIUh0h8
```
yum -y install git java-1.8-openjdk java-1.8.0-openjdk-devel ant
```
Use "update-alternatives" to set Java aliases to 1.8
```
update-alternatives --config java 
update-alternatives --config javac
```
Clone this repository and run ant to compile the project
```
git clone https://github.com/rusher81572/cloudExplorer.git
cd cloudExplorer
ant
```
Run Cloud Explorer
```
java -jar dist/CloudExplorer.jar
```

# Upgrading

To upgrade, please use the updater located in the application. Everytime Cloud Explorer is ran, it will check for the latest version. To apply the update, click "Help -> Check for updates". Some upgrades may contain newer API's and will require that you manually download the entire release under "releases" in GitHub.


# How to migrate data between S3 accounts
* Load the destination account and create or select a bucket to migrate to.
* From the menu "Snapshots and Migration", click "Set Migration/Snapshot Account and Bucket".
* Switch to the origin account and select the bucket to migrate.
* Under the "Snapshots and Migration", select "Migrate Bucket to Another S3 Account".
* Click Start Migration

# How to create a bucket snapshot
* Load the destination account and create or select a bucket to store the snapshot.
* From the menu "Snapshots and Migration", click "Set Migration/Snapshot Account and Bucket".
* Switch to the origin account and select the bucket to snap.
* Under the "Snapshots and Migration", select "Create or Restore a Bucket Snapshot".
* Click Create Snapshot

# Background Sync

Background Sync allows Cloud Explorer to sync a directory in the background to S3 every 5 minutes. Sync will only upload files that do not exist on S3 and no files will be deleted. 
<br>
<br>
From the GUI: 
* Click on "Background Syncing"
* Click Configure
* Click Save.
* Click on "Background Syncing"
* Click "Run".
* Syncing will occur every 5 minutes.

Starting background sync from the Command Line (Useful for running as a background process):

Create your config file from the GUI.
```
	$ java -jar CloudExplorer.jar daemon
```

# Running Cloud Explorer in Docker
* https://github.com/rusher81572/cloudExplorer/tree/docker
