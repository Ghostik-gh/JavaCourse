import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;

public class FractalExplorer {
    private int displaySize;

    private JImageDisplay display;

    private FractalGenerator fractal;

    private Rectangle2D.Double rectangle;

    /**
     * Конструктор сохраняет размер окна и инициализирует все поля
     */
    public FractalExplorer(int size) {
        displaySize = size;

        fractal = new Mandelbrot();
        rectangle = new Rectangle2D.Double();
        fractal.getInitialRange(rectangle);
        display = new JImageDisplay(displaySize, displaySize);
    }

    /**
     * Этот метод инициализирует Swing GUI из полей находящихся в классе
     */
    public void createAndShowGUI() {
        // BorderLayout from java.awt
        display.setLayout(new BorderLayout());

        // Создаем окно с заголовком
        JFrame frame = new JFrame("Fractal Explorer: Mandelbrot");

        frame.add(display, BorderLayout.CENTER);

        // Create new button
        JButton resetButton = new JButton("Reset scale");

        // TODO: Handler for click
        // ResetHandler handler = new ResetHandler();
        // resetButton.addActionListener(handler);
        // frame.add(BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }

    // ActionListener;

    private void drawFractal() {
        for (int x = 0; x < displaySize; x++) {
            for (int y = 0; y < displaySize; y++) {

                double xCoord = FractalGenerator.getCoord(rectangle.x, rectangle.x + rectangle.width, displaySize, x);
                double yCoord = FractalGenerator.getCoord(rectangle.y, rectangle.y + rectangle.height, displaySize, y);

                int iteration = fractal.numIterations(xCoord, yCoord);

                if (iteration == -1) {
                    display.drawPixel(x, y, 0);
                } else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);

                    display.drawPixel(x, y, rgbColor);
                }
            }
        }

        display.repaint();
    }

    public static void main(String[] args) {
        FractalExplorer fractal = new FractalExplorer(800);
        fractal.createAndShowGUI();
        fractal.drawFractal();
    }
}
