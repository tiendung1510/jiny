package com.jinyframework.examples.scalacrud

import com.jinyframework.HttpProxy

object Main extends App {
  new Thread(ServerBootstrap).start() // Port 1234
  new Thread(ServerBootstrapNIO).start() // Port 1235

  val proxy = HttpProxy.port(1111)
  proxy.use("/server1", "localhost:1234")
  proxy.use("/server2", "localhost:1235")
  proxy.start()
}
