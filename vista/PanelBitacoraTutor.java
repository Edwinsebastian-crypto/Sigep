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
 * Vista de Bitácora de un estudiante, usada por Tutor Académico y Asesor Pedagógico.
 *
 * - esTutor = true  → muestra campos editables "Nota Final" y "Observación General"
 * - esTutor = false → solo muestra "Fecha de creación" (sin nota ni observación)
 */
public class PanelBitacoraTutor extends JPanel {

    private static final Color BG             = new Color(242, 242, 242);
    private static final Color COLOR_DARK     = new Color(45, 45, 45);
    private static final Color COLOR_GRAY_IN  = new Color(225, 225, 225);

    public PanelBitacoraTutor(CardLayout cardLayout, JPanel container, boolean esTutor) {
        setBackground(BG);
        setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(BG);
        content.setBorder(new EmptyBorder(25, 40, 25, 40));

        // ── Título ──
        JLabel lblTitulo = new JLabel("Prácticas");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(lblTitulo);
        content.add(Box.createRigidArea(new Dimension(0, 15)));

        // ── Tarjeta: Nombre del estudiante ──
        JPanel studentCard = createRoundedPanel(15, Color.WHITE);
        studentCard.setLayout(new BoxLayout(studentCard, BoxLayout.Y_AXIS));
        studentCard.setBorder(new EmptyBorder(15, 20, 15, 20));
        studentCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        JLabel lblStudentLabel = new JLabel("Nombre Estudiante");
        lblStudentLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        JLabel lblStudentName = new JLabel("Ramiro Jose Estupiñan Rodrigez");
        lblStudentName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        studentCard.add(lblStudentLabel);
        studentCard.add(lblStudentName);
        content.add(studentCard);
        content.add(Box.createRigidArea(new Dimension(0, 12)));

        // ── Tarjeta: Fecha / Nota / Observación ──
        JPanel infoCard = createRoundedPanel(15, Color.WHITE);
        infoCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, esTutor ? 90 : 70));
        infoCard.setBorder(new EmptyBorder(15, 20, 15, 20));

        if (esTutor) {
            // 3 columnas: Fecha | Nota Final editable | Observación General editable
            infoCard.setLayout(new GridLayout(1, 3, 20, 0));
            infoCard.add(createDataField("Fecha de creación", "18/04/2026", false));
            infoCard.add(createDataField("Nota Final", "Ingrese nota", true));
            infoCard.add(createDataField("Observación General", "Ingrese texto", true));
        } else {
            // Solo Fecha
            infoCard.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            infoCard.add(createDataField("Fecha de creación", "18/04/2026", false));
        }

        content.add(infoCard);
        content.add(Box.createRigidArea(new Dimension(0, 15)));

        // ── Descripción de lo que realizó ──
        JPanel descCard = createRoundedPanel(15, Color.WHITE);
        descCard.setLayout(new BorderLayout());
        descCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        descCard.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel lblDesc = new JLabel("Descripción de lo que realizó:");
        lblDesc.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JTextArea txtDesc = new JTextArea("El sistema de evaluacion no es acorde con lo que se enseña en la clase .");
        txtDesc.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setOpaque(false);
        txtDesc.setBorder(new EmptyBorder(8, 0, 0, 0));
        txtDesc.setEditable(false);

        descCard.add(lblDesc, BorderLayout.NORTH);
        descCard.add(txtDesc, BorderLayout.CENTER);
        content.add(descCard);
        content.add(Box.createRigidArea(new Dimension(0, 15)));

        // ── Área de documento PDF (icono) ──
        JPanel pdfCard = createRoundedPanel(15, Color.WHITE);
        pdfCard.setLayout(new GridBagLayout());
        pdfCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));

        JPanel pdfIcon = createPDFIcon();
        pdfCard.add(pdfIcon);
        content.add(pdfCard);
        content.add(Box.createRigidArea(new Dimension(0, 15)));

        // ── Retroalimentación ──
        JPanel retroCard = createRoundedPanel(15, Color.WHITE);
        retroCard.setLayout(new BorderLayout());
        retroCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        retroCard.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel lblRetro = new JLabel("Retroalimentación:");
        lblRetro.setFont(new Font("SansSerif", Font.PLAIN, 15));

        // Fila de comentario
        JPanel commentRow = new JPanel(new BorderLayout());
        commentRow.setOpaque(false);
        commentRow.setBorder(new EmptyBorder(10, 0, 0, 0));

        JPanel commentLeft = new JPanel(new BorderLayout());
        commentLeft.setOpaque(false);
        JLabel lblAddComment = new JLabel("Añadir Comentario:");
        lblAddComment.setFont(new Font("SansSerif", Font.PLAIN, 13));
        JTextField txtComment = new JTextField("Ingresar texto");
        txtComment.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        txtComment.setOpaque(false);
        txtComment.setFont(new Font("SansSerif", Font.PLAIN, 13));
        commentLeft.add(lblAddComment, BorderLayout.NORTH);
        commentLeft.add(txtComment, BorderLayout.CENTER);

        JLabel lblDate = new JLabel("2026-05-12");
        lblDate.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblDate.setForeground(Color.GRAY);

        JButton btnSend = new JButton("\u25BA");
        btnSend.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSend.setBackground(COLOR_DARK);
        btnSend.setForeground(Color.WHITE);
        btnSend.setBorderPainted(false);
        btnSend.setFocusPainted(false);
        btnSend.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSend.setPreferredSize(new Dimension(36, 36));

        JPanel rightSend = new JPanel(new BorderLayout());
        rightSend.setOpaque(false);
        rightSend.add(lblDate, BorderLayout.NORTH);
        rightSend.add(btnSend, BorderLayout.SOUTH);

        commentRow.add(commentLeft, BorderLayout.CENTER);
        commentRow.add(rightSend, BorderLayout.EAST);

        retroCard.add(lblRetro, BorderLayout.NORTH);
        retroCard.add(commentRow, BorderLayout.CENTER);

        content.add(retroCard);
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botón Volver
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.setOpaque(false);
        JButton btnVolver = new JButton("\u2190  Volver");
        btnVolver.setBackground(new Color(210, 210, 210));
        btnVolver.setBorderPainted(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> cardLayout.show(container, "LISTA"));
        btnPanel.add(btnVolver);
        content.add(btnPanel);

        // Scroll
        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUI(new CleanScrollBarUI());
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scroll.getVerticalScrollBar().setBackground(BG);
        add(scroll, BorderLayout.CENTER);
    }

    // ── Campo de datos: etiqueta + valor (editable o no) ──
    private JPanel createDataField(String titulo, String valor, boolean editable) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);

        JLabel t = new JLabel(titulo);
        t.setFont(new Font("SansSerif", Font.BOLD, 14));

        if (editable) {
            JTextField field = new JTextField(valor);
            field.setFont(new Font("SansSerif", Font.PLAIN, 13));
            field.setBorder(new EmptyBorder(4, 6, 4, 6));
            field.setBackground(COLOR_GRAY_IN);
            field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
            p.add(t);
            p.add(Box.createRigidArea(new Dimension(0, 5)));
            p.add(field);
        } else {
            JLabel v = new JLabel(valor);
            v.setFont(new Font("SansSerif", Font.PLAIN, 14));
            v.setBorder(new EmptyBorder(5, 0, 0, 0));
            p.add(t);
            p.add(v);
        }
        return p;
    }

    // ── Ícono PDF dibujado ──
    private JPanel createPDFIcon() {
        JPanel icon = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int cx = getWidth() / 2;
                int pageW = 48, pageH = 58;
                int px = cx - pageW / 2;
                int py = 5;

                // Hoja principal (azul grisáceo)
                g2.setColor(new Color(110, 130, 160));
                g2.fillRoundRect(px, py, pageW, pageH, 6, 6);

                // Líneas de texto
                g2.setColor(new Color(200, 210, 230));
                for (int i = 0; i < 4; i++) {
                    g2.fillRect(px + 8, py + 12 + i * 10, pageW - 16, 4);
                }

                // Etiqueta PDF (barra oscura abajo)
                g2.setColor(new Color(40, 50, 70));
                g2.fillRect(px, py + pageH - 18, pageW, 18);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("SansSerif", Font.BOLD, 10));
                g2.drawString("PDF", px + 13, py + pageH - 5);
                g2.dispose();
            }
        };
        icon.setPreferredSize(new Dimension(80, 80));
        icon.setOpaque(false);
        return icon;
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