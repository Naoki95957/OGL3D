package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f position = new Vector3f(0,0,0);
	private float pitch,yaw,roll;
	private float camSpeed=0.02f;
	
	public Camera(){
		
	}
	
	public void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z-=camSpeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x+=camSpeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x-=camSpeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z+=camSpeed;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
}
