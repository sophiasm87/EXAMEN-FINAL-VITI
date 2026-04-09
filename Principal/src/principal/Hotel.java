/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;
import javax.swing.JOptionPane;

/**
 *
 * @author sophi
 */
import javax.swing.JOptionPane;

public class Hotel {
// aca va la cantidad de pisos y habitaciones 

    int PISOS = 5;
    int HABITACIONES = 5;
// aca creé la Matriz bidimensional 
    Habitacion[][] matriz = new Habitacion[PISOS][HABITACIONES];

    public void precargar() {
// aca estan los numeros de habitacion         
        int[][] numeros = {
            {101, 102, 103, 104, 105},
            {201, 202, 203, 204, 205},
            {301, 302, 303, 304, 305},
            {401, 402, 403, 404, 405},
            {501, 502, 503, 504, 505}
        };
//aca estan los tipos de habitacion
        String[][] tipos = {
            {"Simple", "Doble", "Simple", "Doble", "Simple"},
            {"Simple", "Simple", "Simple", "Doble", "Simple"},
            {"Simple", "Doble", "Doble", "Doble", "Simple"},
            {"Simple", "Doble", "Simple", "Doble", "Simple"},
            {"Simple", "Doble", "Simple", "Doble", "Simple"}
        };
// y por aca los precios por noche 
        double[][] precios = {
            {100, 100, 100, 100, 50},
            {200, 200, 200, 200, 150},
            {10, 20, 30, 40, 50},
            {110, 120, 130, 140, 150},
            {210, 220, 230, 240, 250}
        };
// aca estan los estados iniciales de cada habitacion jiji
        String[][] estados = {
            {"Libre", "Libre", "Libre", "Libre", "Sucia"},
            {"Libre", "Libre", "Libre", "Libre", "Sucia"},
            {"Sucia", "Libre", "Libre", "Libre", "Sucia"},
            {"Ocupada", "Libre", "Libre", "Libre", "Sucia"},
            {"Libre", "Libre", "Libre", "Libre", "Sucia"}
        };

        for (int p = 0; p < PISOS; p++) {
            for (int h = 0; h < HABITACIONES; h++) {
                matriz[p][h] = new Habitacion(numeros[p][h], tipos[p][h], precios[p][h], estados[p][h]);
            }
        }
    }

    public void verHabitaciones() {
        String resultado = "";

        for (int p = PISOS - 1; p >= 0; p--) {
            resultado += "Piso " + (p + 1) + ":\n";
            for (int h = 0; h < HABITACIONES; h++) {
                resultado += "  Hab " + matriz[p][h].numero
                        + " | " + matriz[p][h].tipo
                        + " | " + matriz[p][h].estado
                        + " | $" + matriz[p][h].precio + "\n";
            }
            resultado += "-------------------------\n";
        }

        JOptionPane.showMessageDialog(null, resultado);
    }

    public void modificar() {
        //aca le pido al usuario el numero de habitacion que quiere modificar 

        String input = JOptionPane.showInputDialog("Ingrese el numero de habitacion a modificar:");
        int numBuscado = Integer.parseInt(input);

// aca esta la variable para guardar la posicion de la habitacion 
        int pisoEnc = -1, habEnc = -1;
        for (int p = 0; p < PISOS; p++) {
            for (int h = 0; h < HABITACIONES; h++) {
                if (matriz[p][h].numero == numBuscado) {
                    pisoEnc = p;
                    habEnc = h;
                }
            }
        }

        if (pisoEnc == -1) {
            JOptionPane.showMessageDialog(null, "Habitacion no encontrada.");
            return;
        }

        String opcion = JOptionPane.showInputDialog(
                "Habitacion " + numBuscado + " encontrada\n"
                + "Tipo: " + matriz[pisoEnc][habEnc].tipo + "\n"
                + "Estado: " + matriz[pisoEnc][habEnc].estado + "\n"
                + "Precio: $" + matriz[pisoEnc][habEnc].precio + "\n\n"
                + "Que desea modificar?\n"
                + "1. Estado\n"
                + "2. Tipo\n"
                + "3. Precio"
        );

        if (opcion.equals("1")) {
            // aca se modifica el estado de la habitacion 
            String nuevoEstado = JOptionPane.showInputDialog(
                    "Seleccione nuevo estado:\n"
                    + "1. Libre\n"
                    + "2. Ocupada\n"
                    + "3. Sucia"
            );
            if (nuevoEstado.equals("1")) {
                matriz[pisoEnc][habEnc].estado = "Libre";
            } else if (nuevoEstado.equals("2")) {
                matriz[pisoEnc][habEnc].estado = "Ocupada";
            } else if (nuevoEstado.equals("3")) {
                matriz[pisoEnc][habEnc].estado = "Sucia";
            }
            JOptionPane.showMessageDialog(null, "Estado actualizado correctamente.");

        } else if (opcion.equals("2")) {
            // aca se puede modificar el tipo de habitacion
            String nuevoTipo = JOptionPane.showInputDialog(
                    "Seleccione nuevo tipo:\n"
                    + "1. Simple\n"
                    + "2. Doble"
            );
            if (nuevoTipo.equals("1")) {
                matriz[pisoEnc][habEnc].tipo = "Simple";
            } else if (nuevoTipo.equals("2")) {
                matriz[pisoEnc][habEnc].tipo = "Doble";
            }
            JOptionPane.showMessageDialog(null, "Tipo actualizado correctamente.");

        } else if (opcion.equals("3")) {
            String nuevoPrecio = JOptionPane.showInputDialog("Ingrese el nuevo precio:");
            matriz[pisoEnc][habEnc].precio = Double.parseDouble(nuevoPrecio);
            JOptionPane.showMessageDialog(null, "Precio actualizado correctamente.");
        }
    }
// Aca iria el resumen de las habitaciones, si esta libre, ocupada o sucia 

    public void resumen() {
        int total = PISOS * HABITACIONES;
        int libres = 0, ocupadas = 0, sucias = 0;
        double ganancia = 0;

        for (int p = 0; p < PISOS; p++) {
            for (int h = 0; h < HABITACIONES; h++) {
                if (matriz[p][h].estado.equals("Libre")) {
                    libres++;
                } else if (matriz[p][h].estado.equals("Ocupada")) {
                    ocupadas++;
                    ganancia += matriz[p][h].precio;
                } else if (matriz[p][h].estado.equals("Sucia")) {
                    sucias++;
                }
            }
        }
// aca se hace el resumen total 
        JOptionPane.showMessageDialog(null,
                "--- RESUMEN DEL HOTEL ---\n"
                + "Total habitaciones : " + total + "\n"
                + "Libres   : " + libres + " (" + (libres * 100 / total) + "%)\n"
                + "Ocupadas : " + ocupadas + " (" + (ocupadas * 100 / total) + "%)\n"
                + "Sucias   : " + sucias + " (" + (sucias * 100 / total) + "%)\n"
                + "Ganancia actual: $" + ganancia
        );
    }
}
