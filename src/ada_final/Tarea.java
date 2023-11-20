package ada_final;

public class Tarea {
    public String Nombre;
    public int Duracion;
    public float Prioridad;

    public Tarea() {
    }

    public Tarea(String nombre, int importancia, int dificultad, int urgencia, int duracion) {
        this.Nombre = nombre;
        this.Duracion = duracion;

        this.Prioridad = (dificultad * 0.3f + importancia * 0.4f + urgencia * 0.3f);
    }

    public Tarea(int duracion, float prioridad) {
        this.Duracion = duracion;
        this.Prioridad = prioridad;
    }
}
