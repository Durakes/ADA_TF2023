package ada_final;

import java.util.ArrayList;
import java.util.List;

public class Algoritmo {
    public int diasDeLaSemana = 7;
    public int horasDelDia = 16;
    public boolean[][] calendarioBool = new boolean[diasDeLaSemana][horasDelDia];
    public String[][] calendarioFinal = new String[diasDeLaSemana][horasDelDia];
    List<Tarea> listaTareas = new ArrayList<Tarea>();

    /* constructor con demo
    public Algoritmo() {
        
        crearHorario();
        System.out.println("\n");
        System.out.println("\n");
        organizarTareas();
        imprimirCalendarioTareas(calendarioFinal);    
    }
    */
    
    // Nuevo método para agregar tarea desde la interfaz gráfica
    public void agregarTareaDesdeInterfaz(Tarea tarea) {
        listaTareas.add(tarea);
        //organizarCalendario(listaTareas, 0);
        //imprimirCalendarioTareas(calendarioFinal);
    }
    
    public void finishCalendario()
    {
        organizarCalendario(listaTareas, 0);
        imprimirCalendarioTareas(calendarioFinal);
    }

    public void crearHorario() {
        // Inicializar la matriz con valores de ejemplo
        inicializarCalendario(calendarioBool);
    }

    public void inicializarCalendario(boolean[][] calendario) {
        for (int dia = 0; dia < calendario.length; dia++) {
            for (int hora = 0; hora < calendario[dia].length; hora++) {
                calendario[dia][hora] = false;
            }
        }

        //ACA SE DEBE DE AGREGAR LAS TAREAS FIJAS DESDE FRONT
        //SE DEBE DE LLENAR AMBAS MATRICES, PARA QUE SIRVA LA COMPARACION

        /* Agregar algunos eventos de ejemplo
        calendario[0][1] = true; // Lunes, 8 am
        calendario[0][4] = true; // Lunes, 11 am
        calendario[2][7] = true; // Miércoles, 2 pm
        calendario[5][5] = true; // Sábado, 8 pm
        */
    }

    //Se puede borrar al acabar 
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
    //Se puede borrar al acabar 
    public void imprimirCalendarioTareas(String[][] calendario) {
        System.out.println("Calendario Semanal:");
        System.out.println("\t Lun \t Mar \t Mié \t Jue \t Vie \t Sáb \t Dom");

        int horaIni = 7;
        for (int hora = 0; hora < calendario[0].length; hora++) {
            System.out.printf(horaIni + "-" + (horaIni + 1) + "\t");
            for (int dia = 0; dia < calendario.length; dia++) {
                if (calendario[dia][hora] == null) {
                    System.out.printf("Sin Tareas \t");
                } else {
                    System.out.printf(calendario[dia][hora] + "\t");
                }
            }
            System.out.println();
            horaIni++;
        }
    }

    public void organizarTareas() {
        Tarea tarea1 = new Tarea("Tarea ADA", 2, 1, 3, 4);
        Tarea tarea2 = new Tarea("Tarea GSI", 1, 3, 1, 5);
        Tarea tarea3 = new Tarea("Tarea Compi", 3, 2, 3, 4);
        Tarea tarea4 = new Tarea("Tarea FEMP", 3, 2, 2, 4);

        listaTareas.add(tarea1);
        listaTareas.add(tarea2);
        listaTareas.add(tarea3);
        listaTareas.add(tarea4);

        quickSort(listaTareas, 0, listaTareas.size() - 1);

        organizarCalendario(listaTareas, 0);
    }

    public void organizarCalendario(List<Tarea> listaTareas, int id) {
        int k = id; // Índice para recorrer linealmente la matri
        for (int idx = 0; idx < listaTareas.size(); idx++) {
            Tarea tarea = listaTareas.get(idx);
            int duracion = tarea.Duracion;

            while (duracion > 0 && k < diasDeLaSemana * horasDelDia) {
                int i = k / horasDelDia; // Calcula la fila
                int j = k % horasDelDia; // Calcula la columna

                if (!calendarioBool[i][j]) {
                    calendarioBool[i][j] = true;
                    calendarioFinal[i][j] = tarea.Nombre;
                    duracion--;
                } else {
                    if (duracion != tarea.Duracion) {
                        float ponderacion = (float) duracion / (float) tarea.Duracion;
                        Tarea newTask = new Tarea(duracion, ponderacion * tarea.Prioridad);
                        newTask.Nombre = tarea.Nombre;

                        listaTareas.remove(tarea);

                        agregarTareaOrdenada(listaTareas, newTask);

                        break;
                    }
                }
                k++;
                if (duracion == 0) {
                    listaTareas.remove(tarea);
                }
            }
            organizarCalendario(listaTareas, k);
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

        listaTareas.add(index, nuevaTarea);
    }

    public int busquedaBinaria(List<Tarea> listaTareas, float prioridad, int low, int high) {
        if (high <= low) {
            return (prioridad < listaTareas.get(low).Prioridad) ? (low + 1) : low;
        }

        int mid = (low + high) / 2;

        if (prioridad == listaTareas.get(mid).Prioridad) {
            return mid + 1;
        }

        if (prioridad < listaTareas.get(mid).Prioridad) {
            return busquedaBinaria(listaTareas, prioridad, mid + 1, high);
        }

        return busquedaBinaria(listaTareas, prioridad, low, mid - 1);
    }

}
