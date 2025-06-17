package com.mingliqiye.libs;

import com.mingliqiye.libs.meta.ManifestReader;
import com.mingliqiye.libs.time.DateTime;
import com.mingliqiye.libs.util.StringUtil;

/**
 * @author MingLiPro|Armamem0t
 * @version 1.0
 * @see com.mingliqiye.libs.Main
 * com.mingliqiye.libs.Main
 */
public class Main {

  public static void main(String[] args) {
    ManifestReader.getManifestData()
      .properties()
      .forEach(i -> {
        System.out.println(i.getKey() + " : " + i.getValue());
      });

    System.out.println(StringUtil.format("{}qwq{}", "666", "777"));
  }
}
