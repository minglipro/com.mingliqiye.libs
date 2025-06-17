package com.mingliqiye.libs.meta;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.Manifest;

/**
 * 读取本 jar 的 Manifest 文件
 * @see com.mingliqiye.libs.meta.ManifestReader
 * com.mingliqiye.libs.Main
 * @author MingLiPro|Armamem0t
 * @version 1.0
 */
public final class ManifestReader {

  private static final Manifest manifest = readManifest();
  private static final ManifestData manifestData = new ManifestData(manifest);

  public static ManifestData getManifestData() {
    return ManifestReader.manifestData;
  }

  public static final class ManifestData {

    private final Manifest manifest;

    public ManifestData(Manifest manifest) {
      this.manifest = manifest;
    }

    public String getVersion() {
      if (manifest == null) return null;
      return manifest.getMainAttributes().getValue("Implementation-Version");
    }

    public String getCopyright() {
      if (manifest == null) return null;
      return manifest.getMainAttributes().getValue("Copyright");
    }

    public String getVendor() {
      if (manifest == null) return null;
      return manifest.getMainAttributes().getValue("Implementation-Vendor");
    }

    public String getEmail() {
      if (manifest == null) return null;
      return manifest.getMainAttributes().getValue("Email");
    }

    public String getTitle() {
      if (manifest == null) return null;
      return manifest.getMainAttributes().getValue("Implementation-Title");
    }

    public String getEnv() {
      if (manifest == null) return null;
      return manifest.getMainAttributes().getValue("Env");
    }

    public String getMainClass() {
      if (manifest == null) return null;
      return manifest.getMainAttributes().getValue("Main-Class");
    }

    public boolean getDevelopment() {
      String env = null;
      if (
        manifest == null &&
        (env = manifest.getMainAttributes().getValue("Env")) == null
      ) return true;

      if (env == null) return true;
      return !env.equals("prod");
    }

    public Set<Map.Entry<Object, Object>> properties() {
      if (manifest == null) return new HashSet<>();
      return manifest.getMainAttributes().entrySet();
    }
  }

  private static Manifest readManifest() {
    try {
      ClassLoader classLoader = ManifestReader.class.getClassLoader();
      if (classLoader instanceof URLClassLoader) {
        URL jarUrl =
          ((URLClassLoader) classLoader).findResource("META-INF/MANIFEST.MF");
        if (jarUrl != null) {
          return new Manifest(jarUrl.openStream());
        }
      }
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
