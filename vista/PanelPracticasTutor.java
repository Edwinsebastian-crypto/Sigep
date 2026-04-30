/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect.vista;

/**
 *
 * @author EdwinPruebas
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Panel "Prácticas" compartido por Tutor Académico y Asesor Pedagógico.
 * Muestra la tarjeta de información de la práctica y la lista de estudiantes
 * con botón "Ingresar" para ver la bitácora de cada uno.
 *
 * @param esTutor  true → puede ver nota/observación editable en la bitácora
 */
public class PanelPracticasTutor extends JPanel {

    private static final Color BG = new Color(242, 242, 242);

    public PanelPracticasTutor(CardLayout cardLayout, JPanel container, boolean esTutor) {
        setBackground(BG);
        setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(BG);
        content.setBorder(new EmptyBorder(25, 40, 25, 40));

        // ── Título ──
        JLabel lblTitulo = new JLabel("Prácticas");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(lblTitulo);
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        // ── Tarjeta Información de Práctica ──
        JPanel card = createRoundedPanel(20, Color.WHITE);
        card.setLayout(new BorderLayout());
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));
        card.setBorder(new EmptyBorder(20, 25, 20, 25));

        // Header de tarjeta
        JPanel cardHeader = new JPanel(new BorderLayout());
        cardHeader.setOpaque(false);
        JLabel lblInfoPractica = new JLabel("Información de Práctica");
        lblInfoPractica.setFont(new Font("SansSerif", Font.BOLD, 17));
        JLabel lblMenu = new JLabel("\u22EE"); // ⋮ tres puntos verticales
        lblMenu.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cardHeader.add(lblInfoPractica, BorderLayout.WEST);
        cardHeader.add(lblMenu, BorderLayout.EAST);

        // Grid de datos 3x3
        JPanel grid = new JPanel(new GridLayout(2, 2, 15, 20));
        grid.setOpaque(false);
        grid.setBorder(new EmptyBorder(15, 0, 15, 0));

        grid.add(createFieldBlock("Nombre Practica", "Educación Inicial"));
        grid.add(createFieldBlock("Tutor Asignado", "Jesus Ernesto Cruz Jimenez"));
        grid.add(createFieldBlock("Horas de la practica", "120"));

        grid.add(createFieldBlock("Institución", "I.E. Camacho Carreño"));
        grid.add(createFieldBlock("Fecha", "17/04/2026"));
        grid.add(createFieldBlock("Semestre", "Primer Semestre"));

        grid.add(createFieldBlock("Nombre Programa", "Lic. Educación Infantil"));
        grid.add(createFieldBlock("Estado", "Activo"));
        grid.add(createFieldBlock("Código de la practica", "A2B4C6"));

        card.add(cardHeader, BorderLayout.NORTH);
        card.add(grid, BorderLayout.CENTER);

        // Botón Entrar (solo visible si no hay lista; en este panel se usa Ingresar por estudiante)
        JPanel cardFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        cardFooter.setOpaque(false);
        card.add(cardFooter, BorderLayout.SOUTH);

        content.add(card);
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        // ── Lista de Estudiantes ──
        content.add(createStudentRow("Nombre Estudiante", "Ramiro Jose Estupiñan Rodrigez", cardLayout, container, esTutor));
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(createStudentRow("Nombre Estudiante", "Jesus Orlando Barios Riaño", cardLayout, container, esTutor));
        content.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botón expandir (∨)
        JButton btnExpand = new JButton("\u2228");
        btnExpand.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        btnExpand.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnExpand.setBackground(new Color(160, 160, 160));
        btnExpand.setForeground(Color.WHITE);
        btnExpand.setFocusPainted(false);
        btnExpand.setBorderPainted(false);
        btnExpand.setFont(new Font("SansSerif", Font.BOLD, 16));
        content.add(btnExpand);

        // Scroll
        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUI(new CleanScrollBarUI());
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scroll.getVerticalScrollBar().setBackground(BG);
        add(scroll, BorderLayout.CENTER);
    }

    // ── Fila de estudiante con botón Ingresar ──
    private JPanel createStudentRow(String label, String nombre,
                                    CardLayout cardLayout, JPanel container, boolean esTutor) {
        JPanel row = createRoundedPanel(15, Color.WHITE);
        row.setLayout(new BorderLayout());
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));
        row.setBorder(new EmptyBorder(12, 20, 12, 20));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 13));
        textPanel.add(lblLabel);
        textPanel.add(lblNombre);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBackground(new Color(210, 210, 210));
        btnIngresar.setBorder(BorderFactory.createEmptyBorder(6, 18, 6, 18));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIngresar.addActionListener(e -> cardLayout.show(container, "BITACORA"));

        row.add(textPanel, BorderLayout.CENTER);
        row.add(btnIngresar, BorderLayout.EAST);
        return row;
    }

    // ── Campo con etiqueta + valor en input redondeado ──
    private JPanel createFieldBlock(String titulo, String valor) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);

        JLabel t = new JLabel(titulo);
        t.setFont(new Font("SansSerif", Font.BOLD, 13));

        // Input gris redondeado
        JPanel inputBg = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(225, 225, 225));
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        inputBg.setOpaque(false);
        inputBg.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel v = new JLabel(valor);
        v.setFont(new Font("SansSerif", Font.PLAIN, 13));
        inputBg.add(v);
        inputBg.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));

        p.add(t);
        p.add(Box.createRigidArea(new Dimension(0, 4)));
        p.add(inputBg);
        return p;
    }

    private JPanel createRoundedPanel(int r, Color bg) {
        return new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), r, r));
                g2.dispose();
            }
        };
    }

    static class CleanScrollBarUI extends BasicScrollBarUI {
        @Override protected void configureScrollBarColors() {
            thumbColor = new Color(200, 200, 200);
            trackColor = new Color(242, 242, 242);
        }
        @Override protected JButton createDecreaseButton(int o) { return zeroButton(); }
        @Override protected JButton createIncreaseButton(int o) { return zeroButton(); }
        private JButton zeroButton() {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(0, 0));
            return b;
        }
    }
}