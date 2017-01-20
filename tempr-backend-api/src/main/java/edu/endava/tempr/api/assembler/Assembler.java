package edu.endava.tempr.api.assembler;

import edu.endava.tempr.common.AbstractDto;
import edu.endava.tempr.model.BaseEntity;

/**
 * @author Gergo Nagy<gergo.nagy@endava.com>
 */
public interface Assembler<D extends AbstractDto, E extends BaseEntity> {

    E toEntity(D dto);

    D toDto(E entity);

}
