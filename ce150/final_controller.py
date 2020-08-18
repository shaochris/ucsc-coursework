from pox.core import core
import pox.openflow.libopenflow_01 as of

log = core.getLogger()

class Final (object):
  """
  A Firewall object is created for each switch that connects.
  A Connection object for that switch is passed to the __init__ function.
  """
  def __init__ (self, connection):
    # Keep track of the connection to the switch so that we can
    # send it messages!
    self.connection = connection

    # This binds our PacketIn event listener
    connection.addListeners(self)

  def do_final (self, packet, packet_in, port_on_switch, switch_id):
    # This is where you'll put your code. The following modifications have 
    # been made from Lab 4:
    #   - port_on_switch represents the port that the packet was received on.
    #   - switch_id represents the id of the switch that received the packet
    #      (for example, s1 would have switch_id == 1, s2 would have switch_id == 2, etc...)
    # print "Hello, World!"
    msg = of.ofp_flow_mod()
    x   = of.ofp_flow_mod()
    h1 = "10.0.1.10"
    h2 = "10.0.2.10"
    h3 = "10.0.3.20"
    server = "10.0.4.10"
    trusted = "104.82.214.112"
    untrusted = "156.134.2.12"
    sport = 0

    arpPkt = packet.find('arp')
    # ipPkt = packet.find('ipv4')
    if arpPkt is not None:
      # flood all arp traffic
      msg.match.dl_type = 0x0806
      action = of.ofp_action_output(port = of.OFPP_FLOOD)
      msg.actions.append(action)
      msg.data = packet_in
      self.connection.send(msg)
    else: 
      x.match = of.ofp_match.from_packet(packet)
      x.match.idle_timeout = 30
      x.match.hard_timeout = 30
      src = x.match.nw_src
      dst = x.match.nw_dst

      ipPkt = packet.find('ipv4')
      # src = ipPkt.srcip
      # dst = ipPkt.dstip
      icmpPkt = packet.find('icmp')
      tcpPkt = packet.find('tcp')
      if ipPkt is not None:
        if switch_id == 1:
          # print("hello from switch 1")
          if dst == h1:
            sport = 8
          else:
            sport = 9
          action = of.ofp_action_output(port = sport)
          x.actions.append(action)
          x.data = packet_in
          self.connection.send(x)
        elif switch_id == 2:
          # print("hello from switch 2")
          
          if dst == h2:
            sport = 8
          else:
            sport = 9
          action = of.ofp_action_output(port = sport)
          x.actions.append(action)
          x.data = packet_in
          self.connection.send(x)     
        elif switch_id == 3:
          # print("hello from switch 3")
            if dst == h3:
              sport = 8
            else:
              sport = 9
            action = of.ofp_action_output(port = sport)
            x.actions.append(action)
            x.data = packet_in
            self.connection.send(x)
          # print("hello from core switch")
        elif switch_id == 4:
          if dst == trusted:
            sport = 8
          elif dst == untrusted:
            sport = 9
          elif dst == h1:
            sport = 1 
          elif dst == h2:
            sport = 2 
          elif dst == h3:
            sport = 3 
          else:
            sport = 4 
          if icmpPkt is not None:
            if src == trusted:
              x.match.dl_type = 0x0800
              action = of.ofp_action_output(port = sport)
              x.actions.append(action)
              x.data = packet_in
              self.connection.send(x)
            elif src == untrusted and dst != trusted:
              x.data = packet_in
              x.actions = [] 
              self.connection.send(x)
            else:
              x.match.dl_type = 0x0800
              action = of.ofp_action_output(port = sport)
              x.actions.append(action)
              x.data = packet_in
              self.connection.send(x)
          else:
            # print("ping tcp")
            if dst == server and src == untrusted:
              x.data = packet_in
              x.actions = [] 
              self.connection.send(x)
            else:
              x.match.dl_type = 0x0800
              x.match.nw_proto = 6
              action = of.ofp_action_output(port = sport)
              x.actions.append(action)
              x.data = packet_in
              self.connection.send(x)
          # end of switch 4          
        elif switch_id == 5:
          # print("hello from switch 5")
          if dst == server:
            sport = 8
          else:
            sport = 9
          action = of.ofp_action_output(port = sport)
          x.actions.append(action)
          x.data = packet_in
          self.connection.send(x)
        else:
          print("invalid switch")
          x.data = packet_in
          x.actions = [] 
          self.connection.send(x)
      else: # not ip pkt
        x.data = packet_in
        x.actions = [] 
        self.connection.send(x)

  def _handle_PacketIn (self, event):
    """
    Handles packet in messages from the switch.
    """
    packet = event.parsed # This is the parsed packet data.
    if not packet.parsed:
      log.warning("Ignoring incomplete packet")
      return

    packet_in = event.ofp # The actual ofp_packet_in message.
    self.do_final(packet, packet_in, event.port, event.dpid)

def launch ():
  """
  Starts the component
  """
  def start_switch (event):
    log.debug("Controlling %s" % (event.connection,))
    Final(event.connection)
  core.openflow.addListenerByName("ConnectionUp", start_switch)

