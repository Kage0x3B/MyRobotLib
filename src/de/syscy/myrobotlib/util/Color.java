package de.syscy.myrobotlib.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Color {
	public static final Color WHITE = new Color(java.awt.Color.WHITE);
	public static final Color LIGHT_GRAY = new Color(java.awt.Color.LIGHT_GRAY);
	public static final Color GRAY = new Color(java.awt.Color.GRAY);
	public static final Color DARK_GRAY = new Color(java.awt.Color.DARK_GRAY);
	public static final Color BLACK = new Color(java.awt.Color.BLACK);
	public static final Color RED = new Color(java.awt.Color.RED);
	public static final Color PINK = new Color(java.awt.Color.PINK);
	public static final Color ORANGE = new Color(java.awt.Color.ORANGE);
	public static final Color YELLOW = new Color(java.awt.Color.YELLOW);
	public static final Color GREEN = new Color(java.awt.Color.GREEN);
	public static final Color MAGENTA = new Color(java.awt.Color.MAGENTA);
	public static final Color CYAN = new Color(java.awt.Color.CYAN);
	public static final Color BLUE = new Color(java.awt.Color.BLUE);

	private @Getter @Setter int red;
	private @Getter @Setter int green;
	private @Getter @Setter int blue;

	public Color() {
		this(WHITE);
	}

	public Color(Color color) {
		this.set(color);
	}

	public Color(java.awt.Color color) {
		this(color.getRed(), color.getGreen(), color.getBlue());
	}

	public Color(javafx.scene.paint.Color color) {
		this((int) (color.getRed() * 255.0f), (int) (color.getGreen() * 255.0f), (int) (color.getBlue() * 255.0f));
	}

	public void set(Color color) {
		set(color.getRed(), color.getGreen(), color.getBlue());
	}

	public void set(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public Color clone() {
		return new Color(this);
	}
	
	@Override
	public String toString() {
		return red + " " + green + " " + blue;
	}
}