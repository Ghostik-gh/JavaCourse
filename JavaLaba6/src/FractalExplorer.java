import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.awt.event.*;

public class FractalExplorer {
    /** Поля для enableUI **/
    private JButton saveButton;
    private JButton resetButton;
    private JComboBox<FractalGenerator> selectFractal;
    // Показывает количество обрабатываемых строк
    private int rowsRemaining;

    private int displaySize;

    private JImageDisplay display;

    private FractalGenerator fractal;

    private Rectangle2D.Double range;

    /**
     * Конструктор принимает размер окна и инициализирует все поля класса
     */
    public FractalExplorer(int size) {
        displaySize = size;
        // Фрактал по умолчанию
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
    }

    /**
     * Этот метод инициализирует Swing GUI из полей находящихся в классе
     * Создает интерфейс
     */
    public void createAndShowGUI() {
        // Создаем окно с заголовком
        display.setLayout(new BorderLayout());
        JFrame frame = new JFrame("Fractal Explorer");
        frame.add(display, BorderLayout.CENTER);

        // Создаем кнопку Сброса
        resetButton = new JButton("Reset scale");
        resetButton.setActionCommand("Reset");
        // Обработчик для конпки сброса
        ButtonHandler resetHandler = new ButtonHandler();
        resetButton.addActionListener(resetHandler);

        // Обработчик для клика мыши по фракталу
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);
        // Окно закрывается только при нажатие крестика
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Выпадающее окно
        selectFractal = new JComboBox<FractalGenerator>();
        FractalGenerator mandelbrot = new Mandelbrot();
        selectFractal.addItem(mandelbrot);
        FractalGenerator tricorn = new Tricorn();
        selectFractal.addItem(tricorn);
        FractalGenerator burningship = new BurningShip();
        selectFractal.addItem(burningship);

        ButtonHandler fractalChooser = new ButtonHandler();
        selectFractal.addActionListener(fractalChooser);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Выберите фрактал");
        panel.add(label);
        panel.add(selectFractal);
        frame.add(panel, BorderLayout.NORTH);

        // Кнопка сохранения
        saveButton = new JButton("Save");

        // Обработчик сохранения
        ButtonHandler saveHandler = new ButtonHandler();
        saveButton.addActionListener(saveHandler);

        // Нижняя панель
        JPanel southPanel = new JPanel();
        southPanel.add(saveButton);
        southPanel.add(resetButton);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /*
     * Метод для отрисовки фрактала
     * Окрашивает пиксели в зависимотси от итерации
     */
    private void drawFractal() {

        enableUI(false);
        rowsRemaining = displaySize;

        for (int x = 0; x < displaySize; x++) {
            FractalWorker drawRow = new FractalWorker(x);
            drawRow.execute();
        }
    }

    private void enableUI(boolean val) {
        selectFractal.setEnabled(val);
        resetButton.setEnabled(val);
        saveButton.setEnabled(val);
    }

    private class FractalWorker extends SwingWorker<Object, Object> {
        int yCoordinate;
        int[] calcRGBValues;

        private FractalWorker(int row) {
            yCoordinate = row;
        }

        @Override
        protected Object doInBackground() throws Exception {

            calcRGBValues = new int[displaySize];

            for (int i = 0; i < calcRGBValues.length; i++) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x +
                        range.width, displaySize, i);
                double yCoord = FractalGenerator.getCoord(range.y, range.y +
                        range.height, displaySize, yCoordinate);
                int iteration = fractal.numIterations(xCoord, yCoord);

                if (iteration == -1) {
                    calcRGBValues[i] = 0;
                } else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    // display.drawPixel(x, y, rgbColor);
                    calcRGBValues[i] = rgbColor;
                }
            }
            return null;
        }

        protected void done() {
            for (int i = 0; i < calcRGBValues.length; i++) {
                display.drawPixel(i, yCoordinate, calcRGBValues[i]);
            }
            display.repaint(0, 0, yCoordinate, displaySize, 1);

            rowsRemaining--;
            if (rowsRemaining == 0) {
                enableUI(true);
            }
        }
    }

    private class ButtonHandler implements ActionListener {

        @Override // метод созданный по умолчанию
        public void actionPerformed(ActionEvent e) {
            // Возващает "Reset" для reset'a или "Save"
            String comand = e.getActionCommand();
            // Возвращает фрактал к изначальному положению
            if (e.getSource() instanceof JComboBox) {
                JComboBox<FractalGenerator> source = (JComboBox<FractalGenerator>) e.getSource();
                fractal = (FractalGenerator) source.getSelectedItem();
                fractal.getInitialRange(range);
                drawFractal();
                System.out.println("Choosen another fractal");
            } else if (comand.equals("Reset")) {
                fractal.getInitialRange(range);
                drawFractal();
                System.out.println("Reseted");
            } else if (comand.equals("Save")) {
                // создает окно для выбора места сохранения файла
                JFileChooser chooser = new JFileChooser();
                // сохраянет только png
                FileFilter filter = new FileNameExtensionFilter(
                        "PNG Images, *.png", "png");
                chooser.setFileFilter(filter);
                // и не показывает ничего кроме png и папок
                chooser.setAcceptAllFileFilterUsed(false);
                // отображает всплывающее окно / 0 если файл указан
                // 1 если пользователь передумал
                int userSelection = chooser.showSaveDialog(display);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    // Путь до файла
                    File file = chooser.getSelectedFile();
                    String file_name = file.toString() + ".png";
                    File fileWithExt = new File(file_name);
                    try {
                        BufferedImage renderedImage = display.getImage();
                        ImageIO.write(renderedImage, "png", fileWithExt);
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(
                                display,
                                err.getMessage(),
                                "Cannot Save Image",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    /*
     * Приближает фрактал в точку клика
     */
    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            // Не позволяет кликнуть если идет загрузка
            if (rowsRemaining != 0) {
                return;
            }
            // Координаты клика
            int x = e.getX();
            int y = e.getY();
            // Новые координаты центра
            double xCoord = FractalGenerator.getCoord(range.x,
                    range.x + range.width, displaySize, x);
            double yCoord = FractalGenerator.getCoord(range.y,
                    range.y + range.height, displaySize, y);

            // Устанавливаем центр в точку по которой был клик и приближаем
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            // Перерисовываем
            drawFractal();
        }
    }

    public static void main(String[] args) {
        FractalExplorer fractal = new FractalExplorer(600);
        fractal.createAndShowGUI();
        fractal.drawFractal();
    }
}
