package bibliotecaADDB_v01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Libro extends Ejemplar {
    private String autor;
    private String editorial;
    private int paginas;
    private int isbn;
    private int anio;
    private String idioma;
    private int edicion;

    public Libro(String id, String titulo, String autor, int paginas, int anio, String editorial, int isbn, String idioma, int edicion) {
        super(id, titulo);
        this.autor = autor;
        this.paginas = paginas;
        this.isbn = isbn;
        this.editorial = editorial;
        this.anio = anio;
        this.idioma = idioma;
        this.edicion = edicion;
    }

    @Override
    public String getDetalles() {
        return "Libro - Título: " + titulo + ", Autor: " + autor + ", Editorial: " + editorial + ", Páginas: " + paginas 
             + ", Año de Publicación: " + anio + ", ISBN: " + isbn + ", Idioma: " + idioma + ", Edición: " + edicion;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public int getPaginas() {
        return paginas;
    }

    public int getAnio() {
        return anio;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getEdicion() {
        return edicion;
    }

    public void agregarMaterial() throws SQLException {
        String sql = "INSERT INTO Libro (id, titulo, autor, paginas, anio, editorial, isbn, idioma, edicion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, titulo);
            pstmt.setString(3, autor);
            pstmt.setInt(4, paginas);
            pstmt.setInt(5, anio);
            pstmt.setString(6, editorial);
            pstmt.setInt(7, isbn);
            pstmt.setString(8, idioma);
            pstmt.setInt(9, edicion);
            pstmt.executeUpdate();
        }
    }

    public void borrarMaterial() throws SQLException {
        String sql = "DELETE FROM Libro WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }

    public static Libro buscarMaterial(String id) throws SQLException {
        String sql = "SELECT * FROM Libro WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Libro(
                    rs.getString("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getInt("paginas"),
                    rs.getInt("anio"),
                    rs.getString("editorial"),
                    rs.getInt("isbn"),
                    rs.getString("idioma"),
                    rs.getInt("edicion")
                );
            }
        }
        return null;
    }
}
