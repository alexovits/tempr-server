package edu.endava.tempr.api.assembler;

import edu.endava.tempr.common.HeatingSourceDto;
import edu.endava.tempr.model.HeatingSource;

/**
 * Created by zsoltszabo on 4/6/17.
 */
public class HeatingSourceAssembler implements Assembler<HeatingSourceDto, HeatingSource> {

    @Override
    public HeatingSource toEntity(HeatingSourceDto dto) {
        HeatingSource heatingSource = new HeatingSource();
        return heatingSource;
    }

    @Override
    public HeatingSourceDto toDto(HeatingSource entity) {
        HeatingSourceDto heatingSourceDto = new HeatingSourceDto();
        return heatingSourceDto;
    }
}
