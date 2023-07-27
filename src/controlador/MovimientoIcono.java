package controlador;

import vista.Icono;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class MovimientoIcono {
    public Icono icono;
    private int x;
    private int y;
    private int deltaX;
    private int deltaY;
    private final int delay;
    private final Timer timer;
    private boolean changeDirectionX = false;
    private boolean changeDirectionY = false;

    public MovimientoIcono() {
        icono = new Icono();
        icono.setVisible(true);
        icono.setLocation(10, 100);

        // Establecer la velocidad de movimiento (ajusta el valor según lo desees)
        deltaX = 3;
        deltaY = 1;

        // Establecer el tiempo de retraso entre cada movimiento (en milisegundos)
        delay = 15;

        // Obtener las dimensiones de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Calcular el tamaño del icono y ajustar las coordenadas iniciales dentro de los límites
        int iconWidth = icono.getWidth();
        int iconHeight = icono.getHeight();
        x = Math.max(0, Math.min(x, screenWidth - iconWidth));
        y = Math.max(0, Math.min(y, screenHeight - iconHeight));

        // Crear un temporizador para el movimiento suave
        timer = new Timer(delay, (ActionEvent e) -> {
            moverVentanaSuavemente();
        });
    }

    public void startMovimiento() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    public void stopMovimiento() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    private void moverVentanaSuavemente() {
        int screenWidth = icono.getToolkit().getScreenSize().width;
        int screenHeight = icono.getToolkit().getScreenSize().height;

        // Verificar si el icono alcanzó los límites de la pantalla para invertir la dirección
        if (x <= 0 || x >= screenWidth - icono.getWidth()) {
            // Cambiar dirección en el eje X
            deltaX = -deltaX;
            changeDirectionX = true;
        }

        if (y <= 0 || y >= screenHeight - icono.getHeight()) {
            // Cambiar dirección en el eje Y
            deltaY = -deltaY;
            changeDirectionY = true;
        }

        // Si alguna de las coordenadas cambió de dirección, entonces hacer un giro de 90 grados
        if (changeDirectionX && changeDirectionY) {
            int temp = deltaX;
            deltaX = deltaY;
            deltaY = temp;

            changeDirectionX = false;
            changeDirectionY = false;
        }

        // Calcular nuevas coordenadas para el icono
        x += deltaX;
        y += deltaY;

        // Ajustar las nuevas coordenadas dentro de los límites de la pantalla
        x = Math.max(0, Math.min(x, screenWidth - icono.getWidth()));
        y = Math.max(0, Math.min(y, screenHeight - icono.getHeight()));

        // Establecer las nuevas coordenadas
        icono.setLocation(x, y);
    }
}


