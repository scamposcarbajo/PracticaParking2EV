/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ClasesPrincipales;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carne
 */
public class Terminal extends javax.swing.JFrame {

    /**
     * Creates new form Terminal
     */
    public JFrame pantallaAparcar;
    public JFrame pantallaRetirar;
    public JFrame pantallaPagar;
    public Maquina app = new Maquina();
    public Ticket ticketSeleccionado;
    public Ticket ticket;
    private JTable tabla;//control para mostrar la matriz
    private DefaultTableModel modeloTabla;//contenedor de la matriz de enteros

    public Terminal() {
        initComponents();
        LabelAñadirMatricula.setVisible(false);
        TextAñadirMatricula.setVisible(false);
        BotonAñadirMatricula.setVisible(false);
        LabelIntroducirId.setVisible(false);
        TextIntroducirId.setVisible(false);
        BotonIntroducirId.setVisible(false);
        TextPagar.setVisible(false);
        BotonPagar.setVisible(false);
        LabelPagar4.setVisible(false);

        ScrollTabla.setVisible(true);
        this.setResizable(false);
        modeloTabla = new DefaultTableModel();
        tabla = new JTable(modeloTabla);
        actualizarTabla();
        System.out.println("muestro plano desde terminal");

        mostrarTabla();
        ventanaParquimetro();
        setLocation();
    }

    //metodo para actualizar la matriz basado en la matriz producida en maquina 
    public int indiceColumnaActual = 0;

    public void actualizarTabla() {
        // Obtener la nueva matriz de la otra clase
        Integer[][] nuevaMatriz = app.getPlano();

        // Limpiar el modelo de la tabla
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnCount(0);

        // Número de columnas a mostrar por bloque (10 columnas por fila)
        int columnasPorFila = 10;

        // Número total de columnas por planta (20 columnas por planta)
        int totalColumnasPorPlanta = 20;

        // Establecer las columnas que vamos a mostrar (en bloques de 10)
        for (int i = 0; i < columnasPorFila; i++) {
            modeloTabla.addColumn("Columna " + (i + 1));
        }

        // Contador de filas y añadir las filas con separación
        int contadorFilas = 0;

        for (int planta = 0; planta < nuevaMatriz.length; planta++) {
            Integer[] filaPlanta = nuevaMatriz[planta];

            // Mostrar las dos filas de 10 columnas cada una por cada planta
            for (int i = 0; i < 2; i++) {
                Object[] rowData = new Object[columnasPorFila];

                // Rellenar los 10 primeros o últimos valores de la planta (dependiendo de i)
                for (int j = 0; j < columnasPorFila; j++) {
                    rowData[j] = filaPlanta[i * columnasPorFila + j];
                }

                modeloTabla.addRow(rowData);
                contadorFilas++;

                // Insertar una fila vacía cada 2 filas de datos
                if (contadorFilas % 2 == 0) {
                    Object[] filaVacia = new Object[columnasPorFila];
                    for (int k = 0; k < filaVacia.length; k++) {
                        filaVacia[k] = "";  // Deja la fila vacía
                    }
                    modeloTabla.addRow(filaVacia);
                }
            }
        }
    }

    public void setLocation() {
        this.setLocationRelativeTo(null);
    }

    public void ventanaParquimetro() {
        //ARREGLAR LO DE LA PUTA TABLA
        //ventana principal del parquimetro
        setSize(350, 720);
        LabelParquimetroPrincipal.setSize(350, 720);
        //ScrollTabla.setLayout(null);
        setContentPane(LabelParquimetroPrincipal);
        //setResizable(false);
        LabelParquimetroPrincipal.add(BotonAparcarCoche);
        LabelParquimetroPrincipal.add(BotonRetirarCoche);

        LabelParquimetroPrincipal.add(ScrollTabla);

        BotonAparcarCoche.setSize(250, 40);
        BotonRetirarCoche.setSize(250, 40);

        ScrollTabla.setSize(237, 175);
        //tabla.setSize(237, 175);

        BotonAparcarCoche.setBounds(50, 500, BotonAparcarCoche.getWidth(), BotonAparcarCoche.getHeight());
        BotonRetirarCoche.setBounds(50, 550, BotonRetirarCoche.getWidth(), BotonRetirarCoche.getHeight());

        ScrollTabla.setBounds(56, 272, ScrollTabla.getWidth(), ScrollTabla.getHeight());
        ScrollTabla.setVisible(true);
        actualizarTabla();
        mostrarTabla();
    }

