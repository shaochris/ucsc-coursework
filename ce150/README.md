Final project for CMPE 150

High-level explaination for controller

Since we need to flood all ARP traffic, I have a if-else statement to handle arp traffic first, the rest will be ip traffic

in the "else", since all hosts need to go through core switch for any communications, I let all switches freely flow all traffic except core switch.

floor 1 switch, floor 2 switch, floor 3 siwtch and data center switch are basically similar. If a switch is sending data to a host, the port I set is 8. If a switch is sending data to a switch, then the port I set is 9.

For core switch, if I set port to 1, 2, 3, 4 if it is sending data to floor 1 switch , floor 2 switch, floor 3 switch, data center switch, respectly.
If core switch is sending data to trusted host, the port will be 8. if the core switch is sending data to untrusted host, the port will be 9.

After setting the port number, I install rules inside the core switch after the port number setting.

Policy:
Hosts 10/20/30: Allow ICMP from or to Hosts 10/20/30, Server and trusted host
					  TCP  from or to Hosts 10/20/30, Server, trusted host and untrusted host

Trusted Host:   Allow ICMP from or to Hosts 10/20/30, Server and untrusted host
					  TCP  from or to Hosts 10/20/30, Server and untrusted host

Untrusted Host: Allow ICMP from or to trusted Host
					  TCP  from or to Hosts 10/20/30 and trusted host

Server:         Allow ICMP from or to Hosts 10/20/30 and trusted host
					  TCP  from or to Hosts 10/20/30 and trusted host

I first check if the incoming packet is a icmp packet, otherwise it is a tcp packet.

if it is a icmp packet, 
I check 
if the source ip is trusted host, if yes, allow icmp traffic.
else if the source ip is untrusted host but the destination ip is not trusted, drop the packet
else, I allow icmp traffic among Hosts 10/20/30, trusted host and server

if it is a tcp packet
I check
if the destination ip is server, but the source ip is untrusted host, I drop the packet
else, I aloow all other hosts to use TCP

two hosts use either icmp or tcp, both hosts need to be sender and receiver. So we don't need to check twice.

