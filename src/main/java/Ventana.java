import javax.swing.*;
import java.awt.event.ActionEvent;
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
    private int i;
    private String kokoTalk;
    private final Timer timer = new Timer(0, this::timerAction);
    private int situation;

    public Ventana() {
        startUp();
        situationControl(0);
        neutralWhiteButton.addActionListener(e -> situationControl(0));
        redSadButton.addActionListener(e -> situationControl(1));
        greenHappyButton.addActionListener(e -> situationControl(2));
    }

    private void startUp() {
        setContentPane(mainPanel);
        setBounds(400, 200, 1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("KOKOTalk BETA");
        setVisible(true);
    }

    private void situationControl(int situation) {
        this.situation = situation;
        reloadTextHead();
        kokoTalk = selectText();
        loadPics();
        timer.restart();
    }

    private void reloadTextHead() {
        i = -1;
        labelTalk.setText(switch (situation) {
            default -> "<html>";
            case 1 -> "<html><p><span style=\"color: red;\">";
            case 2 -> "<html><p><span style=\"color: green;\">";
        });
    }

    private String selectText() {
        return switch (situation) {
            default -> " I am Sangonomiya Kokomi, the Divine Priestess of Watatsumi Island. My journey with you" +
                    " will be an opportunity to unwind... Ahem, I mean... to survey beyond our borders. Don't worry, " +
                    "I've left behind ample directives in my absence, and have also completed the island's projected " +
                    "development plans. Departing for a brief period will be of little consequence.\n<html>";
            case 1 -> " No matter how pressing the situation may become, I must " +
                    "remain composed. If I let the stress get to me, then what can I expect from others around me?<p><span><html>";
            case 2 -> " Huh? What do I enjoy? Simple. I like to find a quiet " +
                    "place by myself and read books on strategy... Ahh, the whole day without interruption. I'd be " +
                    "even happier if we looked over some of it together... One thing though... You'd have to sleep " +
                    "on the floor.<p><span><html>";
        };
    }

    private void loadPics() {
        labelPic.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(switch (situation) {
            default -> "/img/kokoStickerRegular.png";
            case 1 -> "/img/kokoStickerFail.png";
            case 2 -> "/img/kokoStickerSuccess.png";
        }))));
    }

    private void timerAction(ActionEvent e) {
        if (i++ < kokoTalk.length() - 1) labelTalk.setText(labelTalk.getText() + kokoTalk.charAt(i));
    }
}
