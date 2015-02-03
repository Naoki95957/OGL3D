package render;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManagement {

	private static final int width=1280,height=720,maxFps=120;
	
	public static void createDisplay(){
		ContextAttribs attrib = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();//new PixelFormat(), attrib
			Display.setTitle("Display");
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0,0,width,height);
		
	}
	public static void updateDisplay(){
		
		Display.sync(maxFps);
		Display.update();
		
	}
	public static void closeDisplay(){
		
		Display.destroy();
		
	}
}
