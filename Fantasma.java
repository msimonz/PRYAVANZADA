import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
public class Fantasma {
    //ATRIBUTOS FANTASMA
    int fanx = 0;
    int fany = 0;
    int altnum;
    Timer timer;
    Random aleatorio = new Random();
    int direccion;
    int mx;
    int my;
    public Fantasma(int fanx, int fany, int num) {
        this.fanx = fanx;
        this.fany = fany;
        this.altnum = num;
        direccion = aleatorio.nextInt(4);
        this.movimiento();
    }
    public int getFanx() {
        return fanx;
    }
    public int getFany() {
        return fany;
    }
    public void setFanx(int fanx) {
        this.fanx = fanx;
    }
    public void setFany(int fany) {
        this.fany = fany;
    }
    public int getAltnum() {
        return altnum;
    }
    public void movimiento(){
        /*
        for (int i = 0; i < Juego.mat.length; i++) {
            for (int j = 0; j < Juego.mat.length; j++) {
                System.out.print(Juego.mat[i][j]+ " ");
            }
            System.out.println(" ");
        }*/
        timer = new Timer(2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ARRIBA
                System.out.println(direccion);
                direccion = aleatorio.nextInt(4);
                Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                if(direccion == 0){
                    //CAMINA
                    if(Juego.mat[fanx][fany-1] == 0 || Juego.mat[fanx][fany-1] == 1){
                        System.out.println("funciona");
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                        if(Juego.mat[fanx][fany-1] == 0){
                            Juego.matAux[fanx][fany] = 0;
                        }
                        else if(Juego.mat[fanx][fany-1] == 1){
                            Juego.matAux[fanx][fany] = 1;
                        }
                        fany -=1;
                        Juego.mat[fanx][fany] = Juego.randomNum;
                    }
                    //CHOCA CON LA PARED
                    if(fany >= 1 && Juego.mat[fanx][fany-1]==2){
                        System.out.println("funciona");
                        direccion = aleatorio.nextInt(4);
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                    }
                    //SE ENCUENTRA A OTRO FANTASMA
                    if(fany >= 1 && Juego.mat[fanx][(fany-1)] == 7){
                        direccion = aleatorio.nextInt(4);
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                    }
                }
                //ABAJO
                if(direccion == 1){
                    if(Juego.mat[fanx][fany+1] == 0 || Juego.mat[fanx][fany+1] == 1){
                        System.out.println("funciona");
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                        if(Juego.mat[fanx][fany+1] == 0){
                            Juego.matAux[fanx][fany] = 0;
                        }
                        else if(Juego.mat[fanx][fany+1] == 1){
                            Juego.matAux[fanx][fany] = 1;
                        }
                        fany +=1;
                        Juego.mat[fanx][fany] = Juego.randomNum;
                    }
                    if(fany <= 14 && Juego.mat[fanx][fany+1]==2){
                        System.out.println("funciona");
                        direccion = aleatorio.nextInt(4);
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                    }
                    //SE ENCUENTRA A OTRO FANTASMA
                    if(fany <= 14 && Juego.mat[fanx][(fany+1)] == 7){
                        direccion = aleatorio.nextInt(4);
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                    }
                }
                //DERECHA
                if(direccion == 2){
                    if(Juego.mat[fanx+1][fany] == 0 || Juego.mat[fanx+1][fany] == 1){
                        System.out.println("funciona");
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                        if(Juego.mat[fanx+1][fany] == 0){
                            Juego.matAux[fanx][fany] = 0;
                        }
                        else if(Juego.mat[fanx+1][fany] == 1){
                            Juego.matAux[fanx][fany] = 1;
                        }
                        fanx +=1;
                        Juego.mat[fanx][fany] = Juego.randomNum;
                    }
                    if(fanx <= 14  && Juego.mat[fanx+1][fany]==2){
                        System.out.println("funciona");
                        direccion = aleatorio.nextInt(4);
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                    }
                    //SE ENCUENTRA A OTRO FANTASMA
                    if(fanx <= 14  && Juego.mat[(fanx+1)][fany] == 7){
                        direccion = aleatorio.nextInt(4);
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                    }
                }
                //IZQUIERDA
                if(direccion == 3){
                    if(Juego.mat[fanx-1][fany] == 0 || Juego.mat[fanx-1][fany] == 1){
                        System.out.println("funciona");
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                        if(Juego.mat[fanx-1][fany] == 0){
                            Juego.matAux[fanx][fany] = 0;
                        }
                        else if(Juego.mat[fanx-1][fany] == 1){
                            Juego.matAux[fanx][fany] = 1;
                        }
                        fanx -=1;
                        Juego.mat[fanx][fany] = Juego.randomNum;
                    }
                    if(fanx >= 1 && Juego.mat[fanx-1][fany]==2){
                        System.out.println("funciona");
                        direccion = aleatorio.nextInt(4);
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                    }
                    //SE ENCUENTRA A OTRO FANTASMA
                    if(fanx >= 1 && Juego.mat[fanx-1][fany] == 7){
                        direccion = aleatorio.nextInt(4);
                        Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
                    }
                }
                Juego.paintMatrix();
            }
        });
        timer.start();
    }
}