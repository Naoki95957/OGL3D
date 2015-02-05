package engine;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;

import render.DisplayManagement;
import render.Loader;
import render.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

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
		float textureCoords []={
			0,0,	//V0
			0,1,	//V1
			1,1,	//V2
			1,0		//V3
		};
		
		RawModel model = loader.loadToVAO(verticies, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("weave"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		while(!Display.isCloseRequested()){
			renderer.prepare();
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			DisplayManagement.updateDisplay();
			
		}
		shader.cleanUp();
		loader.cleanUp();
		DisplayManagement.closeDisplay();
	}
	
}
