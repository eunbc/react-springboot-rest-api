package com.ebcho.marketkurly.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public class JdbcUtils {

	private JdbcUtils() {
	}

	public static byte[] toBytes(UUID uuid) {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
		buffer.putLong(uuid.getMostSignificantBits());
		buffer.putLong(uuid.getLeastSignificantBits());
		return buffer.array();
	}

	public static UUID toUUID(Object key) {
		ByteBuffer bb = ByteBuffer.wrap((byte[])key);
		return new UUID(bb.getLong(), bb.getLong());
	}
}
