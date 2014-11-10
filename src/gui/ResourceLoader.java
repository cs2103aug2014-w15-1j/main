package gui;

import java.io.InputStream;

/**
 * to get resource(image) from the source folder a special thanks to Wichit
 * Sombat. here is the youtube link I got the idea from:
 * https://www.youtube.com/watch?v=rCoed3MKpEA
 * 
 * @author A0119391A
 * 
 */
final public class ResourceLoader {
	public static InputStream load(String path) {
		InputStream input = ResourceLoader.class.getResourceAsStream(path);
		if (input == null) {
			input = ResourceLoader.class.getResourceAsStream("/" + path);
		}
		return input;
	}
}