    public void ventanaAparcar() {
        //ventana para aparcar un coche en el parking
        pantallaAparcar = new JFrame();
        pantallaAparcar.setLayout(null);
        pantallaAparcar.setResizable(false);
        pantallaAparcar.setVisible(true);
        LabelAparcamiento.setVisible(true);
        pantallaAparcar.setSize(515, 350);
        pantallaAparcar.setContentPane(LabelAparcamiento);
        LabelAparcamiento.setSize(500, 500);
        LabelAparcamiento.add(LabelAñadirMatricula);
        LabelAparcamiento.add(TextAñadirMatricula);
        LabelAparcamiento.add(BotonAñadirMatricula);

        LabelAñadirMatricula.setVisible(true);
        TextAñadirMatricula.setVisible(true);
        BotonAñadirMatricula.setVisible(true);

        LabelAñadirMatricula.setSize(450, 70);
        TextAñadirMatricula.setSize(200, 30);
        BotonAñadirMatricula.setSize(100, 30);

        LabelAñadirMatricula.setText("Por favor introduce la matricula de tu vehículo \n Formato (1234-ABC)");

        LabelAñadirMatricula.setBounds(30, 20, LabelAñadirMatricula.getWidth(), LabelAñadirMatricula.getHeight());
        TextAñadirMatricula.setBounds(100, 240, TextAñadirMatricula.getWidth(), TextAñadirMatricula.getHeight());
        BotonAñadirMatricula.setBounds(300, 240, BotonAñadirMatricula.getWidth(), BotonAñadirMatricula.getHeight());

        actualizarTabla();

        pantallaAparcar.setLocationRelativeTo(null);

    }

    public void ventanaRetirar() {
        //ventana para retirar un coche del parking
        pantallaRetirar = new JFrame();
        pantallaRetirar.setLayout(null);
        pantallaRetirar.setResizable(false);
        pantallaRetirar.setVisible(true);
        LabelRetirarCoche.setVisible(true);
        pantallaRetirar.setSize(600, 350);
        pantallaRetirar.setContentPane(LabelRetirarCoche);
        LabelRetirarCoche.setSize(600, 350);
        LabelRetirarCoche.add(LabelIntroducirId);
        LabelRetirarCoche.add(TextIntroducirId);
        LabelRetirarCoche.add(BotonIntroducirId);
        LabelRetirarCoche.add(jEditorPane1);

        LabelIntroducirId.setVisible(true);
        TextIntroducirId.setVisible(true);
        BotonIntroducirId.setVisible(true);
        jEditorPane1.setVisible(true);
        jEditorPane1.setContentType("text/html");

        jEditorPane1.setSize(550, 70);
        TextIntroducirId.setSize(150, 30);
        BotonIntroducirId.setSize(100, 30);

        jEditorPane1.setText("<html><br>Por favor introduce el id del ticket asociado a tu aparcamiento</br><br>Formato (1)</br></html>");
        jEditorPane1.setEditable(false);
        jEditorPane1.setBounds(50, 10, jEditorPane1.getWidth(), jEditorPane1.getHeight());
        TextIntroducirId.setBounds(180, 250, TextIntroducirId.getWidth(), TextIntroducirId.getHeight());
        BotonIntroducirId.setBounds(330, 250, BotonIntroducirId.getWidth(), BotonIntroducirId.getHeight());

        actualizarTabla();

        pantallaRetirar.setLocationRelativeTo(null);
    }

    public void ventanaPagar() {
        //ventana para pagar el importe del ticket
        pantallaPagar = new JFrame();
        pantallaPagar.setLayout(null);
        pantallaPagar.setResizable(false);
        pantallaPagar.setVisible(true);
        LabelHacienda.setVisible(true);
        pantallaPagar.setSize(500, 500);
        pantallaPagar.setContentPane(LabelHacienda);
        LabelHacienda.setSize(500, 500);
        LabelHacienda.add(TextPagar);
        LabelHacienda.add(BotonPagar);
        LabelHacienda.add(LabelPagar4);
        LabelHacienda.add(EditorPane2);

        TextPagar.setVisible(true);
        BotonPagar.setVisible(true);
        EditorPane2.setVisible(true);
        LabelPagar4.setVisible(true);
        EditorPane2.setContentType("text/html");

        TextPagar.setSize(150, 30);
        BotonPagar.setSize(100, 30);
        EditorPane2.setSize(300, 90);
        LabelPagar4.setSize(300, 20);

        //AQUI FALTA QUE DIGA EL COCHE Y EL TIEMPO Y EL IMPORTE
        EditorPane2.setText("<html><br>Vehiculo con matricula (" + ticketSeleccionado.getMatricula() + ")</br>"
                + "<br>Estacionado durante (" + app.calcularTiempoTranscurrido(ticketSeleccionado) + ") minutos</br>"
                + "<br>El importe correspondiente es (" + app.totalDineroDevolver(app.calcularTiempoTranscurrido(ticketSeleccionado)) + " €)</br></html>");
        EditorPane2.setEditable(false);
        LabelPagar4.setText("Hacienda somos todos");

        EditorPane2.setBounds(20, 10, EditorPane2.getWidth(), EditorPane2.getHeight());
        TextPagar.setBounds(20, 360, TextPagar.getWidth(), TextPagar.getHeight());
        BotonPagar.setBounds(170, 360, BotonPagar.getWidth(), BotonPagar.getHeight());
        LabelPagar4.setBounds(10, 430, LabelPagar4.getWidth(), LabelPagar4.getHeight());

        actualizarTabla();

        pantallaPagar.setLocationRelativeTo(null);
    }

