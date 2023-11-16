package ada_final;

import java.util.ArrayList;
import java.util.List;

public class Algoritmo {
    public int diasDeLaSemana = 7;
    public int horasDelDia = 16;
    public boolean[][] calendario = new boolean[diasDeLaSemana][horasDelDia];
    List<Tarea> listaTareas = new ArrayList<Tarea>();

    public Algoritmo() {
        crearHorario();
        System.out.println("\n");
        System.out.println("\n");
        //organizarTareas();
        imprimirCalendario(calendario);
    }

    public void crearHorario() {
        // Inicializar la matriz con valores de ejemplo
        inicializarCalendario(calendario);

        // Imprimir el calendario
        imprimirCalendario(calendario);
    }

    public void inicializarCalendario(boolean[][] calendario) {
        for (int dia = 0; dia < calendario.length; dia++) {
            for (int hora = 0; hora < calendario[dia].length; hora++) {
                calendario[dia][hora] = false;
            }
        }

        // Agregar algunos eventos de ejemplo
        calendario[0][2] = true; // Lunes, 8 am
        calendario[2][7] = true; // Miércoles, 2 pm
        calendario[5][5] = true; // Sábado, 8 pm
    }

    public void imprimirCalendario(boolean[][] calendario) {
        System.out.println("Calendario Semanal:");
        System.out.println("\t Lun \t Mar \t Mié \t Jue \t Vie \t Sáb \t Dom");

        int horaIni = 7;
        for (int hora = 0; hora < calendario[0].length; hora++) {
            System.out.printf(horaIni + "-" + (horaIni + 1) + "\t");
            for (int dia = 0; dia < calendario.length; dia++) {
                System.out.printf(calendario[dia][hora] ? "1 \t" : "0 \t");
            }
            System.out.println();
            horaIni++;
        }
    }

    public void organizarTareas() {
        Tarea tarea1 = new Tarea("Tarea ADA", 2, 1, 3, 4);
        Tarea tarea2 = new Tarea("Tarea GSI", 1, 3, 1, 5);
        Tarea tarea3 = new Tarea("Tarea Compi", 3, 2, 3, 2);
        // Ejemplo de uso
        listaTareas.add(tarea1);
        listaTareas.add(tarea2);
        listaTareas.add(tarea3);
        // Ordenar la lista de tareas por prioridad de mayor a menor
        quickSort(listaTareas, 0, listaTareas.size() - 1);

        for (Tarea tarea : listaTareas) {
            for (int i = 0; i < diasDeLaSemana; i++) {
                for (int j = 0; j < horasDelDia; j++) {
                    try{
                    if (!calendario[i][j]) {
                        calendario[i][j] = true;
                        tarea.Duracion--;
                        if (tarea.Duracion == 0) {
                            break;
                        }
                    }}catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Error en día: " + i + ", hora: " + j);
                        e.printStackTrace();
                    }

                }
                if (tarea.Duracion == 0) 
                {
                    break;
                }
            }
        }

        // Imprimir la lista ordenada
        for (Tarea tarea : listaTareas) {
            System.out.println(tarea.Nombre + " - Prioridad: " + tarea.Prioridad);
        }
    }

    public void quickSort(List<Tarea> tareas, int low, int high) {
        if (low < high) {
            int pi = partition(tareas, low, high);

            quickSort(tareas, low, pi - 1);
            quickSort(tareas, pi + 1, high);
        }
    }

    private int partition(List<Tarea> tareas, int low, int high) {
        double pivot = tareas.get(high).Prioridad;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (tareas.get(j).Prioridad >= pivot) {
                i++;

                Tarea temp = tareas.get(i);
                tareas.set(i, tareas.get(j));
                tareas.set(j, temp);
            }
        }

        Tarea temp = tareas.get(i + 1);
        tareas.set(i + 1, tareas.get(high));
        tareas.set(high, temp);

        return i + 1;
    }

    public void agregarTareaOrdenada(List<Tarea> listaTareas, Tarea nuevaTarea) {
        int index = busquedaBinaria(listaTareas, nuevaTarea.Prioridad, 0, listaTareas.size() - 1);

        // Insertar la nueva tarea en la posición adecuada
        listaTareas.add(index, nuevaTarea);
    }

    public int busquedaBinaria(List<Tarea> listaTareas, double prioridad, int low, int high) {
        if (high <= low) {
            return (prioridad > listaTareas.get(low).Prioridad) ? (low + 1) : low;
        }

        int mid = (low + high) / 2;

        if (prioridad == listaTareas.get(mid).Prioridad) {
            return mid + 1;
        }

        if (prioridad > listaTareas.get(mid).Prioridad) {
            return busquedaBinaria(listaTareas, prioridad, mid + 1, high);
        }

        return busquedaBinaria(listaTareas, prioridad, low, mid - 1);
    }

}
