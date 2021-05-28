package turtle;

import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import util.Time;

import java.util.concurrent.Callable;

public class Window {
    private int width, height;
    private String title;
    private long glfwWindow;

    private static Window window = null;
    private static final long NULL = 0L;

    private static Scene currentScene = null;

    public float r, g, b, a;
    private boolean anim = true;

    private Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
//        r = 0.7f;
//        g = 1.0f;
//        b = 0.8f;
//        a = 1.0f;

        r = 1f;
        g = 1f;
        b = 1f;
        a = 1f;
    }

    public static void changeScene(int newScene) {
        switch (newScene) {
            case 0:
                currentScene = new LevelEditorScene();
                currentScene.init();
                break;
            case 1:
                currentScene = new LevelScene();
                currentScene.init();
                break;
            case 2:
                currentScene = new LogoScene();
                currentScene.init();
                break;
            default:
                assert false : "Unknown scene '" + newScene + "'";
                break;
        }
    }

    public static Window get(int width, int height, String title) {
        if (Window.window == null) {
            Window.window = new Window(width, height, title);
        }
        return Window.window;
    }
    public static Window get() {
        if (Window.window == null) {
            Window.window = new Window(1920, 1080, "Turtle Engine");
        }
        return Window.window;
    }
    
    public void run() {
        init();
//        System.out.println("LWJGL= " + Version.getVersion());
//        System.out.println("GLFW= " + glfwGetVersionString());
//        System.out.println("OPENGL= " + glGetString(GL_VERSION));
        loop();

        // free memory when done
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // terminate
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() {
        // error callback
        GLFWErrorCallback.createPrint(System.err).set();
        // init glfw
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // configure glfw
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, 0);
        glfwWindowHint(GLFW_RESIZABLE, 1);
        glfwWindowHint(GLFW_MAXIMIZED, 1);

        // create window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create GLFW window!");
        }

        // callback stuffs
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);

        // make the opengl context current
        glfwMakeContextCurrent(glfwWindow);
        // enable vsync
        glfwSwapInterval(1);

        // make thw window visible
        glfwShowWindow(glfwWindow);

        // do some stuff to make it work
        GL.createCapabilities();

        Window.changeScene(0);
    }

    private void line(double x1, double y1, double x2, double y2) {
        glBegin( GL_LINES );
        glVertex2d(x1, y1);
        glVertex2d(x2, y2);
        glEnd();
    }

    public void loop() {
        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;

        while (!glfwWindowShouldClose(glfwWindow)) {
            // poll events
            glfwPollEvents();
            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT);

            if (dt >= 0)
                currentScene.update(dt);


            // swap the buffers
            glfwSwapBuffers(glfwWindow);

            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }

}
