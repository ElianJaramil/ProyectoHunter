package vista;

import controlador.ConeccionGPT;
import controlador.MovimientoIcono;

public class aplicacion {
    public static void main(String[] args){
       // Se crea el panel en la pantalla y se visualiza al presionar en el icono
//        imagen Buscador = new imagen();
//        Buscador.setVisible(true);
//        Buscador.setLocationRelativeTo(null);
        Icono icono = new Icono(); 
//        icono.setVisible(true);
//        icono.setLocationRelativeTo(null);
//        controlador.MovimientoIcono.moverVentanaAleatoriamente(Icono);
        MovimientoIcono movimientoIcono = new MovimientoIcono();
        movimientoIcono.startMovimiento();

        // Reemplaza "TU_CLAVE_DE_API" con tu clave de API real
        String apiKey = "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";

        // Crea una instancia del cliente de ChatGPT
        ConeccionGPT chatGPTClient = new ConeccionGPT(apiKey);
        
        // Ejemplo de uso
        String userMessage ="";
        String response = chatGPTClient.obtenerRespuestaChatGPT(userMessage);

        // Muestra la respuesta de la API
        System.out.println("Respuesta de la API:\n" + response);
    }
}
