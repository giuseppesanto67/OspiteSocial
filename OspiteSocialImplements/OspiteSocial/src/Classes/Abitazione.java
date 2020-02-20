
package Classes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Abitazione {
    private Integer id;
    private String proprietario;
    private String indirizzo;
    private String citta;
    private Float distanzaCentro;
    private Float distanzaStazione;
    private Integer  tempoTermineModifiche;
    private List<Stanza> stanze;
    private Date dataInizioDisponibilita;
    private Date dataFineDisponibilita;
    private Integer tariffaGiornaliera ;
    
    public Abitazione(Integer id, String proprietario, String indirizzo, String citta, Float distanzaCentro, Float distanzaStazione, Integer tempoTermineModifiche, Date dataInizioDisponibilita, Date dataFineDisponibilita,Integer tariffaGiornaliera) {
        this.id = id;
        this.proprietario = proprietario;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.distanzaCentro = distanzaCentro;
        this.distanzaStazione = distanzaStazione;
        this.tempoTermineModifiche = tempoTermineModifiche;
        this.stanze = new ArrayList();
        this.dataInizioDisponibilita = dataInizioDisponibilita;
        this.dataFineDisponibilita = dataFineDisponibilita;
        this.tariffaGiornaliera=tariffaGiornaliera;
    }
    
    public Abitazione(Integer id, String proprietario, String indirizzo, String citta, Float distanzaCentro, Float distanzaStazione, Integer tempoTermineModifiche, Date dataInizioDisponibilita, Date dataFineDisponibilita) {
        this.id = id;
        this.proprietario = proprietario;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.distanzaCentro = distanzaCentro;
        this.distanzaStazione = distanzaStazione;
        this.tempoTermineModifiche = tempoTermineModifiche;
        this.stanze = new ArrayList();
        this.dataInizioDisponibilita = dataInizioDisponibilita;
        this.dataFineDisponibilita = dataFineDisponibilita;
    }

    public Integer getTariffaGiornaliera() {
        return tariffaGiornaliera;
    }

    public void setTariffaGiornaliera(Integer tariffaGiornaliera) {
        this.tariffaGiornaliera = tariffaGiornaliera;
    }

    @Override
    public String toString() {
        return "Abitazione{" + "id=" + id + ", proprietario=" + proprietario + ", indirizzo=" + indirizzo + ", citta=" + citta + ", distanzaCentro=" + distanzaCentro + ", distanzaStazione=" + distanzaStazione + ", tempoTermineModifiche=" + tempoTermineModifiche + ", stanze=" + stanze + ", dataInizioDisponibilita=" + dataInizioDisponibilita + ", dataFineDisponibilita=" + dataFineDisponibilita + ", tariffaGiornaliera=" + tariffaGiornaliera + '}';
    }

   


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Float getDistanzaCentro() {
        return distanzaCentro;
    }

    public void setDistanzaCentro(Float distanzaCentro) {
        this.distanzaCentro = distanzaCentro;
    }

    public Float getDistanzaStazione() {
        return distanzaStazione;
    }

    public void setDistanzaStazione(Float distanzaStazione) {
        this.distanzaStazione = distanzaStazione;
    }

    public Integer getTempoTermineModifiche() {
        return tempoTermineModifiche;
    }

    public void setTempoTermineModifiche(Integer tempoTermineModifiche) {
        this.tempoTermineModifiche = tempoTermineModifiche;
    }

    public List<Stanza> getStanze() {
        return stanze;
    }

    public void setStanze(List<Stanza> stanze) {
        this.stanze = stanze;
    }

    public Date getDataInizioDisponibilita() {
        return dataInizioDisponibilita;
    }

    public void setDataInizioDisponibilita(Date dataInizioDisponibilita) {
        this.dataInizioDisponibilita = dataInizioDisponibilita;
    }

    public Date getDataFineDisponibilita() {
        return dataFineDisponibilita;
    }

    public void setDataFineDisponibilita(Date dataFineDisponibilita) {
        this.dataFineDisponibilita = dataFineDisponibilita;
    }

}
