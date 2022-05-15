<h1>Safekey DB</h1>

<h2>Description</h2>
<br />A SE 370 Project that stores encrypted passwords into a database.<br>
Project developed by Andrew Le, Ruth Jimenez, Carter Rath, Jerstine Medrano
<br />

<br />
To begin this analysis, Azure Sentinal must be set up. We will use Sentinal as a SIEM (Security Information and Event Management) to detect failed RDP Brute Force attacks. These attacks will take place on a live virtual machine connected to our SIEM to be used as a honey pot. These attempts will be observed and analyzed from around the world with the help of a custom PowerShell script to plot the attacker's Geolocation info and plot it on an Azure Sentinal Map.
<br />

<h2>Tools and Utilities Used</h2>
- <b>PowerShell:</b> Extract RDP failed logon logs from Windows Event Viewer to Microsoft Azure 
<br />
- <b>Microsoft Azure:</b> Set up a live virtual machine to be exposed to the world. Also manages logs from the VM's Microsoft Event Viewer to be plotted on a map
<br />
- <b>ipgeolocation.io:</b> Geolocation API and Accurate IP Lookup Database to use to plot on the map
<br />

<h2>World map of incoming attacks after roughly 24 hours (built custom logs including geodata)</h2>

<p align="center">
<img src="https://i.imgur.com/VM30Z6x.png" height="85%" width="85%" alt="Image Analysis Dataflow"/>
</p>
