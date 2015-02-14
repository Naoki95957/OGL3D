package render;

import models.RawModel;
import entities.Entity;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import shaders.StaticShader;
import tools.Maths;

public class Renderer {

	private static final float fov=70;
	private static final float nearPlane=0.1f;
	private static final float farPlane=1000;
	
	private Matrix4f projectionMatrix;
	
	public Renderer(StaticShader shader){
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void prepare(){
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0,1,1,1);
	}
	public void render(Entity entity, StaticShader shader){
		TexturedModel model = entity.getModel();
		RawModel rawmodel = model.getRawModel();
		GL30.glBindVertexArray(rawmodel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), 
				entity.getRotx(), entity.getRoty(), entity.getRotz(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, rawmodel.getVertexCount(), GL11.GL_UNSIGNED_INT,0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
	
	private void createProjectionMatrix(){
		float aspectRatio = Display.getWidth()/Display.getHeight();
		float yScale=(float) (1/Math.tan(Math.toRadians(fov/2f))*aspectRatio);
		float xScale=yScale/aspectRatio;
		float frustumLength=farPlane-nearPlane;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00=xScale;
		projectionMatrix.m11=yScale;
		projectionMatrix.m22=-((farPlane+nearPlane)/frustumLength);
		projectionMatrix.m23=-1;
		projectionMatrix.m32=-((2*nearPlane*farPlane)/frustumLength);
		projectionMatrix.m33=0;
	}
}
