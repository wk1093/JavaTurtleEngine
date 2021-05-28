package turtle;

import java.awt.event.KeyEvent;

import static org.lwjgl.opengl.GL11.*;

public class LogoScene extends Scene {
    private boolean changingScene = false;
    private float timeToChangeScenes = 1.5f;
    private float timeToDone = 3.0f;

    private void line(double x1, double y1, double x2, double y2) {
        glBegin( GL_LINES );
        glVertex2d(x1, y1);
        glVertex2d(x2, y2);
        glEnd();
    }

    public LogoScene() {

    }

    @Override
    public void update(float dt) {
        if (timeToDone > 0)
            timeToDone -= dt;
        else
            changingScene = true;


        if (changingScene && timeToChangeScenes > 0)
        {
            timeToChangeScenes -= dt;
            if (Window.get().r > 0.0f) {
                Window.get().r -= 0.007f;
            }
            if (Window.get().g > 0.0f) {
                Window.get().g -= 0.01f;
            }
            if (Window.get().b > 0.0f) {
                Window.get().b -= 0.008f;
            }
        } else if (changingScene)
        {
            System.out.println("test");
            Window.changeScene(0);
        }

        drawTurtleEngine();
    }

    private void drawTurtleEngine() {
        glLineWidth(10);
        glColor3f(0f, 0f, 0f);
        glEnable(GL_COLOR_MATERIAL);
        line(-0.5, 0.2, -0.5, -0.2);
        line(-0.6, 0.2, -0.4, 0.2);

        line(-0.45, 0.12, -0.45, -0.2);
        line(-0.455, -0.2, -0.375, -0.2);
        line(-0.38, 0.09, -0.38, -0.2);

        line(-0.35, 0.1, -0.35, -0.21);
        line(-0.35, 0.07, -0.28, 0.07);

        line(-0.25, 0.1, -0.25, -0.21);
        line(-0.22, 0.02, -0.30, 0.02);

        line(-0.2, 0.2, -0.2, -0.21);
        line(-0.08, -0.20, -0.205, -0.20);

        line(-0.10, -0.15, -0.18, -0.15);
        line(-0.12, -0.08, -0.18, -0.08);
        line(-0.10, -0.01, -0.18, -0.01);
        line(-0.18, -0.16, -0.18, -0.00);


        line(0.0, 0.2, 0.0, -0.2);
        line(-0.005, -0.2, 0.1, -0.2);
        line(-0.005, 0, 0.07, 0);
        line(-0.005, 0.2, 0.1, 0.2);

        line(0.125, -0.21, 0.125, 0);
        line(0.125, -0.02, 0.18, -0.02);
        line(0.18, -0.21, 0.18, -0.01);

        line(0.21, -0.21, 0.21, 0);
        line(0.28, -0.4, 0.28, 0);
        line(0.205, -0.21, 0.286, -0.21);
        line(0.206, 0, 0.286, 0);
        line(0.205, -0.4, 0.286, -0.4);

        line(0.3, -0.21, 0.3, 0);
        line(0.3, 0.17, 0.3, 0.14);

        line(0.32, -0.21, 0.32, 0);
        line(0.32, -0.02, 0.366, -0.02);
        line(0.366, -0.21, 0.366, -0.01);

        line(0.39, -0.21, 0.39, 0);
        line(0.385, -0.21, 0.44, -0.21);
        line(0.385, -0.11, 0.43, -0.11);
        line(0.385, 0, 0.44, 0);
        glDisable(GL_COLOR_MATERIAL);
    }
}
