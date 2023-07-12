package com.finance.lib.budget.mapper;

import com.finance.lib.budget.domain.entity.CompositeId;
import com.finance.lib.budget.dto.CompositeIdDto;

public class IdMapper {
  public CompositeId convert(CompositeIdDto compositeIdDto) {
    return new CompositeId(
      compositeIdDto.getId(),
      compositeIdDto.getUserId()
    );
  }

  public CompositeIdDto convert(CompositeId compositeId) {
    return new CompositeIdDto(
      compositeId.getId(),
      compositeId.getUserId()
    );
  }
}
