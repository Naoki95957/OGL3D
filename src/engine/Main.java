package engine;

import org.lwjgl.opengl.Display;

import render.DisplayManagement;
import render.Loader;
import render.RawModel;
import render.Renderer;
import shaders.StaticShader;

public class Main {

	public static void main(String[] args){
		
		DisplayManagement.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		float verticies[]={ //rectangle
				-0.5f,0.5f,0f,	//v0
				-0.5f,-0.5f,0f,	//v1
				0.5f,-0.5f,0f,	//v2
				0.5f,0.5f,0f,	//v3
				//x,y,z
		};
		int indices[]={
			0,1,3,	//Top left triangle (v0,v1,v3)
			3,1,2	//bottom right triangle (v3,v1,v2)
		};
		
		RawModel model = loader.loadToVAO(verticies, indices);
		
		while(!Display.isCloseRequested()){
			renderer.prepare();
			shader.start();
			renderer.render(model);
			shader.stop();
			DisplayManagement.updateDisplay();
			
		}
		shader.cleanUp();
		loader.cleanUp();
		DisplayManagement.closeDisplay();
	}
	
}
