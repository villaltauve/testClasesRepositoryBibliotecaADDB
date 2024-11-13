package bibliotecaADDB_v01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CD extends Ejemplar {
    private String artista;
    private String genero;
    private int anioLanzamiento;
    private int duracionTotal;
    private int numCanciones;
    private String discografica;

    public CD(String id, String titulo, String artista, int numCanciones, int duracionTotal, String genero, int anioLanzamiento, String discografica) {
        super(id, titulo);
        this.artista = artista;
        this.genero = genero;
        this.duracionTotal = duracionTotal;
        this.numCanciones = numCanciones;
        this.anioLanzamiento = anioLanzamiento;
        this.discografica = discografica;
    }


    @Override
    public String getDetalles() {
        return "CD - Título: " + titulo 
            + ", Artista: " + artista 
            + ", Género: " + genero 
            + ", Duración Total: " + duracionTotal + " minutos"
            + ", Número de Canciones: " + numCanciones 
            + ", Año de Lanzamiento: " + anioLanzamiento 
            + ", Discográfica: " + discografica;
    }
    
    public String getTitulo() {
    	return titulo;
    }
    
    public String getArtista() {
    	return artista;
    }
    
    public String getGenero() {
    	return genero;
    }
    
    public int getDuracion() {
    	return duracionTotal;
    }
    
    public int getNumCanciones() {
    	return numCanciones;
    }
    
    public int getAnioLanzamiento() {
    	return anioLanzamiento;
    }
    
    public String getDiscografica() {
    	return discografica;
    }
    

    public void agregarMaterial() throws SQLException {
        String sql = "INSERT INTO CD (id, titulo, artista, numCanciones, duracionTotal, genero, anioLanzamiento, discografica) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, titulo);
            pstmt.setString(3, artista);
            pstmt.setInt(4, numCanciones);
            pstmt.setInt(5, duracionTotal);
            pstmt.setString(6, genero);
            pstmt.setInt(7, anioLanzamiento);
            pstmt.setString(8, discografica);
            pstmt.executeUpdate();
        }
    }


    public void borrarMaterial() throws SQLException {
        String sql = "DELETE FROM CD WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }

    public static CD buscarMaterial(String id) throws SQLException {
        String sql = "SELECT * FROM CD WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new CD(
                    rs.getString("id"),
                    rs.getString("titulo"),
                    rs.getString("artista"),
                    rs.getInt("numCanciones"),
                    rs.getInt("duracionTotal"),
                    rs.getString("genero"),
                    rs.getInt("anioLanzamiento"),
                    rs.getString("discografica")
                );
            }
        }
        return null;
    }

    

}
