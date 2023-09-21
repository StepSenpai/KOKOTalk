import javax.swing.*;
public class Ventana extends JFrame {
    private JPanel mainPanel;
    private JButton neutralWhiteButton;
    private JButton redSadButton;
    private JButton greenHappyButton;
    private JLabel labelPic;
    private JLabel labelTalk;
    private JPanel buttonPanel;
    private JPanel imgPanel;

    //Situacion que cambia cuando el usuario interactua
    private static int situation = 0;
    //Texto que va a ser mostrado
    private static String kokoTalk;

    /**
     * Constructor e initiator
     */
    public Ventana() {
        setContentPane(mainPanel);
        setBounds(400, 200, 1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("KOKOTalk BETA");
        setVisible(true);
        startAll();

        neutralWhiteButton.addActionListener(e -> {
            situation = 0;
            startAll();
        });
        redSadButton.addActionListener(e -> {
            situation = 1;
            startAll();
        });
        greenHappyButton.addActionListener(e -> {
            situation = 2;
            startAll();
        });
    }

    /**
     * Starts all methods
     */
    private void startAll() {
        loadPics();
        selectText();
        timerTalk();
    }

    /**
     * Selecciona la imagen que va a mostrar
     */
    private void loadPics() {
        if (situation < 0 || situation > 2)
            situation = 0;
        switch (situation) {
            case 0:
                labelPic.setIcon(new ImageIcon(getClass().getResource("/img/kokoStickerRegular.png")));
                break;
            case 1:
                labelPic.setIcon(new ImageIcon(getClass().getResource("/img/kokoStickerFail.png")));
                break;
            case 2:
                labelPic.setIcon(new ImageIcon(getClass().getResource("/img/kokoStickerSuccess.png")));
                break;
        }
    }

    /**
     * Selecciona el texto que va a mostrar
     */
    private void selectText() {
        kokoTalk = "";
        if (situation < 0 || situation > 2)
            situation = 0;
        switch (situation) {
            case 0:
                kokoTalk = "<html> I am Sangonomiya Kokomi, the Divine Priestess of Watatsumi Island. My journey with you" +
                        " will be an opportunity to unwind... Ahem, I mean... to survey beyond our borders. Don't worry, " +
                        "I've left behind ample directives in my absence, and have also completed the island's projected " +
                        "development plans. Departing for a brief period will be of little consequence.\n<html>";
                break;
            case 1:
                kokoTalk = "<html><p><span style=\"color: red;\">No matter how pressing the situation may become, I must " +
                        "remain composed. If I let the stress get to me, then what can I expect from others around me?<p><span><html>";
                break;
            case 2:
                kokoTalk = "<html><p><span style=\"color: green;\">Huh? What do I enjoy? Simple. I like to find a quiet " +
                        "place by myself and read books on strategy... Ahh, the whole day without interruption. I'd be " +
                        "even happier if we looked over some of it together... One thing though... You'd have to sleep " +
                        "on the floor.<p><span><html>";
                break;
        }
    }

    //TODO
    // Fallo consiste en que el Timer una vez creado no se cierra y continua realizandose, no solo ralentizando
    // el proceso pero tambien continuando a meter letras que estropean el textp

    /**
     * Timer, se encarga de mostrar caracter por caracter el texto
     */
    private void timerTalk() {
        labelTalk.setText("");
        int[] index = {0};
        char[] kokoTalkChar = kokoTalk.toCharArray();
        Timer timer = new Timer(0, e -> {
            if (index[0] < kokoTalkChar.length) {
                labelTalk.setText(labelTalk.getText() + kokoTalkChar[index[0]]);
                index[0]++;
            }
        });
        timer.setRepeats(true);
        timer.start();
    }
}
