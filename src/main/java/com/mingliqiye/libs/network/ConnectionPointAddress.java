package com.mingliqiye.libs.network;

import java.net.InetSocketAddress;

/**
 * Copyright © 2015, 2026, minglipro|Armamem0t. All rights reserved.
 * <p>
 * email: minglipro@163.com
 */
public class ConnectionPointAddress
  extends InetSocketAddress
  implements java.io.Serializable {

  public ConnectionPointAddress(int port) {
    super(port);
  }
}
