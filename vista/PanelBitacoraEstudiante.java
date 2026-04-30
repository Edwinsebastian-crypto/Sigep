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
 * Bitácora del estudiante.
 * Muestra (solo lectura): Fecha de creación, Nota Final, Observación General,
 * Retroalimentación.
 * Permite: subir evidencias y escribir descripción de lo realizado.
 */
public class PanelBitacoraEstudiante extends JPanel {

    private static final Color BG      = new Color(242, 242, 242);
    private static final Color DARK    = new Color(45, 45, 45);

    public PanelBitacoraEstudiante(CardLayout cardLayout, JPanel parentPanel) {
        setBackground(BG);
        setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(BG);
        content.setBorder(new EmptyBorder(25, 40, 30, 40));

        // ── Título ──
        JLabel lblTitulo = new JLabel("Bitacora");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(lblTitulo);
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        // ── Tarjeta: Fecha | Nota Final | Observación General ──
        JPanel infoCard = createRoundedPanel(20, Color.WHITE);
        infoCard.setLayout(new GridLayout(1, 3));
        infoCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        infoCard.add(createDataField("Fecha de creación", "18/04/2026"));
        infoCard.add(createDataField("Nota Final", "4.0"));
        infoCard.add(createDataField("Observación General", "Observación"));
        content.add(infoCard);
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        // ── Retroalimentación ──
        JLabel lblRetro = new JLabel("Retroalimentación:");
        lblRetro.setFont(new Font("SansSerif", Font.BOLD, 17));
        lblRetro.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(lblRetro);
        content.add(Box.createRigidArea(new Dimension(0, 12)));

        content.add(createFeedbackItem("Asesor o Tutor", "2026-05-12",
                "\u201CExcelente desempeño en la planeación. Continúa con ese nivel de detalle.\u201D"));
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(createFeedbackItem("Asesor o Tutor", "2026-05-15",
                "\u201CMuy buen manejo del grupo durante la entrega del proyecto.\u201D"));
        content.add(Box.createRigidArea(new Dimension(0, 25)));

        // ── Subir Evidencias ──
        JPanel uploadCard = createRoundedPanel(20, Color.WHITE);
        uploadCard.setLayout(new GridBagLayout());
        uploadCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        // Ícono nube (dibujado)
        JPanel cloudIcon = createCloudIcon();
        gbc.gridy = 0;
        uploadCard.add(cloudIcon, gbc);

        JButton btnUpload = new JButton("Seleccionar archivo");
        btnUpload.setBackground(DARK);
        btnUpload.setForeground(Color.WHITE);
        btnUpload.setFocusPainted(false);
        btnUpload.setBorderPainted(false);
        btnUpload.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnUpload.setPreferredSize(new Dimension(200, 38));
        btnUpload.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 1;
        gbc.insets = new Insets(8, 0, 0, 0);
        uploadCard.add(btnUpload, gbc);

        content.add(uploadCard);
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        // ── Descripción de lo que realizó ──
        JLabel lblDesc = new JLabel("Descripción de lo que realizó:");
        lblDesc.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(lblDesc);
        content.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel descCard = createRoundedPanel(15, Color.WHITE);
        descCard.setLayout(new BorderLayout());
        descCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));
        JTextArea txtDesc = new JTextArea("Ingrese el texto.");
        txtDesc.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setOpaque(false);
        txtDesc.setBorder(new EmptyBorder(15, 20, 15, 20));
        descCard.add(txtDesc, BorderLayout.CENTER);
        content.add(descCard);
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        // ── Botón Volver ──
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        btnPanel.setOpaque(false);
        btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton btnVolver = new JButton("\u2190  Volver");
        btnVolver.setBackground(new Color(210, 210, 210));
        btnVolver.setBorderPainted(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> cardLayout.show(parentPanel, "MiPractica"));
        btnPanel.add(btnVolver);
        content.add(btnPanel);

        // ── Scroll ──
        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUI(new CleanScrollUI());
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scroll.getVerticalScrollBar().setBackground(BG);
        add(scroll, BorderLayout.CENTER);
    }

    // ── Campo de solo lectura ──
    private JPanel createDataField(String titulo, String valor) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(20, 30, 20, 30));
        JLabel t = new JLabel(titulo);
        t.setFont(new Font("SansSerif", Font.BOLD, 14));
        JLabel v = new JLabel(valor);
        v.setFont(new Font("SansSerif", Font.PLAIN, 14));
        v.setBorder(new EmptyBorder(5, 0, 0, 0));
        p.add(t);
        p.add(v);
        return p;
    }

    // ── Item de retroalimentación ──
    private JPanel createFeedbackItem(String autor, String fecha, String texto) {
        JPanel p = createRoundedPanel(15, new Color(238, 238, 238));
        p.setLayout(new BorderLayout());
        p.setBorder(new EmptyBorder(12, 25, 12, 25));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));

        JLabel lblTop = new JLabel(autor + "   " + fecha);
        lblTop.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblTop.setForeground(Color.GRAY);

        JLabel lblTxt = new JLabel(texto);
        lblTxt.setFont(new Font("SansSerif", Font.PLAIN, 14));

        p.add(lblTop, BorderLayout.NORTH);
        p.add(lblTxt, BorderLayout.CENTER);
        return p;
    }

    // ── Ícono nube con flecha ──
    private JPanel createCloudIcon() {
    JPanel icon = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            
            // Suavizado de bordes
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(60, 60, 60));
            g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            int w = getWidth();
            int h = getHeight();
            int cx = w / 2;
            int cy = h / 2;

            // --- Dibujar la Nube ---
            // Usamos coordenadas relativas al centro para que sea fácil de ajustar
            java.awt.geom.Path2D cloud = new java.awt.geom.Path2D.Float();
            
            // Empezamos por la base derecha
            cloud.moveTo(cx + 15, cy + 5); 
            // Curva derecha
            cloud.curveTo(cx + 25, cy + 5, cx + 25, cy - 15, cx + 10, cy - 15);
            // Curva superior (grande)
            cloud.curveTo(cx + 10, cy - 30, cx - 15, cy - 30, cx - 15, cy - 10);
            // Curva izquierda
            cloud.curveTo(cx - 30, cy - 10, cx - 30, cy + 5, cx - 15, cy + 5);
            // Línea base (dejando el hueco para la flecha)
            cloud.lineTo(cx - 6, cy + 5);
            cloud.moveTo(cx + 6, cy + 5);
            cloud.lineTo(cx + 15, cy + 5);

            g2.draw(cloud);

            // --- Dibujar la Flecha ---
            // Línea vertical
            g2.drawLine(cx, cy + 12, cx, cy - 8);
            // Punta de la flecha
            g2.drawLine(cx, cy - 8, cx - 6, cy - 2);
            g2.drawLine(cx, cy - 8, cx + 6, cy - 2);

            g2.dispose();
        }
    };
    icon.setPreferredSize(new Dimension(80, 60));
    icon.setOpaque(false);
    return icon;
}

    private JPanel createRoundedPanel(int r, Color bg) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), r, r));
                g2.dispose();
            }
        };
    }

    static class CleanScrollUI extends BasicScrollBarUI {
        @Override protected void configureScrollBarColors() {
            thumbColor = new Color(200, 200, 200);
            trackColor = new Color(242, 242, 242);
        }
        @Override protected JButton createDecreaseButton(int o) { return zero(); }
        @Override protected JButton createIncreaseButton(int o) { return zero(); }
        private JButton zero() {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(0, 0));
            return b;
        }
    }
}