package arc.graphics.g3d;

import arc.graphics.*;
import arc.graphics.gl.*;
import arc.math.geom.*;
import arc.struct.*;

public class VertexBatch3D{
    private final int maxVertices;
    private final Mesh mesh;
    private final int numTexCoords;
    private final int vertexSize;
    private final int normalOffset;
    private final int colorOffset;
    private final int texCoordOffset;
    private final Mat3D proj = new Mat3D();
    private final float[] vertices;
    private final String[] shaderUniformNames;

    private int vertexIdx;
    private int numSetTexCoords;
    private int numVertices;
    private Shader shader;
    private boolean ownsShader;

    public VertexBatch3D(boolean hasNormals, boolean hasColors, int numTexCoords){
        this(5000, hasNormals, hasColors, numTexCoords, createDefaultShader(hasNormals, hasColors, numTexCoords));
        ownsShader = true;
    }

    public VertexBatch3D(int maxVertices, boolean hasNormals, boolean hasColors, int numTexCoords){
        this(maxVertices, hasNormals, hasColors, numTexCoords, createDefaultShader(hasNormals, hasColors, numTexCoords));
        ownsShader = true;
    }

    public VertexBatch3D(int maxVertices, boolean hasNormals, boolean hasColors, int numTexCoords, Shader shader){
        this.maxVertices = maxVertices;
        this.numTexCoords = numTexCoords;
        this.shader = shader;

        VertexAttribute[] attribs = buildVertexAttributes(hasNormals, hasColors, numTexCoords);
        mesh = new Mesh(false, maxVertices, 0, attribs);

        vertices = new float[maxVertices * (mesh.vertexSize / 4)];
        vertexSize = mesh.vertexSize / 4;

        int offset = 3;

        if(hasNormals){
            normalOffset = offset;
            offset += 2;
        }else{
            normalOffset = 0;
        }

        if(hasColors){
            colorOffset = offset;
            offset += 1;
        }else{
            colorOffset = 0;
        }

        texCoordOffset = offset;

        shaderUniformNames = new String[numTexCoords];
        for(int i = 0; i < numTexCoords; i++){
            shaderUniformNames[i] = "u_sampler" + i;
        }
    }

    private static String createVertexShader(boolean hasNormals, boolean hasColors, int numTexCoords){
        StringBuilder shader = new StringBuilder("attribute vec4 " + Shader.positionAttribute + ";\n"
        + (hasNormals ? "attribute vec3 " + Shader.normalAttribute + ";\n" : "")
        + (hasColors ? "attribute vec4 " + Shader.colorAttribute + ";\n" : ""));

        for(int i = 0; i < numTexCoords; i++){
            shader.append("attribute vec2 " + Shader.texcoordAttribute).append(i).append(";\n");
        }

        shader.append("uniform mat4 u_proj;\n");
        shader.append(hasColors ? "varying vec4 v_col;\n" : "");

        for(int i = 0; i < numTexCoords; i++){
            shader.append("varying vec2 v_tex").append(i).append(";\n");
        }

        shader.append("void main() {\n" + "   gl_Position = u_proj * " + Shader.positionAttribute + ";\n").append(hasColors ? "   v_col = " + Shader.colorAttribute + ";\n" : "");

        for(int i = 0; i < numTexCoords; i++){
            shader.append("   v_tex").append(i).append(" = ").append(Shader.texcoordAttribute).append(i).append(";\n");
        }
        shader.append("   gl_PointSize = 1.0;\n");
        shader.append("}\n");
        return shader.toString();
    }

    private static String createFragmentShader(boolean hasNormals, boolean hasColors, int numTexCoords){
        StringBuilder shader = new StringBuilder();

        if(hasColors) shader.append("varying vec4 v_col;\n");
        for(int i = 0; i < numTexCoords; i++){
            shader.append("varying vec2 v_tex").append(i).append(";\n");
            shader.append("uniform sampler2D u_sampler").append(i).append(";\n");
        }

        shader.append("void main(){\n   gl_FragColor = ").append(hasColors ? "v_col" : "vec4(1, 1, 1, 1)");

        if(numTexCoords > 0) shader.append(" * ");

        for(int i = 0; i < numTexCoords; i++){
            if(i == numTexCoords - 1){
                shader.append(" texture2D(u_sampler").append(i).append(",  v_tex").append(i).append(")");
            }else{
                shader.append(" texture2D(u_sampler").append(i).append(",  v_tex").append(i).append(") *");
            }
        }

        shader.append(";\n}");
        return shader.toString();
    }

