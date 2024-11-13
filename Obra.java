package bibliotecaADDB_v01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Obra extends Ejemplar {
    private String autor;
    private String tipoObra;
    private String generoLiterario;
    private int anioCreacion;
    private String idioma;

    public Obra(String id, String titulo, String autor, String tipoObra, String generoLiterario, int anioCreacion, String idioma) {
        super(id, titulo);
        this.autor = autor;
        this.tipoObra = tipoObra;
        this.generoLiterario = generoLiterario;
        this.anioCreacion = anioCreacion;
        this.idioma = idioma;
    }

    @Override
    public String getDetalles() {
        return "Obra - Título: " + titulo + ", Autor: " + autor + ", Tipo de Obra: " + tipoObra 
             + ", Género Literario: " + generoLiterario + ", Año de Creación: " + anioCreacion + ", Idioma: " + idioma;
    }

    public String getAutor() {
        return autor;
    }

    public String getTipoObra() {
        return tipoObra;
    }

    public String getGeneroLiterario() {
        return generoLiterario;
    }

    public int getAnioCreacion() {
        return anioCreacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void agregarMaterial() throws SQLException {
        String sql = "INSERT INTO Obra (id, titulo, autor, tipoObra, generoLiterario, anioCreacion, idioma) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, titulo);
            pstmt.setString(3, autor);
            pstmt.setString(4, tipoObra);
            pstmt.setString(5, generoLiterario);
            pstmt.setInt(6, anioCreacion);
            pstmt.setString(7, idioma);
            pstmt.executeUpdate();
        }
    }

    public void borrarMaterial() throws SQLException {
        String sql = "DELETE FROM Obra WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }

    public static Obra buscarMaterial(String id) throws SQLException {
        String sql = "SELECT * FROM Obra WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Obra(
                    rs.getString("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("tipoObra"),
                    rs.getString("generoLiterario"),
                    rs.getInt("anioCreacion"),
                    rs.getString("idioma")
                );
            }
        }
        return null;
    }
}
