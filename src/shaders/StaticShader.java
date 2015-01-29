package shaders;

public class StaticShader extends ShaderProgram {

	private static final String vertexFile = "src/shaders/vertexShader";
	private static final String fragmentFile = "src/shaders/fragmentShader";
	
	public StaticShader() {
		super(vertexFile, fragmentFile);
	}
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
}