    /** Returns a new instance of the default shader used by SpriteBatch for GL2 when no shader is specified. */
    public static Shader createDefaultShader(boolean hasNormals, boolean hasColors, int numTexCoords){
        String vertexShader = createVertexShader(hasNormals, hasColors, numTexCoords);
        String fragmentShader = createFragmentShader(hasNormals, hasColors, numTexCoords);
        return new Shader(vertexShader, fragmentShader);
    }

    private VertexAttribute[] buildVertexAttributes(boolean hasNormals, boolean hasColor, int numTexCoords){
        Seq<VertexAttribute> attribs = new Seq<>();
        attribs.add(VertexAttribute.position3);
        if(hasNormals) attribs.add(VertexAttribute.normal);
        if(hasColor) attribs.add(VertexAttribute.color);
        for(int i = 0; i < numTexCoords; i++){
            attribs.add(new VertexAttribute(2, Shader.texcoordAttribute + i));
        }
        return attribs.toArray(VertexAttribute.class);
    }

    public void setShader(Shader shader){
        if(ownsShader) this.shader.dispose();
        this.shader = shader;
        ownsShader = false;
    }

    public void color(Color color){
        vertices[vertexIdx + colorOffset] = color.toFloatBits();
    }

    public void color(float r, float g, float b, float a){
        vertices[vertexIdx + colorOffset] = Color.toFloatBits(r, g, b, a);
    }

    public void color(float colorBits){
        vertices[vertexIdx + colorOffset] = colorBits;
    }

    public void texCoord(float u, float v){
        final int idx = vertexIdx + texCoordOffset;
        vertices[idx + numSetTexCoords] = u;
        vertices[idx + numSetTexCoords + 1] = v;
        numSetTexCoords += 2;
    }

    public void normal(Vec3 v){
        normal(v.x, v.y, v.z);
    }

    public void normal(float x, float y, float z){
        final int idx = vertexIdx + normalOffset;
        vertices[idx] = x;
        vertices[idx + 1] = y;
        vertices[idx + 2] = z;
    }

    public void tri2(Vec3 v1, Vec3 v2, Vec3 v3, Color color){
        tri(v1, v2, v3, color);
        tri(v1, v3, v2, color);
    }

    public void tri(Vec3 v1, Vec3 v2, Vec3 v3, Color color){
        tri(v1.x, v1.y, v1.z, v2.x, v2.y, v2.z, v3.x, v3.y, v3.z, color);
    }

    public void tri(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, Color color){
        color(color);
        vertex(x1, y1, z1);
        color(color);
        vertex(x2, y2, z2);
        color(color);
        vertex(x3, y3, z3);
    }

    public void vertex(Vec3 v, Color color){
        color(color);
        vertex(v.x, v.y, v.z);
    }

    public void vertex(Vec3 v){
        vertex(v.x, v.y, v.z);
    }

    public void vertex(float x, float y, float z){
        final int idx = vertexIdx;
        vertices[idx] = x;
        vertices[idx + 1] = y;
        vertices[idx + 2] = z;

        numSetTexCoords = 0;
        vertexIdx += vertexSize;
        numVertices++;
    }

    public void vertex(float[] floats){
        System.arraycopy(floats, 0, vertices, vertexIdx, vertexSize);
        vertexIdx += vertexSize;
        numVertices ++;
    }

    public Mat3D proj(){
        return proj;
    }

    public void proj(Mat3D projModelView){
        this.proj.set(projModelView);
    }

    public void flush(int primitiveType){
        flush(primitiveType, this.shader);
    }

    public void flush(int primitiveType, Shader shader){
        if(numVertices == 0) return;
        shader.bind();
        shader.apply();
        shader.setUniformMatrix4("u_proj", proj.val);
        for(int i = 0; i < numTexCoords; i++)
            shader.setUniformi(shaderUniformNames[i], i);
        mesh.setVertices(vertices, 0, vertexIdx);
        mesh.render(shader, primitiveType);

        numSetTexCoords = 0;
        vertexIdx = 0;
        numVertices = 0;
    }

    public int getNumVertices(){
        return numVertices;
    }

    public int getMaxVertices(){
        return maxVertices;
    }

    public void dispose(){
        if(ownsShader && shader != null) shader.dispose();
        mesh.dispose();
    }
}
