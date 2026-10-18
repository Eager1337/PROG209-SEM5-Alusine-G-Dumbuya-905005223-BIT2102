import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;

/**
 * PROG209 - Event Driven Programming
 * Basic Interactive GUI assignment.
 *
 * Demonstrates JFrame, JPanel, JLabel, inheritance, constructors,
 * ImageIcon, event handling, text editing, and panel background changes.
 */
public class SwingAssignment {
    private JFrame frame;
    private WelcomePanel panel;
    private JLabel welcomeLabel;
    private JTextField nameField;
    private JLabel statusLabel;
    private JLabel profilePhotoLabel;

    private static final Color FOREST = new Color(34, 61, 53);
    private static final Color INK = new Color(39, 43, 39);
    private static final Color PALE = new Color(247, 244, 237);
    private static final Color GOLD = new Color(190, 153, 91);
    private static final Color MUTED = new Color(116, 112, 101);
    private static final Color BORDER = new Color(228, 221, 207);
    private static final Color CHAMPAGNE = new Color(242, 232, 211);
    private static final Color SAGE = new Color(226, 233, 222);
    private static final Color STONE = new Color(235, 231, 222);
    private static final Color SAGE_BUTTON = new Color(133, 153, 126);
    private static final Color STONE_BUTTON = new Color(150, 142, 124);
    private static final String STUDENT_NAME = "Alusine Dumbuya";

