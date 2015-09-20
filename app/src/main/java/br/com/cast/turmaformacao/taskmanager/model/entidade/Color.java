package br.com.cast.turmaformacao.taskmanager.model.entidade;

/**
 * Created by Administrador on 17/09/2015.
 */
public enum Color {

    //cria-se objetos da classe color baseado no contrutor(padrão: vazio)
    PINK("#F44336"),
    RED("#E91E63"),
    PURPLE("#9C27B0"),
    DEEP_PURPLE("#673AB7"),
    INDIGO("#3F51B5"),
    BLUE("#2196F3"),
    LIGHT_BLUE("#03A9F4"),
    CYAN("#00BCD4"),
    TEAL("#009688"),
    GREEN("#4CAF50"),
    LIGHT_GREEN("#8BC34A"),
    LIME("#CDDC39"),
    YELLOW("#FFEB3B"),
    AMBER("#FFC107"),
    ORANGE("#FF9800"),
    DEEP_ORANGE("#FF5722"),
    BROW("#795548"),
    GREY("#9E9E9E"),
    BLUE_GREY("#607D8B"),
    BLACK("#000000");

    private String hex;

    private Color(String hex) {
        this.hex = hex;
    }

    public String getHex() {
        return this.hex;
    }


    public static Color getInstance(String hex) {
        Color[] values = values();

        for (Color color : values) {
            if (color.getHex().equals(hex)) {
                return color;
            }
        }

        return null;
    }

}
