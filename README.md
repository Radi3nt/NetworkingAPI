<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#usage">Usage</a></li>
      </ul>
    </li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This is a complete networking API to use with Java.
Why SHOULD you use this API:
* This is a fast and efficient API that use dynamic compresseion
* It's easly understandable and usable for anyone familliar with Java
* Disgned for maximum extensibility and flexibility
* This project is currently actively updated

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple example steps.

### Installation

_You don't need anything more than a Java IDE to get started!_

Clone the repo
   ```sh
   git clone https://github.com/Radi3nt/NetworkingAPI.git
   ```

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

### Packets

This librairy uses packets to communicate on a connection.
You can create a packet by implementing the class _PacketWrite_ or _PacketRead_. 
  * _PacketWrite_ class is used to send packets through a connection. 
  * _PacketRead_ class is used for when you recieve a _PacketRead_ through a connection, it reads the data for you to use it later.
  
Here is an exemple of a read and write packet sending an integer.

```Java
public class ExemplePacketReadWrite implements PacketRead, PacketWrite {

    public int number;

    @Override
    public void read(ReadablePacketBuffer packetBuffer) {
        IntReader intReader = new IntReader();
        packetBuffer.read(intReader);

        number = intReader.getIntResult();
    }

    @Override
    public void write(WritablePacketBuffer packetBuffer) throws EncodeException {
        IntWriter intWriter = new IntWriter(number);
        packetBuffer.write(intWriter);
    }

    @Override
    public String toString() {
        return "TestPacketReadWrite{" +
                "number=" + number +
                '}';
    }
}
```

But wait, what are those _WritablePacketBuffer_ and _ReadablePacketBuffer_ ? These are in fact buffers which allow to read or write packet information through the connection.   
*Note: if you have a class more efficient than a ByteBuffer or a DataOutputStream, you can provide a custom implementation of those classes*   
   
    
### Connections

Now that you know how to create packets and send information through them, how do you establish a connection between a client and a server ?   
*Note: this part uses the default implementation of the librairy, java sockets. You can totally use a different protocol and create your own protocol by extending the classes in the connection package*   
   
    
To create a client connection, you need a protocol. The protocol must be the same on the client and on the server to be sure that the data is read correctly. 
A protocol will define how your packets are sent through a connection (you can totally create your own protocol by extending the PacketProtocol class).  
   
The default class, _SizedIdPacketProtocol_ will encode the packet using it's id, provided by the _PacketProtocolIdentification_ you gave it, and it's total size.
  
You then provide the adress and the port of the connection and it will instantly try to establish a link. Here is an exemple of creating a basic client connection

```Java
private static ConnectingConnection createConnection() throws NetworkException {
    PacketFactoryProtocolIdentification packetFactoryProtocolIdentification = createIdentification();
    SizedIdPacketProtocol simplePacketProtocol = new SizedIdPacketProtocol(packetFactoryProtocolIdentification);
    return new ConnectingConnection(new SocketAddress("140.82.124.4", 25565), simplePacketProtocol, System.out::println);
}

public static PacketFactoryProtocolIdentification createIdentification() {
    Map<Integer, PacketFactory> packetFactoryMap = new HashMap<>();
    packetFactoryMap.put(1, new TestPacketFactory());

    return new PacketFactoryProtocolIdentification(packetFactoryMap);
}

private static class TestPacketFactory implements PacketFactory {
        @Override
        public boolean isPacket(Packet packet) {
            return packet instanceof TestPacketReadWrite;
        }

        @Override
        public PacketRead create() {
            return new TestPacketReadWrite();
        }
    }
```


### Listeners

After you have created your connection, you need to hook a listener onto it to be able to recieve packets from the server.   
Use the _InteractiveConnection.attachListener()_ method in a different thread to be able to recieve messages. You can create a PacketListener and give it to the connection on it's creation.   
⚠The _InteractiveConnection.attachListener()_ method blocks until the connection is closed, use it in a SEPARATED thread⚠   

```Java
private static void attachListener(InteractiveConnection interactiveConnection) {
    Thread thread = new Thread(interactiveConnection::attachListener);
    thread.start();
}
```

### Sending packets

Now that you have a working connection, you can send packets using the _sendPacket(PacketWrite... packets)_ method in any connection. It will block until the packet has been written through the connection.

### Server

You need to use a _ConnectionHandler_ to recieve a connection. There is a implementation of it called _SocketServerConnectionHandler_ that you can use.   
Create one and call the _accept()_ method. This method will block until a client is connecting to the server, and will then return a _ServerConnection_ which you can interact with   
_Note: don't forget to attach a listener to this connection by using the_ attachListener() _method !_

### Conclusion

If you still need help, there are two class, called _MainNetworkingAPIDemoServer_ and _MainNetworkingAPIDemoClient_ in the main package for you to study.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/Radi3nt/MultiplayerAPI/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch
3. Commit your Changes
4. Push to the Branch
5. Open a Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the Creative Commons Zero v1.0 Universal. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Radi3nt - pro.radi3nt@gmail.com

Project Link: [https://github.com/Radi3nt/NetworkingAPI](https://github.com/Radi3nt/NetworkingAPI)

<p align="right">(<a href="#top">back to top</a>)</p>
