package bibliotecaADDB_v01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Revista extends Ejemplar {
    private String periodicidad;
    private int anioPublicacion;
    private String autor;
    private String editorial;
    private int numeroVolumen;
    private String tematica;
    private String idioma;

    public Revista(String id, String titulo, String autor, String periodicidad, int anioPublicacion, String editorial, int numeroVolumen, String tematica, String idioma) {
        super(id, titulo);
        this.autor = autor;
        this.editorial = editorial;
        this.periodicidad = periodicidad;
        this.anioPublicacion = anioPublicacion;
        this.numeroVolumen = numeroVolumen;
        this.tematica = tematica;
        this.idioma = idioma;
    }

    @Override
    public String getDetalles() {
        return "Revista - Título: " + titulo 
             + ", Autor: " + autor 
             + ", Editorial: " + editorial 
             + ", Periodicidad: " + periodicidad 
             + ", Año de Publicación: " + anioPublicacion 
             + ", Número o Volumen: " + numeroVolumen 
             + ", Temática: " + tematica 
             + ", Idioma: " + idioma;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public String getEditorial() {
        return editorial;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public String getAutor() {
        return autor;
    }

    public int getNumeroVolumen() {
        return numeroVolumen;
    }

    public String getTematica() {
        return tematica;
    }

    public String getIdioma() {
        return idioma;
    }

    public void agregarMaterial() throws SQLException {
        String sql = "INSERT INTO Revista (id, titulo, autor, periodicidad, anioPublicacion, editorial, numeroVolumen, tematica, idioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, titulo);
            pstmt.setString(3, autor);
            pstmt.setString(4, periodicidad);
            pstmt.setInt(5, anioPublicacion);
            pstmt.setString(6, editorial);
            pstmt.setInt(7, numeroVolumen);
            pstmt.setString(8, tematica);
            pstmt.setString(9, idioma);
            pstmt.executeUpdate();
        }
    }

    public void borrarMaterial() throws SQLException {
        String sql = "DELETE FROM Revista WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }

    public static Revista buscarMaterial(String id) throws SQLException {
        String sql = "SELECT * FROM Revista WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Revista(
                    rs.getString("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("periodicidad"),
                    rs.getInt("anioPublicacion"),
                    rs.getString("editorial"),
                    rs.getInt("numeroVolumen"),
                    rs.getString("tematica"),
                    rs.getString("idioma")
                );
            }
        }
        return null;
    }
}
