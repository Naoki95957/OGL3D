package engine;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import render.DisplayManagement;
import render.Loader;
import render.OBJLoader;
import render.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class Main {

	public static void main(String[] args){
		
		DisplayManagement.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		RawModel model = OBJLoader.loadOBJModel("stall", loader);
		
		/*float vertices[]={ //rectangle
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
		};*/
		
		/*float[] vertices = {//cube			
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				0.5f,0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f
				
		};
		
		float[] textureCoords = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0

				
		};
		
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};*/
		
//		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		Entity entity = new Entity(texturedModel, new Vector3f(0,0,-50),0,0,0,1);
		
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()){//one iteration per frame
//			entity.increasePosition(0, 0, -0.02f);//quickly change position
			entity.increaseRotation(0, 1, 0);//rotate the entity
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			DisplayManagement.updateDisplay();
			
		}
		shader.cleanUp();
		loader.cleanUp();
		DisplayManagement.closeDisplay();
	}
	
}
