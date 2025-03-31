package ClasesPrincipales;

import java.awt.Color;
import java.awt.Component;
import java.lang.reflect.Array;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private JFrame pantallaAparcar;
    private JFrame pantallaRetirar;
    private JFrame pantallaPagar;
    private JFrame pantallaPagarSpinners;
    private Maquina app = new Maquina();
    private Ticket ticketSeleccionado;
    private Ticket ticket;
    private JTable tabla;//control para mostrar la matriz
    private DefaultTableModel modeloTabla;//contenedor de la matriz de enteros
    private Deposito deposito = new Deposito();
    private SpinnerNumberModel spinner = new SpinnerNumberModel(0, 0, 100, 1);  // Billetes de 20

    private JSpinner spinnerBilletes20 = new JSpinner(spinner);
    private JSpinner spinnerBilletes10 = new JSpinner(spinner);
    private JSpinner spinnerBilletes5 = new JSpinner(spinner);
    private JSpinner spinnerMonedas2 = new JSpinner(spinner);
    private JSpinner spinnerMonedas1 = new JSpinner(spinner);
    private JSpinner spinnerMonedas05 = new JSpinner(spinner);
    private JSpinner spinnerMonedas02 = new JSpinner(spinner);
    private JSpinner spinnerMonedas01 = new JSpinner(spinner);
    private JSpinner spinnerMonedas005 = new JSpinner(spinner);
    private JTextField labelBilletes20 = new JTextField("Billetes de 20:");
    private JTextField labelBilletes10 = new JTextField("Billetes de 10:");
    private JTextField labelBilletes5 = new JTextField("Billetes de 5:");
    private JTextField labelMonedas2 = new JTextField("Monedas de 2:");
    private JTextField labelMonedas1 = new JTextField("Monedas de 1:");
    private JTextField labelMonedas05 = new JTextField("Monedas de 0.5:");
    private JTextField labelMonedas02 = new JTextField("Monedas de 0.2:");
    private JTextField labelMonedas01 = new JTextField("Monedas de 0.1:");
    private JTextField labelMonedas005 = new JTextField("Monedas de 0.05:");
    private JTextField labelTotal = new JTextField("Total: 0.00 € ");

    private int[] arraySpinner = new int[9];

    private double total;

    /**
     * Constructor por defecto de la clase Terminal. Este constructor inicializa
     * los componentes de la interfaz de usuario, establece la visibilidad de
     * algunos elementos, y configura la tabla para mostrar el plano del
     * parking. Además, prepara la ventana principal del parquimetro y ajusta la
     * ubicación de la ventana en la pantalla.
     */
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

        spinnerBilletes20.addChangeListener(changeListener);

        spinnerBilletes10.addChangeListener(changeListener);

        spinnerBilletes5.addChangeListener(changeListener);

        spinnerMonedas2.addChangeListener(changeListener);

        spinnerMonedas1.addChangeListener(changeListener);

        spinnerMonedas05.addChangeListener(changeListener);

        spinnerMonedas02.addChangeListener(changeListener);

        spinnerMonedas01.addChangeListener(changeListener);

        spinnerMonedas005.addChangeListener(changeListener);

        total = 0;
        actualizarTabla();
        mostrarTabla();

        ventanaParquimetro();
        setLocation();
    }

    //metodo para actualizar la matriz basado en la matriz producida en maquina 
    public int indiceColumnaActual = 0;

    /**
     * Actualiza el contenido de la tabla que muestra el plano del parking con
     * la nueva matriz de datos.
     *
     * Este método realiza los siguientes pasos: Obtiene la nueva matriz de
     * datos del parking desde la clase app usando el método getPlano(). Limpia
     * el modelo de la tabla (eliminando filas y columnas anteriores). Establece
     * un formato específico para mostrar la tabla con 10 columnas por fila y
     * separando cada 2 filas de datos con una fila vacía. Se configuran las
     * columnas de la tabla y se añaden las filas correspondientes a las plazas
     * del parking, considerando que cada planta tiene una matriz de datos con
     * filas de 20 columnas, pero se divide en bloques de 10 columnas para la
     * visualización.
     */
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

    /**
     * Crea y muestra la ventana principal del parquímetro, donde se visualiza
     * la interfaz para aparcar o retirar un coche del parking, junto con una
     * tabla que muestra las plazas disponibles.
     *
     * La ventana contiene: Una label (LabelParquimetroPrincipal) para el
     * contenedor principal que organiza todos los componentes. Un botón
     * (BotonAparcarCoche)que permite al usuario aparcar un coche en el parking.
     * Un botón (BotonRetirarCoche) que permite retirar un coche previamente
     * aparcado. Un scrollPane (ScrollTabla) que contiene una tabla que muestra
     * el estado actual del parking.
     *
     * La interfaz de la ventana contiene: Los botones para aparcar y retirar el
     * coche. La tabla del plano del parking.
     *
     * El método mostrarTabla() se utiliza para mostrar el plano actualizado del
     * parking. actualizarTabla() asegura que la tabla se actualice con los
     * últimos cambios.
     */
    public void ventanaParquimetro() {
        //ventana principal del parquimetro
        setSize(350, 720);
        LabelParquimetroPrincipal.setSize(350, 720);

        setContentPane(LabelParquimetroPrincipal);

        LabelParquimetroPrincipal.add(BotonAparcarCoche);
        LabelParquimetroPrincipal.add(BotonRetirarCoche);

        LabelParquimetroPrincipal.add(ScrollTabla);

        BotonAparcarCoche.setSize(250, 40);
        BotonRetirarCoche.setSize(250, 40);

        ScrollTabla.setSize(237, 175);

        BotonAparcarCoche.setBounds(50, 500, BotonAparcarCoche.getWidth(), BotonAparcarCoche.getHeight());
        BotonRetirarCoche.setBounds(50, 550, BotonRetirarCoche.getWidth(), BotonRetirarCoche.getHeight());

        ScrollTabla.setBounds(56, 272, ScrollTabla.getWidth(), ScrollTabla.getHeight());
        ScrollTabla.setVisible(true);
        actualizarTabla();
        mostrarTabla();
    }

    /**
     * Crea y muestra una ventana para aparcar un coche en el parking.
     *
     * Esta ventana permite al usuario introducir la matrícula de su vehículo
     * para aparcarlo en una plaza disponible del parking. El sistema valida que
     * la matrícula esté correctamente introducida antes de asignar una plaza.
     *
     * La ventana incluye: Un campo de texto (TextAñadirMatricula) para ingresar
     * la matrícula del vehículo. Un botón (BotonAñadirMatricula) para confirmar
     * la introducción de la matrícula y asignar la plaza. - Una etiqueta
     * (LabelAñadirMatricula) que muestra el formato correcto de la matrícula.
     *
     * Además, la ventana también actualiza el plano del parking para mostrar el
     * estado actual de las plazas.
     */
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
        TextAñadirMatricula.setText("Añade tu matricula");

        LabelAñadirMatricula.setBounds(30, 20, LabelAñadirMatricula.getWidth(), LabelAñadirMatricula.getHeight());
        TextAñadirMatricula.setBounds(100, 240, TextAñadirMatricula.getWidth(), TextAñadirMatricula.getHeight());
        BotonAñadirMatricula.setBounds(300, 240, BotonAñadirMatricula.getWidth(), BotonAñadirMatricula.getHeight());

        actualizarTabla();
        pantallaAparcar.setLocationRelativeTo(null);

    }

    /**
     * Crea y muestra una ventana para retirar un coche del parking.
     *
     * Esta ventana permite al usuario introducir el ID de un ticket asociado a
     * un vehículo estacionado para retirar su coche. El usuario debe introducir
     * el ID en el campo de texto, y hacer clic en el botón.
     *
     * La ventana incluye: Un campo de texto (TextIntroducirId) para que el
     * usuario ingrese el ID del ticket. Un botón (BotonIntroducirId) para
     * confirmar la introducción del ID y proceder con la retirada. Un panel de
     * texto (jEditorPane1) que muestra instrucciones sobre cómo introducir el
     * ID. Una etiqueta (LabelIntroducirId) que acompaña al campo de texto.
     *
     * La ventana también muestra información relevante sobre el formato
     * correcto del ID a introducir, y permite al usuario proceder con la
     * retirada del coche tras la validación del ID.
     */
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

        TextIntroducirId.setText("Introduce el ID");
        jEditorPane1.setText("<html><br>Por favor introduce el id del ticket asociado a tu aparcamiento</br><br>Formato (1)</br></html>");
        jEditorPane1.setEditable(false);
        jEditorPane1.setBounds(50, 10, jEditorPane1.getWidth(), jEditorPane1.getHeight());
        TextIntroducirId.setBounds(180, 250, TextIntroducirId.getWidth(), TextIntroducirId.getHeight());
        BotonIntroducirId.setBounds(330, 250, BotonIntroducirId.getWidth(), BotonIntroducirId.getHeight());

        actualizarTabla();

        pantallaRetirar.setLocationRelativeTo(null);
    }

    /**
     * Crea y muestra una ventana para el pago del importe correspondiente al
     * ticket del vehículo estacionado.
     *
     * Esta ventana enseña información sobre el vehículo, el tiempo que ha
     * estado estacionado y el importe a pagar. También permite introducir el
     * importe mediante un campo de texto y un botón para realizar el pago.
     *
     * La ventana incluye: Un campo de texto (TextPagar) para que el usuario
     * introduzca el importe a pagar. Un botón (BotonPagar) para procesar el
     * pago. Un panel de texto (EditorPane2) que muestra información detallada
     * sobre el vehículo y el importe. Un label (LabelPagar4) con un mensaje
     * adicional.
     *
     * El contenido de la ventana se adapta con los datos del ticket
     * seleccionado.
     */
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

        EditorPane2.setText("<html><br>Vehiculo con matricula (" + ticketSeleccionado.getMatricula() + ")</br>"
                + "<br>Estacionado durante (" + app.calcularTiempoTranscurrido(ticketSeleccionado) + ") minutos</br>"
                + "<br>El importe correspondiente es (" + app.totalDineroDevolver(app.calcularTiempoTranscurrido(ticketSeleccionado)) + " €)</br></html>");
        EditorPane2.setEditable(false);
        TextPagar.setText("Paga aqui");
        LabelPagar4.setText("Hacienda somos todos");

        EditorPane2.setBounds(20, 10, EditorPane2.getWidth(), EditorPane2.getHeight());
        TextPagar.setBounds(20, 360, TextPagar.getWidth(), TextPagar.getHeight());
        BotonPagar.setBounds(170, 360, BotonPagar.getWidth(), BotonPagar.getHeight());
        LabelPagar4.setBounds(10, 430, LabelPagar4.getWidth(), LabelPagar4.getHeight());
        actualizarTabla();
        pantallaPagar.setLocationRelativeTo(null);
    }

    /**
     * Método que crea y configura la ventana para pagar, utilizando spinners
     * para que el usuario seleccione la cantidad de billetes y monedas que
     * desea introducir. Además, muestra la información sobre el vehículo y el
     * monto total a pagar, y permite realizar el pago al hacer clic en el
     * botón.
     */
    public void ventanaPagarSpinners() {
        pantallaPagarSpinners = new JFrame();
        pantallaPagarSpinners.setLayout(null);
        pantallaPagarSpinners.setResizable(false);
        pantallaPagarSpinners.setVisible(true);
        LabelPagarSpinners.setVisible(true);
        pantallaPagarSpinners.setSize(600, 600);
        pantallaPagarSpinners.setContentPane(LabelPagarSpinners);
        LabelPagarSpinners.setSize(600, 600);

        LabelPagarSpinners.add(EditorPane2);

        LabelPagarSpinners.add(labelBilletes20);
        LabelPagarSpinners.add(spinnerBilletes20);

        LabelPagarSpinners.add(labelBilletes10);
        LabelPagarSpinners.add(spinnerBilletes10);

        LabelPagarSpinners.add(labelBilletes5);
        LabelPagarSpinners.add(spinnerBilletes5);

        LabelPagarSpinners.add(labelMonedas2);
        LabelPagarSpinners.add(spinnerMonedas2);

        LabelPagarSpinners.add(labelMonedas1);
        LabelPagarSpinners.add(spinnerMonedas1);

        LabelPagarSpinners.add(labelMonedas05);
        LabelPagarSpinners.add(spinnerMonedas05);

        LabelPagarSpinners.add(labelMonedas02);
        LabelPagarSpinners.add(spinnerMonedas02);

        LabelPagarSpinners.add(labelMonedas01);
        LabelPagarSpinners.add(spinnerMonedas01);

        LabelPagarSpinners.add(labelMonedas005);
        LabelPagarSpinners.add(spinnerMonedas005);

        LabelPagarSpinners.add(labelTotal);
        LabelPagarSpinners.add(BotonPagar);
        labelTotal.setFont(LabelAñadirMatricula.getFont());

        BotonPagar.setVisible(true);

        EditorPane2.setVisible(true);
        EditorPane2.setContentType("text/html");

        LabelPagarSpinners.add(labelTotal); // Agregar el JLabel con el total

        EditorPane2.setSize(300, 90);
        BotonPagar.setSize(100, 30);

        spinnerBilletes20.setBounds(155, 230, 50, 30);
        spinnerBilletes10.setBounds(275, 230, 50, 30);
        spinnerBilletes5.setBounds(395, 230, 50, 30);
        spinnerMonedas2.setBounds(155, 300, 50, 30);
        spinnerMonedas1.setBounds(275, 300, 50, 30);
        spinnerMonedas05.setBounds(395, 300, 50, 30);
        spinnerMonedas02.setBounds(155, 370, 50, 30);
        spinnerMonedas01.setBounds(275, 370, 50, 30);
        spinnerMonedas005.setBounds(395, 370, 50, 30);

        labelBilletes20.setBounds(155, 205, 105, 25);
        labelBilletes10.setBounds(275, 205, 105, 25);
        labelBilletes5.setBounds(395, 205, 105, 25);
        labelMonedas2.setBounds(155, 275, 105, 25);
        labelMonedas1.setBounds(275, 275, 105, 25);
        labelMonedas05.setBounds(395, 275, 105, 25);
        labelMonedas02.setBounds(155, 345, 105, 25);
        labelMonedas01.setBounds(275, 345, 105, 25);
        labelMonedas005.setBounds(395, 345, 105, 25);

        labelBilletes20.setEditable(false);
        labelBilletes10.setEditable(false);
        labelBilletes5.setEditable(false);
        labelMonedas2.setEditable(false);
        labelMonedas1.setEditable(false);
        labelMonedas05.setEditable(false);
        labelMonedas02.setEditable(false);
        labelMonedas01.setEditable(false);
        labelMonedas005.setEditable(false);

        labelTotal.setBounds(260, 420, 105, 25);
        BotonPagar.setBounds(250, 460, BotonPagar.getWidth(), BotonPagar.getHeight());

        EditorPane2.setBounds(150, 10, EditorPane2.getWidth(), EditorPane2.getHeight());

        EditorPane2.setText("<html><br>Vehiculo con matricula (" + ticketSeleccionado.getMatricula() + ")</br>"
                + "<br>Estacionado durante (" + app.calcularTiempoTranscurrido(ticketSeleccionado) + ") minutos</br>"
                + "<br>El importe correspondiente es (" + app.totalDineroDevolver(app.calcularTiempoTranscurrido(ticketSeleccionado)) + " €)</br></html>");
        EditorPane2.setEditable(false);

        // Hacer visible la ventana
        LabelPagarSpinners.setVisible(true);

        actualizarTabla();
        pantallaPagarSpinners.setLocationRelativeTo(null);

    }

    protected ChangeListener changeListener = new ChangeListener() {
        @Override
        /**
         * Método que actualiza el total de dinero introducido en base a las
         * cantidades seleccionadas por el usuario en los JSpinners. Este método
         * recalcula el total de dinero y actualiza el JLabel que muestra el
         * monto total con el formato adecuado.
         *
         * @param e El evento ChangeEvent generado al cambiar el valor de
         * cualquiera de los spinners. Este parámetro se usa para realizar la
         * actualización del total de dinero.
         */
        public void stateChanged(ChangeEvent e) {
            // Obtener los valores de cada JSpinner
            int billetes20 = (Integer) spinnerBilletes20.getValue();
            int billetes10 = (Integer) spinnerBilletes10.getValue();
            int billetes5 = (Integer) spinnerBilletes5.getValue();
            int monedas2 = (Integer) spinnerMonedas2.getValue();
            int monedas1 = (Integer) spinnerMonedas1.getValue();
            int monedas05 = (Integer) spinnerMonedas05.getValue();
            int monedas02 = (Integer) spinnerMonedas02.getValue();
            int monedas01 = (Integer) spinnerMonedas01.getValue();
            int monedas005 = (Integer) spinnerMonedas005.getValue();

            // Agregamos los valores de los spinner en un array para actualizar 
            // los valores en depósito
            arraySpinner[0] = billetes20;
            arraySpinner[1] = billetes10;
            arraySpinner[2] = billetes5;
            arraySpinner[3] = monedas2;
            arraySpinner[4] = monedas1;
            arraySpinner[5] = monedas05;
            arraySpinner[6] = monedas02;
            arraySpinner[7] = monedas01;
            arraySpinner[8] = monedas005;

            // Calcular el total
            total = billetes20 * 20.0 + billetes10 * 10.0 + billetes5 * 5.0
                    + monedas2 * 2.0 + monedas1 * 1.0 + monedas05 * 0.5
                    + monedas02 * 0.2 + monedas01 * 0.1 + monedas005 * 0.05;

            // Actualizar el JLabel con el total formateado
            labelTotal.setText(String.format("Total: %.2f € ", total));
        }
    };

    /**
     * Verifica si el valor introducido contiene solo números. Este método
     * intenta convertir el texto introducido en un número. El método también
     * elimina los espacios al inicio y al final del texto, y convierte el texto
     * a minúsculas.
     *
     * @param valido El texto introducido por el usuario para verificar si es un
     * número.
     * @return true si el texto contiene solo números (puede ser decimal), false
     * si el texto contiene caracteres no numéricos.
     */
    public boolean validoNumeros(String valido) {
        //metodo para verificar que solo haya números en un campo de texto
        //devuelve true si solo hay numeros, en caso contrario devuelve false
        String texto = valido.trim().toLowerCase();
        double validador;
        try {
            validador = Double.parseDouble(texto);  // Intentamos convertir el texto a un número
            return true;
        } catch (Exception e) {
            return false;  // Si no se puede convertir, significa que no es un número válido
        }
    }

    /**
     * Valida si el usuario ha introducido un valor no vacío. Este método
     * verifica si el string proporcionado no está vacío.
     *
     * @param valido El string a validar, que representa el texto introducido
     * por el usuario.
     * @return true si el string no está vacío, false si el string está vacío.
     */
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

    /**
     * Valida el formato de una matrícula de coche. Este método verifica si el
     * string proporcionado cumple con el formato esperado para una matrícula.
     * El formato válido es: 4 dígitos numéricos, seguidos de un guion, y luego
     * 3 letras (mayúsculas o minúsculas).
     *
     * @param valido El string a validar, que representa la matrícula.
     * @return true si el formato de la matrícula es correcto, false en caso
     * contrario.
     */
    public boolean validoMatricula(String valido) {
        // metodo para validar el formato de la matricula
        // va a devolver true si la matricula tiene el formato necesario (4 numeros, un guion, 3 letras)
        // en caso contrario devuelve false
        return valido.matches("\\d{4}-[a-zA-Z]{3}");
    }

    /**
     * Muestra la tabla que representa el plano del parking. Este método crea
     * una nueva instancia de JTable utilizando el modelo de datos modeloTabla.
     * Ajusta el tamaño de la tabla al tamaño del componente ScrollTabla, asigna
     * un renderizador personalizado para cambiar el color de las celdas basado
     * en su valor, y oculta los títulos de las columnas.
     *
     * Finalmente, se establece la tabla dentro del JScrollPane para que sea
     * visible en la interfaz de usuario. Este método es responsable de
     * actualizar la visualización del plano del parking en la interfaz.
     */
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

    /**
     * Esta clase extiende DefaultTableCellRenderer para cambiar el color de las
     * celdas en una tabla de acuerdo con su valor.
     *
     * Se utiliza para mostrar valores en una tabla con colores de fondo y texto
     * que cambian dependiendo del valor contenido en cada celda. Si el valor de
     * la celda es 0, el fondo será verde y el texto blanco. Si el valor de la
     * celda es != 0, el fondo será rojo y el texto negro. Si el valor no es un
     * número, el fondo será blanco y el texto negro.
     */
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
        TextAñadirMatricula = new javax.swing.JTextField();
        LabelAñadirMatricula = new javax.swing.JLabel();
        BotonAñadirMatricula = new javax.swing.JButton();
        LabelIntroducirId = new javax.swing.JLabel();
        BotonIntroducirId = new javax.swing.JButton();
        TextIntroducirId = new javax.swing.JTextField();
        TextPagar = new javax.swing.JTextField();
        BotonPagar = new javax.swing.JButton();
        LabelHacienda = new javax.swing.JLabel();
        LabelPagar4 = new javax.swing.JLabel();
        LabelAparcamiento = new javax.swing.JLabel();
        LabelRetirarCoche = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        EditorPane2 = new javax.swing.JEditorPane();
        ScrollTabla = new javax.swing.JScrollPane();
        LabelPagarSpinners = new javax.swing.JLabel();

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

        LabelPagarSpinners.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resourcesFotos/Pagar(600x600).png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelParquimetroPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotonAparcarCoche, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelAñadirMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextAñadirMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonAñadirMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextIntroducirId, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonIntroducirId, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonRetirarCoche, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelIntroducirId, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelPagar4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelHacienda, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(LabelRetirarCoche, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(LabelAparcamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(LabelPagarSpinners, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LabelParquimetroPrincipal)
                        .addGap(0, 94, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelPagarSpinners)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BotonAparcarCoche)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BotonRetirarCoche)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LabelAñadirMatricula)
                                .addGap(18, 18, 18)
                                .addComponent(TextAñadirMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BotonAñadirMatricula)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LabelIntroducirId)
                                .addGap(18, 18, 18)
                                .addComponent(TextIntroducirId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BotonIntroducirId)
                                .addGap(18, 18, 18)
                                .addComponent(TextPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BotonPagar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LabelPagar4)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ScrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelHacienda)
                            .addComponent(LabelRetirarCoche)
                            .addComponent(LabelAparcamiento))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Maneja el evento cuando se hace clic en el botón para aparcar un coche en
     * el parking. Abre la ventana correspondiente para permitir al usuario
     * aparcar un coche, activando la interfaz para realizar el aparcamiento.
     *
     * @param evt El evento de acción generado cuando el usuario hace clic en el
     * botón para aparcar el coche.
     */
    private void BotonAparcarCocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAparcarCocheActionPerformed

        // Llama al método que muestra la ventana para aparcar un coche
        ventanaAparcar();
    }//GEN-LAST:event_BotonAparcarCocheActionPerformed

    /**
     * Maneja el evento cuando se hace clic en el botón para retirar un coche
     * del parking. Este método abre la ventana para permitir al usuario retirar
     * un coche, activando la interfaz para realizar el proceso de retirada.
     *
     * @param evt El evento de acción generado cuando el usuario hace clic en el
     * botón para retirar el coche.
     */
    private void BotonRetirarCocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRetirarCocheActionPerformed
        // Llama al método que muestra la ventana para retirar un coche
        ventanaRetirar();
    }//GEN-LAST:event_BotonRetirarCocheActionPerformed

    private void TextAñadirMatriculaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextAñadirMatriculaFocusGained
        // TODO add your handling code here:
        TextAñadirMatricula.selectAll();
    }//GEN-LAST:event_TextAñadirMatriculaFocusGained

    /**
     * Maneja el evento cuando el campo de texto para introducir el ID obtiene
     * el foco. Este método selecciona todo el texto del campo para facilitar la
     * edición asegurándose de que al hacer clic en el campo, todo el texto sea
     * seleccionado.
     *
     * @param evt El evento de enfoque que se genera cuando el campo de texto
     * obtiene el foco.
     */
    private void TextIntroducirIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextIntroducirIdFocusGained

        TextIntroducirId.selectAll();
    }//GEN-LAST:event_TextIntroducirIdFocusGained

    /**
     * Maneja el evento cuando el usuario hace clic en el botón para añadir la
     * matrícula de un coche al parking. Este método valida la entrada del
     * usuario, asegurándose de que la matrícula no esté vacía y que tenga el
     * formato correcto. Luego verifica si el coche ya está en el parking. Si no
     * está, asigna una plaza libre y genera un ticket.
     *
     * @param evt El evento de acción que se genera cuando el usuario hace clic
     * en el botón.
     */
    private void BotonAñadirMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAñadirMatriculaActionPerformed

        // Validamos que el texto introducido no esté vacío y que tenga el formato correcto de matrícula
        if (!validoNulos(TextAñadirMatricula.getText().trim().toUpperCase()) || !validoMatricula(TextAñadirMatricula.getText().trim().toUpperCase())) {
            JOptionPane.showMessageDialog(null, "Asegurate de introducir la matricula correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificamos si el coche ya está en el parking
        if (!app.comprobarMatricula(TextAñadirMatricula.getText().trim().toUpperCase())) {
            JOptionPane.showMessageDialog(null, "El coche ya está en el parking", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Asignamos una plaza libre al coche y generamos el ticket correspondiente
        ticket = app.asignarPlaza(app.encontrarEspacioLibre(), TextAñadirMatricula.getText().trim().toUpperCase());

        // Mostramos un mensaje con el ticket asignado
        JOptionPane.showMessageDialog(null, "Tu ticket es: " + ticket.toString() + " NO LO PIERDAS", "TICKET", JOptionPane.DEFAULT_OPTION);

        // Actualizamos el plano de aparcamiento y la tabla de información
        app.mostrarPlano();
        actualizarTabla();

        // Cerramos la ventana de aparcamiento
        pantallaAparcar.dispose();
    }//GEN-LAST:event_BotonAñadirMatriculaActionPerformed

    /**
     * Maneja el evento cuando el usuario hace clic en el botón para introducir
     * el ID del ticket. Este método valida la entrada del usuario, comprobando
     * que el ID no esté vacío y que sea un número. Luego verifica si el ID
     * introducido corresponde a un ticket válido en la lista de tickets. Si el
     * ticket está activo, se procede con la acción correspondiente Si no, se
     * muestra un mensaje de error.
     *
     * @param evt El evento de acción que se genera cuando el usuario hace clic
     * en el botón.
     */
    private void BotonIntroducirIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonIntroducirIdActionPerformed

        // Validamos que el texto introducido no esté vacío y que sea un número
        if (!validoNulos(TextIntroducirId.getText().trim()) || !validoNumeros(TextIntroducirId.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Asegurate de introducir el id correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            // Verificamos si el ID introducido corresponde a un ticket existente
            int cont = app.getListaTickets().size();
            int tamanio = app.getListaTickets().size();
            for (Ticket ticket2 : app.getListaTickets()) {
                // Si encontramos el ticket, asignamos el ticketSeleccionado
                if (ticket2.getId() == Integer.parseInt(TextIntroducirId.getText().trim())) {
                    ticketSeleccionado = ticket2;
                    cont--; // Reducimos el contador si encontramos el ticket

                    // Comprobamos si el ticket está desactivado (es decir, si el coche ya salió del parking)
                    if (!ticket2.isActivo()) {
                        JOptionPane.showMessageDialog(this, "Este coche ya ha salido del parking", "ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            // Si el contador no ha cambiado, significa que el ID no se encuentra en la lista de tickets
            if (cont == tamanio) {
                JOptionPane.showMessageDialog(null, "No existe coche con ese ID en este parking", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                // Si el ticket es válido y activo, mostramos la ventana de pago y cerramos la ventana de retiro

                ventanaPagarSpinners();

                pantallaRetirar.dispose();
            }
        }

    }//GEN-LAST:event_BotonIntroducirIdActionPerformed

    /**
     * Maneja el evento cuando el campo de texto "TextPagar" recibe el foco.
     * Selecciona todo el texto dentro del campo de texto para facilitar el
     * borrado Esto ocurre automáticamente cuando el usuario hace clic en el
     * campo de texto.
     *
     * @param evt El evento de enfoque que se genera cuando el campo de texto
     * recibe el foco.
     */
    private void TextPagarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextPagarFocusGained
        // Selecciona todo el texto del campo "TextPagar" para facilitar su reemplazo
        TextPagar.selectAll();
    }//GEN-LAST:event_TextPagarFocusGained

    /**
     * Maneja el evento cuando el usuario hace clic en el botón de pagar.
     * Verifica que el importe ingresado no esté vacío y que sea un número
     * válido. Asegura que el importe ingresado sea suficiente para cubrir el
     * total correspondiente. Calcula y devuelve el cambio. Actualiza el
     * depósito con el dinero introducido. Libera la plaza correspondiente y
     * actualiza la interfaz de usuario. Desactiva el ticket y cierra la ventana
     * de pago.
     *
     * @param evt El evento de acción que se genera cuando el usuario hace clic
     * en el botón de pagar.
     */
    private void BotonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonPagarActionPerformed

//        // Comprobaciones de que el campo de texto no esté vacío y que el valor ingresado sea un número válido
//        if (!validoNulos(TextPagar.getText().trim()) || !validoNumeros(TextPagar.getText().trim())) {
//            // Muestra un mensaje de error si no se ingresa un número válido
//            JOptionPane.showMessageDialog(null, "Asegurate de introducir el importe correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
//            return;  // Sale del método si la validación falla
//        }
//
//        // Comprobar si el dinero ingresado es suficiente para cubrir el importe correspondiente
//        if (app.totalDineroDevolver(app.calcularTiempoTranscurrido(ticketSeleccionado)) > Double.parseDouble(TextPagar.getText().trim())) {
//            // Muestra un mensaje de error si el dinero ingresado es insuficiente
//            JOptionPane.showMessageDialog(null, "El dinero introducido no alcanza el importe", "ERROR", JOptionPane.ERROR_MESSAGE);
//            return;  // Sale del método si el dinero no es suficiente
//        }
//        // Comprobar si el deposito tiene suficiente dinero como para dar cambio
//        if (Double.parseDouble(TextPagar.getText().trim()) > deposito.totalDeposito) {
//            JOptionPane.showMessageDialog(null, "No se puede dar suficiente cambio, por favor introduce una cantidad mas ajustada", "ERROR", JOptionPane.ERROR_MESSAGE);
//            return; // Sale del metodo si no tiene sufiente dinero
//        }
        // Calcula el cambio que se debe devolver
        Double cambio = total - app.totalDineroDevolver(app.calcularTiempoTranscurrido(ticketSeleccionado));

        // Llama al método devolverCambio para obtener las monedas de vuelta
        Deposito vueltas = deposito.devolverCambio(cambio);

        // Muestra un mensaje con el cambio que se va a devolver
        JOptionPane.showMessageDialog(null, "Has introducido " + total + " €, la vuelta correspondiente es: " + vueltas.toStringMonedas(vueltas), "VUELTAS", JOptionPane.PLAIN_MESSAGE);
        // Actualiza el depósito con el dinero ingresado por el usuario
        deposito.introducirDinero(arraySpinner);

        // Libera la plaza correspondiente, ya que el ticket ha sido pagado
        app.liberarPlaza(ticketSeleccionado);

        // Actualiza la tabla para reflejar el estado actualizado de las plazas
        actualizarTabla();

        // Desactiva el ticket para marcarlo como pagado
        ticketSeleccionado.desactivar();

        System.out.println(deposito.toString());
        // Cierra la ventana de pago
        pantallaPagarSpinners.dispose();

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
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JLabel LabelPagar4;
    private javax.swing.JLabel LabelPagarSpinners;
    private javax.swing.JLabel LabelParquimetroPrincipal;
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
