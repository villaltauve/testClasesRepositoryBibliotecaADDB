package bibliotecaADDB_v01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tesis extends Ejemplar {
    private String autor;
    private String director;
    private String institucion;
    private int anioPublicacion;
    private String nivelAcademico;
    private String idioma;

    public Tesis(String id, String titulo, String autor, String director, String institucion, int anioPublicacion, String nivelAcademico, String idioma) {
        super(id, titulo);
        this.autor = autor;
        this.director = director;
        this.institucion = institucion;
        this.anioPublicacion = anioPublicacion;
        this.nivelAcademico = nivelAcademico;
        this.idioma = idioma;
    }

    @Override
    public String getDetalles() {
        return "ID: " + id 
            + ", Título: " + titulo 
            + ", Autor: " + autor 
            + ", Director: " + director 
            + ", Institución: " + institucion 
            + ", Año de Publicación: " + anioPublicacion 
            + ", Nivel Académico: " + nivelAcademico 
            + ", Idioma: " + idioma;
    }

    public String getAutor() {
        return autor;
    }

    public String getDirector() {
        return director;
    }

    public String getInstitucion() {
        return institucion;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public String getIdioma() {
        return idioma;
    }

    public void agregarMaterial() throws SQLException {
        String sql = "INSERT INTO Tesis (id, titulo, autor, director, institucion, anioPublicacion, nivelAcademico, idioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, titulo);
            pstmt.setString(3, autor);
            pstmt.setString(4, director);
            pstmt.setString(5, institucion);
            pstmt.setInt(6, anioPublicacion);
            pstmt.setString(7, nivelAcademico);
            pstmt.setString(8, idioma);
            pstmt.executeUpdate();
        }
    }

    public void borrarMaterial() throws SQLException {
        String sql = "DELETE FROM Tesis WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }

    public static Tesis buscarMaterial(String id) throws SQLException {
        String sql = "SELECT * FROM Tesis WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Tesis(
                    rs.getString("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("director"),
                    rs.getString("institucion"),
                    rs.getInt("anioPublicacion"),
                    rs.getString("nivelAcademico"),
                    rs.getString("idioma")
                );
            }
        }
        return null;  
    }
}
