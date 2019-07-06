package io.tesla.proviso.archive.delta;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import io.tesla.proviso.archive.ArchiverHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class Hash {

  public static String hashOf(File file) throws IOException {
    return hashOf(new FileInputStream(file));
  }

  public static String hashOf(InputStream stream) throws IOException {
    Hasher hasher = Hashing.md5().newHasher();
    try (InputStream targetStream = stream) {
      ByteStreams.copy(targetStream, Funnels.asOutputStream(hasher));
    }
    return hasher.hash().toString();
  }

  public static Map<String, String> hashEntriesOf(File archive) throws IOException {
    return ArchiverHelper.getArchiveHandler(archive).hashEntriesOf(archive);
  }
}