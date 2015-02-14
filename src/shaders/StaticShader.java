package shaders;

import org.lwjgl.util.vector.Matrix4f;

import entities.Camera;
import tools.Maths;

public class StaticShader extends ShaderProgram {

	private static final String VERTEX_FILE = "src/shaders/vertexShader";
	private static final String FRAGMENT_FILE = "src/shaders/fragmentShader";

	private int locationOfTransformationMatrix;
	private int locationOfProjectionMatrix;
	private int locationOfViewMatrix;
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}
	protected void getAllUniformLocations() {
		locationOfTransformationMatrix = super.getUniformLocation("transformationMatrix");
		locationOfProjectionMatrix = super.getUniformLocation("projectionMatrix");
		locationOfViewMatrix = super.getUniformLocation("viewMatrix");
	}
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(locationOfTransformationMatrix, matrix);
	}

	public void loadProjectionMatrix(Matrix4f projection){
		super.loadMatrix(locationOfProjectionMatrix, projection);
	}
	
	public void loadViewMatrix(Camera camera){
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(locationOfViewMatrix, viewMatrix);
	}
}
