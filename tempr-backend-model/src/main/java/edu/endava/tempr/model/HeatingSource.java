package edu.endava.tempr.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zsoltszabo on 4/3/17.
 */
@Entity
@Table(name = "HeatingSource")
public class HeatingSource extends BaseEntity{
    @Override
    public String toString() {
        return String.format("HeatingSource{id=%1$d}",
                getId());
    }
}
