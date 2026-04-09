/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package principal;

import javax.swing.JOptionPane;

/**
 *
 * @author sophi
 */

/**
 * @param args the command line arguments
 */
public class Principal {

    public static void main(String[] args) {
        // aca cree el objeto hotel 
        Hotel hotel = new Hotel();
        hotel.precargar();

        String opcion = "";
        // aca el menu se repite hasta que se quiera salir del sistema con 0 
        while (!opcion.equals("0")) {
            opcion = JOptionPane.showInputDialog(
                    "--- MENU ---\n"
                    + "1. Ver habitaciones\n"
                    + "2. Modificar habitacion\n"
                    + "3. Resumen del hotel\n"
                    + "0. Salir"
            );

            if (opcion.equals("1")) {
                hotel.verHabitaciones();
            } else if (opcion.equals("2")) {
                hotel.modificar();
            } else if (opcion.equals("3")) {
                hotel.resumen();
            }
        }
    }
}
