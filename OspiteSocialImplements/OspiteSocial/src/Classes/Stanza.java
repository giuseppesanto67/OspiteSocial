package Classes;

public class Stanza {
    private Integer id;
    private String TipologiaStanza;
    private String TipologiaPostoLetto;
    private Integer numeroPotiLetto;
    private Integer codiceAbitazione;

    public Stanza(Integer id, String TipologiaStanza, String TipologiaPostoLetto, Integer numeroPotiLetto, Integer codiceAbitazione) {
        this.id = id;
        this.TipologiaStanza = TipologiaStanza;
        this.TipologiaPostoLetto = TipologiaPostoLetto;
        this.numeroPotiLetto = numeroPotiLetto;
        this.codiceAbitazione = codiceAbitazione;
    }

    @Override
    public String toString() {
        return "Stanza{" + "id=" + id + ", TipologiaStanza=" + TipologiaStanza + ", TipologiaPostoLetto=" + TipologiaPostoLetto + ", numeroPotiLetto=" + numeroPotiLetto + ", codiceAbitazione=" + codiceAbitazione + '}';
    }

    public Integer getId() {
        return id;
    }

    public String getTipologiaStanza() {
        return TipologiaStanza;
    }

    public String getTipologiaPostoLetto() {
        return TipologiaPostoLetto;
    }

    public Integer getNumeroPotiLetto() {
        return numeroPotiLetto;
    }

    public Integer getCodiceAbitazione() {
        return codiceAbitazione;
    }
}
