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
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class PanelMiPractica extends JPanel {

    private final String ICON_ENTRAR = "\u21A6";

    public PanelMiPractica(CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(25, 40, 40, 40));

        // ── Título ──
        JLabel title = new JLabel("Mi Práctica Pedagógica");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        // ── Tarjeta blanca de información ──
        JPanel card = createRoundedPanel(25, Color.WHITE);
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(25, 30, 25, 30));

        // Header de tarjeta
        JPanel cardHeader = new JPanel(new BorderLayout());
        cardHeader.setOpaque(false);

        JLabel infoTitle = new JLabel("Información de Práctica");
        infoTitle.setFont(new Font("SansSerif", Font.BOLD, 18));

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        statusPanel.setOpaque(false);
        JLabel statusLabel = new JLabel("Estado");
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel badge = createRoundedPanel(15, new Color(178, 235, 242));
        badge.setBorder(new EmptyBorder(5, 15, 5, 15));
        JLabel badgeText = new JLabel("Activo");
        badgeText.setFont(new Font("SansSerif", Font.PLAIN, 14));
        badge.add(badgeText);

        statusPanel.add(statusLabel);
        statusPanel.add(badge);

        cardHeader.add(infoTitle, BorderLayout.WEST);
        cardHeader.add(statusPanel, BorderLayout.EAST);

        // Grid 3x3 de información
        JPanel gridInfo = new JPanel(new GridLayout(3, 3, 20, 30));
        gridInfo.setOpaque(false);
        gridInfo.setBorder(new EmptyBorder(30, 0, 30, 0));

        gridInfo.add(createInfoBlock("Nombre Practica", "Nombre de la practica"));
        gridInfo.add(createInfoBlock("Tutor Asignado", "Jesus Ernesto Cruz Jimenez"));
        gridInfo.add(createInfoBlock("Horas de la practica", "45 / 120 Horas"));

        gridInfo.add(createInfoBlock("Institución", "Nombre Institución"));
        gridInfo.add(new JLabel(""));
        gridInfo.add(createInfoBlock("Semestre", "Primer semestre"));

        gridInfo.add(createInfoBlock("Nombre Programa", "Nombre programa"));
        gridInfo.add(createInfoBlock("Fecha", "17/04/2026"));
        gridInfo.add(createInfoBlock("Código de la practica", "A2B4C6"));

        // Footer con botón Entrar
        JPanel cardFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        cardFooter.setOpaque(false);

        JPanel btnEntrar = createRoundedPanel(10, Color.BLACK);
        btnEntrar.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 8));
        btnEntrar.setPreferredSize(new Dimension(110, 40));
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel entrarIcon = new JLabel(ICON_ENTRAR);
        entrarIcon.setForeground(Color.WHITE);
        entrarIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        JLabel entrarText = new JLabel("Entrar");
        entrarText.setForeground(Color.WHITE);
        entrarText.setFont(new Font("SansSerif", Font.PLAIN, 14));

        btnEntrar.add(entrarIcon);
        btnEntrar.add(entrarText);

        // Navega a la Bitácora
        btnEntrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(parentPanel, "Bitacora");
            }
        });

        cardFooter.add(btnEntrar);

        card.add(cardHeader, BorderLayout.NORTH);
        card.add(gridInfo, BorderLayout.CENTER);
        card.add(cardFooter, BorderLayout.SOUTH);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(new EmptyBorder(20, 0, 0, 0));
        wrapper.add(card, BorderLayout.NORTH);

        add(wrapper, BorderLayout.CENTER);
    }

    private JPanel createInfoBlock(String titleStr, String valueStr) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);
        JLabel t = new JLabel(titleStr);
        t.setFont(new Font("SansSerif", Font.BOLD, 14));
        JLabel v = new JLabel(valueStr);
        v.setFont(new Font("SansSerif", Font.PLAIN, 14));
        v.setBorder(new EmptyBorder(5, 0, 0, 0));
        p.add(t);
        p.add(v);
        return p;
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
}