package group2JP2.entities;

public class FilmAndType {
    public Integer filmId;
    public Integer tfId;

    public FilmAndType() {
    }

    public FilmAndType(Integer filmId, Integer tfId) {
        this.filmId = filmId;
        this.tfId = tfId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getTfId() {
        return tfId;
    }

    public void setTfId(Integer tfId) {
        this.tfId = tfId;
    }
}
