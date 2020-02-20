package Classes;

import java.sql.Date;
import java.util.List;

public class Prenotazione {
    private Integer codicePrenotazione;
    private Integer codiceAbitazione;
    private String ospitante;
    private Date dataInizio;
    private Date dataFine;
    private Date checkIn;
    private Date checkOut;
    private String stato;
    private String motivazioni;
    private List<String> ospiti;
    private List<Integer> stanze;

    public Prenotazione(Integer codicePrenotazione, Integer codiceAbitazione, String ospitante, Date dataInizio, Date dataFine, Date checkIn, Date checkOut, String stato, String motivazioni) {
        this.codicePrenotazione = codicePrenotazione;
        this.codiceAbitazione = codiceAbitazione;
        this.ospitante = ospitante;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.stato = stato;
        this.motivazioni = motivazioni;
    }

    @Override
    public String toString() {
        return "Prenotazione{" + "codicePrenotazione=" + codicePrenotazione + ", codiceAbitazione=" + codiceAbitazione + ", ospitante=" + ospitante + ", dataInizio=" + dataInizio + ", dataFine=" + dataFine + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", stato=" + stato + ", motivazioni=" + motivazioni + ", ospiti=" + ospiti + ", stanze=" + stanze + '}';
    }

    public Integer getCodicePrenotazione() {
        return codicePrenotazione;
    }

    public void setCodicePrenotazione(Integer codicePrenotazione) {
        this.codicePrenotazione = codicePrenotazione;
    }

    public Integer getCodiceAbitazione() {
        return codiceAbitazione;
    }

    public void setCodiceAbitazione(Integer codiceAbitazione) {
        this.codiceAbitazione = codiceAbitazione;
    }

    public String getOspitante() {
        return ospitante;
    }

    public void setOspitante(String ospitante) {
        this.ospitante = ospitante;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getMotivazioni() {
        return motivazioni;
    }

    public void setMotivazioni(String motivazioni) {
        this.motivazioni = motivazioni;
    }

    public List<String> getOspiti() {
        return ospiti;
    }

    public void setOspiti(List<String> ospiti) {
        this.ospiti = ospiti;
    }

    public List<Integer> getStanze() {
        return stanze;
    }

    public void setStanze(List<Integer> stanze) {
        this.stanze = stanze;
    }
}
