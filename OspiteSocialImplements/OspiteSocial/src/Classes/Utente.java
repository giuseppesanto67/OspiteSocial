package Classes;

public class Utente {

	private String cf;
	private String nome;
        private String cognome;
        private int eta;
        private char sesso;
        private String cittaResidenza;
        private String cellulare;
        private String password;
        private Integer credito;
        
    public Utente(String cf, String nome, String cognome, int eta, char sesso, String cittaResidenza, String cellulare, String password,Integer crediti) {
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.sesso = sesso;
        this.cittaResidenza = cittaResidenza;
        this.cellulare = cellulare;
        this.password = password;
        this.credito=crediti;
    }

    public Utente(String cf, String nome, String cognome, int eta, char sesso, String cittaResidenza, String cellulare, String password) {
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.sesso = sesso;
        this.cittaResidenza = cittaResidenza;
        this.cellulare = cellulare;
        this.password = password;
    }

    public Utente(String cf, String password) {
        this.cf = cf;
        this.password = password;
    }
    
    public Integer getCredito() {
        return credito;
    }

    public void setCredito(Integer credito) {
        this.credito = credito;
    }

    @Override
    public String toString() {
        return "Utente{" + "cf=" + cf + ", nome=" + nome + ", cognome=" + cognome + ", eta=" + eta + ", sesso=" + sesso + ", cittaResidenza=" + cittaResidenza + ", cellulare=" + cellulare + ", password=" + password + ", credito=" + credito + '}';
    }


    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getEta() {
        return eta;
    }

    public char getSesso() {
        return sesso;
    }

    public String getCittaResidenza() {
        return cittaResidenza;
    }

    public String getCellulare() {
        return cellulare;
    }	
}
