# Command Line Toolkit

## Introduction

This is the Command Line Toolkit. It was intended as a plug-in to a consisting Java server application that has a command-line maintenance interface. While development was going on, I added an IRC library to be able to control several nodes by sending them all the same command.

## Build

Since the project is built with Apache Maven, it can be compiled and started with `mvn compile`.

To start the Terminal, simply execute 
  
    mvn exec:java -Dexec.mainClass="de.kroesch.clt.Terminal"

## Execute Bot 

    mvn exec:java -Dexec.mainClass="de.kroesch.clt.net.CloudBot"

  * Run an IRC server on a master host. Make sure that no other computers can connect to this host.
  * Bot connects to the IRC host specified in ''.console.properties''
  * Bot ignores any messages from users other than botmaster. The latter are executed silently.
  * When invited to a private chat, the bot answers to commands, i.e. sends output as message back.
