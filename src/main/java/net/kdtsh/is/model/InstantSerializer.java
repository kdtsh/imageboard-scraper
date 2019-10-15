package net.kdtsh.is.model;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class InstantSerializer extends StdSerializer<Instant> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2869695937725184328L;

	public InstantSerializer() {
		this(null);
	}

	protected InstantSerializer(Class<Instant> t) {
		super(t);
	}

	@Override
	public void serialize(Instant value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(value.toString());
	}

}
