package ada_final;

public class Tarea {
    public String Nombre;
    public double Importancia;
    public double Dificultad;
    public double Urgencia;
    public int Duracion;
    public double Prioridad;

    public Tarea(){}

    public Tarea(String nombre, double importancia, double dificultad, double urgencia, int duracion)
    {
        this.Nombre = nombre;
        this.Importancia = importancia;
        this.Dificultad = dificultad;
        this.Urgencia = urgencia;
        this.Duracion = duracion;

        this.Prioridad = (this.Dificultad * 0.3 + this.Importancia * 0.4 + this.Urgencia * 0.3);
    }
}