    public SwingAssignment() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // JFrame variable named frame, as specified in the assignment brief.
        frame = new JFrame("Java Swing Assignment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setMinimumSize(new Dimension(640, 640));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        panel = new WelcomePanel();
        panel.setLayout(new BorderLayout(18, 18));
        panel.setBorder(new EmptyBorder(28, 32, 24, 32));
        panel.setBackground(PALE);

        // Header with application icon and title.
        JPanel header = new JPanel(new BorderLayout(14, 8));
        header.setOpaque(false);

        ImageIcon applicationIcon = loadAppIcon();
        JLabel iconLabel = new JLabel(applicationIcon);
        iconLabel.setPreferredSize(new Dimension(64, 64));
        header.add(iconLabel, BorderLayout.WEST);

        JPanel titleBlock = new JPanel();
        titleBlock.setOpaque(false);
        titleBlock.setLayout(new BoxLayout(titleBlock, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Java Swing Assignment");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
        titleLabel.setForeground(FOREST);

        JLabel subtitleLabel = new JLabel("PROG209  •  Event Driven Programming");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitleLabel.setForeground(MUTED);

        titleBlock.add(Box.createVerticalGlue());
        titleBlock.add(titleLabel);
        titleBlock.add(Box.createVerticalStrut(7));
        titleBlock.add(subtitleLabel);
        titleBlock.add(Box.createVerticalGlue());
        header.add(titleBlock, BorderLayout.CENTER);
        panel.add(header, BorderLayout.NORTH);

        // Main welcome card.
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel eyebrow = new JLabel("WELCOME");
        eyebrow.setFont(new Font("SansSerif", Font.BOLD, 12));
        eyebrow.setForeground(GOLD);
        eyebrow.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomeLabel = new JLabel("Welcome to Java Swing First Individual Assignment by " + STUDENT_NAME);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        welcomeLabel.setForeground(INK);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel helpText = new JLabel("Personalize the welcome message and appearance below.");
        helpText.setFont(new Font("SansSerif", Font.PLAIN, 14));
        helpText.setForeground(MUTED);
        helpText.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(Box.createVerticalGlue());
        content.add(eyebrow);
        content.add(Box.createVerticalStrut(18));
        content.add(welcomeLabel);
        content.add(Box.createVerticalStrut(12));
        content.add(helpText);
        content.add(Box.createVerticalStrut(34));

        JPanel controls = new JPanel(new GridBagLayout());
        controls.setBackground(new Color(255, 253, 248));
        controls.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(22, 22, 22, 22)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 7, 7, 7);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Personal profile image area. Use "Choose photo" to load a JPG/PNG from your computer.
        JPanel profileRow = new JPanel(new BorderLayout(16, 0));
        profileRow.setOpaque(false);
        profilePhotoLabel = new JLabel(createPlaceholderIcon(), SwingConstants.CENTER);
        profilePhotoLabel.setPreferredSize(new Dimension(92, 92));
        profilePhotoLabel.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        profileRow.add(profilePhotoLabel, BorderLayout.WEST);

        JPanel profileDetails = new JPanel();
        profileDetails.setOpaque(false);
        profileDetails.setLayout(new BoxLayout(profileDetails, BoxLayout.Y_AXIS));
        JLabel profileTitle = new JLabel("Personalize your profile");
        profileTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        JLabel profileHint = new JLabel("Add your own photo (PNG, JPG, or JPEG).");
        profileHint.setFont(new Font("SansSerif", Font.PLAIN, 12));
        profileHint.setForeground(MUTED);
        JButton photoButton = makeButton("Choose photo", FOREST);
        photoButton.addActionListener((ActionEvent e) -> chooseProfilePhoto());
        profileDetails.add(profileTitle);
        profileDetails.add(Box.createVerticalStrut(5));
        profileDetails.add(profileHint);
        profileDetails.add(Box.createVerticalStrut(10));
        profileDetails.add(photoButton);
        profileRow.add(profileDetails, BorderLayout.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        controls.add(profileRow, gbc);

        JLabel nameLabel = new JLabel("Your name"); 
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        controls.add(nameLabel, gbc);

        nameField = new JTextField(STUDENT_NAME, 24);
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        nameField.setToolTipText("Type your name here");
        gbc.gridy = 2;
        controls.add(nameField, gbc);

        JButton updateButton = makeButton("Update welcome text", FOREST);
        gbc.gridy = 3; gbc.gridwidth = 1; gbc.gridx = 0;
        controls.add(updateButton, gbc);

        JButton resetButton = makeButton("Reset", MUTED);
        gbc.gridx = 1;
        controls.add(resetButton, gbc);

        JLabel colorLabel = new JLabel("Change panel background");
        colorLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        controls.add(colorLabel, gbc);

        JPanel colorButtons = new JPanel(new GridLayout(1, 3, 10, 0));
        colorButtons.setOpaque(false);
        JButton champagneButton = makeButton("Champagne", GOLD);
        champagneButton.setForeground(INK);
        JButton sageButton = makeButton("Sage", SAGE_BUTTON);
        JButton stoneButton = makeButton("Stone", STONE_BUTTON);
        colorButtons.add(champagneButton);
        colorButtons.add(sageButton);
        colorButtons.add(stoneButton);
        gbc.gridy = 5;
        controls.add(colorButtons, gbc);

        updateButton.addActionListener((ActionEvent e) -> updateWelcomeText());
        nameField.addActionListener((ActionEvent e) -> updateWelcomeText());
        resetButton.addActionListener((ActionEvent e) -> {
            nameField.setText(STUDENT_NAME);
            welcomeLabel.setText("Welcome to Java Swing First Individual Assignment by " + STUDENT_NAME);
            panel.setBackground(PALE);
            statusLabel.setText("The application has been reset.");
        });
        champagneButton.addActionListener((ActionEvent e) -> {
            panel.setBackground(CHAMPAGNE);
            statusLabel.setText("Champagne background selected.");
        });
        sageButton.addActionListener((ActionEvent e) -> {
            panel.setBackground(SAGE);
            statusLabel.setText("Sage background selected.");
        });
        stoneButton.addActionListener((ActionEvent e) -> {
            panel.setBackground(STONE);
            statusLabel.setText("Stone background selected.");
        });

        content.add(controls);
        content.add(Box.createVerticalGlue());
        panel.add(content, BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        statusLabel = new JLabel("Ready — enter your name to personalize the message.");
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        statusLabel.setForeground(MUTED);
        footer.add(statusLabel, BorderLayout.WEST);

        JLabel footerRight = new JLabel("Built with Java Swing");
        footerRight.setFont(new Font("SansSerif", Font.ITALIC, 12));
        footerRight.setForeground(MUTED);
        footer.add(footerRight, BorderLayout.EAST);
        panel.add(footer, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setIconImage(applicationIcon.getImage());
        frame.setVisible(true);
    }

    private void updateWelcomeText() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            name = "Your name";
        }
        welcomeLabel.setText("Welcome to Java Swing First Individual Assignment by " + name);
        statusLabel.setText("Welcome message updated for " + name + ".");
    }

    private JButton makeButton(String text, Color background) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground((background.equals(GOLD) || background.equals(SAGE_BUTTON)
                || background.equals(STONE_BUTTON)) ? INK : Color.WHITE);
        button.setBackground(background);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 14, 10, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private ImageIcon createPlaceholderIcon() {
        // Load Alusine's supplied profile photo from the project assets folder.
        File photoFile = new File("assets/alusine-dumbuya-profile.jpeg");
        if (photoFile.exists()) {
            ImageIcon original = new ImageIcon(photoFile.getAbsolutePath());
            Image scaled = original.getImage().getScaledInstance(92, 92, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        }

        // Fallback avatar if the photo asset is moved or unavailable.
        java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(
                92, 92, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(PALE);
        g.fillRoundRect(0, 0, 92, 92, 18, 18);
        g.setColor(FOREST);
        g.fillOval(30, 15, 32, 32);
        g.fillRoundRect(18, 50, 56, 30, 18, 18);
        g.dispose();
        return new ImageIcon(image);
    }

    private void chooseProfilePhoto() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose your profile photo");
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image files (PNG, JPG, JPEG, GIF)", "png", "jpg", "jpeg", "gif"));
        int result = chooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selected = chooser.getSelectedFile();
            ImageIcon original = new ImageIcon(selected.getAbsolutePath());
            Image scaled = original.getImage().getScaledInstance(92, 92, Image.SCALE_SMOOTH);
            profilePhotoLabel.setIcon(new ImageIcon(scaled));
            statusLabel.setText("Profile photo loaded: " + selected.getName());
        }
    }

    private ImageIcon loadAppIcon() {
        URL resource = getClass().getResource("/assets/app-icon.png");
        if (resource != null) {
            return new ImageIcon(resource);
        }
        File file = new File("assets/app-icon.png");
        if (file.exists()) {
            return new ImageIcon(file.getAbsolutePath());
        }
        // Graceful fallback if the asset is not copied.
        return new ImageIcon(getAppImage());
    }

    private Image getAppImage() {
        java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(
                64, 64, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(FOREST);
        g.fillRoundRect(0, 0, 64, 64, 16, 16);
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 27));
        g.drawString("JS", 13, 41);
        g.dispose();
        return image;
    }

    /**
     * Custom JPanel subclass demonstrates inheritance and constructor use.
     */
    private static class WelcomePanel extends JPanel {
        public WelcomePanel() {
            super();
            setBackground(PALE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingAssignment::new);
    }
}
