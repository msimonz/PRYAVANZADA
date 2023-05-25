import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Juego {
    //VENTANA EMERGENTE:
    static JFrame frame;
    //PANEL DE INICIO:
    JPanel ppt;
    JButton iniciar;
    JLabel fondoppt;
    ImageIcon imgfondoppt;
    //PANEL DE MENÚ
    JPanel pptmenu;
    JButton[] bmenu;
    JLabel fondomenu;
    ImageIcon imgfondomenu;
    //PANEL DE JUEGO
    public static JPanel pptjuego = new JPanel();
    JLabel fondojuego;
    ImageIcon imgfondojuego;
    static int[][] mat;
    static int[][] matAux;
    static JLabel[][] matriz;
    String jugador;
    JLabel nombres;
    int puntos;
    int puntosglobal = 0;
    JLabel records = new JLabel();
    public static ImageIcon imgnivel;
    int px = 1;
    int py = 1;
    int abajo = 0;
    int arriba = 0;
    int izquierda = 0;
    int derecha = 0;
    Timer timer;
    //ELECCIÓN DE NIVEL
    JPanel pptnivel;
    JButton[] niveles;
    int opcion;
    JLabel fondonivel;
    ImageIcon imgfondoniveles;
    //GANADOR O PERDEDOR
    JPanel pptgp;
    JButton botongp;
    JLabel fondogp;
    ImageIcon imgfondogp;
    int ganapierde;
    //FANTASMA
    Fantasma fantasma;
    Fantasma fantasma1;
    Fantasma fantasma2;
    public static Random random = new Random();
    public static int randomNum = random.nextInt(4)+7;
    public static int randomNum2 = random.nextInt(4)+7;
    public static int randomNum3 = random.nextInt(4)+7;
    //MOSTRAR RECORDS
    JPanel pptrecords;
    JButton botonrecords;
    JLabel fondorecords;
    JLabel[] ganadores;
    ImageIcon imgfondorecords;
    ArrayList<Records> recordsList = new ArrayList<>();
    public Juego (){
        //CONSTRUCCIÓN VENTANA EMERGENTE:
        frame = new JFrame("PACMAN");
        frame.setSize(700,700);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //CONSTRUCCIÓN PANEL INICIO:
        ppt = new JPanel();
        ppt.setLayout(null);
        ppt.setBounds(0,0,frame.getWidth(), frame.getHeight());
        ppt.setVisible(true);
        //CONSTRUCCIÓN BOTÓN INICIAR:
        iniciar = new JButton("Iniciar");
        iniciar.setBounds((frame.getWidth()) - 400, 550, 100, 30);
        iniciar.setBackground(Color.white);
        iniciar.setVisible(true);
        //CONSTRUCCIÓN FONDO INICIO:
        fondoppt = new JLabel();
        fondoppt.setBounds(0,0,frame.getWidth(), frame.getHeight());
        imgfondoppt = new ImageIcon("ImagenCarga.png");
        imgfondoppt = new ImageIcon(imgfondoppt.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT));
        fondoppt.setIcon(imgfondoppt);
        fondoppt.setVisible(true);
        //SE AÑADEN LOS ELEMENTOS CREADOS AL PANEL DE INICIO EDPECIFICANDO SU ÍNDICE DE CAPA:
        ppt.add(fondoppt);
        ppt.add(iniciar, 0);
        //SE AÑADE EL PANEL DE INICIO A LA VENTANA EMERGENTE ESPECIFICANDO SU ÍNDICE DE CAPA:
        frame.add(ppt,-1);
        //DIRECCIONAMIENTO BOTÓN INICIAR:
        iniciar.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                System.out.println("Iniciar");
                ppt.setVisible(false);
                Menu();
                eventoMenu();
            }
        });
        //MEMORIA BOTONES MENU
        bmenu = new JButton[4];
        for (int i = 0; i < bmenu.length; i++) {
            bmenu[i] = new JButton();
        }
        //MEMORIA MATRICES JUEGO
        mat = new int[15][15];
        matAux = new int[15][15];
        matriz = new JLabel[15][15];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                matriz[i][j] = new JLabel();
            }
        }
        //MEMORIA BOTONES NIVELES
        niveles = new JButton[3];
        for (int i = 0; i < niveles.length; i++) {
            niveles[i] = new JButton();
        }
        //MEMORIA LABELS RECORDS
        ganadores = new JLabel[3];
        for (int i = 0; i < ganadores.length; i++) {
            ganadores[i] = new JLabel();
        }
    }
    public void Menu(){
        //CONSTRUCCIÓN PANEL Menu:
        pptmenu = new JPanel();
        pptmenu.setLayout(null);
        pptmenu.setBounds(0,0,frame.getWidth(), frame.getHeight());
        pptmenu.setVisible(true);
        //CONSTRUCCIÓN FONDO INICIO:
        fondomenu= new JLabel();
        fondomenu.setBounds(0,0,frame.getWidth(), frame.getHeight());
        imgfondomenu = new ImageIcon("FondoMenu.png");
        imgfondomenu = new ImageIcon(imgfondomenu.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT));
        fondomenu.setIcon(imgfondomenu);
        fondomenu.setVisible(true);
        //SE AÑADEN LOS ELEMENTOS CREADOS AL PANEL DE INICIO EDPECIFICANDO SU ÍNDICE DE CAPA:
        pptmenu.add(fondomenu);
        //CONSTRUCCIÓN BOTONES DE MENÚ
        bmenu[0].setText("Jugar");
        bmenu[1].setText("Escoger Nivel");
        bmenu[2].setText("Records");
        bmenu[3].setText("Salir");
        for (int i = 0; i < bmenu.length; i++) {
            bmenu[i].setVisible(true);
            bmenu[i].setBounds(frame.getWidth() - (425), 300 + ((i + 1) * 50), 150, 30);
            bmenu[i].setBackground(Color.WHITE);
            pptmenu.add(bmenu[i], 0);
        }
        //SE AÑADE EL PANEL DE MENÚ A LA VENTANA EMERGENTE ESPECIFICANDO SU ÍNDICE DE CAPA:
        frame.add(pptmenu,-1);
    }
    public void eventoMenu(){
        bmenu[0].addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                System.out.println("Jugar");
                jugador = JOptionPane.showInputDialog(frame, "Nombre del Jugador", "Escribe aquí");
                while(jugador == null || jugador.compareTo("Escribe aquí" ) == 0|| jugador.compareTo("")==0) {
                    jugador = JOptionPane.showInputDialog(frame, "Debes ingresar un usuario", "Escribe aquí");
                }
                pptmenu.setVisible(false);
                opcion = 1;
                mat = tablero(opcion);
                matAux = tablero(opcion);
                fantasma = new Fantasma(13, 13, randomNum);
                mat[px][py] = 3;
                mat[fantasma.getFanx()][fantasma.getFany()] = fantasma.getAltnum();
                jugar();
            }
        });
        bmenu[1].addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                System.out.println("Escoger Nivel");
                jugador = JOptionPane.showInputDialog(frame, "Nombre del Jugador", "Escribe aquí");
                while(jugador == null || jugador.compareTo("Escribe aquí" ) == 0|| jugador.compareTo("")==0) {
                    jugador = JOptionPane.showInputDialog(frame, "Debes ingresar un usuario", "Escribe aquí");
                }
                pptmenu.setVisible(false);
                niveles();
            }
        });
        bmenu[2].addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                System.out.println("Records");
                pptmenu.setVisible(false);
                mostrarRecords();
            }
        });
        bmenu[3].addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                System.out.println("Salir");
                pptmenu.setVisible(false);
                System.exit(0);
            }
        });
    }
    public void niveles(){
        px = 1;
        py = 1;
        //CONSTRUCCIÓN PANEL NIVELES:
        pptnivel = new JPanel();
        pptnivel.setLayout(null);
        pptnivel.setBounds(0,0,frame.getWidth(), frame.getHeight());
        pptnivel.setVisible(true);
        //CONSTRUCCIÓN FONDO NIVELES:
        fondonivel = new JLabel();
        fondonivel.setBounds(0,0,frame.getWidth(), frame.getHeight());
        imgfondoniveles = new ImageIcon("FondoJuego.png");
        imgfondoniveles = new ImageIcon(imgfondoniveles.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT));
        fondonivel.setIcon(imgfondoniveles);
        fondonivel.setVisible(true);
        //SE AÑADE EL FONDO AL PANEL DE NIVELES
        pptnivel.add(fondonivel);
        //CONSTRUCCIÓN BOTONES NIVELES
        niveles[0].setText("Nivel 1");
        niveles[1].setText("Nivel 2");
        niveles[2].setText("Nivel 3");
        for (int j = 0; j < niveles.length; j++) {
            niveles[j].setVisible(true);
            niveles[j].setBounds(frame.getWidth() - (425), 250 + ((j + 1) * 50), 150, 30);
            niveles[j].setBackground(Color.WHITE);
            pptnivel.add(niveles[j], 0);
        }
        frame.add(pptnivel);
        niveles[0].addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                System.out.println("Nivel 1");
                opcion = 1;
                mat = tablero(opcion);
                matAux = tablero(opcion);
                fantasma = new Fantasma(13, 13, randomNum);
                pptnivel.setVisible(false);
                mat[px][py] = 3;
                mat[fantasma.getFanx()][fantasma.getFany()] = fantasma.getAltnum();
                jugar();
            }
        });
        niveles[1].addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                System.out.println("Nivel 2");
                opcion = 2;
                mat = tablero(opcion);
                matAux = tablero(opcion);
                fantasma = new Fantasma(13, 13, randomNum);
                pptnivel.setVisible(false);
                mat[px][py] = 3;
                mat[fantasma.getFanx()][fantasma.getFany()] = fantasma.getAltnum();
                fantasmas(opcion);
                jugar();
            }
        });
        niveles[2].addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                System.out.println("Nivel 3");
                opcion = 3;
                mat = tablero(opcion);
                fantasma = new Fantasma(13, 13, randomNum);
                pptnivel.setVisible(false);
                mat[px][py] = 3;
                mat[fantasma.getFanx()][fantasma.getFany()] = fantasma.getAltnum();
                fantasmas(opcion);
                jugar();
            }
        });
    }
    public void jugar(){
        int encontrar = 0;
        //CONSTRUCCIÓN PANEL JUEGO:
        pptjuego.setLayout(null);
        pptjuego.setBounds(0,0,frame.getWidth(), frame.getHeight());
        pptjuego.setVisible(true);
        //CONSTRUCCIÓN FONDO INICIO:
        fondojuego= new JLabel();
        fondojuego.setBounds(0,0,frame.getWidth(), frame.getHeight());
        imgfondojuego = new ImageIcon("FondoJuego.png");
        imgfondojuego = new ImageIcon(imgfondojuego.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT));
        fondojuego.setIcon(imgfondojuego);
        fondojuego.setVisible(true);
        //SE AÑADEN LOS ELEMENTOS AL PANEL DE JUEGO
        pptjuego.add(fondojuego);
        //SE AÑADE LA MATRIZ (NIVEL) AL PANEL DE JUEGO
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                imgnivel = new ImageIcon(mat[i][j] + ".png");
                imgnivel = new ImageIcon(imgnivel.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
                matriz[i][j].setIcon(imgnivel);
                matriz[i][j].setBounds(200 + (i * 30), 180+ (j * 30), 30, 30);
                matriz[i][j].setVisible(true);
                pptjuego.add(matriz[i][j], 0);
            }
        }
        nombres = new JLabel("JUGADOR: "+jugador);
        nombres.setBounds(35,345, 150, 30);
        nombres.setVisible(true);
        nombres.setForeground(Color.WHITE);
        Font font = new Font("Monospaced", Font.BOLD, 15);
        nombres.setFont(font);
        puntos = 0;
        records.setText("PUNTOS: "+ puntos);
        records.setBounds(35,380, 150, 30);
        records.setForeground(Color.WHITE);
        Font font2 = new Font("Monospaced", Font.BOLD, 15);
        records.setFont(font2);
        records.setVisible(true);
        pptjuego.add(nombres,0);
        pptjuego.add(records,0);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if(mat[i][j] == 1){
                    encontrar += 1;
                }
            }
        }
        System.out.println(encontrar);
        move(encontrar, opcion);

        //SE AÑADE EL PANEL DE JUEGO A LA VENTANA EMERGENTE
        frame.add(pptjuego,0);

    }
    public static void paintMatrix(){
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                imgnivel = new ImageIcon(mat[i][j] + ".png");
                imgnivel = new ImageIcon(imgnivel.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
                matriz[i][j].setIcon(imgnivel);
                matriz[i][j].setBounds(200 + (i * 30), 180+ (j * 30), 30, 30);
                matriz[i][j].setVisible(true);
                pptjuego.add(matriz[i][j], 0);
            }
        }
    }
    public void move(int encontrar, int opcion) {
        abajo = 0;
        arriba = 0;
        izquierda = 0;
        derecha = 0;
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        timer = new Timer(2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(derecha == 1 && (mat[px+1][py] == 1 || mat[px+1][py] == 0)){
                    if(mat[px+1][py] == 1){
                        puntos += 5;
                        records.setText("PUNTOS: " + puntos);
                        ganar(encontrar);
                    }
                    mat[px][py] = 0;
                    matAux = mat;
                    px += 1;
                    mat[px][py] = 3;
                    paintMatrix();
                }
                else if(derecha == 1 && (mat[px+1][py] == 7 || mat[px+1][py] == 8 || mat[px+1][py] == 9 || mat[px+1][py] == 10)){
                    pptjuego.setVisible(false);
                    timer.stop();
                    fantasma.timer.stop();
                    if(opcion == 2){
                        fantasma1.timer.stop();
                    }
                    else if(opcion == 3){
                        fantasma2.timer.stop();
                    }
                    ganarperder(0);
                }
                if(izquierda == 1 && (mat[px-1][py] == 1 || mat[px-1][py] == 0)){
                    if(mat[px-1][py] == 1){
                        puntos += 5;
                        records.setText("PUNTOS: " + puntos);
                        ganar(encontrar);
                    }
                    mat[px][py] = 0;
                    matAux = mat;
                    px -= 1;
                    mat[px][py] = 6;
                    paintMatrix();
                }
                else if(izquierda == 1 && (mat[px-1][py] == 7 || mat[px-1][py] == 8 || mat[px-1][py] == 9 || mat[px-1][py] == 10)){
                    pptjuego.setVisible(false);
                    timer.stop();
                    fantasma.timer.stop();
                    if(opcion == 2){
                        fantasma1.timer.stop();
                    }
                    else if(opcion == 3){
                        fantasma2.timer.stop();
                    }
                    ganarperder(0);
                }
                if(arriba == 1 && (mat[px][py-1] == 1 || mat[px][py-1] == 0)){
                    if(mat[px][py-1] == 1){
                        puntos += 5;
                        records.setText("PUNTOS: " + puntos);
                        ganar(encontrar);
                    }
                    mat[px][py] = 0;
                    matAux = mat;
                    py -= 1;
                    mat[px][py] = 4;
                    matAux = mat;
                    paintMatrix();
                }
                else if(arriba == 1 && (mat[px][py-1] == 7 || mat[px][py-1] == 8 || mat[px][py-1] == 9 || mat[px][py-1] == 10)){
                    pptjuego.setVisible(false);
                    timer.stop();
                    fantasma.timer.stop();
                    if(opcion == 2){
                        fantasma1.timer.stop();
                    }
                    else if(opcion == 3){
                        fantasma2.timer.stop();
                    }
                    ganarperder(0);
                }
                if(abajo == 1 && (mat[px][py+1] == 1 || mat[px][py+1] == 0)){
                    if(mat[px][py+1] == 1){
                        puntos += 5;
                        records.setText("PUNTOS: " + puntos);
                        ganar(encontrar);
                    }
                    mat[px][py] = 0;
                    matAux = mat;
                    py += 1;
                    mat[px][py] = 5;
                    matAux = mat;
                    paintMatrix();
                }
                else if(abajo == 1 && (mat[px][py+1] == 7 || mat[px][py+1] == 8 || mat[px][py+1] == 9 || mat[px][py+1] == 10)){
                    pptjuego.setVisible(false);
                    timer.stop();
                    fantasma.timer.stop();
                    if(opcion == 2){
                        fantasma1.timer.stop();
                    }
                    else if(opcion == 3){
                        fantasma2.timer.stop();
                    }
                    ganarperder(0);
                }
            }
        });
        timer.start();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    System.out.println("Tecla hacia arriba");
                    if(mat[px][py-1] == 1 || mat[px][py-1] == 0) {
                        arriba = 1;
                        abajo = 0;
                        derecha = 0;
                        izquierda = 0;
                    }
                    else if(mat[px][py-1] == 7 || mat[px][py-1] == 8 || mat[px][py-1] == 9 || mat[px][py-1] == 10) {
                        pptjuego.setVisible(false);
                        timer.stop();
                        fantasma.timer.stop();
                        if(opcion == 2){
                            fantasma1.timer.stop();
                        }
                        else if(opcion == 3){
                            fantasma2.timer.stop();
                        }
                        ganarperder(0);
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    System.out.println("Tecla hacia derecha");
                    if(mat[px+1][py] == 1 || mat[px+1][py] == 0) {
                        arriba = 0;
                        abajo = 0;
                        derecha = 1;
                        izquierda = 0;
                    }
                    else if(mat[px+1][py] == 7 || mat[px+1][py] == 8 || mat[px+1][py] == 9 || mat[px+1][py] == 10){
                        pptjuego.setVisible(false);
                        timer.stop();
                        fantasma.timer.stop();
                        if(opcion == 2){
                            fantasma1.timer.stop();
                        }
                        else if(opcion == 3){
                            fantasma2.timer.stop();
                        }
                        ganarperder(0);
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    System.out.println("Tecla hacia izquierda");
                    if(mat[px-1][py] == 1 || mat[px-1][py] == 0) {
                        arriba = 0;
                        abajo = 0;
                        derecha = 0;
                        izquierda = 1;
                    }
                    else if(mat[px-1][py] == 7 || mat[px-1][py] == 8 || mat[px-1][py] == 9 || mat[px-1][py] == 10){
                        pptjuego.setVisible(false);
                        timer.stop();
                        fantasma.timer.stop();
                        if(opcion == 2){
                            fantasma1.timer.stop();
                        }
                        else if(opcion == 3){
                            fantasma2.timer.stop();
                        }
                        ganarperder(0);
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    System.out.println("Tecla hacia abajo");
                    if(mat[px][py+1] == 1 || mat[px][py+1] == 0) {
                        arriba = 0;
                        abajo = 1;
                        derecha = 0;
                        izquierda = 0;
                    }
                    else if(mat[px][py+1] == 7 || mat[px][py+1] == 8 || mat[px][py+1] == 9 || mat[px][py+1] == 10){
                        pptjuego.setVisible(false);
                        timer.stop();
                        fantasma.timer.stop();
                        if(opcion == 2){
                            fantasma1.timer.stop();
                        }
                        else if(opcion == 3){
                            fantasma2.timer.stop();
                        }
                        ganarperder(0);
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
    public void ganar(int encontrar){
        //System.out.println("Ganar");
        if((encontrar*5) == puntos){
            pptjuego.setVisible(false);
            timer.stop();
            //JOptionPane.showMessageDialog(frame, "Felicitaciones");
            ganarperder(1);
        }
    }
    public void ganarperder(int estado){
        System.out.println("Ganarperder");
        //CONSTRUCCIÓN PANEL GANAR O PERDER:
        pptgp = new JPanel();
        pptgp.setLayout(null);
        pptgp.setBounds(0,0,frame.getWidth(), frame.getHeight());
        pptgp.setBackground(Color.BLACK);
        System.out.println("Ganarperder");
        pptgp.setVisible(true);
        //CONSTRUCCIÓN FONDO GANAR O PERDER:
        fondogp = new JLabel();
        if(estado == 0){//0 = PERDER
            fondogp.setBounds(100,50,600, 600);
            imgfondogp = new ImageIcon("Pierde.png");
            imgfondogp = new ImageIcon(imgfondogp.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
            botongp = new JButton("Volver");
            botongp.setBounds(150, 550, 200, 30);
            botongp.setBackground(Color.white);
            botongp.setVisible(true);
            botongp.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    System.out.println("Volver");
                    mat = tablero(opcion);
                    matAux = mat;
                    pptgp.setVisible(false);
                    px = 1;
                    py = 1;
                    fantasma.setFany(13);
                    fantasma.setFanx(13);
                    ganapierde = 0;
                    if(opcion==2) {
                        fantasma1.setFanx(1);
                        fantasma1.setFany(13);
                        escribirArchivo(jugador, puntosglobal, ganapierde, opcion);
                        puntosglobal = 0;
                    }
                    else if (opcion == 3){
                        fantasma2.setFanx(13);
                        fantasma2.setFany(1);
                        escribirArchivo(jugador,puntosglobal,ganapierde,opcion);
                        puntosglobal = 0;
                    }
                    else if(opcion == 1){
                        escribirArchivo(jugador,puntos,ganapierde,opcion);
                    }
                    puntos = 0;
                    records.setText("");
                    mat[px][py] = 3;
                    mat[fantasma.getFanx()][fantasma.getFany()] = fantasma.getAltnum();
                    frame.setFocusable(true);
                    frame.requestFocusInWindow();
                    Menu();
                }
            });
            fondogp.add(botongp, 0);
        }
        else if (estado == 1){//1 = GANAR NIVEL
            fondogp.setBounds(100,50,600, 600);
            imgfondogp = new ImageIcon("GanaNivel.png");
            imgfondogp = new ImageIcon(imgfondogp.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
            if(opcion<3) {
                opcion+=1;
                botongp = new JButton("Siguiente Nivel");
                botongp.setBounds(150, 550, 200, 30);
                botongp.setBackground(Color.white);
                botongp.setVisible(true);
                fondogp.add(botongp, 0);
                botongp.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        System.out.println("Siguiente Nivel");
                        mat = tablero(opcion);
                        matAux = mat;
                        pptgp.setVisible(false);
                        px = 1;
                        py = 1;
                        fantasma.setFany(13);
                        fantasma.setFanx(13);
                        if(opcion == 2){
                            fantasma2.setFanx(13);
                            fantasma2.setFany(1);
                            puntosglobal = 0;
                        }
                        puntosglobal+=puntos;
                        puntos = 0;
                        records.setText("");
                        mat[px][py] = 3;
                        mat[fantasma.getFanx()][fantasma.getFany()] = fantasma.getAltnum();
                        fantasmas(opcion);
                        jugar();
                    }
                });
            }
            else{
                botongp = new JButton("Salir");
                botongp.setBounds(150, 550, 100, 30);
                botongp.setBackground(Color.white);
                botongp.setVisible(true);
                fondogp.add(botongp, 0);
                puntosglobal += puntos;
                botongp.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        System.out.println("Salir");
                        px = 1;
                        py = 1;
                        ganapierde = 1;
                        fantasma.setFanx(13);
                        fantasma.setFany(13);
                        fantasma2.setFanx(13);
                        fantasma2.setFany(1);
                        fantasma1.setFanx(1);
                        fantasma1.setFany(13);
                        records.setText("");
                        puntos = 0;
                        pptgp.setVisible(false);
                        escribirArchivo(jugador,puntosglobal,ganapierde,opcion);
                        puntosglobal = 0;
                        Menu();
                    }
                });
            }
        }
        fondogp.setIcon(imgfondogp);
        fondogp.setVisible(true);
        //SE AÑADE EL FONDO AL PANEL GANAR O PERDER
        pptgp.add(fondogp);
        frame.add(pptgp);
    }
    public void fantasmas(int opcion){
        if(opcion == 2) {
            randomNum2 = random.nextInt(4) + 7;
            fantasma1 = new Fantasma(1, 13, randomNum2);
            mat[fantasma1.getFanx()][fantasma1.getFany()] = fantasma1.getAltnum();
        }
        else if(opcion == 3){
            randomNum3 = random.nextInt(4)+7;
            fantasma2 = new Fantasma(13,1, randomNum3);
            mat[fantasma2.getFanx()][fantasma2.getFany()] = fantasma2.getAltnum();
        }
    }
    public int[][] tablero(int opcion){
        int[][] aux1 = new int[15][15];
        if(opcion == 1){
            int[][] aux ={
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                    {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
                    {2,1,2,2,2,2,1,1,1,2,1,1,2,1,2},
                    {2,1,2,1,1,1,1,1,1,2,1,1,1,1,2},
                    {2,1,2,1,2,1,1,1,1,2,1,1,1,1,2},
                    {2,1,2,1,2,1,1,1,1,2,1,1,1,1,2},
                    {2,1,2,1,1,1,1,1,1,2,1,1,1,1,2},
                    {2,1,2,2,2,2,1,1,1,2,1,1,1,1,2},
                    {2,1,1,1,1,1,1,1,1,2,1,1,1,1,2},
                    {2,1,2,2,2,2,2,2,1,2,2,2,2,1,2},
                    {2,1,1,1,1,1,1,2,1,2,2,2,2,1,2},
                    {2,1,1,1,1,1,1,2,1,1,1,1,1,1,2},
                    {2,1,1,1,1,1,1,2,2,2,2,2,2,1,2},
                    {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}
            };
            return aux;
        }
        else if(opcion == 2){
            int[][] aux ={
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                    {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
                    {2,1,2,1,2,2,2,2,2,2,2,2,2,1,2},
                    {2,1,2,1,2,1,1,1,1,1,1,1,1,1,2},
                    {2,1,2,1,2,2,2,2,2,2,2,2,2,1,2},
                    {2,1,2,1,1,1,1,1,1,1,1,1,1,1,2},
                    {2,1,2,1,1,1,1,1,1,1,1,1,1,1,2},
                    {2,1,2,2,2,2,2,2,2,1,1,1,1,1,2},
                    {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
                    {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
                    {2,1,2,1,2,2,2,2,2,2,2,1,2,1,2},
                    {2,1,2,1,1,1,1,1,1,1,1,1,2,1,2},
                    {2,1,2,2,2,2,2,2,2,2,2,2,2,1,2},
                    {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}
            };
            return aux;
        }
        else if(opcion == 3){
            int[][] aux ={
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                    {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
                    {2,1,2,1,2,1,2,1,2,1,2,1,2,1,2},
                    {2,1,2,1,2,1,2,1,2,1,2,1,1,1,2},
                    {2,1,2,1,2,1,2,1,2,1,1,1,1,1,2},
                    {2,1,2,1,2,1,2,1,2,2,2,2,2,1,2},
                    {2,1,2,1,2,1,2,1,1,1,1,1,1,1,2},
                    {2,1,2,1,2,1,2,2,2,2,2,2,2,1,2},
                    {2,1,2,1,2,1,1,1,1,1,1,1,1,1,2},
                    {2,1,2,1,2,2,2,2,2,2,2,2,2,1,2},
                    {2,1,2,1,1,1,1,1,1,1,1,1,1,1,2},
                    {2,1,2,2,2,2,2,2,2,2,2,2,2,1,2},
                    {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
                    {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}
            };
            return aux;
        }
        return aux1;
    }
    public void escribirArchivo(String jugador, int puntosglobal, int ganapierde, int opcion){
        String gp;
        try{
            File file = new File("Records.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            //saltoLinea(bw);
            if(ganapierde == 1){
                gp = "GANÓ";
                bw.write(jugador+"/"+puntosglobal+"/"+gp+"\n");
            }
            else{
                gp = "PERDIÓ";
                bw.write(jugador+"/"+puntosglobal+"/"+gp+"/"+opcion+"\n");
            }
            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void mostrarRecords(){
        ArrayList<Records> tres = new ArrayList<>();
        //CONSTRUCCIÓN PANEL Menu:
        pptrecords = new JPanel();
        pptrecords.setLayout(null);
        pptrecords.setBounds(0,0,frame.getWidth(), frame.getHeight());
        pptrecords.setVisible(true);
        //CONSTRUCCIÓN FONDO INICIO:
        fondorecords= new JLabel();
        fondorecords.setBounds(0,0,frame.getWidth(), frame.getHeight());
        imgfondorecords = new ImageIcon("FondoRecords.png");
        imgfondorecords = new ImageIcon(imgfondorecords.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT));
        fondorecords.setIcon(imgfondorecords);
        fondorecords.setVisible(true);
        botonrecords = new JButton("Volver");
        botonrecords.setBounds((frame.getWidth()) - 400, 550, 100, 30);
        botonrecords.setBackground(Color.white);
        botonrecords.setVisible(true);
        int cont = 0;
        int posicion = 0;
        int p = 0;
        lectArchivo();
        while(cont <3){
            for (Records value : recordsList) {
                if(value.getPuntos() > p){
                    posicion = recordsList.indexOf(value);
                    p = value.getPuntos();
                }
            }
            tres.add(recordsList.get(posicion));
            recordsList.remove(posicion);
            p = 0;
            posicion = 0;
            cont++;
        }
        cont = 0;
        for (Records val: tres) {
            ganadores[cont].setText("JUGADOR: "+val.getNombre()+" PUNTOS: "+val.getPuntos());
            cont++;
        }
        for (int i = 0; i < ganadores.length; i++) {
            ganadores[i].setBounds(frame.getWidth() - (500), 300 + ((i + 1) * 50), 300, 30);
            ganadores[i].setVisible(true);
            ganadores[i].setForeground(Color.WHITE);
            Font font = new Font("Monospaced", Font.BOLD, 15);
            ganadores[i].setFont(font);
            pptrecords.add(ganadores[i],0);
        }
        //SE AÑADEN LOS ELEMENTOS CREADOS AL PANEL DE INICIO EDPECIFICANDO SU ÍNDICE DE CAPA:
        pptrecords.add(fondorecords);
        pptrecords.add(botonrecords,0);
        botonrecords.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                System.out.println("Volver");
                pptrecords.setVisible(false);
                Menu();
            }
        });
        frame.add(pptrecords);
    }
    public void lectArchivo(){
        String name;
        String level;
        String points;
        String gaper;
        try{
            File file = new File("Records.txt");
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("[/|\n]");
            while(scanner.hasNext()){
                name = scanner.next();
                points = scanner.next();
                gaper = scanner.next();
                level = scanner.next();
                recordsList.add(new Records(name,Integer.parseInt(points),gaper,Integer.parseInt(level)));
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        /*for (Records value: recordsList) {
            System.out.println(value.getNombre()+" "+value.getPuntos()+" "+value.getGp()+" "+value.getNivel());
        }*/
    }
    public void showFrame(){
        frame.setVisible(true);
    }
}