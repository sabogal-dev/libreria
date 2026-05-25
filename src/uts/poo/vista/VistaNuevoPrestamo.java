package uts.poo.vista;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import uts.poo.Libro;
import uts.poo.LibroDAO;
import uts.poo.PrestamoDAO;
import uts.poo.Usuario;
import uts.poo.UsuarioDAO;

/**
 * Vista para crear un nuevo préstamo con selector de fecha simple (combos año/mes/día).
 * @author emilio
 */
public class VistaNuevoPrestamo extends javax.swing.JDialog {

    private final LibroDAO libroDAO;
    private final UsuarioDAO usuarioDAO;
    private final PrestamoDAO prestamoDAO;

    public VistaNuevoPrestamo(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        this.libroDAO = new LibroDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.prestamoDAO = new PrestamoDAO();
        initComponents();
        cargarLibros();
        cargarUsuarios();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabelTitulo = new javax.swing.JLabel();
        lblLibro = new javax.swing.JLabel();
        cmbLibro = new javax.swing.JComboBox<>();
        lblUsuario = new javax.swing.JLabel();
        cmbUsuario = new javax.swing.JComboBox<>();
        lblFechaDevolucion = new javax.swing.JLabel();
        cmbAnio = new javax.swing.JComboBox<>();
        cmbMes = new javax.swing.JComboBox<>();
        cmbDia = new javax.swing.JComboBox<>();
        lblAnio = new javax.swing.JLabel();
        lblMes = new javax.swing.JLabel();
        lblDia = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Préstamo");
        setModal(true);
        setResizable(false);

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabelTitulo.setText("Nuevo Préstamo");

        lblLibro.setText("Libro:");

        lblUsuario.setText("Usuario:");

        lblFechaDevolucion.setText("Fecha estimada de devolución:");

        // Cargar años (actual + 2 años hacia adelante)
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = anioActual; i <= anioActual + 2; i++) {
            cmbAnio.addItem(i);
        }

        // Cargar meses
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        for (int i = 0; i < meses.length; i++) {
            cmbMes.addItem((i + 1) + " - " + meses[i]);
        }

        // Cargar días (1-31)
        for (int i = 1; i <= 31; i++) {
            cmbDia.addItem(i);
        }

        // Seleccionar fecha actual por defecto
        LocalDate hoy = LocalDate.now();
        cmbAnio.setSelectedItem(hoy.getYear());
        cmbMes.setSelectedIndex(hoy.getMonthValue() - 1);
        cmbDia.setSelectedItem(hoy.getDayOfMonth());

        // Listener para ajustar días cuando cambia mes/año
        cmbAnio.addActionListener(e -> actualizarDias());
        cmbMes.addActionListener(e -> actualizarDias());

        lblAnio.setText("Año");
        lblMes.setText("Mes");
        lblDia.setText("Día");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(e -> btnGuardarActionPerformed());

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLibro)
                            .addComponent(cmbLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuario)
                            .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblFechaDevolucion)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblAnio)
                            .addComponent(cmbAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblMes)
                            .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblDia)
                            .addComponent(cmbDia, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelar)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabelTitulo)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLibro)
                    .addComponent(lblUsuario))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(lblFechaDevolucion)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAnio)
                    .addComponent(lblMes)
                    .addComponent(lblDia))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Ajusta los días disponibles en el combo según el mes y año seleccionados.
     */
    private void actualizarDias() {
        int diaSeleccionado = (int) cmbDia.getSelectedItem();
        int mes = cmbMes.getSelectedIndex() + 1; // 1-12
        int anio = (int) cmbAnio.getSelectedItem();

        // Calcular días del mes
        int diasEnMes;
        if (mes == 2) {
            // Febrero: bisiesto?
            diasEnMes = (anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0)) ? 29 : 28;
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            diasEnMes = 30;
        } else {
            diasEnMes = 31;
        }

        cmbDia.removeAllItems();
        for (int i = 1; i <= diasEnMes; i++) {
            cmbDia.addItem(i);
        }
        // Restaurar selección si es posible
        if (diaSeleccionado <= diasEnMes) {
            cmbDia.setSelectedItem(diaSeleccionado);
        }
    }

    private void cargarLibros() {
        cmbLibro.removeAllItems();
        try {
            List<Libro> libros = libroDAO.obtenerDisponibles();
            for (Libro l : libros) {
                cmbLibro.addItem(l);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar libros: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarUsuarios() {
        cmbUsuario.removeAllItems();
        try {
            List<Usuario> usuarios = usuarioDAO.obtenerTodos();
            for (Usuario u : usuarios) {
                cmbUsuario.addItem(u);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnGuardarActionPerformed() {
        Libro libroSeleccionado = (Libro) cmbLibro.getSelectedItem();
        Usuario usuarioSeleccionado = (Usuario) cmbUsuario.getSelectedItem();

        if (libroSeleccionado == null || usuarioSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un libro y un usuario.",
                    "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int anio = (int) cmbAnio.getSelectedItem();
        int mes = cmbMes.getSelectedIndex() + 1;
        int dia = (int) cmbDia.getSelectedItem();

        String fechaDevolucion = String.format("%04d-%02d-%02d", anio, mes, dia);
        String fechaPrestamo = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        // Validar que la fecha de devolución no sea anterior a hoy
        LocalDate devolucion = LocalDate.of(anio, mes, dia);
        if (devolucion.isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(this, "La fecha de devolución no puede estar en el pasado.",
                    "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            prestamoDAO.crearPrestamo(
                    libroSeleccionado.getId(),
                    usuarioSeleccionado.getId(),
                    fechaPrestamo,
                    fechaDevolucion
            );
            JOptionPane.showMessageDialog(this, "Préstamo registrado exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el préstamo: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Integer> cmbAnio;
    private javax.swing.JComboBox<Integer> cmbDia;
    private javax.swing.JComboBox<Libro> cmbLibro;
    private javax.swing.JComboBox<String> cmbMes;
    private javax.swing.JComboBox<Usuario> cmbUsuario;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JLabel lblDia;
    private javax.swing.JLabel lblFechaDevolucion;
    private javax.swing.JLabel lblLibro;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
