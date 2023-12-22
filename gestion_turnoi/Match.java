public class Match {
    private Equipe equipe1;
    private Equipe equipe2;
    private Stade stade;
    private Temps temps;

    public Match(Equipe equipe1, Equipe equipe2, Stade stade, Temps temps) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.stade = stade;
        this.temps = temps;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public Stade getStade() {
        return stade;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
    }

    public Temps getTemps() {
        return temps;
    }

    public void setTemps(Temps temps) {
        this.temps = temps;
    }
}
