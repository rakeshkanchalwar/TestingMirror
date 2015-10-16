package com.bitwise.app.engine.util;

import java.io.IOException;
import java.io.Writer;
import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

/**
 * @author niting
 *
 */
public class CustomCharacterEscapeHandler implements CharacterEscapeHandler{

	@Override
	public void escape(char[] ch, int start, int length, boolean isAttVal, Writer writer) throws IOException {
		writer.write(ch, start, length);
	}
}
