public class Records {
    private String nombre;
    private int puntos;
    private String gp;
    private int nivel;
    public Records(String nombre, int puntos, String gp, int nivel) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.gp = gp;
        this.nivel = nivel;
    }
    public String getGp() {
        return gp;
    }
    public void setGp(String gp) {
        this.gp = gp;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
