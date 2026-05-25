/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uts.poo.vista;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import uts.poo.AutorDAO;
import uts.poo.ConexionBD;
import uts.poo.Libro;
import uts.poo.LibroDAO;
import uts.poo.PrestamoDAO;
import uts.poo.Usuario;

/**
 * Menú principal de la aplicación de librería.
 * @author emilio
 */
public class MenuPrincipal extends javax.swing.JFrame {

    private final LibroDAO libroDAO;
    private final AutorDAO autorDAO;
    private final PrestamoDAO prestamoDAO;
    private final Usuario usuarioAutenticado;

    public MenuPrincipal(Usuario usuario) {
        this.libroDAO = new LibroDAO();
        this.autorDAO = new AutorDAO();
        this.prestamoDAO = new PrestamoDAO();
        this.usuarioAutenticado = usuario;

        initComponents();
        cargarEstadisticas();
        cargarTablaLibros();
        cargarTablaAutores();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelEstadisticas = new javax.swing.JLabel();
        lblTotalLibros = new javax.swing.JLabel();
        txtTotalLibros = new javax.swing.JTextField();
        lblTotalPrestados = new javax.swing.JLabel();
        txtTotalPrestados = new javax.swing.JTextField();
        lblMediaCalificacion = new javax.swing.JLabel();
        txtMediaCalificacion = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelLibros = new javax.swing.JLabel();
        jScrollPaneLibros = new javax.swing.JScrollPane();
        tablaLibros = new javax.swing.JTable();
        jLabelAutores = new javax.swing.JLabel();
        jScrollPaneAutores = new javax.swing.JScrollPane();
        tablaAutores = new javax.swing.JTable();
        jMenuBar2 = new javax.swing.JMenuBar();
        menuInicio = new javax.swing.JMenu();
        menuLibros = new javax.swing.JMenu();
        menuAutores = new javax.swing.JMenu();
        menuPrestamos = new javax.swing.JMenu();
        menuMisPrestamos = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Libreria - UTS POO");

        jLabelEstadisticas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelEstadisticas.setText("Estadísticas");

        lblTotalLibros.setText("Total libros:");

        txtTotalLibros.setEditable(false);

        lblTotalPrestados.setText("Total prestados:");

        txtTotalPrestados.setEditable(false);

        lblMediaCalificacion.setText("Media calificación:");

        txtMediaCalificacion.setEditable(false);

        jLabelLibros.setText("Lista de Libros");

        tablaLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Titulo", "Genero", "Calificacion", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneLibros.setViewportView(tablaLibros);

        jLabelAutores.setText("Lista de Autores");

        tablaAutores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Nacionalidad", "Libros"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneAutores.setViewportView(tablaAutores);

        menuInicio.setText("Inicio");
        menuInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuInicioMouseClicked(evt);
            }
        });
        jMenuBar2.add(menuInicio);

        menuLibros.setText("Libros");
        menuLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLibrosMouseClicked(evt);
            }
        });
        jMenuBar2.add(menuLibros);

        menuAutores.setText("Autores");
        menuAutores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuAutoresMouseClicked(evt);
            }
        });
        jMenuBar2.add(menuAutores);

        menuPrestamos.setText("Préstamos");
        menuPrestamos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPrestamosMouseClicked(evt);
            }
        });
        jMenuBar2.add(menuPrestamos);

        menuMisPrestamos.setText("Mis Préstamos");
        menuMisPrestamos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuMisPrestamosMouseClicked(evt);
            }
        });
        jMenuBar2.add(menuMisPrestamos);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEstadisticas)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalLibros))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalPrestados, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalPrestados))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMediaCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMediaCalificacion)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPaneLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLibros)
                    .addComponent(jScrollPaneAutores, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAutores))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabelEstadisticas)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalLibros)
                    .addComponent(lblTotalPrestados)
                    .addComponent(lblMediaCalificacion))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalLibros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalPrestados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMediaCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabelLibros)
                .addGap(6, 6, 6)
                .addComponent(jScrollPaneLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabelAutores)
                .addGap(6, 6, 6)
                .addComponent(jScrollPaneAutores, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarEstadisticas() {
        try {
            // Total de libros
            List<Libro> libros = libroDAO.obtenerTodos();
            txtTotalLibros.setText(String.valueOf(libros.size()));

            // Total de libros prestados
            int totalPrestados = prestamoDAO.contarActivos();
            txtTotalPrestados.setText(String.valueOf(totalPrestados));

            // Media de calificación (calculada desde los objetos Libro)
            double suma = 0;
            for (Libro l : libros) {
                suma += l.getCalificacion();
            }
            double media = libros.isEmpty() ? 0.0 : suma / libros.size();
            txtMediaCalificacion.setText(String.format("%.1f", media));
        } catch (SQLException e) {
            System.err.println("Error al cargar estadísticas: " + e.getMessage());
        }
    }

    private void cargarTablaLibros() {
        DefaultTableModel modelo = (DefaultTableModel) tablaLibros.getModel();
        modelo.setRowCount(0);

        try {
            List<Libro> libros = libroDAO.obtenerTodos();
            for (Libro l : libros) {
                modelo.addRow(new Object[]{
                    l.getId(),
                    l.getTitulo(),
                    l.getGenero(),
                    l.getCalificacion(),
                    l.getEstado()
                });
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar tabla de libros: " + e.getMessage());
        }
    }

    private void cargarTablaAutores() {
        DefaultTableModel modelo = (DefaultTableModel) tablaAutores.getModel();
        modelo.setRowCount(0);

        try {
            List<Object[]> autores = autorDAO.obtenerTodosConLibros();
            for (Object[] a : autores) {
                modelo.addRow(a);
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar tabla de autores: " + e.getMessage());
        }
    }

    private void menuInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuInicioMouseClicked
        cargarEstadisticas();
        cargarTablaLibros();
        cargarTablaAutores();
    }//GEN-LAST:event_menuInicioMouseClicked

    private void menuLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLibrosMouseClicked
        VistaLibros dialog = new VistaLibros(this, true);
        dialog.setVisible(true);
        // Al volver, refrescar
        cargarEstadisticas();
        cargarTablaLibros();
    }//GEN-LAST:event_menuLibrosMouseClicked

    private void menuAutoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAutoresMouseClicked
        VistaAutores dialog = new VistaAutores(this, true);
        dialog.setVisible(true);
        // Al volver, refrescar
        cargarTablaAutores();
    }//GEN-LAST:event_menuAutoresMouseClicked

    private void menuPrestamosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPrestamosMouseClicked
        VistaPrestamos dialog = new VistaPrestamos(this, true);
        dialog.setVisible(true);
        // Al volver de préstamos, refrescar estadísticas y tabla de libros
        cargarEstadisticas();
        cargarTablaLibros();
    }//GEN-LAST:event_menuPrestamosMouseClicked

    private void menuMisPrestamosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMisPrestamosMouseClicked
        VistaMisPrestamos dialog = new VistaMisPrestamos(this, true, usuarioAutenticado);
        dialog.setVisible(true);
        // Al volver, refrescar estadísticas y tablas
        cargarEstadisticas();
        cargarTablaLibros();
    }//GEN-LAST:event_menuMisPrestamosMouseClicked

    public static void main(String args[]) {
        // Inicializar la base de datos (crea tablas y datos de ejemplo si no existen)
        ConexionBD.inicializarBD();

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        // Mostrar login primero
        java.awt.EventQueue.invokeLater(() -> {
            VistaLogin login = new VistaLogin(null, true);
            login.setVisible(true);

            // Solo abrir el menú si el login fue exitoso
            if (login.getUsuarioAutenticado() != null) {
                new MenuPrincipal(login.getUsuarioAutenticado()).setVisible(true);
            } else {
                System.exit(0);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAutores;
    private javax.swing.JLabel jLabelEstadisticas;
    private javax.swing.JLabel jLabelLibros;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPaneAutores;
    private javax.swing.JScrollPane jScrollPaneLibros;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblMediaCalificacion;
    private javax.swing.JLabel lblTotalLibros;
    private javax.swing.JLabel lblTotalPrestados;
    private javax.swing.JMenu menuAutores;
    private javax.swing.JMenu menuInicio;
    private javax.swing.JMenu menuLibros;
    private javax.swing.JMenu menuMisPrestamos;
    private javax.swing.JMenu menuPrestamos;
    private javax.swing.JTable tablaAutores;
    private javax.swing.JTable tablaLibros;
    private javax.swing.JTextField txtMediaCalificacion;
    private javax.swing.JTextField txtTotalLibros;
    private javax.swing.JTextField txtTotalPrestados;
    // End of variables declaration//GEN-END:variables
}