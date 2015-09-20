package br.com.cast.turmaformacao.taskmanager.model.entidade;

import java.io.Serializable;

/**
 * Created by Wanilton on 19/09/2015.
 */
public class Calculator implements Serializable {
    private Long id;
    private Long numA;
    private Long numB;
    private Long total;

    public Calculator() {

    }

    public Long getId() {
        return id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getNumA() {
        return numA;
    }

    public void setNumA(Long numA) {
        this.numA = numA;
    }

    public Long getNumB() {
        return numB;
    }

    public void setNumB(Long numB) {
        this.numB = numB;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calculator that = (Calculator) o;

        if (numA != that.numA) return false;
        return numB == that.numB;

    }

    @Override
    public int hashCode() {
        int result = (int) (numA ^ (numA >>> 32));
        result = 31 * result + (int) (numB ^ (numB >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "numA=" + numA +
                ", numB=" + numB +
                '}';
    }
}
