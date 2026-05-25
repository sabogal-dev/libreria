package uts.poo.vista;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import uts.poo.Autor;
import uts.poo.AutorDAO;
import uts.poo.Libro;
import uts.poo.LibroDAO;

/**
 * Formulario para crear o editar un libro.
 * @author emilio
 */
public class VistaLibroForm extends javax.swing.JDialog {

    private final LibroDAO libroDAO;
    private final AutorDAO autorDAO;
    private final Libro libroEditar;
    private final java.util.List<Autor> autores;

    public VistaLibroForm(java.awt.Dialog parent, boolean modal, Libro libroExistente) {
        super(parent, modal);
        this.libroDAO = new LibroDAO();
        this.autorDAO = new AutorDAO();
        this.libroEditar = libroExistente;
        this.autores = new java.util.ArrayList<>();

        initComponents();
        cargarAutores();
        if (libroEditar != null) {
            cargarDatosLibro();
            setTitle("Editar Libro");
        } else {
            setTitle("Nuevo Libro");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblTitulo = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        lblGenero = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();
        lblCalificacion = new javax.swing.JLabel();
        txtCalificacion = new javax.swing.JTextField();
        lblAutor = new javax.swing.JLabel();
        comboAutor = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        lblTitulo.setText("Título:");
        txtTitulo.setColumns(25);

        lblGenero.setText("Género:");
        txtGenero.setColumns(25);

        lblCalificacion.setText("Calificación (0-5):");
        txtCalificacion.setColumns(10);

        lblAutor.setText("Autor:");
        comboAutor.setPreferredSize(new java.awt.Dimension(250, 22));

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
                    .addComponent(lblTitulo)
                    .addComponent(lblGenero)
                    .addComponent(lblCalificacion)
                    .addComponent(lblAutor))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTitulo)
                    .addComponent(txtGenero)
                    .addComponent(txtCalificacion)
                    .addComponent(comboAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btnGuardar)
                .addGap(20, 20, 20)
                .addComponent(btnCancelar)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGenero)
                    .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCalificacion)
                    .addComponent(txtCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAutor)
                    .addComponent(comboAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cargarAutores() {
        comboAutor.removeAllItems();
        try {
            List<Autor> lista = autorDAO.obtenerTodos();
            autores.clear();
            autores.addAll(lista);
            for (Autor a : lista) {
                comboAutor.addItem(a);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar autores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatosLibro() {
        txtTitulo.setText(libroEditar.getTitulo());
        txtGenero.setText(libroEditar.getGenero());
        txtCalificacion.setText(String.valueOf(libroEditar.getCalificacion()));
        // Seleccionar autor en combo
        for (int i = 0; i < comboAutor.getItemCount(); i++) {
            Autor a = comboAutor.getItemAt(i);
            if (a.getId() == libroEditar.getAutorId()) {
                comboAutor.setSelectedIndex(i);
                break;
            }
        }
    }

    private void btnGuardarActionPerformed() {
        String titulo = txtTitulo.getText().trim();
        String genero = txtGenero.getText().trim();
        String calificacionStr = txtCalificacion.getText().trim();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El título es obligatorio.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double calificacion;
        try {
            calificacion = Double.parseDouble(calificacionStr);
            if (calificacion < 0 || calificacion > 5) {
                JOptionPane.showMessageDialog(this, "Calificación debe estar entre 0 y 5.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Calificación inválida.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Autor autorSeleccionado = (Autor) comboAutor.getSelectedItem();
        if (autorSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un autor.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if (libroEditar == null) {
                // Nuevo
                Libro l = new Libro();
                l.setTitulo(titulo);
                l.setGenero(genero);
                l.setCalificacion(calificacion);
                l.setAutorId(autorSeleccionado.getId());
                libroDAO.crear(l);
            } else {
                // Editar
                libroEditar.setTitulo(titulo);
                libroEditar.setGenero(genero);
                libroEditar.setCalificacion(calificacion);
                libroEditar.setAutorId(autorSeleccionado.getId());
                libroDAO.actualizar(libroEditar);
            }
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Autor> comboAutor;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblCalificacion;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCalificacion;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
