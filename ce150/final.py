#!/usr/bin/python

from mininet.topo import Topo
from mininet.net import Mininet
from mininet.util import dumpNodeConnections
from mininet.log import setLogLevel
from mininet.cli import CLI
from mininet.node import RemoteController

class final_topo(Topo):
  def build(self):
    
    # Examples!
    # Create a host with a default route of the ethernet interface. You'll need to set the
    # default gateway like this for every host you make on this assignment to make sure all 
    # packets are sent out that port. Make sure to change the h# in the defaultRoute area
    # and the MAC address when you add more hosts!
    h1 = self.addHost('h1',mac='00:00:00:00:00:01',ip='10.0.1.10/24', defaultRoute="h1-eth0") # h10
    h2 = self.addHost('h2',mac='00:00:00:00:00:02',ip='10.0.2.10/24', defaultRoute="h2-eth0") # h20
    h3 = self.addHost('h3',mac='00:00:00:00:00:03',ip='10.0.3.20/24', defaultRoute="h3-eth0") # h30
    h4 = self.addHost('h4',mac='00:00:00:00:00:04',ip='10.0.4.10/24', defaultRoute="h4-eth0") # server
    h5 = self.addHost('h5',mac='00:00:00:00:00:05',ip='104.82.214.112/24', defaultRoute="h5-eth0") # trusted host
    h6 = self.addHost('h6',mac='00:00:00:00:00:06',ip='156.134.2.12/24', defaultRoute="h6-eth0") # untrusted host
    # Create a switch. No changes here from Lab 1.
    s1 = self.addSwitch('s1')
    s2 = self.addSwitch('s2')
    s3 = self.addSwitch('s3')
    s4 = self.addSwitch('s4') # core switch
    s5 = self.addSwitch('s5') # data center switch

    # Connect Port 8 on the Switch to Port 0 on Host 1 and Port 9 on the Switch to Port 0 on 
    # Host 2. This is representing the physical port on the switch or host that you are 
    # connecting to.
    #floor 1
    # host1 8 <-switch-> 9 core switch
    self.addLink(s1,h1, port1=8, port2=0) 
    self.addLink(s1,s4, port1=9, port2=1)

    #floor 2
    # host2 8 <-switch-> 9 core switch
    self.addLink(s2,h2, port1=8, port2=0)
    self.addLink(s2,s4, port1=9, port2=2)

    #floor 3
    # host3 8 <-switch-> 9 core switch
    self.addLink(s3,h3, port1=8, port2=0)
    self.addLink(s3,s4, port1=9, port2=3)


    #extern hosts to core
    # trusted host 8 <-switch-> 9 untrusted host
    # host1 1 <-switch-> 2 host2
    # host3 3 <-switch-> 4 server
    self.addLink(s4,h5, port1=8, port2=0) # trusted host to core switch
    self.addLink(s4,h6, port1=9, port2=0) # untrusted host to core switch
    
    #server to data center
    # server 8 <-switch-> 9 core siwtch
    self.addLink(s5,h4, port1=8, port2=0) # server to data center switch
    #core to data center switch
    self.addLink(s5,s4, port1=9, port2=4) # core switch to data center switch

def configure():
  topo = final_topo()
  net = Mininet(topo=topo, controller=RemoteController)
  net.start()

  CLI(net)
  
  net.stop()


if __name__ == '__main__':
  configure()
