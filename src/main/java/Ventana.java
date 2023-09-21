import javax.swing.*;
import java.util.Objects;

public class Ventana extends JFrame {
    private JPanel mainPanel;
    private JButton neutralWhiteButton;
    private JButton redSadButton;
    private JButton greenHappyButton;
    private JLabel labelPic;
    private JLabel labelTalk;
    private JPanel buttonPanel;
    private JPanel imgPanel;
    private int i = -1;
    private String kokoTalk;
    private final Timer timer = new Timer(0, e -> {
        if (i++ < kokoTalk.length() - 1) labelTalk.setText(labelTalk.getText() + kokoTalk.charAt(i));
    });

    /**
     * Constructor e initiator
     */
    public Ventana() {
        setContentPane(mainPanel);
        setBounds(400, 200, 1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("KOKOTalk BETA");
        setVisible(true);
        startAll(0);

        neutralWhiteButton.addActionListener(e -> startAll(0));
        redSadButton.addActionListener(e -> startAll(1));
        greenHappyButton.addActionListener(e -> startAll(2));
    }

    /**
     * Reinicia el estado del timer y empieza los metodos de seleccion de imagen y texto
     */
    private void startAll(int situation) {
        i = -1;
        labelTalk.setText("<html>");
        loadPics(situation);
        kokoTalk = selectText(situation);
        timer.restart();
    }

    /**
     * Selecciona la imagen que va a mostrar
     */
    private void loadPics(int situation) {
        switch (situation) {
            default ->
                    labelPic.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/kokoStickerRegular.png"))));
            case 1 ->
                    labelPic.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/kokoStickerFail.png"))));
            case 2 ->
                    labelPic.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/kokoStickerSuccess.png"))));
        }
    }

    /**
     * Selecciona el texto que va a mostrar
     */
    private String selectText(int situation) {
        return switch (situation) {
            default -> " I am Sangonomiya Kokomi, the Divine Priestess of Watatsumi Island. My journey with you" +
                    " will be an opportunity to unwind... Ahem, I mean... to survey beyond our borders. Don't worry, " +
                    "I've left behind ample directives in my absence, and have also completed the island's projected " +
                    "development plans. Departing for a brief period will be of little consequence.\n<html>";
            case 1 -> "<p><span style=\"color: red;\">No matter how pressing the situation may become, I must " +
                    "remain composed. If I let the stress get to me, then what can I expect from others around me?<p><span><html>";
            case 2 -> "<p><span style=\"color: green;\">Huh? What do I enjoy? Simple. I like to find a quiet " +
                    "place by myself and read books on strategy... Ahh, the whole day without interruption. I'd be " +
                    "even happier if we looked over some of it together... One thing though... You'd have to sleep " +
                    "on the floor.<p><span><html>";
        };
    }
}