    public boolean validoNumeros(String valido) {
        //metodo para verificar que solo haya números en un campo de texto
        //devuelve true si solo hay numeros, en caso contrario devuelve false
        String texto = valido.trim().toLowerCase();
        double validador;
        try {
            validador = Double.parseDouble(texto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validoDouble(String valido) {
        //metodo para verificar que solo haya números en un campo de texto
        //devuelve true si solo hay numeros, en caso contrario devuelve false
        String texto = valido.trim().toLowerCase();
        int validador;
        try {
            validador = Integer.parseInt(texto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validoNulos(String valido) {
        //metodo para validar si el usuario ha introducido algo por teclado
        //devuelve true si se ha introducido algo
        //devuelve false si lo introducido es un conjunto vacio
        if (valido.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validoMatricula(String valido) {
        //metodo para validar el formato de la matricula
        //va a devolver true si la matricula tiene el formato necesario (4 numeros, un guion, 3 letras)
        //en caso contrario devuelve false
        return valido.matches("\\d{4}-[a-zA-Z]{3}");
    }

    //muestra la tabla con el plano del parking
    private void mostrarTabla() {
        //creamos el JTable con el modelo
        tabla = new JTable(modeloTabla);
        tabla.setSize(ScrollTabla.getWidth(), ScrollTabla.getHeight());
        tabla.setDefaultRenderer(Object.class, new ColorCeldaRenderer());
        //ocultamos los titulos de las columnas
        tabla.getTableHeader().setVisible(false);

        //damos al scroll el JTable para que se vea
        this.ScrollTabla.setViewportView(tabla);
        this.repaint();
    }

    //NO SE QUE HACE ESTO PERO CAMBIA LOS COLORES NO TOCAR
    static class ColorCeldaRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value == null) {
                cell.setBackground(Color.WHITE);
                cell.setForeground(Color.BLACK);
                return cell;
            }

            // Convertir el valor a número
            try {
                int numero = Integer.parseInt(value.toString());
                if (numero == 0) {
                    cell.setBackground(Color.GREEN);  // 📌 Rojo si es 0
                    cell.setForeground(Color.WHITE);
                } else {
                    cell.setBackground(Color.RED); // 📌 Verde si NO es 0
                    cell.setForeground(Color.BLACK);
                }
            } catch (NumberFormatException e) {
                cell.setBackground(Color.WHITE); // Si no es número, fondo blanco
                cell.setForeground(Color.BLACK);
            }

            return cell;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelParquimetroPrincipal = new javax.swing.JLabel();
        BotonAparcarCoche = new javax.swing.JButton();
        BotonRetirarCoche = new javax.swing.JButton();
        LabelPlaceHolderTabla = new javax.swing.JLabel();
        TextAñadirMatricula = new javax.swing.JTextField();
        LabelAñadirMatricula = new javax.swing.JLabel();
        BotonAñadirMatricula = new javax.swing.JButton();
        LabelIntroducirId = new javax.swing.JLabel();
        BotonIntroducirId = new javax.swing.JButton();
        TextIntroducirId = new javax.swing.JTextField();
        LabelPagar = new javax.swing.JLabel();
        TextPagar = new javax.swing.JTextField();
        BotonPagar = new javax.swing.JButton();
        LabelHacienda = new javax.swing.JLabel();
        LabelPagar2 = new javax.swing.JLabel();
        LabelPagar3 = new javax.swing.JLabel();
        LabelPagar4 = new javax.swing.JLabel();
        LabelAparcamiento = new javax.swing.JLabel();
        LabelRetirarCoche = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        EditorPane2 = new javax.swing.JEditorPane();
        ScrollTabla = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(365, 755));
        setResizable(false);

        LabelParquimetroPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resourcesFotos/Parquimetro_Parking3(350x720).png"))); // NOI18N

        BotonAparcarCoche.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BotonAparcarCoche.setText("Aparcar coche");
        BotonAparcarCoche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAparcarCocheActionPerformed(evt);
            }
        });

        BotonRetirarCoche.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BotonRetirarCoche.setText("Retirar coche");
        BotonRetirarCoche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRetirarCocheActionPerformed(evt);
            }
        });

        LabelPlaceHolderTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resourcesFotos/Victor2(500x500).png"))); // NOI18N

        TextAñadirMatricula.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TextAñadirMatricula.setText("Introduce aqui tu matricula");
        TextAñadirMatricula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextAñadirMatriculaFocusGained(evt);
            }
        });

        LabelAñadirMatricula.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        LabelAñadirMatricula.setText("jLabel1");

        BotonAñadirMatricula.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BotonAñadirMatricula.setText("Añadir");
        BotonAñadirMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAñadirMatriculaActionPerformed(evt);
            }
        });

        LabelIntroducirId.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        LabelIntroducirId.setText("jLabel1");

        BotonIntroducirId.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BotonIntroducirId.setText("Aceptar");
        BotonIntroducirId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonIntroducirIdActionPerformed(evt);
            }
        });

        TextIntroducirId.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TextIntroducirId.setText("Introduce tu id aqui");
        TextIntroducirId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextIntroducirIdFocusGained(evt);
            }
        });

        LabelPagar.setBackground(new java.awt.Color(0, 0, 0));
        LabelPagar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        LabelPagar.setForeground(new java.awt.Color(0, 0, 0));
        LabelPagar.setText("jLabel1");
        LabelPagar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TextPagar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TextPagar.setText("Pague aqui");
        TextPagar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextPagarFocusGained(evt);
            }
        });

        BotonPagar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BotonPagar.setText("Pagar");
        BotonPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonPagarActionPerformed(evt);
            }
        });

        LabelHacienda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resourcesFotos/Hacienda(500x500).png"))); // NOI18N

        LabelPagar2.setBackground(new java.awt.Color(0, 0, 0));
        LabelPagar2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        LabelPagar2.setForeground(new java.awt.Color(0, 0, 0));
        LabelPagar2.setText("jLabel1");
        LabelPagar2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LabelPagar3.setBackground(new java.awt.Color(0, 0, 0));
        LabelPagar3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        LabelPagar3.setForeground(new java.awt.Color(0, 0, 0));
        LabelPagar3.setText("jLabel1");
        LabelPagar3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LabelPagar4.setBackground(new java.awt.Color(0, 0, 0));
        LabelPagar4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        LabelPagar4.setForeground(new java.awt.Color(0, 0, 0));
        LabelPagar4.setText("jLabel1");

        LabelAparcamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resourcesFotos/Aparcamiento(500x500).png"))); // NOI18N

        LabelRetirarCoche.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resourcesFotos/RetirarCoche2(600x360).png"))); // NOI18N

        jEditorPane1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(jEditorPane1);

        EditorPane2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(EditorPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(LabelPlaceHolderTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(LabelParquimetroPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(BotonAparcarCoche, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BotonRetirarCoche, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TextAñadirMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelAñadirMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonAñadirMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotonIntroducirId, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextIntroducirId, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelIntroducirId, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LabelPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(LabelPagar2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(LabelRetirarCoche, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BotonPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TextPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LabelPagar3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(LabelPagar4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(211, 211, 211))
                                    .addComponent(LabelAparcamiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(ScrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(304, 304, 304)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LabelHacienda, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(157, 157, 157))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(LabelPlaceHolderTabla))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(ScrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(78, 78, 78)
                .addComponent(LabelIntroducirId)
                .addGap(69, 69, 69)
                .addComponent(TextIntroducirId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonIntroducirId)
                    .addComponent(LabelHacienda))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPagar)
                    .addComponent(LabelPagar2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelPagar3))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonPagar)
                    .addComponent(LabelPagar4))
                .addGap(166, 166, 166)
                .addComponent(LabelRetirarCoche)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BotonAparcarCoche)
                        .addGap(29, 29, 29)
                        .addComponent(BotonRetirarCoche)
                        .addGap(48, 48, 48)
                        .addComponent(LabelAñadirMatricula)
                        .addGap(30, 30, 30)
                        .addComponent(TextAñadirMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(BotonAñadirMatricula))
                    .addComponent(LabelParquimetroPrincipal)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(992, 992, 992)
                .addComponent(LabelAparcamiento)
                .addGap(411, 411, 411))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonAparcarCocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAparcarCocheActionPerformed
        // TODO add your handling code here:
        ventanaAparcar();
    }//GEN-LAST:event_BotonAparcarCocheActionPerformed

    private void BotonRetirarCocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRetirarCocheActionPerformed
        // TODO add your handling code here:
        ventanaRetirar();
    }//GEN-LAST:event_BotonRetirarCocheActionPerformed

    private void TextAñadirMatriculaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextAñadirMatriculaFocusGained
        // TODO add your handling code here:
        TextAñadirMatricula.selectAll();
    }//GEN-LAST:event_TextAñadirMatriculaFocusGained

    private void TextIntroducirIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextIntroducirIdFocusGained
        // TODO add your handling code here:
        TextIntroducirId.selectAll();
    }//GEN-LAST:event_TextIntroducirIdFocusGained

    private void BotonAñadirMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAñadirMatriculaActionPerformed
        // TODO add your handling code here:
        if (!validoNulos(TextAñadirMatricula.getText().trim().toUpperCase()) || !validoMatricula(TextAñadirMatricula.getText().trim().toUpperCase())) {
            JOptionPane.showMessageDialog(null, "Asegurate de introducir la matricula correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ticket = app.asignarPlaza(app.encontrarEspacioLibre(), TextAñadirMatricula.getText().trim().toUpperCase());
        JOptionPane.showMessageDialog(null, "Tu ticket es: " + ticket.toString() + " NO LO PIERDAS", "TICKET", JOptionPane.DEFAULT_OPTION);

        ticketSeleccionado = ticket;
        System.out.println("ticket seleccionado" + ticketSeleccionado.toString());

        app.mostrarPlano();
        actualizarTabla();

    }//GEN-LAST:event_BotonAñadirMatriculaActionPerformed

    private void BotonIntroducirIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonIntroducirIdActionPerformed
        // TODO add your handling code here:
        if (!validoNulos(TextIntroducirId.getText().trim()) || !validoNumeros(TextIntroducirId.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Asegurate de introducir el id correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            for (Ticket ticket2 : app.getListaTickets()) {
                if (ticket2.getId() == Integer.parseInt(TextIntroducirId.getText().trim())) {
                    ticketSeleccionado = ticket2;
                }
            }
            System.out.println("ticket seleccionado " + ticketSeleccionado.toString());
            ventanaPagar();
        }
    }//GEN-LAST:event_BotonIntroducirIdActionPerformed

    private void TextPagarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextPagarFocusGained
        // TODO add your handling code here:
        TextPagar.selectAll();
    }//GEN-LAST:event_TextPagarFocusGained

    private void BotonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonPagarActionPerformed
        // TODO add your handling code here:

        if (!validoNulos(TextPagar.getText().trim()) || !validoNumeros(TextPagar.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Asegurate de introducir el importe correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (app.totalDineroDevolver(app.calcularTiempoTranscurrido(ticketSeleccionado)) > Double.parseDouble(TextPagar.getText().trim())) {
            JOptionPane.showMessageDialog(null, "El dinero introducido no alcanza el importe", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        app.devolverCambio(TextPagar.getText().trim());
        JOptionPane.showMessageDialog(null, "Has introducido " + TextPagar.getText().trim() + " €, la vuelta correspondiente es: " + app.devolverCambio(TextPagar.getText().trim()).toString(), "VUELTAS", JOptionPane.PLAIN_MESSAGE);
        app.liberarPlaza(ticketSeleccionado);
        actualizarTabla();
    }//GEN-LAST:event_BotonPagarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Terminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Terminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Terminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Terminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Terminal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAparcarCoche;
    private javax.swing.JButton BotonAñadirMatricula;
    private javax.swing.JButton BotonIntroducirId;
    private javax.swing.JButton BotonPagar;
    private javax.swing.JButton BotonRetirarCoche;
    private javax.swing.JEditorPane EditorPane2;
    private javax.swing.JLabel LabelAparcamiento;
    private javax.swing.JLabel LabelAñadirMatricula;
    private javax.swing.JLabel LabelHacienda;
    private javax.swing.JLabel LabelIntroducirId;
    private javax.swing.JLabel LabelPagar;
    private javax.swing.JLabel LabelPagar2;
    private javax.swing.JLabel LabelPagar3;
    private javax.swing.JLabel LabelPagar4;
    private javax.swing.JLabel LabelParquimetroPrincipal;
    private javax.swing.JLabel LabelPlaceHolderTabla;
    private javax.swing.JLabel LabelRetirarCoche;
    private javax.swing.JScrollPane ScrollTabla;
    private javax.swing.JTextField TextAñadirMatricula;
    private javax.swing.JTextField TextIntroducirId;
    private javax.swing.JTextField TextPagar;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
