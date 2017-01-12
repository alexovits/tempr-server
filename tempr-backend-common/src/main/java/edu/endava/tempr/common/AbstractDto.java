package edu.endava.tempr.common;

public class AbstractDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractDto{" +
                "id=" + id +
                '}';
    }

}
