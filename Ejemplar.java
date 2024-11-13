package bibliotecaADDB_v01;

public abstract class Ejemplar {
    protected String id;
    protected String titulo;

    public Ejemplar(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public abstract String getDetalles();
}
